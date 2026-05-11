# Back-of-envelope sizing — quick reference

Order-of-magnitude only. Say **“roughly”**; pick assumptions you can defend (requests/user/day, peak factor, bytes/request, RPS/server). 
---

## Memorize this chain (one pass)

| Step | What to compute | Easy form |
|------|-----------------|-----------|
| 1 | **Requests/day** | `DAU × u` (u = requests per user per **day**) |
| 2 | **Average RPS** | `requests_day ÷ 86,400` |
| 3 | **Peak RPS** | **`avg_RPS × 2`** (default peak factor **k = 2**; use **3–10×** only if the workload is clearly bursty—say that out loud) |
| 4 | **Servers** | `⌈ peak_RPS ÷ C ⌉` (C = RPS per server; default **10,000**) |
| 5 | **Storage/day** | `requests_day × bytes_per_request` (writes you actually persist—often a **fraction** of all API calls) |

**Sloppy-but-fast mental shortcut:** \(86{,}400 \approx 10^5\) so **avg_RPS ≈ requests_day / 100k** (within ~13%; say you rounded seconds/day).

---

## Four anchor numbers

| Anchor | Value | Why |
|--------|------:|-----|
| **Seconds/day** | **86,400** (= 24 × 60 × 60) | Divides daily volume → average RPS |
| **Peak factor** | **2×** (optional **3–10×** for spiky traffic) | Provisioning uses peak, not 24h average |
| **RPS/server** | **10,000** (range **1k–50k**) | Stateless-ish app tier; tune in interview |
| **GB/day @ 1 KB/req** | **≈ avg_RPS × 0.086** | Because \(86{,}400 \times 1{,}000 / 10^9 \approx 0.0864\) |

**Tighter:** **GB/day ≈ avg_RPS × 0.0864** (round to **0.086** in your head).

**Same line in other units (1 KB/req):** **TB/day ≈ avg_RPS × 8.64 × 10⁻⁵** · **PB/day ≈ avg_RPS × 8.64 × 10⁻⁸**

---

## Table 1 — From **average RPS** (1 KB per request, **peak = 2× avg**, 10k RPS/server)

Use when you already have **avg RPS** or read it off `requests_day / 86.4k`.

**Storage/day** uses the most readable of **MB / GB / TB / PB** (decimal SI; **1 MB = 1000 KB**, same underlying math).

| Avg RPS | Requests/day | Storage/day (1 KB/req) | Peak RPS (2×) | Servers (⌈peak/10k⌉) |
|--------:|-------------:|------------------------|--------------:|---------------------:|
| 1 | ~86k | ~**86 MB** | ~2 | 1 |
| 10 | ~860k | ~**864 MB** | ~20 | 1 |
| 100 | ~8.6M | ~**8.6 GB** | ~200 | 1 |
| 1,000 | ~86M | ~**86 GB** | ~2k | 1 |
| 10,000 | ~860M | ~**864 GB** (~**0.86 TB**) | ~20k | 2 |
| 100,000 | ~8.6B | ~**8.6 TB** | ~200k | 20 |
| 1,000,000 | ~86B | ~**86 TB** (~**0.09 PB**) | ~2M | 200 |

**Patterns to remember**

- **Requests/day** ≈ **avg_RPS × 86k** (exact: × 86,400).
- **GB/day @ 1 KB** ≈ **avg_RPS × 0.086** (≈ **86 GB per 1k avg RPS**).
- **Peak RPS** = **2 × avg_RPS** (your default).
- **Servers** ≈ **(2 × avg_RPS) / 10k** = **avg_RPS / 5k** when **k = 2** and **C = 10k**.

---

## Table 2 — From **DAU** (fixed **u = 20** req/user/day, **k = 2**, **C = 10k**, **1 KB**/req)

Change **u** by scaling: double u → double all columns (requests, RPS, storage, servers).

| DAU | Requests/day | Avg RPS | Peak RPS (2×) | Servers (⌈peak/10k⌉) | Storage/day (1 KB/req) |
|----:|-------------:|--------:|--------------:|---------------------:|------------------------|
| 1M | 20M | ~230 | ~460 | 1 | ~**20 GB** |
| 10M | 200M | ~2.3k | ~4.6k | 1 | ~**200 GB** |
| 100M | 2B | ~23k | ~46k | 5 | ~**2 TB** |
| 1B | 20B | ~232k | ~463k | 47 | ~**20 TB** (~**0.02 PB**) |

**Memory trick for this table only:** with **u = 20**, **avg_RPS ≈ DAU / 4,320** (because \(20/86{,}400 = 1/4320\)); still say **86,400** when you walk through the math in the interview.

---

## Table 3 — Three interview storylines (mixed **u**)

Round deliberately; don’t imply false precision.

| Story | DAU | u (req/user/day) | Requests/day | Avg RPS | Peak (2×) | Servers @10k | Storage/day (1 KB/req) |
|-------|----:|-----------------:|---------------:|--------:|----------:|-------------:|-------------------------|
| **Producty** | 10M | 20 | 200M | ~2.3k | ~4.6k | **1** | ~**200 GB** |
| **Light social** | 100M | 10 | 1B | ~12k | ~23k | **3** | ~**1000 GB** (~**1 TB**) |
| **Global head product** | 1B | 5 | 5B | ~58k | ~116k | **12** | ~**5000 GB** (~**5 TB**) |

- **`u` or DAU ↑/↓:** **Requests/day**, **Avg RPS**, **Peak**, and **stored volume/day** scale together (linear in volume); **Servers** jumps in steps because of **`⌈·⌉`** and \(C\)—not strictly proportional.
- **`k` (peak factor) ↑/↓ only:** scales **Peak RPS** and **Servers**; **Avg RPS** and **stored volume/day** unchanged (daily volume did not change).

---

## Adjustments (don’t rebuild the table)

| Knob | Effect |
|------|--------|
| **Payload ≠ 1 KB** | Multiply **bytes/day** by \((\text{your payload bytes}) / 1{,}000\) (or multiply **KB/day** by payload KB / 1) |
| **Only w fraction of requests write bytes** | `storage_day × w` |
| **Peak k** | multiply **Peak RPS** and **Servers** vs Table 1; **storage/day** usually still from **daily** volume, not peak |
| **Server capacity C** | `servers = ⌈ peak_RPS / C ⌉` |
| **Replication** | **Physical storage** ≈ `storage_day × replication_factor` |

---

## Interview line (template)

> “**DAU × requests per user** is **requests per day**. Divide by **86k seconds** for **average RPS**, multiply by **2** for **peak RPS** (or higher if traffic is bursty—say why), divide by **~10k RPS per box** for **instances**, and **requests per day × payload** for **daily write volume**—then **replication** on top if we’re talking disks.”

---

## Detailed formulas (reference, decimal)

| Quantity | Formula |
|----------|---------|
| Requests/day | `DAU × u` or `avg_RPS × 86,400` |
| Avg RPS | `requests_day / 86,400` |
| Peak RPS | `avg_RPS × 2` (default **k = 2**) |
| Servers | `⌈ peak_RPS / C ⌉` |
| Bytes/day @ 1 KB/req | `requests_day × 1,000` |
| KB/day @ 1 KB/req | `requests_day` (each request adds **1 KB**) |
| MB/day @ 1 KB/req | `requests_day / 10^3` |
| GB/day @ 1 KB/req | `requests_day / 10^6` |
| TB/day @ 1 KB/req | `requests_day / 10^9` |
| PB/day @ 1 KB/req | `requests_day / 10^12` |

---

## Sanity checklist

- [ ] Stated **DAU** and **u** (or equivalent **requests/day**).
- [ ] Used **86,400** (or said you rounded to **100k**).
- [ ] Applied **peak = 2× average RPS** for **servers** (or stated a higher **k** for bursty traffic); not for **daily storage** unless the question is peak **write** bandwidth.
- [ ] Chose **C** (RPS/server) and said it’s a guess.
- [ ] Mentioned **replication / retention** if talking cost of storage.

---

## Related

- [system-design.md](system-design.md)
- [core-concepts.md](core-concepts.md)

---

*Order of magnitude beats false precision.*

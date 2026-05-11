# Back-of-envelope sizing — quick reference

Order-of-magnitude only. Say **“roughly”**; pick assumptions you can defend (requests/user/day, peak factor, bytes/request, RPS/server).

---

## Memorize this chain (one pass)

| Step | What to compute | Easy form |
|------|-----------------|-----------|
| 1 | **Requests/day** | `DAU × u` (u = requests per user per **day**) |
| 2 | **Average RPS** | `requests_day ÷ 86,400` |
| 3 | **Peak RPS** | `avg_RPS × k` (k ≈ **3–10**; default **5** if silent) |
| 4 | **Servers** | `⌈ peak_RPS ÷ C ⌉` (C = RPS per server; default **10,000**) |
| 5 | **Storage/day** | `requests_day × bytes_per_request` (writes you actually persist—often a **fraction** of all API calls) |

**Sloppy-but-fast mental shortcut:** \(86{,}400 \approx 10^5\) so **avg_RPS ≈ requests_day / 100k** (within ~13%; say you rounded seconds/day).

---

## Four anchor numbers

| Anchor | Value | Why |
|--------|------:|-----|
| **Seconds/day** | **86,400** (= 24 × 60 × 60) | Divides daily volume → average RPS |
| **Peak factor** | **5×** (range 3–10×) | Average RPS is not provisioning RPS |
| **RPS/server** | **10,000** (range **1k–50k**) | Stateless-ish app tier; tune in interview |
| **GiB/day @ 1 KiB/req** | **≈ 0.08 × avg_RPS** | Because \(86{,}400 \times 1{,}024 / 2^{30} \approx 0.082\) |

**Storage @ 1 KiB/request (tighter):** `GiB/day ≈ avg_RPS × 0.082` — round to **0.08** in your head.

---

## Table 1 — From **average RPS** (1 KiB per request, peak = 5× avg, 10k RPS/server)

Use when you already have **avg RPS** or read it off `requests_day / 86.4k`.

| Avg RPS | Requests/day | GiB/day @ 1 KiB/req | Peak RPS (5×) | Servers (⌈peak/10k⌉) |
|--------:|-------------:|--------------------:|--------------:|---------------------:|
| 1 | ~86k | ~0.08 | ~5 | 1 |
| 10 | ~860k | ~0.8 | ~50 | 1 |
| 100 | ~8.6M | ~8 | ~500 | 1 |
| 1,000 | ~86M | ~82 | ~5k | 1 |
| 10,000 | ~860M | ~820 | ~50k | 5 |
| 100,000 | ~8.6B | ~8,200 (~8 TB) | ~500k | 50 |
| 1,000,000 | ~86B | ~82,000 (~80 TB) | ~5M | 500 |

**Patterns to remember**

- **Requests/day** ≈ **avg_RPS × 86k** (exact: × 86,400).
- **GiB/day @ 1 KiB** ≈ **avg_RPS × 0.08** (same order as **82 GiB per 1k avg RPS**).
- **Servers** ≈ **(5 × avg_RPS) / 10k** = **avg_RPS / 2k** when k = 5 and C = 10k.

---

## Table 2 — From **DAU** (fixed **u = 20** req/user/day, **k = 5**, **C = 10k**, **1 KiB**/req)

Change **u** by scaling: double u → double all columns (requests, RPS, storage, servers).

| DAU | Requests/day | Avg RPS | Peak RPS | Servers (⌈peak/10k⌉) | GiB/day @ 1 KiB |
|----:|-------------:|--------:|---------:|---------------------:|----------------:|
| 1M | 20M | ~230 | ~1.2k | 1 | ~19 |
| 10M | 200M | ~2.3k | ~12k | 2 | ~190 |
| 100M | 2B | ~23k | ~116k | 12 | ~1,900 (~1.9 TB) |
| 1B | 20B | ~232k | ~1.16M | 116 | ~19,000 (~19 TB) |

**Memory trick for this table only:** with **u = 20** and **k = 5**, **avg_RPS ≈ DAU / 4,400** (because \(20/(86{,}400) \approx 1/4320\)); still say **86,400** in the real interview.

---

## Table 3 — Three interview storylines (mixed **u**)

Round deliberately; don’t imply false precision.

| Story | DAU | u (req/user/day) | Requests/day | Avg RPS | Peak (5×) | Servers @10k | GiB/day @1 KiB |
|-------|----:|-----------------:|---------------:|--------:|----------:|-------------:|---------------:|
| **Producty** | 10M | 20 | 200M | ~2.3k | ~12k | **2** | **~200 GiB** |
| **Light social** | 100M | 10 | 1B | ~12k | ~60k | **6** | **~1 TB** |
| **Global head product** | 1B | 5 | 5B | ~58k | ~290k | **29** | **~5 TB** |

If **u** or **k** changes, scale **Avg RPS**, **Peak**, **Servers**, and **GiB/day** proportionally (servers after ceiling).

---

## Adjustments (don’t rebuild the table)

| Knob | Effect |
|------|--------|
| **Payload ≠ 1 KiB** | `storage_day × (your_size / 1 KiB)` |
| **Only w fraction of requests write bytes** | `storage_day × w` |
| **Peak k** | multiply **Peak RPS** and **Servers** vs Table 1; **storage/day** usually still from **daily** volume, not peak |
| **Server capacity C** | `servers = ⌈ peak_RPS / C ⌉` |
| **Replication** | **Physical storage** ≈ `storage_day × replication_factor` |

---

## Interview line (template)

> “**DAU × requests per user** is **requests per day**. Divide by **86k seconds** for **average RPS**, multiply by a **peak factor** (often **5×**) for **provisioned RPS**, divide by **~10k RPS per box** for **instances**, and **requests per day × payload** for **daily write volume**—then **replication** on top if we’re talking disks.”

---

## Detailed formulas (reference)

| Quantity | Formula |
|----------|---------|
| Requests/day | `DAU × u` or `avg_RPS × 86,400` |
| Avg RPS | `requests_day / 86,400` |
| Peak RPS | `avg_RPS × k` or busy-window variant (see older notes in your own deck) |
| Servers | `⌈ peak_RPS / C ⌉` |
| Bytes/day | `requests_day × bytes_per_request` |
| GiB/day @ 1 KiB/req | `avg_RPS × 86,400 × 1024 / 2^30 ≈ avg_RPS × 0.082` |

---

## Sanity checklist

- [ ] Stated **DAU** and **u** (or equivalent **requests/day**).
- [ ] Used **86,400** (or said you rounded to **100k**).
- [ ] Applied **peak factor** for **servers**, not for **daily storage** unless the question is peak **write** bandwidth.
- [ ] Chose **C** (RPS/server) and said it’s a guess.
- [ ] Mentioned **replication / retention** if talking cost of storage.

---

## Related

- [system-design.md](system-design.md)
- [core-concepts.md](core-concepts.md)

---

*Order of magnitude beats false precision.*

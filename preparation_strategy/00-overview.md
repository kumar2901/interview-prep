# FAANG / MAANG preparation — start here

Use this file as the **single consolidated entry** for `preparation_strategy/`: what each document is for, in what order to read it, and how that ties to your timeline.  
Deeper shortcuts and duplicate reference tables live in [**faang-interview-preparation-strategy.md**](01-faang-interview-preparation-strategy.md).

---

## How to start preparation (practical sequence)

### Day 0 — Orient (~45–60 minutes)

1. Skim **all documents at a glance** (table below) so you know what exists.
2. Pick your **prep phase** (timeline section) based on weeks until interviews.
3. Open only the files for **Track A** (everyone) in order **1 → 7**; add **Track B** if you are targeting **senior (L5+) / staff-style** depth.
4. Write down one **weekly goal** (e.g., “finish hash + two pointers” or “one system design + one LLD”) using the weekly review template.

### First 1–2 weeks — Build the core loop

| Daily | Primary docs |
|--------|----------------|
| Coding | [leetcode-topics.md](leetcode-topics.md) — follow topic order; mix **M** and **H** per your phase |
| Design / fundamentals reading | [core-concepts.md](core-concepts.md) and/or [system-design.md](system-design.md) (alternate days); keep [back-of-envelope-sizing.md](back-of-envelope-calculations.md) / [back-of-envelope-calculations.md](back-of-envelope-calculations.md) open for sizing (same content; second is a short pointer) |
| LLD (2× / week minimum) | [lld.md](lld.md) — one drill per session, timed |
| Concurrency (if role needs it) | [concurrency-faqs.md](concurrency-faqs.md) — a few FAQs per day |

### After core loop is stable — Senior / company-specific layer (L5+)

5. [ROLE-AND-COMPANY-GUIDE.md](ROLE-AND-COMPANY-GUIDE.md) — your role + target company emphasis.  
6. [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) — read the **Quick Prep Priority Matrix**, then only the sections that match your guide.  
7. [SENIOR-INTERVIEW-GAP-REVIEW.md](SENIOR-INTERVIEW-GAP-REVIEW.md) — extra mock questions and audit notes.  
8. [QUICK-REFERENCE-STUDY-MAP.md](QUICK-REFERENCE-STUDY-MAP.md) — how senior topics layer onto the core docs over several weeks.  
9. [00-ANALYSIS-SUMMARY.md](00-ANALYSIS-SUMMARY.md) — optional narrative of the gap analysis; skim if you want context.

### If you have almost no time today (~20 minutes)

Open [leetcode-topics.md](leetcode-topics.md) **or** [system-design.md](system-design.md) based on your **next** interview round, and scan one section only.

---

## All documents at a glance (summary + role)

| Order | Document | One-line summary |
|------:|----------|------------------|
| 0 | [**faang-interview-preparation-strategy.md**](01-faang-interview-preparation-strategy.md) | Compact index: same read order, **time-boxed shortcuts** (20 min / one evening / coding vs design sprint). |
| 1 | **overview.md** (this file) | **Master hub:** how to start, timeline, behavioral rhythm, weekly template, full doc catalog. |
| 2 | [**leetcode-topics.md**](leetcode-topics.md) | Topic-wise **LeetCode** plan, **E/M/H** difficulty, problem IDs, practice discipline. |
| 3 | [**core-concepts.md**](core-concepts.md) | Interview fundamentals: **ACID**, **CAP**, consistency, caches, queues, HTTP/auth, resilience, DB basics, etc. |
| 4 | [**system-design.md**](system-design.md) | Classic designs, sizing, traffic/LB/CDN, deploy strategies, streaming, **SLOs**, reliability/incidents. |
| 5 | [**lld.md**](lld.md) | **Low-level design** drills (parking lot, LRU, elevator, …) + interview checklist. |
| 6 | [**concurrency-faqs.md**](concurrency-faqs.md) | **Senior-depth** concurrency Q&A (memory model, deadlock, CAS/ABA, pools, testing). |
| 7 | [**ROLE-AND-COMPANY-GUIDE.md**](ROLE-AND-COMPANY-GUIDE.md) | Prep **by role** (backend/frontend/full-stack/infra/ML/data) and **by company**, plus mock prompts. |
| 8 | [**MISSING-SENIOR-CONCEPTS.md**](MISSING-SENIOR-CONCEPTS.md) | ~**26 thematic gaps** seniors need: cost, ML systems, pipelines, chaos, payments, migrations, tracing, … |
| 9 | [**SENIOR-INTERVIEW-GAP-REVIEW.md**](SENIOR-INTERVIEW-GAP-REVIEW.md) | **Audit** of the folder vs senior bar + **additional question checklist** not spelled out elsewhere. |
| 10 | [**QUICK-REFERENCE-STUDY-MAP.md**](QUICK-REFERENCE-STUDY-MAP.md) | **Study sequencing:** connect MISSING topics to core files; phased deep-dive suggestions. |
| 11 | [**00-ANALYSIS-SUMMARY.md**](00-ANALYSIS-SUMMARY.md) | **Meta write-up** of the gap-analysis effort; lowest priority unless you want the story. |
| 12 | [**back-of-envelope-sizing.md**](back-of-envelope-calculations.md), [**back-of-envelope-calculations.md**](back-of-envelope-calculations.md) | **Sizing cheat sheet:** RPS → requests/day → storage (MB / GB / TB / PB @ **decimal 1 KB**/req) → server count; **peak RPS = 2 × avg RPS** by default; DAU chains. (Both names point at the same file here.) |

**Tracks:**

- **Track A (everyone):** rows **1 → 6** above (`overview` through `concurrency-faqs`).  
- **Track B (senior MAANG-style):** rows **7 → 11** after Track A basics feel familiar.

---

## Recommended reading order (checklist)

Use this checklist the first time; after that, use it as a **TOC** (“where did I leave off?”).

| Step | Read | Purpose |
|:----:|------|---------|
| 1 | This **overview.md** | Plan, phases, rhythms. |
| 2 | [leetcode-topics.md](leetcode-topics.md) | Lock your coding roadmap. |
| 3 | [core-concepts.md](core-concepts.md) | Vocabulary for system + fundamentals rounds. |
| 4 | [system-design.md](system-design.md) | End-to-end design template + classics. |
| — | [back-of-envelope-sizing.md](back-of-envelope-calculations.md) ([alias](back-of-envelope-calculations.md)) | Optional **capacity** sidecar while doing system design (RPS, storage, servers). |
| 5 | [lld.md](lld.md) | LLD patterns + first whiteboard drills. |
| 6 | [concurrency-faqs.md](concurrency-faqs.md) | If job is backend/systems-heavy. |
| 7 | [ROLE-AND-COMPANY-GUIDE.md](ROLE-AND-COMPANY-GUIDE.md) | Filter what matters for *your* loop. |
| 8 | [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) Priority Matrix | Choose 5–8 senior themes; ignore the rest initially. |
| 9 | [SENIOR-INTERVIEW-GAP-REVIEW.md](SENIOR-INTERVIEW-GAP-REVIEW.md) | Fill blind spots with the question list. |
| 10 | [QUICK-REFERENCE-STUDY-MAP.md](QUICK-REFERENCE-STUDY-MAP.md) | Multi-week integration plan. |
| 11 | [00-ANALYSIS-SUMMARY.md](00-ANALYSIS-SUMMARY.md) | Optional. |
| — | [faang-interview-preparation-strategy.md](01-faang-interview-preparation-strategy.md) | Quick paths when you don’t want to re-read this whole overview. |

---

## Timeline (adjust to your date)

| Prep phase | Focus | Duration (typical) | Main documents |
|------------|--------|-------------------|----------------|
| **1** | Patterns + mostly medium problems daily | 4–8 weeks | [leetcode-topics.md](leetcode-topics.md), start [core-concepts.md](core-concepts.md) |
| **2** | Hard problems + timed contests / mixed sheets | 3–6 weeks | [leetcode-topics.md](leetcode-topics.md) (**H** column), [concurrency-faqs.md](concurrency-faqs.md) if needed |
| **3** | LLD + system design + behavioral (parallel) | 4–8 weeks | [system-design.md](system-design.md), [back-of-envelope-sizing.md](back-of-envelope-calculations.md) / [back-of-envelope-calculations.md](back-of-envelope-calculations.md), [lld.md](lld.md), this file (behavioral) |
| **4** | Full mocks (coding + design + stories) | 2–4 weeks | All Track A; add Track B topics you shortlisted |

**Daily rhythm (example):** 1–2 coding problems (one pattern review), 30–60 minutes design or concepts reading **or** one LLD sketch, refine **one** behavioral story per week.

---

## Behavioral and process

- **STAR** stories: conflict, failure, leadership, ambiguity, scale/metrics.
- Know **your** projects: constraints, metrics, what you would redo.
- **Questions for the interviewer:** team topology, on-call, how success is measured for the role.

---

## Weekly review template

1. List topics still weak from timed sessions.  
2. Redo two missed problems **cold**.  
3. One LLD whiteboard from [lld.md](lld.md).  
4. One system design deep dive with notes ([system-design.md](system-design.md)); sanity-check numbers with [back-of-envelope-sizing.md](back-of-envelope-calculations.md) or [back-of-envelope-calculations.md](back-of-envelope-calculations.md) (pointer to the same sheet).  
5. Refine one behavioral story with **numbers** (latency, users, revenue, error rate—whatever fits).  
6. If on Track B: one subsection from [MISSING-SENIOR-CONCEPTS.md](MISSING-SENIOR-CONCEPTS.md) + two mock questions from [SENIOR-INTERVIEW-GAP-REVIEW.md](SENIOR-INTERVIEW-GAP-REVIEW.md).

---

## References

Problem IDs refer to [LeetCode](https://leetcode.com/problemset/all/). Use tags and difficulty filters; add company-tagged lists only after core patterns feel automatic.

---

*Tune phase lengths and daily load to your baseline and interview date.*

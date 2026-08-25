# Online Collaborative Editing Service - Requirement and design

---

Google Docs is a browser-based collaborative document editor. 
Users can create rich text documents and collaborate with others in real-time.

---
## Functional Requirement

1. Users should be able to create new documents.
2. Multiple users should be able to edit the same document concurrently.
3. Users should be able to view each other's changes in real-time.
4. Users should be able to see the cursor position and presence of other users.
5. Document can have text, links, images and comments (avg doc size 1MB)

### Below the line (out of scope)
1. Document history and versioning
2. Sophisticated document structure. We'll assume a simple text editor.
3. Permissions and collaboration levels (e.g. who has access to a document).

----

## Non-Functional Requirement
1. Low latency <300ms for users from different regions
2. Consistency → must resolve concurrent conflicts  → **eventual consistency** for concurrent users editing document at the same time.
3. Scalable → 100M DAU → must support a large number of concurrent users viewing or editing. say 20 concurrent editor per document
4. Availability: must be highly available  → Availability >> Consistency

---


## Choosing database

1. **NoSQL Over Relational Databases**: NoSQL is preferred for large-scale applications because performing continuous CRUD (Create, Read, Update, Delete) operations on dynamic row and column data scales significantly better than in a Relational Database Management System (RDBMS).
2. **Avoid Overhead from Table Joins**: In a relational design, storing column values in separate tables creates high performance overhead due to heavy join operations when fetching data for multiple users simultaneously.
3. **Avoid Cell-Level Relational Queries**: Creating database rows per cell requires massive batches of update queries for single-row changes and expensive database updates across subsequent rows when inserting new rows in between.
4. **Store Rows as JSON Documents**: Using NoSQL to store row data as a JSON object grouped by sheetID avoids heavy table joins and simplifies reading and updating row content.
5. **Use Linked List Data Structures for Rows and Columns**: To prevent expensive bulk updates when inserting new rows or columns, represent ordering via node pointers (prevRowID and prevColumnID) instead of fixed sequential numbers (rowNo). Inserting a new row/column then only requires updating a single pointer rather than shifting all subsequent records.
6. **Row table schema**:
   ```
   {
    "sheetID": "sheet_12345",
    "rowID": "row_99",
    "prevRowID": "row_98",
    "rawJSON": [
     {
       "columnID": "col_A",
       "prevColumnID": null,
       "value": "Header 1",
       "updatedBy": "user_01"
     },
     {
       "columnID": "col_B",
       "prevColumnID": "col_A",
       "value": "100",
       "updatedBy": "user_02"
     }
    ]
   }
7. **Column table schema**:
   ```
   {
     "sheetID": "sheet_12345",
     "columnID": "col_B",
     "prevColumnID": "col_A",
     "width": 120,
     "hidden": false
   }
   

---

## Concurrency in Collaborating editing
To resolve conflicts, the system must enforce two rules:

1. **Commutativity**: The order of applied operations must not affect the final result.
2. **Idempotency**: Repeated application of the same operation must not change the result beyond the initial application.

### Techniques for conflict resolution

1. **Operational transformation(OT)**
   * Lock free and non blocking approach
   * Edits are sent as operations (e.g., insert("text", position) or delete(position)). 
   * A central server receives these operations, compares them against concurrent edits, and transforms their positional indexes so the text lands in the correct spot across all clients.
   * **Disadvantages**:
     * Operations rely on positional indexes, making them **order-dependent**. An insertion at the beginning of a document shifts the indexes of all subsequent characters.
     * **Extreme Implementation Complexity** -> Implementing correct algorithm is difficult (Google spent two years to fix)
     * **Centralized Dependency**: OT typically requires a central server to establish the sequence of operation
2. **Conflict-free replicated data type (CRDT)**
   * Simplify conflict resolution by using more complex data structures
   * Instead of relying on mutable array positions, every character is assigned a globally unique identity (e.g., combining a `SiteID` and a `PositionalIndex`).
   * Edits use persistent or fractional indexing so inserting or deleting text does not shift the position of existing characters.
   * Replicas sync updates in any order, and the underlying data structure mathematically guarantees that all copies automatically converge to the exact same state without conflicts.
   * **Disadvantage**
     * High Memory Overhead: Every single character, deletion tombstone, and metadata marker must track a unique identifier
     * Network Bandwidth Costs: Transmitting metadata (site IDs, unique timestamps, vector clocks)
     * Garbage Collection Challenges: Fully purging deleted data (tombstone) requires complex garbage collection algorithm

---

## API Design

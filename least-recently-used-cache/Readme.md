## 🧠 LRUCache Implementation: A Deep Dive

This repository features a robust and highly performant implementation of the **Least Recently Used (LRU) Cache**,
built from scratch using foundational Java data structures.

---

## 🎯 What is an LRU Cache?

An LRU Cache is a storage limit system that keeps data it thinks you'll need next, based on the simple rule: if you used
something recently, you'll probably use it again soon.

When the cache reaches its capacity limit and a new item needs to be added, the LRU policy dictates that the **Least
Recently Used** item (the item that hasn't been touched in the longest time) must be evicted (removed) to make space
for the new data.

The primary goal of this implementation is to ensure all core cache operations—**lookup (`get`)**,
**insertion/update (`put`)**, and **removal (`remove`)**—are performed in **$\mathcal{O}(1)$ (constant
time)**.

---

## 🛠️ Data Structures Used

Achieving $\mathcal{O}(1)$ complexity requires combining two distinct data structures, each handling a specific
requirement:

### 1. `HashMap` (`keyToIndexMap`)

* **Purpose:** Provides **$\mathcal{O}(1)$ lookup** for a key.
* **Implementation Detail:** Maps the user's `key` (e.g., `Integer`) directly to the **reference** of the
  corresponding `Node` object within the linked list. This allows us to instantly find a node's location in memory
  without traversing the list.

### 2. `DoublyLinkedList` (`doublyLinkedList`)

* **Purpose:** Maintains the **order of recency** for all data.
* **Implementation Detail:** Allows **$\mathcal{O}(1)$ insertion and removal** anywhere in the list, crucial
  for moving an accessed node to the front.
* **Front (Head):** Represents the **Most Recently Used (MRU)** item.
* **Back (Tail):** Represents the **Least Recently Used (LRU)** item.

*ℹ️You can't use a Singly Linked List for this implementation because we need to be able to move a node to the
front of the list in $\mathcal{O}(1)$ time.*

The **Node<K, V>** class is the connecting piece, storing the user's key and value, along with the `prev` and
`next` pointers required by the Doubly Linked List.

---

## 💡 Design Decisions \& Insights

### 1. Sentinel (Dummy) Head and Tail Nodes

The `DoublyLinkedList` is initialized with two **sentinel nodes** (`head` and `tail`) that do not hold any
actual user data.

* **Benefit:** This design choice **eliminates edge cases**. We never have to check if the list is empty (
  `head == null`) or if we are inserting/removing the very first or last item. Every operation becomes a standardized
  action between two nodes, dramatically simplifying the pointer logic and making the code more robust.

### 2. $\mathcal{O}(1)$ Complexity for All Operations

Every core operation is engineered for constant time performance:

| Operation                         | Action                                                                                              | Complexity       |
|:----------------------------------|:----------------------------------------------------------------------------------------------------|:-----------------|
| **`get(key)`** (Cache Hit)        | Map lookup $\rightarrow$ Node access $\rightarrow$ List `moveToFirst`                               | $\mathcal{O}(1)$ |
| **`put(key, value)`** (Cache Hit) | Map lookup $\rightarrow$ Node value update $\rightarrow$ List `moveToFirst`                         | $\mathcal{O}(1)$ |
| **`put(key, value)`** (Eviction)  | Map lookup $\rightarrow$ List `removeLast` $\rightarrow$ Map `remove` $\rightarrow$ List `addFirst` | $\mathcal{O}(1)$ |
| **`remove(key)`**                 | Map lookup $\rightarrow$ List `remove` $\rightarrow$ Map `remove`                                   | $\mathcal{O}(1)$ |

### 3. Eviction Tracking

The **`put`** method returns a `boolean` indicating if an item was evicted (**`true`**) or not (**`false`**). This is
useful for systems that need to perform external cleanup (like persisting the evicted data) or for
simple metrics tracking.

---

## 🗓️ When to Use an LRU Cache

An LRU Cache is a strategic tool for managing limited resources.

| Scenario                       | Use LRU Cache | Why?                                                                                            |
|:-------------------------------|:--------------|:------------------------------------------------------------------------------------------------|
| **Database Caching**           | **Yes**       | Caching frequently accessed query results or full records to avoid expensive disk I/O.          |
| **Web Server Caching**         | **Yes**       | Storing the most recent API responses or rendered page fragments.                               |
| **File Systems**               | **Yes**       | Managing blocks of data read from disk into main memory buffers.                                |
| **Dynamic Memory Allocation**  | **No**        | General-purpose memory management where object creation/destruction patterns are unpredictable. |
| **Strict FIFO Requirement**    | **No**        | If you need the \_oldest\_ item removed, regardless of access (use a simple Queue).             |
| **Infrequently Accessed Data** | **No**        | If data is accessed once and never again, the overhead of managing recency is wasted.           |
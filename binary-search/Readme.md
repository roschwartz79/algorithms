# Binary Search Tree

A Binary Search Tree (BST) is a binary tree in which all the nodes follow the below-mentioned properties −

The left sub-tree of a node has a key less than or equal to its parent node's key.
The right sub-tree of a node has a key greater than to its parent node's key.

An example of a Binary Search Tree is shown below −

```
        4
       / \
      2   6
     / \ / \
    1  3 5  7
```

## Runtime

A table of the runtime of the operations on a Binary Search Tree is shown below:

| Operation | Average Case | Worst Case |
|-----------|--------------|------------|
| insert    | O(log n)     | O(n)       |
| delete    | O(log n)     | O(n)       |
| search    | O(log n)     | O(n)       |

## Balanced/Unbalanced

An unbalanced tree can occur when the nodes are inserted in a sorted order, which results in a tree with a height of n,
like the example below:

```
    1
     \
      2
       \
        3
         \
          4
```

A balanced tree is a tree where the height of the left and right subtrees of every node differ by 1 or less. An example
of a balanced tree is shown below:

```
        4
       / \
      2   6
     / \ / 
    1  3 5  
```
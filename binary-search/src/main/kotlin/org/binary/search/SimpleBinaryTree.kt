package org.binary.search

/**
 * BinaryNode data class to represent a node in a binary tree. Each node has:
 * - A value
 * - A left child node
 * - A right child node
 * - A parent node
 */
data class BinaryNode(var value: Int, var left: BinaryNode?, var right: BinaryNode?, var parent: BinaryNode?)

/**
 * SimpleBinaryTree is an implementation of a binary tree. It is an unbalanced binary tree.
 */
class SimpleBinaryTree(var rootNode: BinaryNode?) {

  /**
   * Insert a value into the binary tree
   */
  fun insert(value: Int) {
    insert(value, rootNode)
  }

  /**
   * Recursive function to insert a value into the binary tree. We first traverse the tree, and then create a new child.
   * The parent and left/right references are set on creation. Steps:
   *
   * 1. If the current node is null, create a new node with the value and set it as the root node.
   *    There is no way this function can be called with a null BinaryNode unless it is to set the root
   * 2. If the value is less than the current node's value, go left
   * 3. If the left child is null, create a new node with the value and set it as the left child
   * 4. If the value is greater than the current node's value, go right
   * 5. If the right child is null, create a new node with the value and set it as the right child
   */
  fun insert(value: Int, currNode: BinaryNode?) {
    if (currNode == null) {
      rootNode = BinaryNode(value, null, null, null)
    } else if (value < currNode.value) {
      if (currNode.left == null) {
        currNode.left = BinaryNode(value, null, null, currNode)
      } else {
        insert(value, currNode.left!!)
      }
    } else {
      if (currNode.right == null) {
        currNode.right = BinaryNode(value, null, null, currNode)
      } else {
        insert(value, currNode.right!!)
      }
    }
  }

  /**
   * Search for a value in the binary tree
   */
  fun search(value: Int): Boolean {
    return searchFromNode(value, rootNode)
  }

  fun searchFromNode(value: Int, currNode: BinaryNode?): Boolean {
    return when {
      currNode == null -> false

      value < currNode.value -> searchFromNode(value, currNode.left)

      value > currNode.value -> searchFromNode(value, currNode.right)

      else -> true
    }
  }

  /**
   * Deletes a value from the binary tree. Calls the recursive deleteNode function starting with the rootNode.
   */
  fun delete(value: Int) {
    deleteNode(value, rootNode)
  }

  /**
   * Recursive function to delete a node from the binary tree. Steps:
   *
   * Part 1: Search for the node we want to delete. Starting at the root node, we will:
   * 1. If the node is null, the value given doesn't exist
   * 2. If the value is greater than the current node's value, go right
   * 3. If the value is less than the current node's value, go left
   * 4. If the value is found, move to Part 2
   *
   * Part 2: Remove the node from the tree. There are three cases:
   * 1. The node is a leaf node (no children)
   * 2. The node has one child
   * 3. The node has two children
   */
  fun deleteNode(value: Int, node: BinaryNode?) {
    return when {
      // if the node is null, the value given doesn't exist
      node == null -> return
      // if the value is greater than the current node's value, go right
      value > node.value -> deleteNode(value, node.right)
      // if the value is less than the current node's value, go left
      value < node.value -> deleteNode(value, node.left)
      // if the value is found
      else -> {
        when {
          node.left == null && node.right == null -> removeLeafNode(node)
          (node.left == null) xor (node.right == null) -> removeNodeWithOneChild(node)
          else -> removeNodeWithTwoChildren(node)

        }
      }
    }
  }

  /**
   * Remove a node with two children from the binary tree. Steps:
   *
   * 1. Find the node with the minimum value in the right subtree
   * 2. Replace the value of the node to delete with the value of the successor node
   *     (Note the node isn't actually deleted, just the value is replaced)
   * 3. Call deleteNode with the value of the successor node to remove the duplicate node
   */
  fun removeNodeWithTwoChildren(nodeToDelete: BinaryNode) {
    val successor = minValueNode(nodeToDelete.right!!)
    nodeToDelete.value = successor.value

    // Call deleteNode with the value of the successor node to remove the duplicate node
    // This works because the node we are starting at is the right child of the node we just replaced the value of,
    // so we will traverse the tree until we find the leaf node and then delete it, reusing our deleteNode function
    deleteNode(successor.value, nodeToDelete.right)
  }

  /**
   * Remove a node with one child from the binary tree. Steps:
   *
   * 1. If the node to delete is the root node, we can just replace the root node with the left or right child. No parent values to update.
   * 2. If the node has a left child, set the parent's left child to the left child of the node we are deleting
   * 3. If the node has a right child, set the parent's right child to the right child of the node we are deleting
   * 4. Update the parent value of the child node to the parent of the node we are deleting
   */
  fun removeNodeWithOneChild(nodeToDelete: BinaryNode) {
    when {
      // If the node to delete is the root node
      nodeToDelete == rootNode -> rootNode = nodeToDelete.left ?: nodeToDelete.right
      // If the node has a left child
      nodeToDelete.left != null -> {
        if (nodeToDelete.parent?.left == nodeToDelete) {
          nodeToDelete.parent?.left = nodeToDelete.left
        } else {
          nodeToDelete.parent?.right = nodeToDelete.left
        }
        nodeToDelete.left!!.parent = nodeToDelete.parent

      }
      // If the node has a right child
      nodeToDelete.right != null -> {
        if (nodeToDelete.parent?.left == nodeToDelete) {
          nodeToDelete.parent?.left = nodeToDelete.right
        } else {
          nodeToDelete.parent?.right = nodeToDelete.right
        }
        nodeToDelete.right!!.parent = nodeToDelete.parent
      }
    }
  }

  /**
   * Remove a leaf node from the binary tree. Steps:
   *
   * 1. If the node to delete is the root node, set the root node to null
   * 2. If the node to delete is the left child of the parent, set the parent's left child to null
   * 3. If the node to delete is the right child of the parent, set the parent's right child to null
   */
  fun removeLeafNode(nodeToDelete: BinaryNode) {
    when {
      nodeToDelete == rootNode -> rootNode = null
      nodeToDelete.parent?.left == nodeToDelete -> nodeToDelete.parent?.left = null
      else -> nodeToDelete.parent?.right = null
    }
  }

  /**
   * Finds the node with the minimum value in a given subtree. By always going to the left child, we know we are
   * guaranteed the minimum value
   */
  private fun minValueNode(node: BinaryNode): BinaryNode {
    var current = node
    while (current.left != null) {
      current = current.left!!
    }
    return current
  }

  /**
   * Print the binary tree
   */
  fun printTree() {
    printTreeHelper(rootNode, "", true, null)
  }

  /**
   * Helper function to print the binary tree. GH Copilot helped me write this to visualize a Binary Tree.
   */
  private fun printTreeHelper(node: BinaryNode?, prefix: String, isTail: Boolean, isLeft: Boolean? = null) {
    node?.let {
      val childPrefix = if (isLeft == true) "L── " else if (isLeft == false) "R── " else ""
      println(prefix + (if (isTail) "└── " else "├── ") + childPrefix + it.value)
      val newPrefix = prefix + if (isTail) "    " else "│   "
      printTreeHelper(it.right, newPrefix, false, false)
      printTreeHelper(it.left, newPrefix, true, true)
    }
  }
}
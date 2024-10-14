package org.binary.search

fun main() {
  val binaryTree = SimpleBinaryTree(null)

  binaryTree.insert(10)
  binaryTree.insert(15)
  binaryTree.insert(5)
  binaryTree.insert(7)
  binaryTree.insert(1)

  binaryTree.printTree()

  binaryTree.delete(10)

  binaryTree.printTree()

  binaryTree.delete(15)

  binaryTree.printTree()

  println(binaryTree.search(3))

}

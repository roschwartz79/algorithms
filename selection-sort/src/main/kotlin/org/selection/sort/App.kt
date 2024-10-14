package org.selection.sort

class SimpleSort {

    fun selectionSort(unsortedArray: Array<Int>): Array<Int> {
        val newArray = IntArray(unsortedArray.size)
        val arrAsList = unsortedArray.toMutableList()

        for (i in arrAsList.indices) {
            val smallest = findSmallest(arrAsList)
            newArray[i] = arrAsList.removeAt(smallest)
        }

        return newArray.toTypedArray()
    }

    fun findSmallest(array: List<Int>): Int {
        var smallest = array[0]
        var smallestIndex = 0

        for (i in array.indices) {
            if (array[i] < smallest) {
                smallest = array[i]
                smallestIndex = i
            }
        }

        return smallestIndex
    }
}

fun main() {
    val unsortedArray = arrayOf(10, 3, 7, 5)
    println(SimpleSort().selectionSort(unsortedArray).toList()) // toList for pretty printing :)
}

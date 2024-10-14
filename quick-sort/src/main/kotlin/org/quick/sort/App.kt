package org.quick.sort

class QuickSort {

    fun quickSort(unsortedArray: Array<Int>, pivotSelection: PivotSelection): Array<Int> {

        // Base case, if the arr size is 1 or 0 it is already sorted
        if (unsortedArray.size < 2) {
            return unsortedArray
        }

        // Choose a pivot value
        val pivot = choosePivot(unsortedArray, pivotSelection)

        // Sort the array into two sub-arrays, less than the pivot and greater than the pivot
        val less = unsortedArray.filter { it < pivot }.toTypedArray()
        val greater = unsortedArray.filter { it > pivot }.toTypedArray()

        // Recursively sort the sub-arrays
        return quickSort(less, pivotSelection) + pivot + quickSort(greater, pivotSelection)
    }

    // Pick the pivot value
    fun choosePivot(arr: Array<Int>, pivotSelection: PivotSelection): Int {
        return when(pivotSelection) {
            PivotSelection.FIRST_ELEMENT -> arr[0]
            PivotSelection.LAST_ELEMENT -> arr[arr.size - 1]
            PivotSelection.MEDIAN_OF_THREE -> {
                val first = arr[0]
                val last = arr[arr.size - 1]
                val middle = arr[arr.size / 2]
                val median = quickSort(arrayOf(first, last, middle), PivotSelection.FIRST_ELEMENT)[1]
                median
            }
        }
    }

    enum class PivotSelection {
        FIRST_ELEMENT,
        LAST_ELEMENT,
        MEDIAN_OF_THREE
    }
}

fun main() {
    val unsortedArray = arrayOf(6, 10, 3, 7, 5, 15)
    println(QuickSort().quickSort(unsortedArray, QuickSort.PivotSelection.MEDIAN_OF_THREE).toList()) // toList for pretty printing :)
}

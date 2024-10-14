# Quick Sort

Quick Sort is a sorting algorithm that uses a divide-and-conquer approach to sort an array. It works by selecting a
pivot element from the array and partitioning the other elements into two sub-arrays according to whether they are less
than or greater than the pivot. The sub-arrays are then sorted recursively.

The steps for Quick Sort are as follows:

1) Choose a pivot element from the array.
2) Partition the array into two sub-arrays: one with elements less than the pivot and one with elements greater than the
   pivot.
3) Recursively sort the sub-arrays.
4) Combine the sorted sub-arrays to get the final sorted array.
5) Return the sorted array.

A table of the runtime of Quick Sort is shown below:

| Best Case  | Average Case | Worst Case |
|------------|--------------|------------|
| O(n log n) | O(n log n)   | O(n^2)     |

I would recommend using Quick Sort when you need a fast sorting algorithm with an average-case time complexity of O(n
log n).

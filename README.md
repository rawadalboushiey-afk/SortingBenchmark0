# Sorting Algorithms Benchmark

- **Student Name:** رواد محمد البوعيشي
- **University ID:** 3169061
- **Section / Class:** [رقم الشعبة]

---

## 1. Benchmark Results (Microseconds - $\mu s$)

| Algorithm | Random ($\mu s$) | Reversely Sorted ($\mu s$) | Partially Sorted ($\mu s$) | Fully Sorted ($\mu s$) |
| :--- | :---: | :---: | :---: | :---: |
| **Selection Sort** | 13299054 | 17517427 | 6466878 | 8119006 |
| **Insertion Sort** | 1709781 | 3272643 | 690855 | 538 |
| **Bubble Sort** | 32357716 | 29816978 | 19016326 | 139 |
| **Merge Sort** | 66583 | 24179 | 39199 | 18314 |
| **Quick Sort** | 36914 | Overflow | 23587 | Overflow |

---

## 2. Analysis and Discussion

### Performance Comparison: $O(N^2)$ vs $O(N \log N)$
- **Quadratic Algorithms ($O(N^2)$):** Selection, Insertion, and Bubble sort show significantly higher execution times as the dataset size increases ($N = 100,000$), especially under random or reverse conditions, due to nested loops checking every element pair.
- **Log-Linear Algorithms ($O(N \log N)$):** Merge Sort and Quick Sort perform exceptionally faster, scaling efficiently for large datasets by dividing the problem recursively.

### Best Performing Algorithm in Pre-sorted States
- **Insertion Sort** and **Bubble Sort** perform remarkably well on **Fully Sorted** data (achieving execution times as low as $538 \mu s$ and $139 \mu s$ respectively), approaching $O(N)$ time complexity because inner loops/swaps are skipped when no elements need relocation.

### Quick Sort Behavior & Pivot Impact
- **Quick Sort** relies heavily on the choice of the Pivot element. In degenerate states (such as fully sorted or reverse data with a naive pivot choice), call depth reaches $O(N)$, triggering a `StackOverflowError` (Overflow) due to maximum recursion limit exhaustion.

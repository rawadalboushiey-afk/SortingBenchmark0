# Sorting Algorithms Benchmark

- **Student Name:** رواد محمد البوعيشي
- **University ID:** 3169061
- **Section / Class:** [رقم الشعبة]

---

## 1. Benchmark Results (Microseconds - $\mu s$)

| Algorithm | Random ($\mu s$) | Reversely Sorted ($\mu s$) | Partially Sorted ($\mu s$) | Fully Sorted ($\mu s$) |
| :--- | :---: | :---: | :---: | :---: |
| **Selection Sort** | - | - | - | - |
| **Insertion Sort** | - | - | - | - |
| **Bubble Sort** | - | - | - | - |
| **Merge Sort** | - | - | - | - |
| **Quick Sort** | - | - | - | - |

---

## 2. Analysis and Discussion

### Performance Comparison: $O(N^2)$ vs $O(N \log N)$
- **Quadratic Algorithms ($O(N^2)$):** Selection, Insertion, and Bubble sort show significantly higher execution times as the dataset size increases ($N = 100,000$), especially under random or reverse conditions, due to nested loops checking every element pair.
- **Log-Linear Algorithms ($O(N \log N)$):** Merge Sort and Quick Sort perform exceptionally faster, scaling efficiently for large datasets by dividing the problem recursively.

### Best Performing Algorithm in Pre-sorted States
- **Insertion Sort** performs remarkably well on **Fully Sorted** or **Partially Sorted** data, achieving near $O(N)$ time complexity because the inner loop terminates immediately when no shifts are needed.

### Quick Sort Behavior & Pivot Impact
- **Quick Sort** relies heavily on the choice of the Pivot element. In poorly partitioned states (like sorted or reverse data with a naive pivot choice), its performance can degrade toward $O(N^2)$, potentially causing deep recursive call stacks.

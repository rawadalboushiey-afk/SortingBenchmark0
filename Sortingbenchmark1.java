package sortingbenchmark1;

import java.util.Arrays;
import java.util.Random;

public class Sortingbenchmark1 {

    public static void selectionSort(int[] data) {
        int n = data.length;
        for (int i = 0; i < n - 1; ++i) {
            int smallest = i;
            for (int j = i + 1; j < n; ++j) {
                if (data[smallest] > data[j]) smallest = j;
            }
            int temp = data[i];
            data[i] = data[smallest];
            data[smallest] = temp;
        }
    }

    public static void insertionSort(int[] data) {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int temp = data[i];
            int j = i;
            while (j > 0 && data[j - 1] > temp) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = temp;
        }
    }

    public static void bubbleSort(int[] data) {
        int n = data.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pi = partition(data, low, high);
            quickSort(data, low, pi - 1);
            quickSort(data, pi + 1, high);
        }
    }

    private static int partition(int[] data, int low, int high) {
        int pivot = data[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (data[j] <= pivot) {
                i++;
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }
        int temp = data[i + 1];
        data[i + 1] = data[high];
        data[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int n = 100000;
        int[] originalRandom = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            originalRandom[i] = rand.nextInt(1000000);
        }

        int[] reverseSorted = new int[n];
        for (int i = 0; i < n; i++) {
            reverseSorted[i] = n - i;
        }

        int[] partiallySorted = originalRandom.clone();
        Arrays.sort(partiallySorted);
        for (int i = 0; i < n; i += 5) {
            int idx1 = rand.nextInt(n);
            int idx2 = rand.nextInt(n);
            int temp = partiallySorted[idx1];
            partiallySorted[idx1] = partiallySorted[idx2];
            partiallySorted[idx2] = temp;
        }

        int[] fullySorted = originalRandom.clone();
        Arrays.sort(fullySorted);

        String[] conditions = {"Random", "Reversely Sorted", "Partially Sorted", "Fully Sorted"};
        int[][] datasets = {originalRandom, reverseSorted, partiallySorted, fullySorted};

        System.out.println("Running benchmark for N = " + n + " ... Please wait...\n");
        System.out.printf("%-18s | %-18s | %-18s | %-18s | %-18s%n", "Algorithm", "Random (ns)", "Reversely Sorted (ns)", "Partially Sorted (ns)", "Fully Sorted (ns)");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        String[] algorithms = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Quick Sort"};

        for (int alg = 0; alg < algorithms.length; alg++) {
            System.out.printf("%-18s", algorithms[alg]);
            for (int cond = 0; cond < datasets.length; cond++) {
                int[] dataCopy = datasets[cond].clone();
                long startTime = System.nanoTime();

                if (alg == 0) {
                    selectionSort(dataCopy);
                } else if (alg == 1) {
                    insertionSort(dataCopy);
                } else if (alg == 2) {
                    bubbleSort(dataCopy);
                } else if (alg == 3) {
                    try {
                        quickSort(dataCopy, 0, dataCopy.length - 1);
                    } catch (StackOverflowError e) {
                        System.out.printf(" | %-18s", "Overflow");
                        continue;
                    }
                }

                long endTime = System.nanoTime();
                long duration = endTime - startTime;
                System.out.printf(" | %-18d", duration);
            }
            System.out.println();
        }
    }
}
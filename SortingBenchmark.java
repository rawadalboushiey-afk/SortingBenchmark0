package sortingbenchmark;
import java.util.Arrays; import java.util.Random;
public class SortingBenchmark {

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
    boolean swapped = true;
    for (int i = 0; swapped && i < n - 1; i++) {
        swapped = false;
        for (int j = 0; j < n - 1 - i; j++) {
            if (data[j] > data[j + 1]) {
                int temp = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temp;
                swapped = true;
            }
        }
    }
}

public static void mergeSort(int[] data, int left, int right) {
    if (left < right) {
        int mid = left + (right - left) / 2;
        mergeSort(data, left, mid);
        mergeSort(data, mid + 1, right);
        merge(data, left, mid, right);
    }
}

private static void merge(int[] data, int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int[] L = new int[n1];
    int[] R = new int[n2];
    for (int i = 0; i < n1; ++i) L[i] = data[left + i];
    for (int j = 0; j < n2; ++j) R[j] = data[mid + 1 + j];
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) data[k++] = L[i++];
        else data[k++] = R[j++];
    }
    while (i < n1) data[k++] = L[i++];
    while (j < n2) data[k++] = R[j++];
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

public static int[] generateTestArray(int size, String type) {
    int[] arr = new int[size];
    Random rand = new Random(42);
    if (type.equals("Random")) {
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(1000000);
    } else if (type.equals("Reversely Sorted")) {
        for (int i = 0; i < size; i++) arr[i] = size - i;
    } else if (type.equals("Partially Sorted")) {
        for (int i = 0; i < size; i++) arr[i] = i;
        for (int i = 0; i < size * 0.2; i++) {
            int idx1 = rand.nextInt(size);
            int idx2 = rand.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
    } else if (type.equals("Fully Sorted")) {
        for (int i = 0; i < size; i++) arr[i] = i;
    }
    return arr;
}

public static long measureTime(String algorithm, int[] originalArray) {
    int[] testArray = Arrays.copyOf(originalArray, originalArray.length);
    long startTime = System.nanoTime();
    switch (algorithm) {
        case "Selection Sort": selectionSort(testArray); break;
        case "Insertion Sort": insertionSort(testArray); break;
        case "Bubble Sort": bubbleSort(testArray); break;
        case "Merge Sort": mergeSort(testArray, 0, testArray.length - 1); break;
        case "Quick Sort":
            try { quickSort(testArray, 0, testArray.length - 1); }
            catch (StackOverflowError e) { return -1; }
            break;
    }
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1000;
}

public static void main(String[] args) {
    int size = 100000;
    String[] algorithms = {"Selection Sort", "Insertion Sort", "Bubble Sort", "Merge Sort", "Quick Sort"};
    String[] conditions = {"Random", "Reversely Sorted", "Partially Sorted", "Fully Sorted"};
    long[][] results = new long[algorithms.length][conditions.length];

    System.out.println("Running benchmark for N = " + size + " ... Please wait...\n");

    for (int i = 0; i < algorithms.length; i++) {
        for (int j = 0; j < conditions.length; j++) {
            int[] baseArray = generateTestArray(size, conditions[j]);
            results[i][j] = measureTime(algorithms[i], baseArray);
        }
    }

    System.out.println("---------------------------------------------------------------------------------------------------------");
    System.out.printf("%-18s | %-18s | %-18s | %-18s | %-18s%n", "Algorithm", "Random", "Reversely Sorted", "Partially Sorted", "Fully Sorted");
    System.out.println("---------------------------------------------------------------------------------------------------------");

    for (int i = 0; i < algorithms.length; i++) {
        System.out.printf("%-18s", algorithms[i]);
        for (int j = 0; j < conditions.length; j++) {
            String timeStr = (results[i][j] == -1) ? "Overflow" : results[i][j] + " us";
            System.out.printf(" | %-18s", timeStr);
        }
        System.out.println();
    }
    System.out.println("---------------------------------------------------------------------------------------------------------");
}
}
import java.util.Arrays;
import java.util.Random;

public class SortBenchmark {

    //1. INSERTION SORT
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //2. STANDARD MERGESORT
    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    //3. NATURAL MERGESORT
    public static void naturalMergeSort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;

        boolean sorted = false;
        while (!sorted) {
            int l = 0;
            int numMerges = 0;

            while (l < n) {
                // Xác định đường chạy tăng 1 (Left Run)
                int m = l;
                while (m + 1 < n && arr[m] <= arr[m + 1]) {
                    m++;
                }

                if (m == n - 1) {
                    if (l == 0) sorted = true; // Cả mảng đã được sắp xếp
                    break;
                }

                // Xác định đường chạy tăng 2 (Right Run)
                int r = m + 1;
                while (r + 1 < n && arr[r] <= arr[r + 1]) {
                    r++;
                }

                // Merge 2 đường chạy
                merge(arr, l, m, r);
                numMerges++;
                l = r + 1;
            }

            if (numMerges == 0) sorted = true;
        }
    }

    //HÀM TẠO DỮ LIỆU MẪU
    public static int[] generateRandomData(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = rand.nextInt(100000);
        return arr;
    }

    public static int[] generateSortedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = i;
        return arr;
    }

    public static int[] generateReversedData(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) arr[i] = size - i;
        return arr;
    }

    // HÀM ĐO THỜI GIAN CHẠY (Millisecond)
    @FunctionalInterface
    interface SortAlgorithm {
        void sort(int[] arr);
    }

    public static double measureTime(SortAlgorithm algorithm, int[] originalArr) {
        int[] arr = Arrays.copyOf(originalArr, originalArr.length); // Tạo bản sao để không ảnh hưởng dữ liệu gốc
        long startTime = System.nanoTime();
        algorithm.sort(arr);
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1e6; // Đổi sang millisecond
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000, 50000};

        System.out.printf("%-10s | %-12s | %-18s | %-15s | %-20s%n",
                "Size (N)", "Dữ liệu", "Insertion Sort (ms)", "MergeSort (ms)", "Natural MergeSort (ms)");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int n : sizes) {
            // 1. Ngẫu nhiên
            int[] randomData = generateRandomData(n);
            double tInsertR = (n <= 10000) ? measureTime(SortBenchmark::insertionSort, randomData) : -1; // Bỏ qua nếu N quá lớn
            double tMergeR = measureTime(a -> mergeSort(a, 0, a.length - 1), randomData);
            double tNatMergeR = measureTime(SortBenchmark::naturalMergeSort, randomData);

            System.out.printf("%-10d | %-12s | %-18.3f | %-15.3f | %-20.3f%n",
                    n, "Ngẫu nhiên", tInsertR, tMergeR, tNatMergeR);

            // 2. Đã sắp xếp
            int[] sortedData = generateSortedData(n);
            double tInsertS = measureTime(SortBenchmark::insertionSort, sortedData);
            double tMergeS = measureTime(a -> mergeSort(a, 0, a.length - 1), sortedData);
            double tNatMergeS = measureTime(SortBenchmark::naturalMergeSort, sortedData);

            System.out.printf("%-10d | %-12s | %-18.3f | %-15.3f | %-20.3f%n",
                    n, "Đã sắp xếp", tInsertS, tMergeS, tNatMergeS);

            // 3. Sắp xếp ngược
            int[] reversedData = generateReversedData(n);
            double tInsertRev = (n <= 10000) ? measureTime(SortBenchmark::insertionSort, reversedData) : -1;
            double tMergeRev = measureTime(a -> mergeSort(a, 0, a.length - 1), reversedData);
            double tNatMergeRev = measureTime(SortBenchmark::naturalMergeSort, reversedData);

            System.out.printf("%-10d | %-12s | %-18.3f | %-15.3f | %-20.3f%n",
                    n, "Sắp ngược", tInsertRev, tMergeRev, tNatMergeRev);
            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }
}
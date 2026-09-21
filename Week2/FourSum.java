import java.util.Arrays;

public class FourSum {
    public static void fourSum(int[] array, int target) {
        int n = array.length;
        if (n < 4) return;
        Arrays.sort(array);
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && array[j] == array[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    long currentSum = (long) array[i] + array[j] + array[left] + array[right];
                    if (currentSum == target) {
                        System.out.println(array[i] + " " + array[j] + " " + array[left] + " " + array[right]);
                        while (left < right && array[left] == array[left + 1]) left++;
                        while (left < right && array[right] == array[right - 1]) right--;
                        left++;
                        right--;
                    } else if (currentSum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
    }
}

    public static void main(String[] args) {
        int[] arr = {1, 0, -1, 0, -2, 2};
        int target = 0;
        FourSum.fourSum(arr, target);
    }
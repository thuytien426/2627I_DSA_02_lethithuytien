import java.util.Arrays;

public class FourSum {
    public static void fourSum(int[] array, int target) {
        int n = array.length;
        if (n < 4) return;

        // 1. Sắp xếp mảng tăng dần để áp dụng kỹ thuật 2 con trỏ
        Arrays.sort(array);

        // 2. Vòng lặp thứ nhất cho chỉ số i (chạy đến n - 3 để chừa vị trí cho j, left, right)
        for (int i = 0; i < n - 3; i++) {
            // Tránh trùng lặp cho i: so sánh với số NGAY TRƯỚC NÓ (i - 1)
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }

            // 3. Vòng lặp thứ hai cho chỉ số j (chạy đến n - 2)
            for (int j = i + 1; j < n - 2; j++) {
                // Tránh trùng lặp cho j: so sánh với số NGAY TRƯỚC NÓ (j - 1)
                if (j > i + 1 && array[j] == array[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = n - 1;

                // 4. Vòng lặp 2 con trỏ tìm cặp số còn lại
                while (left < right) {
                    // Ép kiểu long để tránh bị tràn số khi cộng 4 số nguyên lớn
                    long currentSum = (long) array[i] + array[j] + array[left] + array[right];

                    if (currentSum == target) {
                        // In kết quả tìm thấy
                        System.out.println(array[i] + " " + array[j] + " " + array[left] + " " + array[right]);

                        // Bỏ qua các phần tử trùng lặp ở hai đầu con trỏ
                        while (left < right && array[left] == array[left + 1]) left++;
                        while (left < right && array[right] == array[right - 1]) right--;

                        // Tiếp tục dịch chuyển 2 con trỏ để tìm các bộ số khác
                        left++;
                        right--;
                    } else if (currentSum < target) {
                        left++;  // Tổng đang nhỏ hơn target -> tăng left để tăng tổng
                    } else {
                        right--; // Tổng đang lớn hơn target -> giảm right để giảm tổng
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, -1, 0, -2, 2};
        int target = 0;
        fourSum(arr, target);
    }
}
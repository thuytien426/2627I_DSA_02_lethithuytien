import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class W4_25020362 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();

        Integer[] citations = new Integer[n];
        for (int i = 0; i < n; i++) {
            citations[i] = sc.nextInt();
        }

        Arrays.sort(citations, Collections.reverseOrder());

        int hIndex = 0;
        for (int i = 0; i < n; i++) {
            if (citations[i] >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }

        System.out.println(hIndex);
        sc.close();
    }
}
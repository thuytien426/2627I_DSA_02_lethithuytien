import java.util.Scanner;

public class countingSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] count = new int[100];
        for (int i = 0; i<n; i++){
            int x = sc.nextInt();
            count[x]++;
        }
        for ( int i = 0; i < 100; i++ ){
            System.out.println(i + ":" + count[i] + ", ");
        }
        sc.close();
    }
}
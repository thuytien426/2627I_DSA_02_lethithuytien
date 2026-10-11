import java.util.Scanner;
import java.util.Stack;

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();

        String S = "";
        Stack<String> history = new Stack<>();

        for (int i = 0; i < q; i++) {
            int type = sc.nextInt();

            if (type == 1) {
                String W = sc.next();
                history.push(S);
                S = S + W;
            }
            else if (type == 2) {
                int k = sc.nextInt();
                history.push(S);
                S = S.substring(0, S.length() - k);
            }
            else if (type == 3) {
                int k = sc.nextInt();
                System.out.println(S.charAt(k - 1));
            }
            else if (type == 4) {
                if (!history.isEmpty()) {
                    S = history.pop();
                }
            }
        }
        sc.close();
    }
}
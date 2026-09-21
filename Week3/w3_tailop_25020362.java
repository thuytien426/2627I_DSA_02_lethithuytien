import java.util.Stack;
public class w3_tailop_25020362 {
    static boolean letterOrDigit(char c){
        return Character.isLetterOrDigit(c);
    }
    static int getPrecedence(char c){
        if ( c == '+' || c == '-') return 1;
        else if ( c == '*' || c == '/') return 2;
        else return -1;
    }
    static boolean hasLeftAssociativity(char ch) {
        if (ch == '+' || ch == '-' || ch == '/' || ch == '*') {
            return true;
        } else {
            return false;
        }
    }
    static String infixToPostfix(String expression) {
        Stack<Character> stack = new Stack<>();
        String output = new String("");
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            if (Character.isWhitespace(c))
                continue;
            if (letterOrDigit(c))
                output += c;
            else if (c == '(')
                stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    output += stack.pop();
                if (!stack.isEmpty())
                    stack.pop();
            }
            else {
                while (!stack.isEmpty()
                        && getPrecedence(c) <= getPrecedence(stack.peek())
                        && hasLeftAssociativity(c)) {
                    output += stack.pop();
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "This expression is invalid";
            output += stack.pop();
        }
        return output;
    }
    public static void main(String[] args)
    {
        String expression = "20-(5+2)*1*3-2*(3+1)";
        System.out.println(infixToPostfix(expression));
    }
}
package easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {


    public static void main(String[] args) {
        String parentheses = "{}[]{[]}}}";

        System.out.println(validParentheses2(parentheses));
    }




    public static boolean validParentheses2(String parentheses) {
        if (parentheses.length() % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();

        for (char p : parentheses.toCharArray()) {
            if (p == '(') {
                stack.push(')');
            } else  if (p == '[') {
                stack.push(']');
            } else if (p == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != p) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static boolean validParentheses1(String parentheses) {

        if (parentheses.length() % 2 != 0) return false;


        Map<Character, Character> charMap = new HashMap<>(4);
        charMap.put(')', '(');
        charMap.put(']', '[');
        charMap.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (char p : parentheses.toCharArray()) {

            if (charMap.containsKey(p)) {

                if (charMap.get(p) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }

            } else {
                stack.push(p);
            }

        }
        return stack.empty();

    }
}

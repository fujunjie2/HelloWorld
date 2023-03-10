package easy;

import array.LeastBeforeIndex;

import java.util.Stack;

/**
 *   i < j < k 时
 *   arr[i]  < arr[k] < arr[j]
 */

public class Pattern132 {

    public static void main(String[] args) {
        int[] arr = new int[]{3,5,0,3,4};  // 3 5 4

        boolean result = has132_20201117(arr);
        System.out.println(result);
    }

    public static boolean has132_20201117(int[] arr) {

        if (arr.length < 3) {
            return false;
        }

        int[] least = LeastBeforeIndex.leastBeforeIndex(arr);

        int len = arr.length;
        Stack<Integer> stack = new Stack<>();

        for (int i = len - 1; i > 0; i--) {
            if (arr[i] > least[i]) {
                while (!stack.isEmpty() && stack.peek() < least[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < arr[i]) {
                    return true;
                }
                stack.push(arr[i]);
            }
        }
        return false;
    }

    public static boolean has132(int[] arr) {

        if (arr.length < 3) {
            return false;
        }

        int[] leastBeforeIndex = LeastBeforeIndex.leastBeforeIndex(arr);

        Stack<Integer> upStack = new Stack<>();

        int len = arr.length;

        for (int i = len - 1; i > 0; i--) {
            // 是否含有 1 - 3 这种 模式
            if (leastBeforeIndex[i] < arr [i]) {
                // 是否含有 1 - 3 - 2 这种模式
                while (!upStack.isEmpty() && leastBeforeIndex[i] > upStack.peek()) {
                    upStack.pop();
                }
                if (!upStack.isEmpty() && upStack.peek() < arr[i]) {
                    return true;
                }
                upStack.push(arr[i]);
            }
        }
        return false;

    }
}

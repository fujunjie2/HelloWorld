package dynamicPrograming;

public class FrogJumpGame_1 {

    public static void main(String[] args) {
        int[] path = new int[]{4,2,1,2,0,4};

        System.out.println(greedy(path));
    }

    public static boolean solution(int[] A) {
        int n = A.length;
        boolean[] state = new boolean[n];

        state[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (state[j] && A[j] >= i - j) {
                    state[i] = true;
                    break;
                }
            }
        }
        return state[n-1];
    }

    public static boolean greedy(int[] A) {
        int n = A.length;
        // max 表示 从 第 i 个石头起跳，所能跳到的最远的距离

        if (A[0] >= n - 1) {
            return true;
        }
        int max  = A[0];
        for (int i = 1; i < n - 1; i++) {

            if (max < i) {
                return false;
            } else {
                max = max > A[i] + i ? max : A[i] + i;
            }

            if (max > n - 2) {
                return true;
            }
        }
        return false;
    }
}

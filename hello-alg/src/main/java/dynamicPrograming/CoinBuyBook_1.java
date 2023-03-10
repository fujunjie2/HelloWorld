package dynamicPrograming;


/**
 * 2，5，7，总价27
 *
 * f(x) = min{f(x-2), f(x-5), f(x-7)} + 1
 *
 * 问题转化为求 f(27)
 *
 * @return
 */
public class CoinBuyBook_1 {

    public static void main(String[] args) {
        int[] A = new int[]{10,5};
        System.out.println(solution(A,27));

    }

    /**
     * 递归， f(x) = min{f(x-2), f(x-5), f(x-7)} + 1
     * @param x
     * @return
     */
    public static int solution2(int x) {
        if (x == 0) return 0;
        int res = 100000;
        if (x >= 2) {
            res = Math.min(solution2(x - 2) + 1, res);
        }
        if (x >= 5) {
            res = Math.min(solution2(x - 5) + 1, res);
        }
        if (x >= 7) {
            res = Math.min(solution2(x - 7) + 1, res);
        }
        return res;

    }

    public static int solution(int[] A, int M){
        int[] state = new int[M + 1];
        int n = A.length;

        state[0] = 0;

        for (int i = 1; i <= M; i++) {
            state[i] = Integer.MAX_VALUE;

            for (int j = 0; j < n; j++) {
                // state[i - A[j]] == Integer.MAX_VALUE 表示 拼不出 i - A[j], 比如 {2,5,7}为硬币面额，则 6是拼不出的, 当 i = 8时， A[j] = 2,
                if (i >= A[j] && state[i - A[j]] != Integer.MAX_VALUE) {
                    state[i] = Math.min(state[i], state[i - A[j]] + 1);
                }
            }
        }
        if (state[M] == Integer.MAX_VALUE) {
            state[M] = -1;
        }
        return state[M];
    }



}

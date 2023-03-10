package dynamicPrograming;

/**
 *  有一个背包，体积为 M，
 *  有 n 个物品 A1 ... An,
 *  他们的价值为 C1 ... Cn,
 *  他们的重量为 W1 ... Wn
 *  问：将哪些物品装入背包使得价值最大
 */
public class 背包问题01 {

    public static void main(String[] args) {
        int M = 8;
        int[] w = {3,4,5,6};
        int[] c = {2,3,4,5};


        int x = solutionOptimiseSpace(M, w, c);

        System.out.println(x);
    }

    // state[i][j] 表示将 前i件 物品放入容量为 j 的背包时，最大的价值
    // 则 state[i][j] = max{ state[i-1][j]，state[i-1][j-c[i]] + w[i]}
    // 对于第i个物品， 有两种选择，1：不放；2：放
    // 不放，则当前背包的价值和不放当前物品的价值一样，即 state[i-1][j];
    // 放，则可能需要腾出空间来， 当腾出刚好的空间，即去查 j - c[i] 空间时，背包的最大价值 state[i-1][j - c[i]]
    //     再加上当前物品的最大价值，
    // 取放与不放两者策略间背包价值最大的那种情况
    public static int solution(int M, int[] w, int[] c) {
        int n = w.length;

        int[][] state = new int[n + 1][M + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= M; j++) {
                if (j - c[i-1] >= 0) {
                    state[i][j] = Math.max(state[i-1][j], state[i-1][j-c[i-1]] + w[i-1]);
                } else {
                    state[i][j] = state[i-1][j];
                }
            }

        }

//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= M; j++) {
//                System.out.print(state[i][j]);
//                System.out.print("|");
//            }
//            System.out.println();
//        }

        int j = M;
        for (int i = n; i > 0; i--) {
            if (state[i][j] != state[i-1][j] && j > 0) {
                System.out.println(i);
                j = j - c[i-1];
            }
        }

        return state[n][M];
    }

    /**
     * 对于容量 0 ~ M ， 如果从 0 到 M 枚举，比如我先 得到 state[4],
     * @param M
     * @param w
     * @param c
     * @return
     */
    public static int solutionOptimiseSpace(int M, int[] w, int[] c) {
        int n = w.length;

        int state[] = new int[M+1];

        for (int i = 0; i < n; i++) {

            for (int j = M; j >= 0; j--) {

                if (j >= c[i]) {
                    state[j] = Math.max(state[j], state[j - c[i]] + w[i]);
                }
            }
        }

        return state[M];
    }
}

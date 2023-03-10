package dynamicPrograming;

/**
 * 在n个物品中挑选若干物品装入背包，最多能装多满？
 * 假设背包的大小为m，每个物品的大小为A[i]
 */
public class 背包问题2 {

    public static void main(String[] args) {
        int[] A = {3,4,8,5};
        int w = 10;

        int x = solution(w, A);

        System.out.println(x);
    }

    /**
     * 第 n 个物品放置与否
     * 背包的剩余容量如果够，则放
     * 否则，
     *      不放，则最多放置的重量 和 第 n-1 一致
     *       放，则需要为 第 n 个物品腾出空间，并计算容量为  M - A[n] 时，最大的放置的重量，
     *
     *  state[i][j] 表示 放置第 i 个物品时，容量为 j 时，能放置的最大重量
     *
     *  state[i][j] = max{ state[i-1][j], state[i-1][j - A[i] ] + A[i]}
     *
     *
     * 这题和背包问题一致，区别在于，这题的开销也是它的价值
     */
    public static int solution(int M, int[] w) {
        int n = w.length;
        int[][] state = new int[n +1][M+1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= M; j++) {
                if (j > w[i-1]) {
                    state[i][j] = Math.max(state[i-1][j], state[i-1][j-w[i-1]] + w[i-1]);
                } else {
                    state[i][j] = state[i-1][j]  ;
                }
            }

        }
        return state[n][M];
    }
}

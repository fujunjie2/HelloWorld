package dynamicPrograming;

public class UniquePath_2 {

    public static void main(String[] args) {

        int m = 2;
        int n = 3;

        System.out.println(solution(m, n));
    }

    /**
     *    [ 2 2 2]
     *    [ 1 1 1]
     */

    // f(m - 1，n - 1) = f(m - 2，n -1) + f( m - 1，n - 2 )
    public static int solution(int m, int n) {

        int[][] state = new int[m][n];
        state[0][0] = 0;
        for (int i = 1; i < m; i++) {
            state[i][0] = 1;
        }

        for (int i = 1; i < n; i++) {
            state[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                state[i][j] = state[i - 1][j] + state[i][j - 1];
            }
        }

        return state[m-1][n-1];
    }

}

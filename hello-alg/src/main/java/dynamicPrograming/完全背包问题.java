package dynamicPrograming;

public class 完全背包问题 {

    public static void main(String[] args) {
        int M = 10;
        int[] w = {1,5,2,4};
        int[] c = {2,3,4,5};
        int x = solution(M, w, c);
        System.out.println(x);
    }

    public static int solution(int M, int[] w, int[] c){
        int n = w.length;

        int state[] = new int[M+1];

        for (int i = 0; i < n; i++) {

            // 和01背包的区别在于，从前往后遍历，表示  第 i 物品可以重复使用
            for (int j =0; j <= M; j++) {

                if (j >= c[i]) {
                    state[j] = Math.max(state[j], state[j - c[i]] + w[i]);
                }
            }
        }

        return state[M];
    }
}

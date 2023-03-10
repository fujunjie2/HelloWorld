package dynamicPrograming;

public class 约翰的后花园 {


    public static void main(String[] args) {
        System.out.println(solution(121));
    }


    public static boolean solution(int x) {
        if (x < 3 && x > 0) {
            return false;
        }

        boolean[] state = new boolean[x + 1];
        int[] w = new int[]{3,7};

        state[0] = true;

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < 2; j++) {
                if (i >= w[j])
                    state[i] = state[i] || state[i - w[j]];
            }
        }
        return state[x];
    }

}

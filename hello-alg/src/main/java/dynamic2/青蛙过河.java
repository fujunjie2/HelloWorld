package dynamic2;

public class 青蛙过河 {

    public static void main(String[] args) {
        int[] path = new int[]{5,2,1,0,0,4};

        System.out.println(solution(path));

    }

    public static boolean solution(int[] arr) {

        int n = arr.length;

        boolean state[] = new boolean[n];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] >= i - j) {
                    state[i] = true;
                    break;
                }
            }
        }

        return state[n - 1];

    }


    public static boolean greedy(int[] arr) {

        int n = arr.length;

        if (arr[0] >= n - 1) {
            return true;
        }

        int[] max = new int[n];

        for (int i = 1; i < n - 1; i++){

            if (arr[i] >= n - 1 ) {
                return true;
            } else {
                int next = arr[i] + i;
                max[i] = max[i - 1] > next ? max[i] : next;
            }

            if (max[i] >= n - 1) {
                return true;
            }

        }

        return false;
    }
}

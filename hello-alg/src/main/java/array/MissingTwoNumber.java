package array;

import org.apache.commons.lang3.tuple.Pair;

public class MissingTwoNumber {

    public static void main(String[] args) {
        int[] param = new int[]{1,2,3,4,5,6,7,8,9,10,12};

        solutionSum(param);

    }

    public static Pair<Integer, Integer> solutionSum(int[] arr) {
        int n = arr.length + 2;
        // 未缺失的数组和
        int sum = n * (n + 1) / 2;

        int sums = 0;
        for (int i = 0; i < n - 2; i++) {
            sums += arr[i];
        }

        int twoSum = sum - sums;
        int limit = twoSum / 2;

        sums = 0;
        for (int i = 0; i < limit - 1; i++) {
            sums += arr[i];
        }
        int one = limit * (limit + 1) / 2 - sums;

        System.out.println(one);
        System.out.println(twoSum - one);

        return Pair.of(one, twoSum - one);
    }

//    1,2,3,4,,6,7,8,9,10
    public static Pair<Integer, Integer> solutionIter(int[] arr) {

        int adjust = 1;
        int one = 0, two = 0;
        int arri, tmp;
        for (int i = 0; i < arr.length; i++) {
            arri = arr[i];
            tmp = i + adjust;
            if (arri != tmp) {
                if (adjust == 1) {
                    one = tmp;
                    adjust = 2;
                } else if (adjust == 2) {
                    two = tmp;
                    break;
                }
            }
        }

        if (one == 0) {
            int end = arr[arr.length - 1] + 1;
            one = end;
            two = end + 1;
        }

        if (two == 0) {
            two = arr[arr.length - 1] + 1;
        }


        System.out.println(one);
        System.out.println(two);

        return null;
    }

}

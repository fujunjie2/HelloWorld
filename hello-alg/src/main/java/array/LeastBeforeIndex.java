package array;

public class LeastBeforeIndex {


    public static void main(String[] args) {
        int[] arr = new int[]{11,13,14,12,4,5,8,-3,11};

        int[] result = leastBeforeIndex(arr);

        for (int k : result) {
            System.out.println(k);
        }

    }

    /**
     *
     * @param arr
     * @return
     */
    public static int[] leastBeforeIndex(int[] arr) {
        int[] leastArr = new int[arr.length];

        leastArr[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            leastArr[i] = Math.min(leastArr[i-1], arr[i]);
        }

        return leastArr;
    }
}

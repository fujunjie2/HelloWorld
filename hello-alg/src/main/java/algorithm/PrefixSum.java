package algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 与前缀和有关的题目
 */
public class PrefixSum {


    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     * @return
     */
    public static int sumK(int[] arr, int k) {

        Map<Integer, Integer> mp = new HashMap<>();
        int preSum = 0;
        int count = 0;
        mp.put(0, 1);
        for (int i = 0; i < arr.length; i++) {
            preSum += arr[i];
            if (mp.containsKey(preSum - k)) {
                 count += mp.get(preSum - k);
            }
            mp.put(preSum, mp.getOrDefault(preSum, 0) +1);
        }

        return count;
    }

}

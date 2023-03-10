package algorithm;

import java.util.HashMap;
import java.util.Map;

public class Simple {


    /**
     * 两数之和
     * @param nums  int[] param = new int[]{1,2,3,5,6,7};
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> bus = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (bus.containsKey( temp )) {
                return new int[]{i, bus.get( temp )};
            } else {
                bus.put(nums[i], i);
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

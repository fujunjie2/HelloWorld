package plus;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15, 10};
        int target = 21;

        int[] po = solution(nums, target);

        if (po != null) {
            System.out.println(po[0] + "-" + po[1]);
        }
    }

    public static int[] solution(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {

            int t = target - nums[i];

            if (!map.containsKey(t)) {
                map.put(nums[i], i);
            } else {
                return new int[]{map.get(t), i};
            }
        }
        return null;
    }
}

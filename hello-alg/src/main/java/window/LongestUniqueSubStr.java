package window;

import java.util.HashMap;
import java.util.Map;

/**
 * lc 3
 * 问题描述：
 *      给出一串字符串，要求返回字符串中最长的不重复的字串的长度
 */

/**
 *判断当前 的字符是否已经存在，如果存在，那么从 map 中获取的是前面最近出现的 字符及其位置
 * 这时要做的就是把窗口的 左边 移动到 这个位置后
 * 但是还有一个问题， 窗口是不能后退的，必须与当前窗口比较，取大的值
 * 例如 abba ，窗口 left 的值 依次为  0, 0, 2, 此时读取 a ，发现 map 中已经有
 *          a->0 ,如果此时不与当前窗口比较，取大的值，而是直接移动 到 0 后面的 1 上
 *          那 相当于窗口 从 2 变成 1 ，从 第二个b 退回到 第一个 a 后面，相当于窗口后退了
 */
public class LongestUniqueSubStr {

    public static void main(String[] args) {
        String str = "abccba";

        int max = solution(str);

        System.out.println(max);

    }


    public static int solution(String str) {

        if (str == null || str.length() == 0) {
            return 0;
        }

        Map<Character, Integer> indexMap = new HashMap<>();

        int len = str.length();
        int left = 0;
        int max = 0;
        for (int i=0; i < len; ++i) {
            char ch = str.charAt(i);
            if (indexMap.containsKey(ch)) {
                // Max 防止窗口后退
                left = Math.max(left, indexMap.get(ch) + 1);
            }
            indexMap.put(ch, i);
            max = Math.max(max, i-left+1);
        }
        return max;
    }
}


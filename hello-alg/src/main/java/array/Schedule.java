package array;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Schedule {


    private static int getPeriodDistant(int before, int next) {
        int tmp = next - before;
        return tmp > 0 ? tmp : tmp + 7;
    }

    private static int[] parsePeriodArray(String period) {
        String[] format = period.split(",");
        int[] intFormat = new int[format.length];
        int i = 0;
        for (String each : format) {
            int x = Integer.valueOf(each.trim());
            intFormat[i++] = x;
        }
        Arrays.sort(intFormat);
        return intFormat;
    }


    private static int contain(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    private static int getNearestForwardIndex(int[] period, int limitStartWeek) {
        int index = 0;
        for (int i = 0; i < period.length; i++) {
            if (period[i] == limitStartWeek) {
                index = i;
                break;
            } else if (period[i] > limitStartWeek) {
                index = i;
                break;
            }
        }
        return index;
    }

    private static int getLastIndex(int[] period, int index) {
        if (index == 0) {
            return period.length - 1;
        }  else {
            return index - 1;
        }

    }


    private static List<String> schedule(String periodStr, String startDate, int count) {
        LocalDate startLimitDate = LocalDate.parse(startDate);
        int startLimitWeek = startLimitDate.getDayOfWeek().getValue();

        int[] period = parsePeriodArray(periodStr);
        int startIndex = 0;
        int mode = period.length;

        LocalDate firstDate;
        int conIdx = contain(period, startLimitWeek);
        if ( conIdx > -1) {
            firstDate = startLimitDate;
            startIndex = conIdx;
        } else {
            // 起始周数不在周期中, 获取 > 起始周数的 周期
            int nearestIndex = getNearestForwardIndex(period, startLimitWeek);
            int len = period[nearestIndex] - startLimitWeek;

            if (len < 0) {
                len = len + 7;
            }
            firstDate = startLimitDate.plusDays(Long.valueOf(len));
            startIndex = nearestIndex;
        }

        List<LocalDate> localDateList = new ArrayList<>();
        localDateList.add(firstDate);

//        System.out.println((startIndex + 1) % mode);
//        System.out.println(firstDate);

        int cursor = (startIndex + 1) % mode;

        for (int i = 1; i < count; i++) {
             int lastIndex = getLastIndex(period, cursor);
             int len = getPeriodDistant(period[lastIndex], period[cursor]);

             LocalDate localDate = localDateList.get(i - 1).plusDays(Long.valueOf(len));
             localDateList.add(localDate);

             cursor = (cursor + 1) % mode;
        }

        localDateList.forEach(e -> System.out.println(e));

        return null;
    }

    public static void main(String[] args) {
        String periodStr = "1,2";
        String startLimitDate = "2021-01-05";
        int[] period = parsePeriodArray(periodStr);

//        System.out.println(getNearestForwardIndex(period, 5));
        schedule(periodStr, startLimitDate, 10);

    }


}

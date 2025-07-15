package PrefixSum;

import java.util.HashMap;

public class LongestWellPerformingInterval {

    public static int longestWPI(int[] hours) {
        int n = hours.length;
        int maxLength = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                maxLength = i + 1;
            } else {
                if (map.containsKey(sum - 1)) {
                    maxLength = Math.max(maxLength, i - map.get(sum - 1));
                }
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return maxLength;
    }
}

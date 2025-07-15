package PrefixSum;

import java.util.HashMap;

public class SubarrayEqualsK {
    public int maxSubArrayLen(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int ans = -1;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int delta = sum - k;
            if (map.containsKey(delta)) {
                ans = Math.max(ans, i - map.get(delta));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans == -1 ? 0 : ans;
    }

}

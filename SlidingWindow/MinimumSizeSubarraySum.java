package SlidingWindow;

public class MinimumSizeSubarraySum {
    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int sum = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                sum -= nums[l];
                l++;
            }
            if (sum >= target) {
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans == 0x7fffffff ? 0 : ans;
    }

    public static void main(String[] s) {
        int target = 11;
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(minSubArrayLen(target, nums));
    }
}

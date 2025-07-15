package BinarySearch;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long ans = 0;
        for (long l = 0, r = sum, m, cur; l <= r; ) {
            m = (l + r) / 2;
            cur = f(nums, m);
            if (cur <= k) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) ans;
    }

    public static long f(int[] arr, long aim) {
        int parts = 1;
        int sum = 0;
        for (int num : arr) {
            if (num > aim) {
                return Integer.MAX_VALUE;
            }
            if (sum + num > aim) {
                parts++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return parts;
    }
}

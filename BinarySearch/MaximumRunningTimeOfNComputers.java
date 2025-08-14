package BinarySearch;

public class MaximumRunningTimeOfNComputers {
    public static long maxRunningTime1(int num, int[] arr) {
        long sum = 0;
        for (int x : arr) {
            sum += x;
        }
        long m = 0;
        for (long l = 0, r = sum; l <= r; ) {
            m = (l + r) / 2;
            if (f(num, arr, m)) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return m;
    }

    public static boolean f(int num, int[] arr, long time) {
        long sum = 0;
        for (int x : arr) {
            if (x >= time) {
                num--;
            } else {
                sum += x;
            }
            if (sum >= (long) time * num) {
                return true;
            }
        }
        return false;
    }
}

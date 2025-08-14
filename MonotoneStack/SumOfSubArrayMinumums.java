package MonotoneStack;

public class SumOfSubArrayMinumums {

    public int sumSubarrayMins(int[] arr) {
        int mod = 1_000_000_007, n = arr.length;
        long ans = 0;
        int stackLen = 0;
        int[] stack = new int[n + 2];
        stack[stackLen++] = -1;
        for (int i = 0; i <= n; i++) {
            int e = i < n ? arr[i] : -1;
            while (stackLen > 1 && arr[stack[stackLen - 1]] >= e) {
                int idx = stack[--stackLen];
                ans += (long) arr[idx] * (idx - stack[stackLen - 1]) * (i - idx);
            }
            stack[stackLen++] = i;
        }
        return (int) (ans % mod);
    }

}

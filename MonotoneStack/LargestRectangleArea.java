package MonotoneStack;

public class LargestRectangleArea {

    public int[] stack;

    public int largestRectangleArea(int[] height) {
        int n = height.length;
        stack = new int[n];
        int r = 0;
        int ans = 0, cur, left;
        for (int i = 0; i < n; i++) {
            while (r > 0 && height[i] <= height[stack[r - 1]]) {
                cur = stack[--r];
                left = r == 0 ? -1 : stack[r - 1];
                ans = Math.max(ans, height[cur] * (i - left - 1));
            }
            stack[r++] = i;
        }
        while (r > 0) {
            cur = stack[--r];
            left = r == 0 ? -1 : stack[r - 1];
            ans = Math.max(ans, height[cur] * (n - left - 1));
        }
        return ans;
    }
}

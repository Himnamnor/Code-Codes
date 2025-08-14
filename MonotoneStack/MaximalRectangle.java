package MonotoneStack;

import java.util.Arrays;

public class MaximalRectangle {
    public static int MAXN = 201;
    public static int[] height = new int[MAXN];
    public static int[] stack = new int[MAXN];
    public static int r;

    public static int maximalRectangle(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Arrays.fill(height, 0, m, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                height[j] = grid[i][j] == '0' ? 0 : height[j] + 1;
            }
            ans = Math.max(largestRectangleArea(m), ans);
        }
        return ans;
    }

    public static int largestRectangleArea(int n) {
        r = 0;
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

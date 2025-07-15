package DoublePointer;

public class TrappingRainWater {
    public int trap1(int[] height) {
        int n = height.length;
        int[] lMax = new int[n];
        int[] rMax = new int[n];
        lMax[0] = height[0];
        rMax[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            lMax[i] = Math.max(lMax[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], height[i]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            int l = lMax[i];
            int r = rMax[i];
            ans += Math.max(Math.min(l, r) - height[i], 0);
        }
        return ans;
    }

    public int trap(int[] height) {
        int n = height.length;
        int l = 1, r = n - 2;
        int lMax = height[0], rMax = height[n - 1];
        int ans = 0;
        while (l <= r) {
            if (lMax < rMax) {
                ans += Math.max(lMax - height[l], 0);
                lMax = Math.max(height[l++], lMax);
            } else {
                ans += Math.max(rMax - height[r], 0);
                rMax = Math.max(rMax, height[r--]);
            }
        }
        return ans;
    }
}

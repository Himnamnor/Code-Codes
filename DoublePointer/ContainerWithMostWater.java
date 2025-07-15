package DoublePointer;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxVolume = 0, curVolume = 0;
        int l = 0, r = height.length - 1;
        while (l <= r) {
            int h = Math.min(height[l], height[r]);
            curVolume = (r - l) * h;
            maxVolume = Math.max(maxVolume, curVolume);
            while (l < height.length && height[l] <= h) l++;
            while (r >= 0 && height[r] <= h) r--;
        }
        return maxVolume;
    }
}

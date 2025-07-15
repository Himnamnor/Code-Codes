package BinarySearch;

public class KokoEatingBananas {
    public static int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = 0;
        for (int pile : piles) {
            r = Math.max(r, pile);
        }
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (judge(piles, mid, h)) {
                r = mid - 1;
                ans = mid;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    public static boolean judge(int[] arr, int speed, int h) {
        int i = 0;
        for (int elem : arr) {
            i += (elem + speed - 1) / speed;
        }
        if (i > h) return false;
        else return true;
    }
}

package SlidingWindow;

//leetcode 76
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        int[] remainingTimes = new int[256];
        for (int i = 0; i < t.length(); i++) {
            remainingTimes[t.charAt(i)]++;
        }
        int remaining = t.length();
        int minl = 0, minlen = 0x7fffffff;
        for (int l = 0, r = 0; r < s.length(); r++) {
            if (remainingTimes[s.charAt(r)]-- > 0) {
                remaining--;
            }
            if (remaining == 0) {
                while (remainingTimes[s.charAt(l)] < 0) {
                    remainingTimes[s.charAt(l++)]++;
                }
                if (r - l + 1 < minlen) {
                    minlen = r - l + 1;
                    minl = l;
                }
            }
        }
        return minlen == 0x7fffffff ? "" : s.substring(minl, minl + minlen);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}

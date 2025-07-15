package SlidingWindow;

import java.util.Arrays;

public class LongestNoneRepeatSubstring {
//    public static int lengthOfLongestSubstring(String s) {
//        int len = s.length();
//        if (len <= 1) return len;
//        int l = 0, r = 1;
//        char lc = s.charAt(l), rc = s.charAt(r);
//        int ans = 0;
//        HashMap<Character, Integer> map = new HashMap<>();
//        map.put(lc, 0);
//        while (l < len) {
//            while (!map.containsKey(rc)) {
//                map.put(rc, r);
//                r++;
//                if (r < len) rc = s.charAt(r);
//                else break;
//            }
//            ans = Math.max(ans, r - l);
//            if (r == len) {
//                break;
//            }
//            int index = map.get(rc);
//            while (l <= index) {
//                l++;
//                map.remove(lc);
//                lc = s.charAt(l);
//            }
//        }
//        return ans;
//    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = 0;
        int ans = 0;
        int[] lastIndex = new int[256];
        Arrays.fill(lastIndex, -1);
        for (l = 0, r = 0; r < s.length(); r++) {
            l = Math.max(l, lastIndex[chars[r]] + 1);
            ans = Math.max(r - l + 1, ans);
            lastIndex[chars[r]] = r;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "dvdf";
        System.out.println(lengthOfLongestSubstring(s));
    }
}

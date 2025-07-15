package SlidingWindow;

//leetcode 1234
public class ReplaceSubstringForBalancedString {
    public static int balancedString(String str) {
        int n = str.length();
        int[] s = new int[n];
        int[] cnts = new int[4];
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            s[i] = c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0));
            cnts[s[i]]++;
        }
        int debt = 0;
        for (int i = 0; i < 4; i++) {
            if (cnts[i] < n / 4) {
                cnts[i] = 0;
            } else {
                cnts[i] = n / 4 - cnts[i];
                debt -= cnts[i];
            }
        }
        if (debt == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < n; r++) {
            if (cnts[s[r]]++ < 0) {
                debt--;
                
            }
            if (debt == 0) {
                while (cnts[s[l]] > 0) {
                    cnts[s[l++]]--;
                }
                ans = Math.min(ans, r - l + 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "QQWE";
        System.out.println(balancedString(s));
    }

}

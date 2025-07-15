package PrefixSum;

import java.util.Arrays;

public class EvenCountsLongestSubarray {
    public int longestEven(String s) {
        int curStatus = 0;
        int[] status = new int[32];
        Arrays.fill(status, -2);
        status[0] = -1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int vowel = isVowel(s.charAt(i));
            if (vowel != -1) {
                curStatus ^= 1 << vowel;
            }
            if (status[curStatus] != -2) {
                ans = Math.max(ans, i - status[curStatus]);
            } else {
                status[curStatus] = i;
            }
        }
        return ans;
    }

    private int isVowel(char c) {
        switch (c) {
            case 'a':
                return 0;
            case 'e':
                return 1;
            case 'i':
                return 2;
            case 'o':
                return 3;
            case 'u':
                return 4;
            default:
                return -1;
        }
    }

}

package DoublePointer;

import java.util.Arrays;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int l = 0, r = people.length - 1;
        int ans = 0;
        while (l <= r) {
            if (l == r) {
                ans += 1;
                break;
            }
            int weight = people[l] + people[r];
            if (weight > limit) {
                r--;
            } else {
                l++;
                r--;
            }
            ans += 1;
        }
        return ans;
    }
}

package DoublePointer;

import java.util.Arrays;

public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int minRadius = 0;
        int housePtr, heaterPtr = 0;
        for (housePtr = 0; housePtr < houses.length; housePtr++) {
            while (!best(houses, heaters, housePtr, heaterPtr)) heaterPtr++;
            minRadius = Math.max(Math.abs(heaters[heaterPtr] - houses[housePtr]), minRadius);
        }
        return minRadius;
    }

    private boolean best(int[] houses, int[] heaters, int houseP, int heaterP) {
        return heaterP + 1 == heaters.length ||
                Math.abs(houses[houseP] - heaters[heaterP]) < Math.abs(houses[houseP] - heaters[heaterP + 1]);
    }

}

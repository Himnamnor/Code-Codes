package PrefixSum;

import java.util.HashMap;

public class MakeSumDivisiablByP {

    public int minSubarray(int[] nums, int p) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);   //一个元素都没有的情况，模是0

        int targetMod = 0;
        int currentMod = 0;
        int ans = 0x7fffffff;

        for (int i = 0; i < nums.length; i++) {
            targetMod = (targetMod + nums[i]) % p;

        }

        if (targetMod == 0) return 0; //如果已经是p的倍数了，直接返回0

        for (int i = 0; i < nums.length; i++) {
            currentMod = (currentMod + nums[i]) % p;
            int wanted = currentMod >= targetMod ? currentMod - targetMod : p + currentMod - targetMod;
            if (map.containsKey(wanted)) {
                ans = Math.min(ans, i - map.get(wanted));
            }

            map.put(currentMod, i);
        }
        return ans >= nums.length ? -1 : ans;
    }
}

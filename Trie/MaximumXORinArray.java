package Trie;

public class MaximumXORinArray {

    public static int MAXN = 100000;
    public static int[][] tree = new int[MAXN][2];
    public static int all;

    public static void init() {
        all = 0;
        for (int i = 0; i < MAXN; i++) {
            tree[i][0] = tree[i][1] = 0;
        }
    }

    public static void insert(int x) {
        int cur = 0;
        for (int i = 31; i >= 0; i--) {
            int path = (x >> i) & 1;
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++all;
            }
            cur = tree[cur][path];
        }
    }

    public static int query(int x) {
        int cur = 0;
        int ret = 0;
        for (int i = 31; i >= 0; i--) {
            int path = (x >> i) & 1;
            int want = path ^ 1;
            if (tree[cur][want] != 0) {
                ret |= (1 << i);
                cur = tree[cur][want];
            } else {
                cur = tree[cur][path];
            }
        }
        return ret;
    }

    public static int findMaxXOR(int[] nums) {
        init();
        insert(nums[0]);
        int maxXor = 0;
        for (int i = 1; i < nums.length; i++) {
            maxXor = Math.max(maxXor, query(nums[i]));
            insert(nums[i]);
        }
        return maxXor;
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(findMaxXOR(nums)); // Output: 28
    }
}

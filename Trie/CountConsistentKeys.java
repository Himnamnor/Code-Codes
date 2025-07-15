package Trie;

import java.util.Arrays;

public class CountConsistentKeys {
    public static int MAXN = 200000;
    public static int[][] tree = new int[MAXN][12];
    //public static int[] end = new int[MAXN];
    public static int[] pass = new int[MAXN];

    public static int getPath(char c) {
        if (c == '#') {
            return 11;
        } else if (c == '-') {
            return 10;
        } else {
            return c - '0';
        }
    }

    public static int all;

    public static void init() {
        all = 0;
    }

    public static void insert(String word) {
        int cur = 0;
        pass[cur]++;
        for (int i = 0; i < word.length(); i++) {
            int path = getPath(word.charAt(i));
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++all;
            }
            cur = tree[cur][path];
        }
        //end[cur]++;
    }

    public static int search(String word) {
        int cur = 0;
        for (int i = 0; i < word.length(); i++) {
            int path = getPath(word.charAt(i));
            if (tree[cur][path] == 0) {
                return 0;
            }
            cur = tree[cur][path];
        }
        return pass[cur];
    }

    public static int[] countConsistentKeys(int[][] a, int[][] b) {
        init();
        StringBuilder builder = new StringBuilder();
        for (int[] arr : a) {
            builder.setLength(0);
            for (int i = 1; i < arr.length; i++) {
                int delta = arr[i] - arr[i - 1];
                builder.append(delta + "#");
            }
            insert(builder.toString());
        }

        int[] ans = new int[b.length];

        for (int i = 0; i < b.length; i++) {
            builder.setLength(0);
            for (int j = 1; j < b[i].length; j++) {
                int delta = b[i][j] - b[i][j - 1];
                builder.append(delta + "#");
            }
            ans[i] = search(builder.toString());
        }
        return ans;
    }

    public static void clear() {
        for (int i = 0; i < all; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

}

package Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearchII {

    public static int maxN = 100000;
    public static int[][] tree = new int[maxN][26];
    public static int[] pass = new int[maxN];
    public static String[] end = new String[maxN];
    public static int all;

    public static void init() {
        all = 0;
        for (int i = 0; i < maxN; i++) {
            Arrays.fill(tree[i], 0);
            pass[i] = 0;
        }
    }

    public static void insert(String word) {
        int cur = 0;
        pass[cur]++;
        for (int i = 0, path; i < word.length(); i++) {
            path = word.charAt(i) - 'a';
            if (tree[cur][path] == 0) {
                tree[cur][path] = ++all;
            }
            cur = tree[cur][path];
            pass[cur]++;
        }
        end[cur] = word;
    }

    public static void build(String[] words) {
        init();
        for (String word : words) {
            insert(word);
        }
    }

    public static boolean overBoard(int x, int y, int n, int m) {
        return x < 0 || y < 0 || x >= n || y >= m;
    }

    public static int dfs(char[][] board, int x, int y, int t, List<String> ans) {
        if (overBoard(x, y, board.length, board[0].length)) {
            return 0;
        }
        char tmp = board[x][y];
        int path = tmp - 'a';
        t = tree[t][path];
        if (pass[t] == 0) {
            return 0;
        }
        int collectNum = 0;
        if (end[t] != null) {
            collectNum++;
            ans.add(end[t]);
            end[t] = null;
        }
        board[x][y] = 0;
        collectNum += dfs(board, x, y + 1, t, ans);
        collectNum += dfs(board, x, y - 1, t, ans);
        collectNum += dfs(board, x + 1, y, t, ans);
        collectNum += dfs(board, x - 1, y, t, ans);
        pass[t] -= collectNum;
        board[x][y] = tmp;
        return collectNum;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        build(words);
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, 1, ans);
            }
        }
        return ans;
    }
}

package Trie;

import java.util.Arrays;
import java.util.HashMap;

public class Trie {

    class Trie_Normal {

        class Node {
            public int pass;
            public int end;
            public Node[] nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new Node[26];
            }
        }

        private Node root;

        public Trie_Normal() {
            root = new Node();
        }

        public void insert(String word) {
            Node current = root;
            current.pass++;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (current.nexts[index] == null) {
                    current.nexts[index] = new Node();
                }
                current = current.nexts[index];
                current.pass++;
            }
            current.end++;
        }

        public int countWordsEqualTo(String word) {
            Node cur = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (cur.nexts[index] == null) {
                    return 0;
                }
                cur = cur.nexts[index];
            }
            return cur.end;
        }

        public int countWordsStartingWith(String prefix) {
            Node cur = root;
            for (char c : prefix.toCharArray()) {
                int index = c - 'a';
                if (cur.nexts[index] == null) {
                    return 0;
                }
                cur = cur.nexts[index];
            }
            return cur.pass;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                Node cur = root;
                cur.pass--;
                for (char c : word.toCharArray()) {
                    int index = c - 'a';
                    if (--cur.nexts[index].pass == 0) {
                        cur.nexts[index] = null;
                        return;
                    }
                    cur = cur.nexts[index];
                }
                cur.end--;
            }
        }
    }

    class Trie_Hash {
        class Node {
            public int pass;
            public int end;
            HashMap<Integer, Node> nexts;

            public Node() {
                pass = 0;
                end = 0;
                nexts = new HashMap<>();
            }
        }

        private Node root;

        public Trie_Hash() {
            root = new Node();
        }

        public void insert(String word) {
            Node cur = root;
            cur.pass++;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i);
                if (!cur.nexts.containsKey(path)) {
                    cur.nexts.put(path, new Node());
                }
                cur = cur.nexts.get(path);
                cur.pass++;
            }
            cur.end++;
        }

        public int countWordsEqualTo(String word) {
            Node cur = root;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i);
                if (!cur.nexts.containsKey(path)) {
                    return 0;
                }
                cur = cur.nexts.get(path);
            }
            return cur.end;
        }

        public int countWordsStartingWith(String prefix) {
            Node cur = root;
            for (int i = 0, path; i < prefix.length(); i++) {
                path = prefix.charAt(i);
                if (!cur.nexts.containsKey(path)) {
                    return 0;
                }
                cur = cur.nexts.get(path);
            }
            return cur.pass;
        }

        public void erase(String word) {
            if (countWordsEqualTo(word) > 0) {
                Node cur = root;
                cur.pass--;
                for (int i = 0, path; i < word.length(); i++) {
                    path = word.charAt(i);
                    if (--cur.nexts.get(path).pass == 0) {
                        cur.nexts.remove(path);
                        return;
                    }
                    cur = cur.nexts.get(path);
                }
                cur.end--;
            }
        }
    }

    class Trie_Static {
        public static int MAXN = 10000;
        public static int[][] tree = new int[MAXN][26];
        public static int[] end = new int[MAXN];
        public static int[] pass = new int[MAXN];
        public static int cnt;


        public static void build() {
            cnt = 1;
        }

        public static void insert(String word) {
            int cur = 1;
            pass[cur]++;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    tree[cur][path] = ++cnt;
                }
                cur = tree[cur][path];
                pass[cur]++;
            }
            end[cur]++;
        }

        public static int search(String word) {
            int cur = 1;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    return cur;
                }
                cur = tree[cur][path];
            }
            return end[cur];
        }

        public static int predixNumber(String word) {
            int cur = 1;
            for (int i = 0, path; i < word.length(); i++) {
                path = word.charAt(i) - 'a';
                if (tree[cur][path] == 0) {
                    return 0;
                }
                cur = tree[cur][path];
            }
            return pass[cur];
        }

        public static void delete(String word) {
            if (search(word) > 0) {
                int cur = 1;
                pass[cur]--;
                for (int i = 0, path; i < word.length(); i++) {
                    path = word.charAt(i) - 'a';
                    if (--pass[tree[cur][path]] == 0) {
                        tree[cur][path] = 0;
                        return;
                    }
                    cur = tree[cur][path];
                }
                end[cur]--;
            }
        }

        public static void clear() {
            for (int i = 1; i <= cnt; i++) {
                Arrays.fill(tree[i], 0);
                end[i] = 0;
                pass[i] = 0;
            }
        }
    }


}

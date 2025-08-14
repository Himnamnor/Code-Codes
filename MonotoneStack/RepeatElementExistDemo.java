package MonotoneStack;

import java.io.*;

public class RepeatElementExistDemo {
    public static int MAXN = 1000001;
    public static int[] arr = new int[MAXN];
    public static int[] stack = new int[MAXN];
    public static int[][] ans = new int[MAXN][2];
    public static int n, r;

    public static void compute() {
        r = 0;
        int cur;
        for (int i = 0; i < n; i++) {
            while (r > 0 && arr[stack[r - 1]] >= arr[i]) {
                cur = stack[--r];
                ans[cur][0] = r > 0 ? stack[r - 1] : -1;
                ans[cur][1] = i;
            }
            stack[r++] = i;
        }
        while (r > 0) {
            cur = stack[--r];
            ans[cur][0] = r > 0 ? stack[r - 1] : -1;
            ans[cur][1] = -1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ans[i][1] != -1 && arr[ans[i][1]] == arr[i]) {
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(System.out));
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) streamTokenizer.nval;
            for (int i = 0; i < n; i++) {
                streamTokenizer.nextToken();
                arr[i] = (int) streamTokenizer.nval;
            }
            compute();
            for (int i = 0; i < n; i++) {
                printWriter.println(ans[i][0] + " " + ans[i][1]);
            }
        }
        printWriter.flush();
        printWriter.close();
        bufferedReader.close();
    }


}

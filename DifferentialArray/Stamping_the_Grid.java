package DifferentialArray;

public class Stamping_the_Grid {

    public static int[][] diff;


    public static int getValue(int[][] arr, int i, int j) {
        if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length) {
            return 0;
        }
        return arr[i][j];
    }

    public static void build(int[][] arr, int n, int m) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                arr[i][j] += getValue(arr, i - 1, j) + getValue(arr, i, j - 1) - getValue(arr, i - 1, j - 1);
            }
        }
    }

    public static int sumRegion(int[][] arr, int a, int b, int c, int d) {
        return arr[c][d] - getValue(arr, a - 1, d) - getValue(arr, c, b - 1) + getValue(arr, a - 1, b - 1);
    }

    public static void add(int[][] arr, int i, int j, int stampHeight, int stampWidth, int value) {
        arr[i][j] += value;
        arr[i + stampHeight][j + stampWidth] += value;
        arr[i + stampHeight][j] -= value;
        arr[i][j + stampWidth] -= value;
    }

    public static boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] sum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum[i + 1][j + 1] = grid[i][j];
            }
        }
        build(sum, n, m);

        diff = new int[n + 2][m + 2];
        for (int a = 1, c = a + stampHeight - 1; c <= n; a++, c++) {
            for (int b = 1, d = b + stampWidth - 1; d <= m; b++, d++) {
                if (sumRegion(sum, a, b, c, d) == 0) {
                    add(diff, a, b, stampHeight, stampWidth, 1);
                }
            }
        }
        build(diff, n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && diff[i + 1][j + 1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}

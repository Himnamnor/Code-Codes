package DifferentialArray;

import java.util.Arrays;

//leetcode lcp74
public class StrongestBlessingField {

    public static int fieldOfGreatestBlessing(int[][] forceField) {
        int fieldNum = forceField.length;
        long[] xArr = new long[fieldNum << 1];
        long[] yArr = new long[fieldNum << 1];
        for (int i = 0, xPtr = 0, yPtr = 0; i < fieldNum; i++) {
            long r = forceField[i][2];
            xArr[xPtr++] = (forceField[i][0] << 1) - r;
            xArr[xPtr++] = (forceField[i][0] << 1) + r;
            yArr[yPtr++] = (forceField[i][1] << 1) - r;
            yArr[yPtr++] = (forceField[i][1] << 1) + r;
        }
        int xLen = sort(xArr);
//        System.out.println(Arrays.toString(xArr));
//        System.out.println(xLen);
        int yLen = sort(yArr);
//        System.out.println(Arrays.toString(yArr));
//        System.out.println(yLen);
        int[][] diff = new int[xLen + 2][yLen + 2];
        for (int i = 0; i < fieldNum; i++) {
            long x = forceField[i][0] << 1;
            long y = forceField[i][1] << 1;
            int r = forceField[i][2];
            int a = getIndex(xArr, xLen, x - r);
            int c = getIndex(xArr, xLen, x + r);
            int b = getIndex(yArr, yLen, y - r);
            int d = getIndex(yArr, yLen, y + r);
            add(diff, a, b, c, d);
        }
        int ans = 0;
        for (int i = 1; i < diff.length; i++) {
            for (int j = 1; j < diff[0].length; j++) {
                diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1];
                ans = Math.max(ans, diff[i][j]);
            }
        }
        return ans;
    }

    //    public static int sort(long[] arr) {
//        Arrays.sort(arr);
//        int slow = 0, fast = 1;
//        while (fast < arr.length) {
//            if (arr[fast] != arr[slow]) {
//                arr[++slow] = arr[fast];
//            }
//            fast++;
//        }
//        return slow + 1;
//    }
    public static int sort(long[] nums) {
        Arrays.sort(nums);
        int size = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[size - 1]) {
                nums[size++] = nums[i];
            }
        }
        return size;
    }


    public static int getIndex(long[] arr, int len, long num) {
        int l = 0, r = len - 1;
        int mid = 0, ans = 0;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (arr[mid] >= num) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans + 1;
    }

    public static void add(int[][] diff, int a, int b, int c, int d) {
        diff[a][b]++;
        diff[c + 1][d + 1]++;
        diff[a][d + 1]--;
        diff[c + 1][b]--;
    }

    public static void build(int[][] diff) {
        int n = diff.length;
        int m = diff[0].length;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += -diff[i - 1][j - 1] + diff[i - 1][j] + diff[i][j - 1];
            }
        }
    }

    public static void main(String[] args) {
        int[][] forceField = {{7, 7, 9}, {7, 5, 3}, {1, 8, 5}, {5, 6, 3}, {9, 10, 2}, {8, 4, 10}};
        System.out.println(fieldOfGreatestBlessing(forceField));
    }
}

package DoublePointer;

public class FirstMissingPositive {
    public static void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }

    public int firstMissingPositive(int[] arr) {
        int l = 0, r = arr.length;
        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[arr[l] - 1] == arr[l]) {
                swap(arr, l, --r);
            } else {
                swap(arr, l, arr[l] - 1);
            }
        }
        return l + 1;
    }
}

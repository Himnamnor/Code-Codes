package MonotoneStack;

public class DailyTemperatures {

    int[] nextIndex;
    int[] stack;

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        nextIndex = new int[n];
        stack = new int[n];
        compute(temperatures);
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = nextIndex[i] == -1 ? 0 : nextIndex[i] - i;
        }
        return ans;
    }

    private void compute(int[] temperatures) {
        int top = 0;
        int cur;
        int n = temperatures.length;
        for (int i = 0; i < n; i++) {
            while (top > 0 && temperatures[i] >= temperatures[stack[top - 1]]) {
                cur = stack[--top];
                nextIndex[cur] = i;
            }
            stack[top++] = i;
        }
        while (top > 0) {
            cur = stack[--top];
            nextIndex[cur] = -1;
        }
        for (int i = n - 2; i >= 0; i--) {
            if (nextIndex[i] != -1 && temperatures[nextIndex[i]] == temperatures[i]) {
                nextIndex[i] = nextIndex[nextIndex[i]];
            }
        }
    }
}

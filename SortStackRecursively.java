import java.util.Stack;

public class SortStackRecursively {

    public static int getStackDepth(Stack<Integer> stack) {
        if (stack.empty()) return 0;
        Integer top = stack.pop();
        int depth = 1 + getStackDepth(stack);
        stack.push(top);
        return depth;
    }

    public static int getMax(int level, Stack<Integer> stack) {
        if (level == 0) return Integer.MIN_VALUE;
        Integer top = stack.pop();
        int ret = Math.max(top, getMax(level - 1, stack));
        stack.push(top);
        return ret;
    }

    public static int maxValueCounter(int level, Stack<Integer> stack, int maxValue) {
        if (level == 0) return 0;
        int top = stack.pop();
        int ret = maxValueCounter(level - 1, stack, maxValue) + top == maxValue ? 1 : 0;
        stack.push(top);
        return ret;
    }

    public static void down(Stack<Integer> stack, int level, int maxValue, int maxCount) {
        if (level == 0) {
            for (int i = 0; i < maxCount; i++) {
                stack.push(maxValue);
            }
            return;
        }
        int top = stack.pop();
        down(stack, level - 1, maxValue, maxCount);
        if (top != maxValue) {
            stack.push(maxValue);
        }
    }

    public static void sortStack(Stack<Integer> stack) {
        int depth = getStackDepth(stack);
        while (depth > 0) {
            int maxValue = getMax(depth, stack);
            int maxCount = maxValueCounter(depth, stack, maxValue);
            down(stack, depth, maxValue, maxCount);
            depth -= maxCount;
        }
    }
}

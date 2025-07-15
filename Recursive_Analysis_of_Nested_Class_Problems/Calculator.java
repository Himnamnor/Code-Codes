package Recursive_Analysis_of_Nested_Class_Problems;

import java.util.ArrayList;

public class Calculator {

    public static int calculate(String str) {
        where = 0;
        return f(str.toCharArray(), 0);
    }

    public static int where;

    //从s[i]开始计算，遇到字符串终止或者右括号停止
    //返回值：自己负责的这一段计算的结果
    //更新where，使上游函数知道从哪里继续
    public static int f(char[] s, int i) {
        int cur = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        while (i < s.length && s[i] != ')') {
            if (s[i] >= '0' && s[i] <= '9') {
                cur = cur * 10 + s[i++] - '0';
            } else if (s[i] != '(') {
                push(numbers, operators, cur, s[i++]);
                cur = 0;
            } else {
                cur = f(s, i + 1);
                i = where + 1;
            }
        }
        push(numbers, operators, cur, '+');
        where = i;
        return compute(numbers, operators);
    }

    public static void push(ArrayList<Integer> numbers, ArrayList<Character> operators, int cur, char op) {
        int n = numbers.size();
        if (n == 0 || operators.get(n - 1) == '+' || operators.get(n - 1) == '-') {
            numbers.add(cur);
            operators.add(op);
        } else {
            int topNumber = numbers.get(n - 1);
            char topOperator = operators.get(topNumber);
            if (topOperator == '*') {
                numbers.set(n - 1, topNumber * cur);
            } else {
                numbers.set(n - 1, topNumber / cur);
            }
            operators.set(n - 1, op);
        }
    }

    public static int compute(ArrayList<Integer> numbers, ArrayList<Character> operators) {
        int n = numbers.size();
        int ans = numbers.get(0);
        for (int i = 1; i < n; i++) {
            ans += operators.get(i - 1) == '+' ? numbers.get(i) : -numbers.get(i);
        }
        return ans;
    }
}

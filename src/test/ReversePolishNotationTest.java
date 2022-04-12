package test;

import linear.Stack;

/**
 * 计算逆波兰表达式的字符串数组形式的数据的结果
 */
public class ReversePolishNotationTest {

    //逐个将数字入栈，遇到运算符号时再依次出栈参与运算，并将运算结果入栈，以此类推
    private static int calculate(String[] notation) {
        Stack<Integer> stack = new Stack<>();  //创建只保存整数类型的栈
        Integer o1;
        Integer o2;
        Integer result = null;

        for (String curr : notation) {
            switch (curr) {
                case "+" -> {
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 + o1;
                    stack.push(result);
                }
                case "-" -> {
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 - o1;
                    stack.push(result);
                }
                case "*" -> {
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 * o1;
                    stack.push(result);
                }
                case "/" -> {
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 / o1;
                    stack.push(result);
                }
                default -> stack.push(Integer.valueOf(curr)); //将数字入栈
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] mathStr = {"4", "17", "15", "-", "*", "18", "6", "/", "+"};
        int result = calculate(mathStr);
        System.out.println(result);
    }
}

package test;

import linear.Stack;

/**
 * 括号匹配以及内部字符串提取
 */
public class BraketMatchTest {

    public static void main(String[] args) {
        String matchedStr = "(中国(上海自由(贸易区)在浦江(法华路133号)))";
        String[] matched = isMatched(matchedStr);
        for (String s : matched) {
            System.out.println(s);
        }
    }

    //将左括号全部入栈，每遇到一个右括号弹栈进行判断
    private static String[] isMatched(String text) {
        boolean isMatched = true;
        String[] result = new String[2];    //存放是否匹配和匹配的字符串
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        for (int i = 0; i < text.length(); i++) {
            String str = text.charAt(i) + "";
            if (str.equals("(")) {
                stack.push(str);
                stack1.push(i);
            } else if (str.equals(")")) {
                String pop = stack.pop();
                if (pop != null) {
                    Integer popIndex = stack1.pop();    //对应左括号的数组索引值
                    sb.append(text, popIndex + 1, i).append(",");   //将这对括号内的字符串进行保存
                } else {
                    isMatched = false;
                    break;
                }
            }
        }

        //判断左括号是否多余
        if (isMatched) {
            if (stack.size() != 0) {
                isMatched = false;
            }
        }

        result[0] = isMatched + "";
        result[1] = sb.toString();
        return result;
    }
}

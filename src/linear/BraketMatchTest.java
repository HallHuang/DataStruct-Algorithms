package linear;

public class BraketMatchTest {

    public static void main(String[] args) {

        String matchedStr = "(中国(上海自由(贸易区)在浦江))";
        String[] matched = isMatched(matchedStr);
        System.out.println(matched[0]);
        System.out.println(matched[1]);
    }

    private static String[] isMatched(String text) {
        boolean isMatched = true;
        String[] result = new String[2];
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
                    Integer popIndex = stack1.pop();
                    if (null != popIndex) {
                        sb.append(text.subSequence(popIndex + 1, i)).append(",");
                    }
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

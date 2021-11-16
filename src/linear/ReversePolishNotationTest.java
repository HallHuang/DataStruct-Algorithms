package linear;

public class ReversePolishNotationTest {

    public static void main(String[] args) {
        String[] mathStr = {"4", "17", "15", "-", "*", "18", "6", "/", "+"};
        int result = calculate(mathStr);
        System.out.println(result);

    }

    private static int calculate(String[] notation) {
        Stack<Integer> stack = new Stack<>();
        Integer o1;
        Integer o2;
        Integer result = null;

        for (int i = 0; i < notation.length; i++) {
            String curr = notation[i];
            switch (curr) {
                case "+":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 + o1;
                    stack.push(result);
                    break;
                case "-":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 - o1;
                    stack.push(result);
                    break;
                case "*":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 * o1;
                    stack.push(result);
                    break;
                case "/":
                    o1 = stack.pop();
                    o2 = stack.pop();
                    result = o2 / o1;
                    stack.push(result);
                    break;
                default:
                    stack.push(Integer.parseInt(curr));
            }
        }
        return result;
    }
}

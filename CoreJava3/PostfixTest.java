public class PostfixTest {
    public static void main(String[] args) {
        test("3 + 5", "3 5 +", 8.0);
        test("3 + 5 * 2", "3 5 2 * +", 13.0);
        test("( 1 + 2 ) * 3", "1 2 + 3 *", 9.0);
        test("10 / ( 5 - 3 )", "10 5 3 - /", 5.0);
        test("5 + 3 * ( 8 - 6 )", "5 3 8 6 - * +", 11.0);
    }

    public static void test(String infix, String expectedPostfix, Double expectedResult) {
        String resultPostfix = Postfix.infixToPostfix(infix);
        Double resultEvaluation = Postfix.evaluatePostfix(resultPostfix);

        System.out.println("Infix: " + infix);
        System.out.println("Expected Postfix: " + expectedPostfix);
        System.out.println("Result Postfix: " + resultPostfix);
        System.out.println("Expected Result: " + expectedResult);
        System.out.println("Calculated Result: " + resultEvaluation);
        System.out.println(resultPostfix.equals(expectedPostfix) && resultEvaluation.equals(expectedResult) ? "✅ Test Passed" : "❌ Test Failed");
        System.out.println();
    }
}

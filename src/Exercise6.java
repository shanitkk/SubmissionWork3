public class Exercise6 {

    final static int THE_VALUE_OF_ZERO = 48;
    final static int THE_VALUE_OF_NINE = 57;
    final static int THE_VALUE_OF_PLUS = 43;
    final static int THE_VALUE_OF_MINUS = 45;
    final static int THE_VALUE_OF_MULTIPLICATION = 42;
    final static int THE_VALUE_OF_DIVISION = 58;
    final static int THE_VALUE_OF_STRONG = 94;
    final static int THE_VALUE_OF_BRACKET_OPEN = 40;
    final static int THE_VALUE_OF_BRACKET_CLOSE = 41;

    public static void main(String[] args) {

        String aa = "(2+6+9)";
        if (checkIfThereAreClosures(aa))
            //if (checkLegal(aa) == true)
            System.out.println("The expression is correct");
        else
            System.out.println("The expression is not correct");
    }

    public static boolean checkLegal(String expression) {
        boolean legal = true;
        char a;
        char b;
        char c;
        for (int i = 0; i < expression.length() - 2; i++) {
            a = expression.charAt(i);
            b = expression.charAt(i + 1);
            c = expression.charAt(i + 2);

            if (expression.length() % 2 == 0) {
                legal = false;
            }

            if ((a >= THE_VALUE_OF_ZERO && a <= THE_VALUE_OF_NINE) && CheckMarkType(b)
                    && (c >= THE_VALUE_OF_ZERO && c <= THE_VALUE_OF_NINE)) {
                i = i + 1;
            } else {
                legal = false;
            }
        }
        return legal;
    }

    public static boolean checkIfThereAreClosures(String expression) {
        boolean legal = false;
        int counter = 0;
        String partOfTheExpression = "";
        if (expression.charAt(0) == THE_VALUE_OF_BRACKET_OPEN) {
            for (int i = 1; i < expression.length() - 1; i++) {
                partOfTheExpression += expression.charAt(i);
            }
            //  System.out.println(str);
            for (int j = 1; j < expression.length(); j++) {
                if (expression.charAt(j) != THE_VALUE_OF_BRACKET_CLOSE) {
                    counter++;
                }
                if (checkLegal(partOfTheExpression) == true && expression.charAt(counter + 1) == 41) {
                    legal = true;
                }
            }
            //   System.out.println(counter);
        }
        return legal;
    }

    public static boolean CheckMarkType(char mark) {
        boolean markType = false;
        if (mark == THE_VALUE_OF_MULTIPLICATION || mark == THE_VALUE_OF_PLUS ||
                mark == THE_VALUE_OF_MINUS || mark == THE_VALUE_OF_DIVISION || mark == THE_VALUE_OF_STRONG) {
            markType = true;
        }
        return markType;
    }
}

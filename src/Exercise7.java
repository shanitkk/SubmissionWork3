import java.util.Locale;
import java.util.Scanner;

public class Exercise7 {
    final static String ONLY_NUMBERS = "0123456789";
    final static int MIN_QUADRATIC_EQUATION_LENGTH = 7;
    final static String QUADRATIC_EQUATION_PART_A = "x^2";
    final static char QUADRATIC_EQUATION_PART_B = 'x';
    final static String QUADRATIC_EQUATION_LAST_PART = "=0";
    final static int AMOUNT_OF_X = 2;
    final static int SIZE_ARRAY_OF_ABC = 3;
    final static char MINUS = '-';
    final static char PLUS = '+';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Quadratic equation in the following form: ax^2+bx+c=0");
        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        userInput = changeStrWithoutSpacing(userInput);

        if (userInput.length() >= MIN_QUADRATIC_EQUATION_LENGTH && hasTwoXInStr(userInput)) {
            int[] arrayOfIndex = findIndexXInStr(userInput);
            startCheckQuadraticEquation(userInput, arrayOfIndex);
        } else
            System.out.println("Invalid value");
    }

    public static String changeStrWithoutSpacing(String userInput) {
        String strWithoutSpacing = "";
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) != ' ')
                strWithoutSpacing += userInput.charAt(i);
        }
        return strWithoutSpacing;
    }

    public static boolean hasTwoXInStr(String userInput) {
        boolean hasTwoX = false;
        int counterX = 0;
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == QUADRATIC_EQUATION_PART_B)
                counterX++;
        }
        if (counterX == AMOUNT_OF_X)
            hasTwoX = true;
        return hasTwoX;

    }

    public static int[] findIndexXInStr(String userInput) {
        userInput = changeStrWithoutSpacing(userInput.toLowerCase(Locale.ROOT));
        int indexFirstX = -1;
        int indexSecondX = -1;
        int[] arrayIndexOfX = new int[AMOUNT_OF_X];
        for (int i = 0; i < userInput.length(); i++) {
            if (userInput.charAt(i) == QUADRATIC_EQUATION_PART_B) {
                indexFirstX = i;
                break;
            }
        }
        for (int i = indexFirstX + 1; i < userInput.length(); i++) {
            if (userInput.charAt(i) == QUADRATIC_EQUATION_PART_B)
                indexSecondX = i;
        }

        arrayIndexOfX[0] = indexFirstX;
        arrayIndexOfX[1] = indexSecondX;
        //   System.out.println("מיקום ה-X הראשון" + indexFirstX);
        // System.out.println("מיקום ה-X השני" + indexSecondX);
        return arrayIndexOfX;
    }

    public static String[] takeFactorsFromStr(String userInput, int[] arrayOfIndexX) {
        userInput = changeStrWithoutSpacing(userInput);
        String[] arrayOfFactors = new String[SIZE_ARRAY_OF_ABC];
        for (int i = 0; i < arrayOfFactors.length; i++) {
            arrayOfFactors[i] = "";
        }

        for (int i = 0; i < arrayOfIndexX[0]; i++) {
            arrayOfFactors[0] += userInput.charAt(i);
        }
        for (int j = arrayOfIndexX[0] + 3; j < arrayOfIndexX[1]; j++) {
            arrayOfFactors[1] += userInput.charAt(j);
        }
        for (int k = arrayOfIndexX[1] + 1; k < userInput.length() - QUADRATIC_EQUATION_LAST_PART.length(); k++) {
            arrayOfFactors[2] += userInput.charAt(k);
        }

        for (int i = 0; i < arrayOfFactors.length - 1; i++) {
            if (arrayOfFactors[i].length() == 1 && arrayOfFactors[i].charAt(0) == MINUS)
                arrayOfFactors[i] = "-1";
            else if (arrayOfFactors[i].length() == 1 && arrayOfFactors[i].charAt(0) == PLUS || arrayOfFactors[i] == "")
                arrayOfFactors[i] = "+1";

        }
        if (arrayOfFactors[2] == "") {
            arrayOfFactors[2] = "0";
        }
        return arrayOfFactors;
    }

    public static boolean isOnlyNumbers(String factor) {
        boolean onlyNumbers = false;
        int from = 0;
        int counterDigit = 0;
        if (factor.charAt(0) == PLUS || factor.charAt(0) == MINUS) {
            counterDigit++;
            from++;
        }
        for (int j = from; j < factor.length(); j++) {
            for (int i = 0; i < ONLY_NUMBERS.length(); i++) {
                if (factor.charAt(j) == ONLY_NUMBERS.charAt(i))
                    counterDigit++;
            }
        }

        if (counterDigit == factor.length())
            onlyNumbers = true;
        //   System.out.println(onlyNumbers);
        if (factor.length() == 1 && factor.charAt(0) == '0')
            onlyNumbers = true;
        return onlyNumbers;
    }

    public static boolean isSameString(String str1, String str2) {
        boolean equals = true;
        if (str1.length() == str2.length()) {
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    equals = false;
                    break;
                }
            }
        } else
            equals = false;
        return equals;
    }

    public static void startCheckQuadraticEquation(String userInput, int[] arrayOfIndex) {
        String[] arrayOfFactors = takeFactorsFromStr(userInput, arrayOfIndex);
        String a = arrayOfFactors[0];
        String b = arrayOfFactors[1];
        String c = arrayOfFactors[2];

        String partOneOfQuadratic = "";
        String partThreeOfQuadratic = "";
        if (isOnlyNumbers(a) && isOnlyNumbers(b) && isOnlyNumbers(c)) {

            int intA = Integer.parseInt(a);
            int intB = Integer.parseInt(b);
            int intC = Integer.parseInt(c);
            // System.out.println("a: " + intA + "  b: " + intB + " c:  " + intC);
            for (int i = arrayOfIndex[0]; i < arrayOfIndex[0] + 3; i++) {
                partOneOfQuadratic += userInput.charAt(i);
                partOneOfQuadratic = partOneOfQuadratic.toLowerCase(Locale.ROOT);
            }
            // System.out.println(partAOfQuadraticEquation);

            for (int i = userInput.length() - 2; i < userInput.length(); i++) {
                partThreeOfQuadratic += userInput.charAt(i);
                partThreeOfQuadratic = partThreeOfQuadratic.toLowerCase(Locale.ROOT);
            }
            // System.out.println(partThreeOfQuadratic);
            if (isSameString(QUADRATIC_EQUATION_PART_A, partOneOfQuadratic) &&
                    isSameString(QUADRATIC_EQUATION_LAST_PART, partThreeOfQuadratic)) {
                quadraticEquation(intA, intB, intC);
            } else
                System.out.println("Invalid value");
        } else
            System.out.println("Invalid value");
    }

    public static void quadraticEquation(int a, int b, int c) {
        double newB = b * b;
        double root = newB - 4 * a * c;
        double denominator = 2 * a;
        if (root < 0.0D) {
            System.out.println("No solution to a negative root");
        } else if (root == 0) {
            newB = b * -1;
            System.out.println("There is " + newB / denominator);
        } else {
            root = Math.sqrt(root);
            newB = b * -1;
            System.out.println("There is 2 answer: ");
            double x1 = (newB + root) / denominator;
            double x2 = (newB - root) / denominator;
            System.out.println("x1 = " + x1 + "  , x2 = " + x2);
        }
    }
}
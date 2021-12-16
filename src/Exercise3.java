public class Exercise3 {
    public static void main(String[] args) {
        int number = 100;
        int[] array = componentsOfTheNumber(number);
        System.out.println("The decomposition of the number " + number + " is: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " , ");
        }
    }

    public static int amountDecompositionsInNumber(int number) {
        int counterDecompositionsInNumber = 0;
        int i = 2;
        while (number != 1) {
            if (number % i == 0) {
                number = number / i;
                counterDecompositionsInNumber++;
            } else
                i++;
        }
        return counterDecompositionsInNumber;
    }

    public static int[] componentsOfTheNumber(int number) {
        int[] componentsArray = new int[amountDecompositionsInNumber(number)];
        int indexArray = 0;
        int i = 2;
        while (number != 1) {
            if (number % i == 0) {
                number = number / i;
                componentsArray[indexArray] = i;
                indexArray++;
            } else
                i++;
        }
        return componentsArray;
    }
}
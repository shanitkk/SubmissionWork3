import java.util.Scanner;

public class Exercise2 {

    public static void main(String[] args) {
        int[] arrayOne = {5, 16, 3, 4, 306};
        int[] arrayTwo = {111, 102, 22, 71};
        System.out.println(superProximityLevel(arrayOne, arrayTwo));
        //superProximityLevel(arrayOne,arrayTwo);
    }

    public static int checkDigit(int numberToCheck) {
        int counter = 0;
        int numberOfDigit = numberToCheck;
        while (numberOfDigit != 0) {
            numberOfDigit = numberOfDigit / 10;
            counter++;
        }
        return counter;
    }

    public static int[] prepareNumber(int sizeArray, int inputNumber) {
        int[] numbersArray = new int[sizeArray];
        for (int i = 0; i < sizeArray; i++) {
            numbersArray[i] = inputNumber % 10;
            inputNumber = inputNumber / 10;
        }
        return numbersArray;
    }

    public static int sumArray(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    public static boolean checkIfBrothers(int number1, int number2) {
        boolean ifAreBrothers = false;
        int sum1 = 0;
        int sum2 = 0;
        int[] arrayOfNumberOne = prepareNumber(checkDigit(number1), number1);
        int[] arrayOfNumberTwo = prepareNumber(checkDigit(number2), number2);
        sum1 = sumArray(arrayOfNumberOne);
        sum2 = sumArray(arrayOfNumberTwo);
        if (sum1 == sum2) {
            ifAreBrothers = true;
        }
        return ifAreBrothers;
    }

    public static int checkProximityLevel(int inputNumber, int[] inputArray) {
        int counter = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if (checkIfBrothers(inputArray[i], inputNumber) == true) {
                counter++;
            }
        }
        return counter;
    }

    public static int superProximityLevel(int[] arr1, int[] arr2) {
        int index = 0;
        int[] proximityLevelArray = new int[arr1.length];
        for (int j = 0; j < arr1.length; j++) {
            proximityLevelArray[j] = checkProximityLevel(arr1[j], arr2);
        }
        for (int k = 0; k < arr1.length; k++) {
            if (proximityLevelArray[index] < proximityLevelArray[k]) {
                index = k;
            }
            //System.out.println(index);
        }
        return index;
    }
}


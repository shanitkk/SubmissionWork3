import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;
        do {
            System.out.println("Enter a positive number: ");
            number = scanner.nextInt();
        } while (number < 0);

        boolean change = isChangingNumbers(number);
        System.out.println("Does the number change?  " + change);

        int[] array2 = {218, 123, 44, 32,87};
        int index = indexSmallestChangeNumbers(array2);
        if (index >= 0) {
            System.out.println("The index of smallest  " + index);
        }
        else
            System.out.println("Not found.");
    }


    public static int counterDigInNum(int number) {
        int counter = 0;
        while (number > 0) {
            number = number / 10;
            counter++;
        }
        return counter;
    }

    public static int[] changeNumToArray(int number) {
        int counter = counterDigInNum(number);
        int[] array = new int[counter];

        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = number % 10;
            number = number / 10;
        }
        return array;
    }

    public static boolean isChangingNumbers(int number) {
        int[] array = changeNumToArray(number);
        boolean changeNumbers = true;
        int sum;
        for (int i = 0; i < array.length - 1; i++) {
            sum = array[i] + array[i + 1];
            if (sum % 2 == 0)
                changeNumbers = false;
        }
        return changeNumbers;
    }

    public static int indexSmallestChangeNumbers(int[] numbers) {
        int index = -1;
        int smallestSum = -1;

        for (int i = 0; i< numbers.length; i++) {
            if (isChangingNumbers(numbers[i])) {
                if (smallestSum == -1) {
                    smallestSum = sumChangesNumbers(numbers[i]);
                    index = i;
                } else if (sumChangesNumbers(numbers[i]) < smallestSum) {
                    smallestSum = sumChangesNumbers(numbers[i]);
                    index = i;
                }
            }
        }
        return index;
    }

    public static int sumChangesNumbers(int number) {
        int[] numArray = changeNumToArray(number);
        int sum = 0;
        for (int i = 0; i < numArray.length; i++) {
            sum = sum + numArray[i];
        }
        return sum;
    }
}

import java.util.Random;
import java.util.Scanner;

public class Exercise9 {

    final static int CODE_LENGTH = 4;
    final static int TEN = 10;
    final static int MIN_DIGIT_CODE = 1;
    final static int MAX_DIGIT_CODE = 6;
    final static int RANDOM_CODE_RANGE_BEGINNING = 1000;
    final static int RANDOM_CODE_MAX_RANGE = 5543;
    final static int FIRST_OPTION_ROUTE = 1;
    final static int SECOND_OPTION_ROUTE = 2;
    final static int THIRD_OPTION_ROUTE = 3;
    final static int LAST_OPTION_ROUTE = 4;
    final static int EASY_ROUTE = 20;
    final static int MIDDLE_ROUTE = 15;
    final static int HARD_ROUTE = 10;
    final static int RANDOM_ROUTE_MIN_CHANCES = 5;
    final static int RANDOM_ROUTE_MAX_CHANCES = 20;
    final static int PENALTY_GUESS = 2;
    final static int LIMIT_OF_CHANCES = 0;

    /*
    changeCodeToArray(int code) // עזר - משנה את המספר למערך
    checksDigitInRange(int valueInArray) // בודק את הספרות בטווח
    ifDigitShowMoreThenOnce(int code) // אם הספרה מופיעה יותר מפעם אחת
    returnRandomNumbInRange() // מחזירה את המספר שהוגרל
    getGuessFromUser() // ניחוש של המשתמש
    checkCode(int guess) // בודק את הניחוש ומדפיס בהתאם
    route(int userChoose) // סבב ניחושים
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoose;
        do {
            System.out.println("You have 4 route, press to choose: " +
                    " (1) for 20 chances, (2) for 15 chances, (3) for 10 chances, (4) surprising route: could be 5-25 chances");
            userChoose = scanner.nextInt();
        } while (userChoose < FIRST_OPTION_ROUTE || userChoose > LAST_OPTION_ROUTE);
        route(userChoose);
    }

    public static int counterDig(int userGuess) {
        int counter = 0;
        while (userGuess > 0) {
            userGuess = userGuess / 10;
            counter++;
        }
        return counter;
    }

    public static int[] changeCodeToArray(int code) {
        int[] arrayCode = new int[CODE_LENGTH];
        for (int i = arrayCode.length - 1; i >= 0; i--) {
            arrayCode[i] = code % TEN;
            code = code / TEN;
        }
        return arrayCode;
    }

    public static boolean checksDigitInRange(int valueInArray) {
        boolean isInRange = true;
        int[] arrayRandomNumber = changeCodeToArray(valueInArray);
        for (int i = 0; i < arrayRandomNumber.length; i++) {
            if (arrayRandomNumber[i] < MIN_DIGIT_CODE || arrayRandomNumber[i] > MAX_DIGIT_CODE) {
                isInRange = false;
                break;
            }
        }
        return isInRange;
    }

    public static boolean ifDigitShowMoreThenOnce(int code) {
        boolean digitShowMoreThenOnce = false;
        int[] arrayCode = changeCodeToArray(code);
        for (int j = 0; j < arrayCode.length - 1; j++) {
            for (int i = j; i < arrayCode.length - 1; i++) {
                if (arrayCode[j] == arrayCode[i + 1]) {
                    digitShowMoreThenOnce = true;
                }
            }
        }
        return digitShowMoreThenOnce;
    }

    public static int returnRandomNumbInRange() {
        Random random = new Random();
        int randomNum;
        do {
            randomNum = random.nextInt(RANDOM_CODE_MAX_RANGE) + RANDOM_CODE_RANGE_BEGINNING;
        } while (!checksDigitInRange(randomNum) || ifDigitShowMoreThenOnce(randomNum));
        return randomNum;
    }

    public static int getGuessFromUser() {
        Scanner scanner = new Scanner(System.in);
        int guess;
        do {
            System.out.println(" The rules are: \n*Length code must be 4,\n*The digits must be 1-6 ,\n*Every digit must show only once  ,\nEnter your guess: ");
            guess = scanner.nextInt();
        } while (counterDig(guess) > CODE_LENGTH || !checksDigitInRange(guess));
        return guess;
    }

    public static int checkCode(int guess, int randomCode) {
        int[] changeRandomCodeToArray = changeCodeToArray(randomCode);
        int[] changeUserGuessToArray = changeCodeToArray(guess);
        int counterExactlyGuesses = 0;
        int counterPartialGuesses = 0;
        for (int j = 0; j < changeRandomCodeToArray.length; j++) {
            for (int i = 0; i < changeUserGuessToArray.length; i++) {
                if (changeRandomCodeToArray[j] == changeUserGuessToArray[i]) {
                    if (i == j)
                        counterExactlyGuesses++;
                    else
                        counterPartialGuesses++;
                }
            }
        }
        System.out.println("You have " + counterExactlyGuesses + " exactly guesses.");
        System.out.println("You have " + counterPartialGuesses + " partial guesses (right number but not in right index.)");
        return counterExactlyGuesses;
    }

    public static void route(int userChoose) {
        int chances = 0;
        Random random = new Random();
        int randomCode = returnRandomNumbInRange();
        System.out.println(randomCode);
        int guessFromUser;
        boolean digitShowMoreThenOnce;
        int counterExactlyGuesses;
        if (userChoose == FIRST_OPTION_ROUTE) {
            chances = EASY_ROUTE;
        } else if (userChoose == SECOND_OPTION_ROUTE) {
            chances = MIDDLE_ROUTE;
        } else if (userChoose == THIRD_OPTION_ROUTE) {
            chances = HARD_ROUTE;
        } else {
            int randomRoute = random.nextInt(RANDOM_ROUTE_MAX_CHANCES) + RANDOM_ROUTE_MIN_CHANCES;
            chances = randomRoute;
        }

        for (int i = chances; i > LIMIT_OF_CHANCES; i--) {
            if (userChoose != LAST_OPTION_ROUTE) {
                System.out.println("\nYou have " + i + " chances");
            }
            guessFromUser = getGuessFromUser();

            digitShowMoreThenOnce = ifDigitShowMoreThenOnce(guessFromUser);
            counterExactlyGuesses = checkCode(guessFromUser, randomCode);
            if (digitShowMoreThenOnce) {
                i -= PENALTY_GUESS;
                System.out.println("you can't choose same digit twice, you have 2 chances less");
            }
            if (counterExactlyGuesses == CODE_LENGTH) {
                System.out.println("You win! the random code is: " + randomCode);
                break;
            }
            if (i <= LIMIT_OF_CHANCES) {
                System.out.println("You lose, the code is " + randomCode);
            }
        }
    }
}
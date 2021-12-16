import java.util.Scanner;

public class Exercise5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String newString;
        boolean hasMoreTheOneLetter = strHasLetters(str);
        if (!hasMoreTheOneLetter) {
            System.out.println("There are not enough characters to replace the code " +
                    "\n The code has not changed " + str);
        } else {
            newString = changeLettersInStr(str);
            System.out.println(" the new code is: " + newString);
        }
    }

    public static int counterOfTimesTheLetterAppears(String str, char letter) {
        int counterLetter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == letter)
                counterLetter++;
        }
        return counterLetter;
    }

    public static char returnLetterWithHighestAppearance(String str) {
        int indexLetter = 0;
        int letterTimes;
        char letterWithHighestAppearance;
        do { // במידה והתווים הראשוניים חהם רווח
            letterTimes = counterOfTimesTheLetterAppears(str, str.charAt(indexLetter));
            letterWithHighestAppearance = str.charAt(indexLetter);
            indexLetter++;
        } while (letterWithHighestAppearance == ' ' && indexLetter < str.length());

        for (int i = indexLetter; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                if (counterOfTimesTheLetterAppears(str, str.charAt(i)) > letterTimes) {
                    letterTimes = counterOfTimesTheLetterAppears(str, str.charAt(i));
                    letterWithHighestAppearance = str.charAt(i);
                }
            }
        }
        return letterWithHighestAppearance;
    }

    public static String strWithoutHighestLetter(String str, char deleteLetter) {
        String strWithoutLetter = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != deleteLetter)
                strWithoutLetter = strWithoutLetter + str.charAt(i);
        }
        return strWithoutLetter;
    }

    public static String changeLettersInStr(String strToChange) {
        char firstHighestLetter = returnLetterWithHighestAppearance(strToChange);
        //System.out.println("the highest letter is - " + firstHighestLetter);

        String strWithoutLetter = strWithoutHighestLetter(strToChange, firstHighestLetter);
        //System.out.println("the new string is - " + strWithoutLetter);

        char secondHighestLetter = returnLetterWithHighestAppearance(strWithoutLetter);
        //System.out.println(" the second highest letter is - " + secondHighestLetter);

        String newString = "";
        int nextCharInStr = 0;
        for (int i = 0; i < strToChange.length(); i++) {
            if (strToChange.charAt(i) == firstHighestLetter) {
                newString = newString + secondHighestLetter;
                nextCharInStr++;
            } else if (strToChange.charAt(i) == secondHighestLetter) {
                newString = newString + firstHighestLetter;
                nextCharInStr++;
            } else
                newString = newString + strToChange.charAt(i);
        }
        // System.out.println("  " + newString);
        return newString;
    }

    public static boolean strHasLetters(String str) { // האם יש יותר מסוג אחד של אותיות
        boolean hasLetter = false;
        int counterLetters = 0;
        char letter = str.charAt(0);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != letter && str.charAt(i) != ' ')
                counterLetters++;
        }
        if (counterLetters > 0)
            hasLetter = true;

        return hasLetter;
    }
}
public class Exercise4 {

    public static void main(String[] args) {
        int[] array = {11,13,12};
        System.out.print("this array:  ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " , ");
        }
        boolean arrayDescending = isArrayWithDescendingNumbers(array);
        if (arrayDescending)
            System.out.println("\n Is full array");
        else
        System.out.println("\n is not a full array");
    }

    public static int biggestIndexNumInArray(int[] array, int indexFrom) { // מציאת הערך הכי גבוה ממיקום מסויים
        int biggestNum = array[indexFrom];
        int indexBiggest = indexFrom;
        for (int i = indexFrom + 1; i < array.length; i++) {
            if (array[i] > biggestNum) {
                biggestNum = array[i];
                indexBiggest = i;
            }
        }
        return indexBiggest;
    }

    public static int[] changingArrayToArrayDecreases(int[] array) {  // שינוי מערך למערך יורד
        int indexBiggestNum;
        int saveTheValueOfTheNumInArray;
        for (int i = 0; i < array.length; i++) {
            {
                indexBiggestNum = biggestIndexNumInArray(array, i);
                saveTheValueOfTheNumInArray = array[i];
                array[i] = array[indexBiggestNum];
                array[indexBiggestNum] = saveTheValueOfTheNumInArray;
            }
        }
        return array;
    }

    public static boolean isArrayWithDescendingNumbers(int[] array) {
        boolean arrayDescending = true;
        array = changingArrayToArrayDecreases(array);
        for (int i = 0; i < array.length - 1; i++) {
            if ((array[i] - array[i + 1]) != 1)
                arrayDescending = false;
        }
        return arrayDescending;
    }
}
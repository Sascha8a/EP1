/*
    Aufgabe 1) Zweidimensionale Arrays - Diverse Methoden
*/

import java.util.Arrays;

public class Aufgabe1 {

    private static int[][] genFilledArray(int n) {
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            int currentValue = i + 1;
            boolean reverse = false;

            for (int j = 0; j < n; j++) {
                result[i][j] = currentValue;

                if (currentValue == n) {
                    reverse = true;
                }

                currentValue = reverse ? currentValue - 1 : currentValue + 1;
            }
        }

        return result;
    }

    private static void shiftLinesInArray(int[][] workArray) {
        int[] lastRow = workArray[workArray.length - 1];

        for (int i = workArray.length - 2; i >= 0; i--) {
            workArray[i + 1] = workArray[i];
        }

        workArray[0] = lastRow;
    }

    private static int[][] extendArray(int[][] inputArray) {
        int maxLength = inputArray[0].length;
        for (int[] row : inputArray) {
            if (maxLength < row.length) {
                maxLength = row.length;
            }
        }

        int[][] result = new int[inputArray.length][maxLength];

        for (int i = 0; i < inputArray.length; i++) {
            int[] current = inputArray[i];
            boolean shift = i % 2 == 0;
            System.arraycopy(current, 0, result[i], shift ? maxLength - current.length : 0, current.length);
        }

        return result;
    }

    private static int[] reformatArray(int[][] inputArray) {
        int[] result = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            int[] current = inputArray[i];
            int acc = 0;

            for (int j = 0; j < current.length; j++) {
                acc += current[j] * Math.pow(2, current.length - j - 1);
            }

            result[i] = acc;
        }

        return result;
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[][] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                for (int j = 0; j < inputArray[i].length; j++) {
                    System.out.print(inputArray[i][j] + "\t");
                }
                System.out.println();
            }
        }
    }

    //Vorgegebene Methode - BITTE NICHT VERÄNDERN!
    private static void printArray(int[] inputArray) {
        if (inputArray != null) {
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[i] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = genFilledArray(2);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2}, {2, 1}}));
        System.out.println();

        array = genFilledArray(4);
        printArray(array);
        assert (Arrays.deepEquals(array, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 3}, {3, 4, 3, 2}, {4, 3, 2, 1}}));
        System.out.println();

        array = genFilledArray(7);
        printArray(array);
        System.out.println();


        int[][] array1 = new int[][]{{1, 3, 5}, {6, 2, 1}, {0, 7, 9}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{0, 7, 9}, {1, 3, 5}, {6, 2, 1}}));
        printArray(array1);
        System.out.println();

        array1 = new int[][]{{1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}, {6, 3, 0}};
        shiftLinesInArray(array1);
        assert (Arrays.deepEquals(array1, new int[][]{{6, 3, 0}, {1, 5, 6, 7}, {1, 9, 3}, {4}, {6, 3, 0, 6, 2}}));
        printArray(array1);
        System.out.println();


        int[][] array2 = new int[][]{{4}, {1, 2, 3}, {5, 6}, {7, 8, 9, 1}};
        int[][] array2new1 = extendArray(array2);
        printArray(array2new1);
        assert (Arrays.deepEquals(array2new1, new int[][]{{0, 0, 0, 4}, {1, 2, 3, 0}, {0, 0, 5, 6}, {7, 8, 9, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1}, {1, 1}, {1, 0, 0, 0}, {1, 1, 0, 1}, {1}, {1}};
        int[][] array2new2 = extendArray(array2);
        printArray(array2new2);
        assert (Arrays.deepEquals(array2new2, new int[][]{{1, 0, 1, 1, 0, 0, 0, 0}, {0, 1, 1, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1}}));
        System.out.println();

        array2 = new int[][]{{1, 3, 2}, {5, 1}, {6, 8, 5, 4}, {9, 4, 1, 9, 2}, {1, 8, 7, 5, 3, 2, 5}, {3}};
        int[][] array2new3 = extendArray(array2);
        printArray(array2new3);
        assert (Arrays.deepEquals(array2new3, new int[][]{{0, 0, 0, 0, 1, 3, 2}, {5, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 6, 8, 5, 4}, {9, 4, 1, 9, 2, 0, 0}, {1, 8, 7, 5, 3, 2, 5}, {3, 0, 0, 0, 0, 0, 0}}));
        System.out.println();


        int[][] array3 = new int[][]{{1, 0, 1, 1}, {0, 1, 1}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 0}, {1, 1, 1, 1, 1}};
        int[] array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{11, 3, 24, 2, 2, 31}));
        System.out.println();

        array3 = array2new2.clone();
        array3new = reformatArray(array3);
        printArray(array3new);
        assert (Arrays.equals(array3new, new int[]{176, 124, 3, 128, 13, 128, 1}));
        System.out.println();
    }
}


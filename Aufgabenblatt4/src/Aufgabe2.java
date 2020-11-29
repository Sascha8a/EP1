import java.util.Arrays;

/*
    Aufgabe 2) Eindimensionale Arrays
*/
public class Aufgabe2 {
    public static void printArray(int[] array, String delimiter) {
        String[] stringArray = Arrays.stream(array)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        System.out.println(String.join(delimiter, stringArray));
    }

    public static void printArray(int[] array) {
        printArray(array, ",");
    }

    public static void main(String[] args) {
        // 5A
        int[] array1 = {1, 4, 7, 0, 3, 6, 2, 8};
        printArray(array1);


        // 5B
        int[] array2 = new int[20];
        for (int i = 0; i < array2.length; i++) {
            int currentValue = (i + 3) * 4;
            if (currentValue % 9 == 0) {
                array2[i] = 0;
            } else {
                array2[i] = currentValue;
            }
        }
        printArray(array2, " ");


        // 5C
        int[] array3 = {4, 8, 1, 5, 2};
        int[] array4 = new int[array3.length + 2];

        array4[0] = 100;
        for (int i = 0; i < array3.length; i++) {
            array4[i+1] = array3[i];
        }
        array4[array4.length-1] = 200;

        printArray(array4, " ");


        // 5D
        int[] array5 = new int[15];
        for (int i = 0; i < array5.length; i++) {
            int currentValue = array5.length - i;
            array5[i] = currentValue;
        }

        {
            System.out.print("while-Schleife: ");
            int i = array5.length - 1;
            while (i >= 0) {
                System.out.print(array5[i] + " ");
                i--;
            }
            System.out.println();
        }


        System.out.print("for-Schleife: ");
        for (int i = array5.length - 1; i >= 0; i--) {
            System.out.print(array5[i] + " ");
        }
        System.out.println();


        // 5E
        int[] array6 = {61, 13, 19, 10, 2, 33, 41, 73, 0, 56, 94, 6, 45, 84, 23};
        int min = array6[0];
        int max = array6[0];
        int avg = 0;

        for (int elem : array6) {
            if (elem < min) {
                min = elem;
            }
            if (elem > max) {
                max = elem;
            }
            avg = avg + elem;
        }
        avg = avg / array6.length;

        printArray(new int[]{min, avg, max}, " ");
    }
}


/*
    Aufgabe 1) Code Analyse - Eindimensionale Arrays
*/
public class Aufgabe1 {
    private static void genArray(int[] filledArray) {
        int value = 5;
        for (int i = 0; i < filledArray.length; i++) {
            filledArray[i] = value;
            value += 5;
        }
    }

    private static void printFilteredArrayContent(int[] workArray) {
        int[] copiedArray = workArray;
        for (int i = 0; i < copiedArray.length; i++) {
            if (copiedArray[i] % 4 == 0) {
                copiedArray[i] = 0;
            }
        }
        printArray(copiedArray);
    }

    private static void genNewArrayContent(int[] workArray) {
        int[] helpArray = new int[15];
        int value = 7;
        for (int i = 0; i < helpArray.length; i++) {
            helpArray[i] = value;
            value += 7;
        }
        workArray = helpArray;
        printArray(workArray);
    }

    private static void printArray(int[] workArray) {
//      for (int i = 0; i < workArray.length; i++) {
        for (int elem : workArray) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] filledArray = new int[15];
        genArray(filledArray);
        printArray(filledArray);

        printFilteredArrayContent(filledArray);
        printArray(filledArray);

        filledArray[0] = 2020;
        printArray(filledArray);

        genNewArrayContent(filledArray);
        printArray(filledArray);
    }

    //**************************************************************************
    //**** Notizen und Fragebeantwortungen bitte hier unterhalb durchführen! ***
    //**************************************************************************
    //Frage 1:
    // i <= array.length ====> Index out of range
    // i--               ====> Infinite Loop
    //Frage 2:
    // Java übergibt Objekte per Value, in diesem Fall ist jedoch das Value eine Referenz auf ein Array.
    //Frage 3:
    // In diesem Fall wird wieder das Array kopiert, jedoch ist das Array eine Referenz...
    // Also haben wir eine Referenz auf das Original Array.
    // (Kein Deep Copy)
    //Frage 4:
    // Es wird eine KOPIE des Arrays übergeben.
    // Zur Erinnerung: Das Array ist eine Referenz.
    // Wird nun workArray geändert, wird die kopie verändert, nicht das original.

    // Zusatzfragen:
    // Frage 1:
    // Arrays must be indexed by int values; short, byte, or char values may also be used as index values because they are subjected to unary numeric promotion (§5.6.1) and become int values.
    // An attempt to access an array component with a long index value results in a compile-time error.
    // All array accesses are checked at run time; an attempt to use an index that is less than zero or greater than or equal to the length of the array causes an ArrayIndexOutOfBoundsException to be thrown (§15.10.4).

    // Frage 2:
    // Nein

    // Frage 3:
    // Gar nicht.
    // => Deep Copy, Variable Length Array, etc.

    // Frage 4:
    // System.arraycopy(...);
    // Arrays.copyOf(...);
    // For loop und Kopie der Elemente

    // Frage 5:
    // Ja

    // Frage 6:
    // Die Adressen werden verglichen.
    // Ist es das SELBE Array (aka. NICHT das GLEICHE)
}

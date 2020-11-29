/*
    Aufgabe 3) Rekursion
*/
public class Aufgabe3 {
    private static void printNumbersAscending(int start, int end, int divider) {
        if (start <= end) {
            if (start % divider == 0) {
                System.out.println(start);
            }
            printNumbersAscending(start + 1, end, divider);
        }
    }

    private static void printNumbersDescending(int start, int end, int divider) {
        if (start <= end) {
            printNumbersDescending(start + 1, end, divider);

            if (start % divider != 0) {
                System.out.println(start);
            }
        }
    }

    private static int calcCrossSum(int number) {
        if (number > 0) {
            int quotient = (int) Math.floor(number / 10);
            int rest = number % 10;

            return calcCrossSum(quotient) + rest;
        } else {
            return 0;
        }
    }

    private static String duplicateLetterInString(String text, char letter) {
        if (text.length() > 0) {
            if (text.charAt(0) == letter) {
                return Character.toString(letter) + Character.toString(letter) + duplicateLetterInString(text.substring(1), letter);
            } else {
                return text.charAt(0) + duplicateLetterInString(text.substring(1), letter);
            }
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        printNumbersAscending(10, 20, 2);
        System.out.println();
        printNumbersDescending(5, 15, 3);
        System.out.println();

        System.out.println(calcCrossSum(1));
        System.out.println(calcCrossSum(102));
        System.out.println(calcCrossSum(1234));
        System.out.println(calcCrossSum(10000));
        System.out.println(calcCrossSum(93842));
        System.out.println(calcCrossSum(875943789));
        assert (calcCrossSum(1) == 1);
        assert (calcCrossSum(102) == 3);
        assert (calcCrossSum(1234) == 10);
        assert (calcCrossSum(10000) == 1);
        assert (calcCrossSum(93842) == 26);
        assert (calcCrossSum(875943789) == 60);
        System.out.println();

        System.out.println(duplicateLetterInString("hallo", 'a'));
        System.out.println(duplicateLetterInString("Es ist die Erde", 'e'));
        System.out.println(duplicateLetterInString("3HALLO4", 'L'));
        System.out.println(duplicateLetterInString("a1b2c3d4e5", 'g'));
        assert (duplicateLetterInString("hallo", 'a').equals("haallo"));
        assert (duplicateLetterInString("Es ist die Erde", 'e').equals("Es ist diee Erdee"));
        assert (duplicateLetterInString("3HALLO4", 'L').equals("3HALLLLO4"));
        assert (duplicateLetterInString("a1b2c3d4e5", 'g').equals("a1b2c3d4e5"));
    }
}


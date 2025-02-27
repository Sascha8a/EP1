/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {
    private static void addSeparator(String text, char separator) {
        for (int i = 0; i < text.length() - 1; i++) {
            System.out.print(text.charAt(i));
            System.out.print(separator);
        }
        System.out.println(text.charAt(text.length() - 1));
    }

    private static void addSeparator(int number, char separator) {
        addSeparator(Integer.toString(number), separator);
    }

    private static void addSeparator(String text, String separators) {
        for (char separator : separators.toCharArray()) {
            addSeparator(text, separator);
        }
    }

    private static void addSeparator(String text) {
        addSeparator(text, '$');
    }

    public static void main(String[] args) {
        String text0 = "A";
        String text1 = "AB";
        String text2 = "Hello!";
        String text3 = "-Java-";
        String text4 = " TEST ";

        addSeparator(text0, '?');
        addSeparator(text1, ',');
        addSeparator(text2, ':');
        addSeparator(text3, '-');
        addSeparator(text4, '+');

        addSeparator(1, '$');
        addSeparator(35, '*');
        addSeparator(657, ':');
        addSeparator(2048, '#');
        addSeparator(26348, '+');

        addSeparator(text1, "+#$");
        addSeparator(text2, ":*&!");

        addSeparator(text0);
        addSeparator(text1);
        addSeparator(text2);
    }
}

/*
    Aufgabe 2) Überladen von Methoden
*/
public class Aufgabe2 {

    private static void addSeparator(String text, char separator) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void addSeparator(int number, char separator) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void addSeparator(String text, String separators) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
    }

    private static void addSeparator(String text) {
        //TODO: Implementieren Sie hier Ihre Lösung für die Angabe
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

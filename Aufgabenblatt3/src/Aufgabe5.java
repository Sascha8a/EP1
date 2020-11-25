import java.awt.*;

/*
    Aufgabe 5) Kreuzmuster mit Rechtecken => Rekursiv vs. Iterativ
*/
public class Aufgabe5 {

    private static void drawPatternRecursive(int x, int y, int l, boolean c) {
        if (l >= 16) {
            drawPatternRecursive(x - l / 4, y + l / 4, l / 2, !c);
            drawPatternRecursive(x + l / 4, y + l / 4, l / 2, !c);
            drawPatternRecursive(x + l / 4, y - l / 4, l / 2, !c);
            drawPatternRecursive(x - l / 4, y - l / 4, l / 2, !c);

            if (c) {
                StdDraw.setPenColor(Color.ORANGE);
            } else {
                StdDraw.setPenColor(Color.BLUE);
            }

            double shortSide = l * 0.05;

            StdDraw.filledRectangle(x, y, l / 2.0, shortSide / 2.0); // Horizontal Line
            StdDraw.filledRectangle(x, y, shortSide / 2.0, l / 2.0); // Vertical Line
        }
    }

    private static void drawPatternIterative(int width) {
        int stepsize = 8;
        Boolean isOrange = false;

        while (stepsize < 512) {
            if (isOrange) {
                StdDraw.setPenColor(Color.ORANGE);
            } else {
                StdDraw.setPenColor(Color.BLUE);
            }

            for (int i = 1; i < width / stepsize; i++) {
                StdDraw.filledRectangle(i * stepsize, width / 2, stepsize * 0.05, width / 2.0); // Horizontal Line
                StdDraw.filledRectangle(width / 2, i * stepsize, width / 2, stepsize * 0.05); // Vertical Line
            }

            isOrange = !isOrange;
            stepsize = stepsize * 2;
        }
    }

    public static void main(String[] args) {
        int length = 512;

        StdDraw.setCanvasSize(length, length);
        StdDraw.setXscale(0, length);
        StdDraw.setYscale(0, length);


//        StdDraw. enableDoubleBuffering();
        drawPatternRecursive(length / 2, length / 2, length, true);
//        StdDraw.disableDoubleBuffering();
//        StdDraw.show();

//        StdDraw. enableDoubleBuffering();
        drawPatternIterative(length);
//        StdDraw.disableDoubleBuffering();
//        StdDraw.show();
    }
}



/*
    Aufgabe 4) Rekursion + Zweidimensionale Arrays - primitive Landschaftssimulation
*/

import java.awt.*;

public class Aufgabe4 {

    private static final int canvasSize = 800;

    private static Color[][] genLandscape(int size) {
        Color[][] result = new Color[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                result[y][x] = Math.random() < 0.1 ? Color.GRAY : Color.GREEN;
            }
        }

        return result;
    }

    private static void drawLandscape(Color[][] landscape) {
        int pixelHeight = canvasSize / landscape.length;
        int pixelWidth = canvasSize / landscape[0].length;

        for (int y = 0; y < landscape.length; y++) {
            for (int x = 0; x < landscape[0].length; x++) {
                StdDraw.setPenColor(landscape[y][x]);
                StdDraw.filledRectangle(x * pixelWidth + pixelWidth / 2.0, y * pixelHeight + pixelHeight / 2.0, pixelWidth / 2.0, pixelHeight / 2.0);
            }
        }
    }

    private static void simLiquidFlow(Color[][] landscape, int x, int y) {
        if (y < 0 || y > landscape.length - 1 || x < 0 || x > landscape[y].length - 1) return;

        if (landscape[y][x] == Color.GRAY) {
            landscape[y][x] = Color.BLACK;
            landscape[y + 1][x] = Color.ORANGE;
            simLiquidFlow(landscape, x - 1, y);
            simLiquidFlow(landscape, x + 1, y);
        }

        if (landscape[y][x] == Color.GREEN) {
            landscape[y][x] = Color.ORANGE;
            boolean left = Math.random() < 0.5;

            simLiquidFlow(landscape, left ? x - 1 : x + 1, y - 1);
        }
    }

    private static void simSpreadingFire(Color[][] landscape, int x, int y) {
        if (y < 0 || y > landscape.length - 1 || x < 0 || x > landscape[y].length - 1) return;
        if (landscape[y][x] == Color.BLACK) return;
        if (landscape[y][x] == Color.GRAY) return;
        if (landscape[y][x] == Color.RED) return;
        if (landscape[y][x] == Color.ORANGE) {
            spreadFireInLiquid(landscape, x, y);
            return;
        }

        landscape[y][x] = Color.RED;

        if (Math.random() <= 0.6) simSpreadingFire(landscape, x+1, y);
        if (Math.random() <= 0.6) simSpreadingFire(landscape, x-1, y);
        if (Math.random() <= 0.6) simSpreadingFire(landscape, x, y+1);
        if (Math.random() <= 0.6) simSpreadingFire(landscape, x, y-1);
    }

    private static void spreadFireInLiquid(Color[][] landscape, int x, int y) {
        if (y < 0 || y > landscape.length - 1 || x < 0 || x > landscape[y].length - 1) return;

        if (landscape[y][x] == Color.ORANGE) {
            landscape[y][x] = Color.RED;

            spreadFireInLiquid(landscape, x+1, y);
            spreadFireInLiquid(landscape, x+1, y+1);
            spreadFireInLiquid(landscape, x, y+1);
            spreadFireInLiquid(landscape, x-1, y+1);
            spreadFireInLiquid(landscape, x-1, y);
            spreadFireInLiquid(landscape, x-1, y-1);
            spreadFireInLiquid(landscape, x, y-1);
            spreadFireInLiquid(landscape, x+1, y-1);
        }
    }

    public static void main(String[] args) {
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(0, canvasSize);
        StdDraw.enableDoubleBuffering();

        int size = 100;
        Color[][] landscape = genLandscape(size);

        simLiquidFlow(landscape, size / 2, size - 1);
        drawLandscape(landscape);
        StdDraw.show();
        StdDraw.pause(1000);

        landscape[75][25] = Color.GREEN;
        simSpreadingFire(landscape, 25, 75);
        drawLandscape(landscape);
        StdDraw.show();
    }
}

/*
    Aufgabe 5) Eindimensionale Arrays und File I/O
*/

import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class Aufgabe5 {
    private static String[] readFileData(String fileName, int lineStart, int lineEnd) {
        In fileReader = new In(fileName);
        int numLines = lineEnd - lineStart + 1;
        String[] lines = new String[numLines];

        try {
            // Discard first n lines, where n = lineStart - 1
            for (int i = 0; i < lineStart; i++) {
                fileReader.readLine();
            }

            // Read numLines
            for (int i = 0; i < numLines; i++) {
                lines[i] = fileReader.readLine();
            }
        } finally {
            fileReader.close();
        }

        return lines;
    }

    private static void extractData(String[] dataArray, int[] resultArray, int numColumn, int entriesPerYear) {
        int sumIndex = 0;
        int sum = 0;
        int yearIndex = 0;

        for (String dataRow : dataArray) {
            int dataPoint = Integer.parseInt(dataRow.split(";")[numColumn]);
            sum += dataPoint;
            sumIndex++;

            if (sumIndex == entriesPerYear) {
                resultArray[yearIndex] = sum;
                yearIndex++;
                sum = 0;
                sumIndex = 0;
            }
        }
    }

    private static void drawChart(int[] sunHours) {
        {
            int width = 1275;
            int height = 600;
            StdDraw.setCanvasSize(width, height);
            StdDraw.setXscale(0, width);
            StdDraw.setYscale(0, height);
        }

        Font font = new Font("Times", Font.PLAIN, 10);
        StdDraw.setFont(font);

        for (int i = 0; i < sunHours.length; i++) {
            double width = 15;
            double height = sunHours[i] / 4.0;

            double leftMargin = 30.0;
            double gap = 5.0;

            double x = leftMargin + i * (width + gap) + width / 2.0;
            double y = height / 2;

            int year = i + 55 >= 100 ? i + 55 - 100 : i + 55;

            StdDraw.setPenColor(Color.orange);
            StdDraw.filledRectangle(x, y, width / 2.0, height / 2.0);
            StdDraw.setPenColor(Color.black);
            StdDraw.text(x, 8, String.format("%2d", year));
        }

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        for (int i = 1; i < sunHours.length; i++) {
            double leftMargin = 30.0;
            double gap = 5.0;
            double width = 15.0;

            double x0 = leftMargin + (i-1) * (width + gap) + width / 2.0;
            double y0 = sunHours[i-1] / 4.0;

            double x1 = leftMargin + i * (width + gap) + width / 2.0;
            double y1 = sunHours[i] / 4.0;

            StdDraw.line(x0, y0, x1, y1);
        }

        int min = Arrays.stream(sunHours).min().getAsInt();
        int max = Arrays.stream(sunHours).max().getAsInt();

        StdDraw.setPenRadius(0.002);
        StdDraw.line(30, min / 4.0, 1275 - 30, min / 4.0);
        StdDraw.text(15, min / 4.0, String.format("%d", min));
        StdDraw.text(1275 - 15, min / 4.0, String.format("%d", min));

        StdDraw.line(30, max / 4.0, 1275 - 30, max / 4.0);
        StdDraw.text(15, max / 4.0, String.format("%d", max));
        StdDraw.text(1275 - 15, max / 4.0, String.format("%d", max));

    }

    public static void main(String[] args) {
        String fileName = "weather_data.csv";

        if (!fileName.equals("weather_data.csv")) {
            System.err.printf("File should be named \"weather_data.csv\"");
            return;
        }

        String[] dataArray;
        try {
            dataArray = readFileData("weather_data.csv", 1, 732);
        } catch (Error e) {
            System.err.printf("Error reading file \"%s\"", fileName);
            return;
        }

        if (dataArray.length == 0) {
            System.err.printf("File \"%s\" empty.", fileName);
            return;
        }

        if (dataArray.length != 732) {
            System.err.printf("File \"%s\" should have 732 rows.", fileName);
            return;
        }

        int[] resultArray = new int[61];
        extractData(dataArray, resultArray, 16, 12);
        drawChart(resultArray);
    }
}

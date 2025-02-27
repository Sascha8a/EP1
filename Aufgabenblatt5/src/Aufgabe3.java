/*
    Aufgabe 3) Zweidimensionale Arrays und StdDraw - Bildverarbeitung "Finding Waldo"
*/

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Aufgabe3 {

    // converts RGB image into a grayscale array
    private static int[][] convertImg2Array(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArray = new int[height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[row][col] = (int) (tempColor.getRed() * 0.3 + tempColor.getGreen() * 0.59 + tempColor.getBlue() * 0.11);
            }

        }
        return imgArray;
    }

    // converts RGB image into a 3D color array
    private static int[][][] convertImg2ColorArray(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        int[][][] imgArray = new int[3][height][width];
        Color tempColor;

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                tempColor = new Color(img.getRGB(col, row));
                imgArray[0][row][col] = tempColor.getRed();
                imgArray[1][row][col] = tempColor.getGreen();
                imgArray[2][row][col] = tempColor.getBlue();
            }
        }
        return imgArray;
    }

    //draws the image array specified by color channels imgArrayR, imgArrayG and imgArrayB into the canvas
    private static void drawImage(int[][] imgArrayR, int[][] imgArrayG, int[][] imgArrayB) {
        // draw color image on the StdDraw window
        StdDraw.enableDoubleBuffering();
        for (int y = 0; y < imgArrayR.length; y++) {
            for (int x = 0; x < imgArrayR[y].length; x++) {
                StdDraw.setPenColor(imgArrayR[y][x], imgArrayG[y][x], imgArrayB[y][x]);
                StdDraw.filledSquare(x, imgArrayR.length - y, 0.5);
            }
        }
        StdDraw.show();
        StdDraw.disableDoubleBuffering();
    }

    //detect waldo by template matching and return its bounding box values
    private static int[] detectWaldo(int[][] imgArrayGrayscale, int[][] templateArray) {
        int[] result = new int[4];
        double difference = Integer.MAX_VALUE;

        int imgw = imgArrayGrayscale[0].length;     // imgArrayGrayscale width
        int imgh = imgArrayGrayscale.length;        // imgArrayGrayscale height
        int tw = templateArray[0].length;           // templateArray width
        int th = templateArray.length;               // templateArray height

        for (int imgy = 0; imgy < imgh - th; imgy++) {
            for (int imgx = 0; imgx < imgw - tw; imgx++) {
                int acc = 0;

                for (int ty = 0; ty < th; ty++) {
                    for (int tx = 0; tx < tw; tx++) {
                        acc += Math.abs(imgArrayGrayscale[imgy + ty][imgx + tx] - templateArray[ty][tx]);
                    }
                }

                if (acc < difference) {
                    difference = acc;
                    result[0] = imgx;
                    result[1] = imgy;
                    result[2] = imgx + tw;
                    result[3] = imgy + th;
                }
            }
        }

        return result;
    }

    public static void darkenImgAroundBB(int[][] img, int[] boundingBox) {
        int height = img.length;
        int width = img[0].length;

        int x1 = boundingBox[0];
        int y1 = boundingBox[1];
        int x2 = boundingBox[2];
        int y2 = boundingBox[3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!(y > y1 && y < y2 && x > x1 && x < x2)) {
                    img[y][x] = Math.max(0, img[y][x] - 150);
                }
            }
        }
    }

    public static void main(String[] args) {
        //waldo1
//        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/lht2cy0GFclxbl2/download"; //waldo1.png
//        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/f9onCE9vf89ZYLJ/download"; //template1.png

        //waldo2
//        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/3HYvf4xBkiZUYr1/download"; //waldo2.png
//        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/spG8LoK4x6HqOkf/download"; //template2.png

        //waldo3
        String linkWaldo = "https://owncloud.tuwien.ac.at/index.php/s/9RmCwGkOjgwwkzh/download"; //waldo3.png
        String linkTemplate = "https://owncloud.tuwien.ac.at/index.php/s/CDVrqihS7t9lfvm/download"; //template3.png


        BufferedImage img = null;
        // try to open image file
        try {
            URL url_img_waldo = new URL(linkWaldo);
            img = ImageIO.read(url_img_waldo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        BufferedImage template = null;
        // try to open template image file
        try {
            URL url_img_template = new URL(linkTemplate);
            template = ImageIO.read(url_img_template);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // set StdDraw window size based on the image size
        int width = img.getWidth();
        int height = img.getHeight();
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        //extract color channels R,G,B
        int[][][] imgArray = convertImg2ColorArray(img);
        int[][] imgArrayR = imgArray[0];
        int[][] imgArrayG = imgArray[1];
        int[][] imgArrayB = imgArray[2];
        //convert input image and template image to grayscale, because detection of Waldo works by gray value comparison
        int[][] imgArrayGrayscale = convertImg2Array(img);
        int[][] templateArray = convertImg2Array(template);

        int[] boundingBox = detectWaldo(imgArrayGrayscale, templateArray);

        if (boundingBox != null) {
            darkenImgAroundBB(imgArrayR, boundingBox);
            darkenImgAroundBB(imgArrayG, boundingBox);
            darkenImgAroundBB(imgArrayB, boundingBox);
        }

        drawImage(imgArrayR, imgArrayG, imgArrayB);
    }
}






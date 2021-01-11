/*
    Aufgabe 1) Zweidimensionale Arrays und Gameplay - Sokoban
*/

import java.awt.*;
import java.awt.event.KeyEvent;

public class Aufgabe1 {
    private static final int SQUARE_SIZE = 40;

    private static final char FIELD_EMPTY = ' ';
    private static final char FIELD_WALL = '#';
    private static final char FIELD_GOAL = '.';
    private static final char FIELD_BOX = '$';
    private static final char FIELD_PLAYER = '@';

    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = 2;
    private static final int DIRECTION_LEFT = 3;
    private static final int DIRECTION_RIGHT = 4;

    public static void main(String[] args) {
        String[] allLevels = readLevels();
        int levelId = 0;
        int[][] goals = new int[numberOfGoals(allLevels[levelId])][];
        char[][] level = newLevel(goals, allLevels[levelId]);
        boolean gameRunning = true;
        int moveDirection = 0;
        int stepsLevel = 0;
        int stepsTotal = 0;

        setWindowSize(level.length, level[0].length);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();

        drawGame(level, goals);

        while (gameRunning) {
            // up -> right up
            // down -> left down
            // left -> left up
            // right -> right down
            // restart -> r
            // to next level -> t
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                moveDirection = 1;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDirection = 2;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                moveDirection = 3;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                moveDirection = 4;
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                stepsLevel = 0;
                goals = new int[numberOfGoals(allLevels[levelId])][];
                level = newLevel(goals, allLevels[levelId]);
                drawGame(level, goals);
                StdDraw.pause(200);
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_T)) {
                if (levelId < allLevels.length - 1) { // skip to next level
                    stepsLevel = 0;
                    levelId++;
                    goals = new int[numberOfGoals(allLevels[levelId])][];
                    level = newLevel(goals, allLevels[levelId]);
                    setWindowSize(level.length, level[0].length);
                    drawGame(level, goals);
                    StdDraw.pause(200);
                } else { // end game
                    gameRunning = false;
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                }
            }

            if (moveDirection != 0) {
                if (move(level, moveDirection)) {
                    stepsLevel++;
                }
                moveDirection = 0;
                drawGame(level, goals);
                if (won(level, goals)) {
                    showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "Steps: " + stepsLevel);
                    StdDraw.pause(2000);
                    stepsTotal += stepsLevel;
                    stepsLevel = 0;
                    if (levelId < allLevels.length - 1) { // load next level
                        levelId++;
                        goals = new int[numberOfGoals(allLevels[levelId])][];
                        level = newLevel(goals, allLevels[levelId]);
                        setWindowSize(level.length, level[0].length);
                        drawGame(level, goals);
                    } else { // end game
                        gameRunning = false;
                        showText(level[0].length * SQUARE_SIZE / 2.0, level.length * SQUARE_SIZE / 2.0, "YOU WON!!! Total steps: " + stepsTotal);
                    }
                }
                StdDraw.pause(200);
            }
        }
    }

    // reads levels from file / first line is number of levels
    private static String[] readLevels() {
        In reader = new In("sokoban_level.csv");
        int numberOfLevels = reader.readInt();
        int counter = -1; // starts at -1 because first line is empty after reading int
        String[] levels = new String[numberOfLevels];
        while (!reader.isEmpty()) {
            String line = reader.readLine();
            if (line.isEmpty()) {
                counter++;
                levels[counter] = "";
            } else {
                levels[counter] += line + "\n";
            }
        }
        return levels;
    }

    // returns level as char array and fills goal positions into goals array
    private static char[][] newLevel(int[][] goals, String levelString) {
        // calculate array size
        int xSize = 0;
        int ySize = 0;
        int counter = 0;

        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == '\n') {
                ySize++;
                if (counter > xSize) {
                    xSize = counter;
                }
                counter = 0;
            } else {
                counter++;
            }
        }

        // fill array and goals
        char[][] levelArr = new char[ySize][xSize];
        int goalCounter = 0;
        int x = 0;
        int y = 0;

        for (int i = 0; i < levelString.length(); i++) {
            char item = levelString.charAt(i);
            switch (item) {
                case '.':
                    levelArr[y][x] = ' ';
                    goals[goalCounter] = new int[]{x, y};
                    goalCounter++;
                    x++;
                    break;
                case '\n':
                    y++;
                    x = 0;
                    break;
                case '#':
                case '$':
                case '@':
                case ' ':
                    levelArr[y][x] = item;
                    x++;
                    break;
                default:
                    break;
            }
        }

        return levelArr;
    }

    // returns the total number of goals in the level
    private static int numberOfGoals(String levelString) {
        int acc = 0;

        for (int i = 0; i < levelString.length(); i++) {
            if (levelString.charAt(i) == FIELD_GOAL) {
                acc += 1;
            }
        }

        return acc;
    }

    // returns position of the figure. [0] = x, [1] = y
    private static int[] figurePosition(char[][] level) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                if (level[y][x] == FIELD_PLAYER) {
                    return new int[]{x, y};
                }
            }
        }

        return null;
    }

    private static int[] incrementPosition(int[] pos, int direction, int length) {
        int[] clone = pos.clone();

        switch (direction) {
            case DIRECTION_UP: {
                clone[1] -= length;
                break;
            }
            case DIRECTION_DOWN: {
                clone[1] += length;
                break;
            }
            case DIRECTION_LEFT: {
                clone[0] -= length;
                break;
            }
            case DIRECTION_RIGHT: {
                clone[0] += length;
                break;
            }
        }

        return clone;
    }

    private static boolean isValidPosition(char[][] level, int[] pos) {
        int x = pos[0];
        int y = pos[1];

        return x >= 0 &&
                x < level[y].length &&
                y >= 0 &&
                y < level.length;
    }

    // moves figure and box if they don't hit an obstacle
    // returns true if figure was moved
    private static boolean move(char[][] level, int direction) {
        int[] currentPosition = figurePosition(level);
        if (currentPosition == null) {
            return false;
        }

        int[] newPosition = incrementPosition(currentPosition, direction, 1);
        int[] furtherNewPosition = incrementPosition(currentPosition, direction, 2);
        if (!isValidPosition(level, newPosition)) return false;
        if (!isValidPosition(level, furtherNewPosition)) return false;

        int cpX = currentPosition[0];
        int cpY = currentPosition[1];
        int npX = newPosition[0];
        int npY = newPosition[1];
        int fpX = furtherNewPosition[0];
        int fpY = furtherNewPosition[1];

        if (level[npY][npX] == FIELD_EMPTY) {
            level[cpY][cpX] = FIELD_EMPTY;
            level[npY][cpX] = FIELD_PLAYER;
            return true;
        }

        if (level[npY][npX] == FIELD_BOX && level[fpY][fpX] == FIELD_EMPTY) {
            level[cpY][cpX] = FIELD_EMPTY;
            level[npY][cpX] = FIELD_PLAYER;
            level[fpY][fpX] = FIELD_BOX;
        }

        return false;
    }

    // returns current position of all boxes
    private static int[][] boxPositions(char[][] level, int numberOfBoxes) {
        int[][] boxPositions = new int[numberOfBoxes][2];
        int i = 0;

        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; y++) {
                if (level[y][x] == FIELD_BOX) {
                    boxPositions[i][0] = x;
                    boxPositions[i][1] = y;
                    i += 1;
                }
            }
        }

        return boxPositions;
    }

    // returns true if all boxes are on a goal
    private static boolean won(char[][] level, int[][] goals) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
        return false; //Zeile kann geändert oder entfernt werden.
    }

    // helping method to set the StdDraw window size and the scaling of the axis
    private static void setWindowSize(int ySquares, int xSquares) {
        StdDraw.setCanvasSize(SQUARE_SIZE * xSquares, SQUARE_SIZE * ySquares);
        StdDraw.setXscale(0, SQUARE_SIZE * xSquares);
        StdDraw.setYscale(0, SQUARE_SIZE * ySquares);
    }

    // helping method for writing text in the StdDraw window
    private static void showText(double x, double y, String text) {
        StdDraw.clear(Color.white);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(x, y, text);
        StdDraw.show();
    }

    // draws the current level with all elements
    private static void drawGame(char[][] level, int[][] goals) {
        // TODO: Implementieren Sie hier Ihre Lösung für die Methode
    }
}

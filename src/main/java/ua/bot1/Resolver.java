package ua.bot1;

import java.util.Arrays;
import java.util.Scanner;

public class Resolver {
    private static char symbol;
    private static char[][] matrix = new char[3][3];
    private static char oponent;


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter who is bot(X or 0):");
        symbol = scn.nextLine().toCharArray()[0];
        if (symbol == 'X') {
            oponent = '0';
        } else {
            oponent = 'X';
        }

        init();

        while (true) {
            printMatrix();
            System.out.println("Your step");
            int i = scn.nextInt();
            int j = scn.nextInt();
            int[] process = process(new int[]{i, j});

            if (process[0] == -1) {
                System.out.println("Bot wins");
                return;
            }
        }
    }

    private static void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }


    private static int[] process(int[] params) {
        matrix[params[0]][params[1]] = oponent;
        int win[] = checkWin();
        if (win != null) {
            return win;
        }
        int[] res = checkBadShit();
        if (res != null) {
            return res;
        } else {
            res = setDiagonals();
            if (res != null) {
                return res;
            } else {
                return doStep();
            }
        }

    }

    private static int[] doStep() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == ' ') {
                    matrix[i][j] = symbol;
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private static int[] checkWin() {
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == symbol) {
                    sum++;
                }
            }
            if (sum == 2) {
                return new int[]{-1};
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] == symbol) {
                    sum++;
                }
            }
            if (sum == 2) {

                return new int[]{-1};
            }
        }
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == symbol) {
                sum++;
            }
            if (sum == 2) {
                return new int[]{-1};
            }
        }
        sum = 0;

        for (int i = matrix.length - 1; i <= 0; i++) {
            if (matrix[i][i] == symbol) {
                sum++;
            }

            if (sum == 2) {
                return new int[]{-1};
            }

        }
        return null;
    }

    private static int[] init() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = ' ';
            }
        }
        if (symbol == 'X') {
            matrix[1][1] = symbol;
        }
        return new int[]{1, 1};
    }

    private static int[] setDiagonals() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == 1 && j == 1) {
                    continue;
                }

                if (matrix[i][j] != ' ') {
                    return null;
                }
            }
        }
        double n = Math.random();
        int i, j;
        if (n > 0.5) {
            i = 2;
        } else {
            i = 0;
        }
        n = Math.random();
        if (n > 0.51) {
            j = 2;
        } else {
            j = 0;
        }
        matrix[i][j] = symbol;
        return new int[]{i, j};

    }


    private static int[] checkBadShit() {
        return checkRows() != null ? checkRows()
                : checkCols() != null ? checkCols()
                : checkDiags() != null ? checkDiags()
                : null;
    }

    private static int[] checkRows() {
        int[] result = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == oponent) {
                    sum++;
                }
            }
            if (sum == 2) {
                result[0] = i;
                if (matrix[i][0] == oponent && matrix[i][1] == oponent && matrix[i][2] == ' ') {
                    matrix[i][2] = symbol;
                    result[1] = 2;
                } else if (matrix[i][0] == oponent && matrix[i][2] == oponent && matrix[i][1] == ' ') {
                    matrix[i][1] = symbol;
                    result[1] = 1;
                } else if (matrix[i][1] == oponent && matrix[i][2] == oponent && matrix[i][0] == ' ') {
                    matrix[i][0] = symbol;
                    result[1] = 0;
                }
                return result;
            }
        }
        return null;
    }

    private static int[] checkCols() {
        int[] result = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] == oponent) {
                    sum++;
                }
            }
            if (sum == 2) {
                result[1] = i;
                if (matrix[0][i] == oponent && matrix[1][i] == oponent && matrix[2][i] == ' ') {
                    matrix[2][i] = symbol;
                    result[0] = 2;
                } else if (matrix[0][i] == oponent && matrix[2][i] == oponent && matrix[1][i] == ' ') {
                    matrix[1][i] = symbol;
                    result[0] = 1;
                } else if (matrix[1][i] == oponent && matrix[2][i] == oponent && matrix[0][i] == ' ') {
                    matrix[0][i] = symbol;
                    result[0] = 0;
                }
                return result;
            }
        }
        return null;
    }

    private static int[] checkDiags() {
        int[] result = new int[2];
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == oponent) {
                sum++;
            }

            if (sum == 2) {
                result[0] = i;
                if (matrix[0][i] == oponent && matrix[1][i] == oponent && matrix[2][i] == ' ') {
                    matrix[2][i] = symbol;
                    result[1] = 2;
                } else if (matrix[0][i] == oponent && matrix[2][i] == oponent && matrix[1][i] == ' ') {
                    matrix[1][i] = symbol;
                    result[1] = 1;
                } else if (matrix[1][i] == oponent && matrix[2][i] == oponent && matrix[0][i] == ' ') {
                    matrix[0][i] = symbol;
                    result[1] = 0;
                }
                return result;
            }
        }

        for (int i = matrix.length - 1; i <= 0; i++) {
            if (matrix[i][i] == oponent) {
                sum++;
            }

            if (sum == 2) {
                result[0] = i;
                if (matrix[0][i] == oponent && matrix[1][i] == oponent && matrix[2][i] == ' ') {
                    matrix[2][i] = symbol;
                    result[1] = 2;
                } else if (matrix[0][i] == oponent && matrix[2][i] == oponent && matrix[1][i] == ' ') {
                    matrix[1][i] = symbol;
                    result[1] = 1;
                } else if (matrix[1][i] == oponent && matrix[2][i] == oponent && matrix[0][i] == ' ') {
                    matrix[0][i] = symbol;
                    result[1] = 0;
                }
                return result;
            }
        }
        return null;
    }
}

package BackBone;

import java.util.Random;

public class Grid {
    private int[][] grid;
    private Random random;
    private final int size = 9;

    public Grid() {
        grid = new int[size][size];
        random = new Random();
        generateSudoku();
    }

    public void generateSudoku() {
        fillDiagonal();
        fillRemaining(0, 3);
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setBox(int row, int col, int val) {
        grid[row][col] = val;
    }

    public boolean isValid(int row, int col, int num) {
        for (int i = 0; i < size; i++) {
            if (grid[row][i] == num || grid[i][col] == num) {
                return false;
            }
        }
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fillDiagonal() {
        for (int i = 0; i < size; i = i + 3) {
            fillBox(i, i);
        }
    }

    private void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = random.nextInt(9) + 1;
                } while (!isValid(row, col, num));
                grid[row + i][col + j] = num;
            }
        }
    }

    private boolean fillRemaining(int row, int col) {
        if (col >= size && row < 8) {
            row = row + 1;
            col = 0;
        }
        if (row >= size && col >= 9) {
            return true;
        }
        if (row < 3) {
            if (col < 3) {
                col = 3;
            }
        } else if (row < 6) {
            if (col == (int) (row / 3) * 3) {
                col = col + 3;
            }
        } else {
            if (col == 6) {
                row = row + 1;
                col = 0;
                if (row >= 9) {
                    return true;
                }
            }
        }
        for (int num = 1; num <= size; num++) {
            if (isValid(row, col, num)) {
                grid[row][col] = num;
                if (fillRemaining(row, col + 1)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false;
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == -1) { 
                    System.out.print("  ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
package BackBone;

import java.util.Random;

public class GameGrid extends Grid {
    private Random random = new Random();
    private final Grid result = new Grid();
    public int[][] grid;

    public GameGrid(Difficulty challenge) {
        grid = getGrid();
        for (int row = 0; row < getSize(); row++) {
            for (int col = 0; col < getSize(); col++) {
                grid[row][col] = result.getGrid()[row][col];
            }
        }
        int clear = 0;
        switch(challenge) {
            case EASY:
                clear = 35;
                break;
            case MEDIUM:
                clear = 45;
                break;
            case HARD:
                clear = 55;
                break;
            case EXPERT:
                clear = 65;
                break;
        }
        int i = 0;
        while (i < clear) {
            int row = random.nextInt(getSize());
            int col = random.nextInt(getSize());
            if (getGrid()[row][col] != -1) {
                setBox(row, col, -1);
                i++;
            }
        }
    }

    public boolean validMove(int row, int col, int input) {
        int[][] resultGrid = result.getGrid();
        return (resultGrid[row][col] == input);
    }

    public boolean gameOver() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (result.getGrid()[i][j] != getGrid()[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
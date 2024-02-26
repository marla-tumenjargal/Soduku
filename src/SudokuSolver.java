/**
 * SodukuSolver class; contains all of the business logic
 */
public class SudokuSolver {
    private int[][] puzzle;

    public SudokuSolver() {
        puzzle = new int[9][9];
    }

    /**
     * Initializes the game with the puzzle to solve
     * @param puzzle, puzzle to solve
     */
    public void setPuzzle(int[][] puzzle) {
        this.puzzle = puzzle;
    }

    /**
     * Solve the Sudoku puzzle using recursion and backtracking
     * @return true if a solution is found
     */
    public boolean solve() {
        int row;
        int col;

        if (!findUnassignedLocation() == true) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            row = findRow();
            col = findColumn();

            if (isValidPlacement(num, row, col)) {
                puzzle[row][col] = num;
                if (solve()) {
                    return true;
                }

                puzzle[row][col] = 0;
            }
        }
        return false;
    }

    /**
     * Finds unassigned box in grid.
     * @return true if location is found
     */
    public boolean findUnassignedLocation() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if placing a number is ok
     * @param number, the number to be placed.
     * @param row, the row to place the number.
     * @param col, the column to place the number.
     * @return true if # is valid
     */
    public boolean isValidPlacement(int number, int row, int col) {
        for (int i = 0; i < 9; i++) {
            if (puzzle[row][i] == number || puzzle[i][col] == number) {
                return false;
            }
        }

        int subgridStartRow = row - row % 3;
        int subgridStartCol = col - col % 3;
        for (int i = subgridStartRow; i < subgridStartRow + 3; i++) {
            for (int j = subgridStartCol; j < subgridStartCol + 3; j++) {
                if (puzzle[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * determines whether a row is unassigned
     * @returns the row or -1 if not found
     */
    public int findRow() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    return row;
                }
            }
        }
        return -1;
    }

    /**
     * determines whether a colum is unassigned
     * @returns col or -1 if not found
     */
    public int findColumn() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (puzzle[row][col] == 0) {
                    return col;
                }
            }
        }
        return -1;
    }

    /**
     * Getter, gets puzzle
     * @return puzzle
     */
    public int[][] getPuzzle() {
        return puzzle;
    }

    /**
     * "prints" the board by returning a string w the board
     * @param board, the soduku board
     * @return a string version of the board
     */
    public static String printBoard(int[][] board) {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                boardString.append("-------------------|----------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    boardString.append("| ");
                }
                if (col == 8) {
                    boardString.append(board[row][col]).append("\n");
                } else {
                    boardString.append(board[row][col]).append(" ");
                }
            }
        }
        return boardString.toString();
    }

}


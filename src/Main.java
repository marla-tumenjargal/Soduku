import java.util.Scanner;

/**
 * Main class; handles all input and output from the terminal
 */
public class Main {
        private static final int EASY = 1;
        private static final int MEDIUM = 2;
        private static final int HARD = 3;

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            SudokuSolver solver = new SudokuSolver();

            System.out.println("Welcome to Sudoku!\n Enter difficulty level:\n1 \n2 \n3");
            int choice = scanner.nextInt();

            int[][] puzzle = getPuzzle(choice);
            solver.setPuzzle(puzzle);

            System.out.println("Current Board:");
            System.out.println(SudokuSolver.printBoard(puzzle));

            System.out.println("Choose an option:\n1 - Automatically solve the puzzle\n2 - Manually enter a value\n3 - Exit");
            choice = scanner.nextInt();

            if (choice == 1) {
                if (solver.solve()) {
                    System.out.println(SudokuSolver.printBoard(solver.getPuzzle()));
                } else {
                    System.out.println("There is no solutionn");
                }
            } else if (choice == 2) {
                System.out.println("Enter row : ");
                int row = scanner.nextInt() - 1;

                System.out.println("Enter column : ");
                int col = scanner.nextInt() - 1;

                System.out.println("Enter value : ");
                int value = scanner.nextInt();

                if (value < 1 || value > 9) {
                    System.out.println("Incorrect value");
                } else {
                    if (puzzle[row][col] == 0) {
                        puzzle[row][col] = value;
                        System.out.println("This is the updated board:");
                        System.out.println(SudokuSolver.printBoard(puzzle));
                    }
                }
            } else if (choice == 3) {
                System.out.println("Exited");
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        }

        private static int[][] getPuzzle(int difficulty) {
            switch (difficulty) {
                case EASY -> { return getEasy(); }
                case MEDIUM -> { return getMedium(); }
                case HARD -> { return getHard(); }
                default -> throw new IllegalArgumentException("Invalid difficulty level");
            }
        }

        private static int[][] getEasy() {
            return new int[][] {
                    {5, 3, 0, 0, 7, 0, 0, 0, 0},
                    {6, 0, 0, 1, 9, 5, 0, 0, 0},
                    {0, 9, 8, 0, 0, 0, 0, 6, 0},
                    {8, 0, 0, 0, 6, 0, 0, 0, 3},
                    {4, 0, 0, 8, 0, 3, 0, 0, 1},
                    {7, 0, 0, 0, 2, 0, 0, 0, 6},
                    {0, 6, 0, 0, 0, 0, 2, 8, 0},
                    {0, 0, 0, 4, 1, 9, 0, 0, 5},
                    {0, 0, 0, 0, 8, 0, 0, 7, 9}
            };
        }

        private static int[][] getMedium() {
            return new int[][] {
                    {0, 2, 0, 6, 0, 8, 0, 0, 0},
                    {5, 8, 0, 0, 0, 9, 7, 0, 0},
                    {0, 0, 0, 0, 4, 0, 0, 0, 0},
                    {3, 7, 0, 0, 0, 0, 5, 0, 0},
                    {6, 0, 0, 0, 0, 0, 0, 0, 4},
                    {0, 0, 8, 0, 0, 0, 0, 1, 3},
                    {0, 0, 0, 0, 2, 0, 0, 0, 0},
                    {0, 0, 9, 8, 0, 0, 0, 3, 6},
                    {0, 0, 0, 3, 0, 6, 0, 9, 0}
            };
        }

        private static int[][] getHard() {
            return new int[][] {
                    {8, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 3, 6, 0, 0, 0, 0, 0},
                    {0, 7, 0, 0, 9, 0, 2, 0, 0},
                    {0, 5, 0, 0, 0, 7, 0, 0, 0},
                    {0, 0, 0, 0, 4, 5, 7, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 3, 0},
                    {0, 0, 1, 0, 0, 0, 0, 6, 8},
                    {0, 0, 8, 5, 0, 0, 0, 1, 0},
                    {0, 9, 0, 0, 0, 0, 4, 0, 0}
            };
        }
    }

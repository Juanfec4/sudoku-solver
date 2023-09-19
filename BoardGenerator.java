import java.util.Random;

public class BoardGenerator {

  // A solvable Sudoku seed board
  private static final int[][] SEED_BOARD = {
      { 5, 3, 4, 6, 7, 8, 9, 1, 2 },
      { 6, 7, 2, 1, 9, 5, 3, 4, 8 },
      { 1, 9, 8, 3, 4, 2, 5, 6, 7 },
      { 8, 5, 9, 7, 6, 1, 4, 2, 3 },
      { 4, 2, 6, 8, 5, 3, 7, 9, 1 },
      { 7, 1, 3, 9, 2, 4, 8, 5, 6 },
      { 9, 6, 1, 5, 3, 7, 2, 8, 4 },
      { 2, 8, 7, 4, 1, 9, 6, 3, 5 },
      { 3, 4, 5, 2, 8, 6, 1, 7, 9 }
  };

  /*
   * Generate a shuffled Sudoku board based on a seed board.
   * The shuffling ensures the board remains solvable.
   * 
   * @return A shuffled Sudoku board
   */
  public static int[][] shuffleBoard() {
    // Create a copy of the seed board to modify
    int[][] board = deepCopy(SEED_BOARD);

    Random rand = new Random();

    System.out.println("Starting with seed board:");
    SudokuSolver.printBoard(board);
    delay(2000); // Delay for half a second

    System.out.println("\nShuffling the board...");
    // Shuffle the board multiple times
    for (int i = 0; i < 100; i++) {
      // Swap two rows within the same 3x3 grid
      int grid = rand.nextInt(3);
      int row1 = rand.nextInt(3) + grid * 3;
      int row2 = rand.nextInt(3) + grid * 3;
      int[] tempRow = board[row1];
      board[row1] = board[row2];
      board[row2] = tempRow;

      // Swap two columns within the same 3x3 grid
      grid = rand.nextInt(3);
      int col1 = rand.nextInt(3) + grid * 3;
      int col2 = rand.nextInt(3) + grid * 3;
      for (int r = 0; r < 9; r++) {
        int tempValue = board[r][col1];
        board[r][col1] = board[r][col2];
        board[r][col2] = tempValue;
      }

      // Swap two numbers across the entire board
      int num1 = rand.nextInt(9) + 1;
      int num2 = rand.nextInt(9) + 1;
      for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
          if (board[r][c] == num1) {
            board[r][c] = num2;
          } else if (board[r][c] == num2) {
            board[r][c] = num1;
          }
        }
      }

      // Print the board after each major operation
      clearConsole();
      SudokuSolver.printBoard(board);
      delay(50); // Delay for 50 milliseconds
    }

    System.out.println("Removing numbers to produce the puzzle...");
    // Randomly remove numbers to generate the puzzle
    int numbersToRemove = rand.nextInt(40) + 25; // Remove between 25 and 39 numbers
    for (int i = 0; i < numbersToRemove; i++) {
      int row = rand.nextInt(9);
      int col = rand.nextInt(9);
      board[row][col] = 0; // Set number to 0 (empty)
      // Print the board after each number removal
      clearConsole();
      SudokuSolver.printBoard(board);
      delay(50); // Delay for 50 milliseconds
    }

    clearConsole();
    return board;
  }

  /*
   * Perform a deep copy of a 2D array.
   * 
   * @param original The original 2D array to copy
   * 
   * @return A new 2D array with the same contents as the original
   */
  private static int[][] deepCopy(int[][] original) {
    int[][] copy = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i].clone();
    }
    return copy;
  }

  // Helper method to add a delay
  private static void delay(long milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  // Helper method to clear console (hope it works, had to check stackoverflow)
  private static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033[H\033[2J");
        // ANSI escape code to clear console for UNIX systems
        System.out.flush();
      }
    } catch (final Exception e) {
      // Handle any possible IO exception
    }
  }
}
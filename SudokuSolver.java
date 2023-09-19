public class SudokuSolver {

  public static final int SIZE = 9; // Size of the board (9x9)
  public static final int SUBGRIDSIZE = 3; // Size of the subgrid (3x3)

  /*
   * Check if placing a number in a given cell is valid as per Sudoku rules.
   *
   * @param board The current Sudoku board.
   * 
   * @param row Row index.
   * 
   * @param col Column index.
   * 
   * @param num Number to be placed.
   * 
   * @return True if placing the number is valid, otherwise false.
   */

  public boolean isValid(int[][] board, int row, int col, int num) {
    // Check the entire row for duplicates
    for (int x = 0; x < SIZE; x++) {
      if (board[row][x] == num) {
        return false;
      }
    }

    // Check the entire column for duplicates
    for (int x = 0; x < SIZE; x++) {
      if (board[x][col] == num) {
        return false;
      }
    }

    // Check the 3x3 grid containing the cell for duplicates
    int startRow = row - row % SUBGRIDSIZE, startCol = col - col % SUBGRIDSIZE;
    for (int i = 0; i < SUBGRIDSIZE; i++) {
      for (int j = 0; j < SUBGRIDSIZE; j++) {
        if (board[i + startRow][j + startCol] == num) {
          return false;
        }
      }
    }

    return true;
  }

  /*
   * Solve the Sudoku puzzle using backtracking.
   *
   * @param board The Sudoku board.
   * 
   * @return True if a solution is found, otherwise false.
   */
  public boolean solveSudoku(int[][] board) {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j] == 0) {
          for (int num = 1; num <= SIZE; num++) {
            if (isValid(board, i, j, num)) {
              board[i][j] = num; // Try placing the number
              if (solveSudoku(board)) {
                return true; // Return true if placing leads to a solution
              } else {
                board[i][j] = 0; // Backtrack if not leading to a solution
              }
            }
          }
          return false; // No number can be placed in this cell
        }
      }
    }
    return true; // All cells are filled, solution found
  }

  /*
   * Display the Sudoku board.
   *
   * @param board The Sudoku board.
   */
  public static void printBoard(int[][] board) {
    for (int r = 0; r < SIZE; r++) {
      for (int d = 0; d < SIZE; d++) {
        if (board[r][d] == 0) {
          System.out.print(". "); // Replace 0 with a dot or space
        } else {
          System.out.print(board[r][d] + " ");
        }
      }
      System.out.print("\n");
    }
    System.out.print("\n");
  }
}
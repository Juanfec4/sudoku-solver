public class Main {

  public static void main(String[] args) {
    // Inform the user that a Sudoku board is being generated
    System.out.println("Generating a solvable Sudoku board...");
    int[][] board = BoardGenerator.shuffleBoard();

    // Display the generated Sudoku board to the user
    System.out.println("\nGenerated Board:");
    SudokuSolver.printBoard(board);

    // Inform the user that the program is attempting to solve the generated board
    System.out.println("Solving the generated board...");
    SudokuSolver sudoku = new SudokuSolver();

    // Try to solve the Sudoku and print the solution if one exists
    if (sudoku.solveSudoku(board)) {
      System.out.println("\nSolved Board:");
      SudokuSolver.printBoard(board);
    } else {
      System.out.println("No solution exists"); // Notify user if no solution is found
    }
  }
}
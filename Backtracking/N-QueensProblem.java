/*
Problem Statement
The N-Queens problem is to place N queens on an N×N chessboard such that no two queens attack each other.
This means that no two queens share the same row, column, or diagonal.

Solution
We can solve this problem using backtracking by trying to place queens row by row, and for each row, trying every column. 
If placing a queen in a particular column of a row is valid (i.e., it doesn't conflict with already placed queens),
we proceed to place a queen in the next row. If placing a queen in any column of the current row leads to a conflict,
we backtrack and move the previously placed queen to the next possible column.
*/
public class NQueens {
    private int size;
    private int[] queens; // queens[i] represents the column position of the queen in row i
    private boolean[] columns; // columns[i] indicates if a column i is under attack
    private boolean[] diagonals1; // diagonals1[i] indicates if a main diagonal i is under attack
    private boolean[] diagonals2; // diagonals2[i] indicates if an anti-diagonal i is under attack

    public NQueens(int size) {
        this.size = size;
        this.queens = new int[size];
        this.columns = new boolean[size];
        this.diagonals1 = new boolean[2 * size - 1];
        this.diagonals2 = new boolean[2 * size - 1];
    }

    public void solve() {
        placeQueen(0);
    }

    private void placeQueen(int row) {
        if (row == size) {
            printSolution();
            return;
        }
        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                place(row, col);
                placeQueen(row + 1);
                remove(row, col);
            }
        }
    }

    private boolean isSafe(int row, int col) {
        return !columns[col] && !diagonals1[row - col + size - 1] && !diagonals2[row + col];
    }

    private void place(int row, int col) {
        queens[row] = col;
        columns[col] = true;
        diagonals1[row - col + size - 1] = true;
        diagonals2[row + col] = true;
    }

    private void remove(int row, int col) {
        queens[row] = 0;
        columns[col] = false;
        diagonals1[row - col + size - 1] = false;
        diagonals2[row + col] = false;
    }

    private void printSolution() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (queens[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 8; // Change this value for different sizes of the board
        NQueens nQueens = new NQueens(n);
        nQueens.solve();
    }
}

/*
Q . . . . . . .
. . . . Q . . .
. . . . . . . Q
. . . . . Q . .
. . Q . . . . .
. . . . . . Q .
. Q . . . . . .
. . . Q . . . .
*/
/*
Explanation
Initialization:

size: Size of the chessboard (N).
queens: An array where queens[i] indicates the column of the queen in the i-th row.
columns: A boolean array indicating whether a column is under attack.
diagonals1 and diagonals2: Boolean arrays for the two types of diagonals (main and anti-diagonals) 
to track if they are under attack.
Placing Queens:

The placeQueen method tries to place a queen in each column of the current row (row). 
If a safe position is found (checked by isSafe), it places the queen (place), proceeds to the next row,
and recursively calls placeQueen.
If placing the queen in the current configuration leads to a conflict in subsequent rows,
the method backtracks by removing the queen (remove) and tries the next column.
Checking Safety:

The isSafe method checks if a given column and its corresponding diagonals are not under attack.
Printing the Solution:

The printSolution method prints the board with 'Q' representing queens and '.' representing empty spaces.
Main Method:

The main method creates an instance of NQueens for a board of size 8 and calls
the solve method to find and print all possible solutions.
This implementation prints all possible solutions to the N-Queens problem for a given board size.
For an 8x8 board (the classical problem), it will print all 92 solutions.









*/

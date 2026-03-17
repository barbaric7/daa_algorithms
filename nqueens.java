import java.util.Scanner;

public class nqueens {

    static int N;
    static int Q;

    static boolean isSafe(int board[][], int row, int col) {

        // Going through board checking if position is safe or not.

        for (int i = 0; i < row; i++)
            if (board[i][col] == 1)
                return false;

        int i = row - 1, left = col - 1, right = col + 1;

        while (i >= 0) {

            if (left >= 0 && board[i][left] == 1)
                return false;

            if (right < N && board[i][right] == 1)
                return false;

            i--;
            left--;
            right++;
        }

        return true;
    }

    static void placeQueen(int board[][], int row, int queensPlaced) {

        if (queensPlaced == Q) {
            printBoard(board);
            System.out.println();
            return;
        }

        if (row == N)
            return;

        for (int col = 0; col < N; col++) {

            if (isSafe(board, row, col)) {

                board[row][col] = 1;

                placeQueen(board, row + 1, queensPlaced + 1);

                board[row][col] = 0;  // backtracking and checking for other posibilities.
            }
        }

        placeQueen(board, row + 1, queensPlaced);
    }


    // Prints the board.
    static void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Taking inputs
        System.out.print("Enter board size : ");
        N = sc.nextInt();

        System.out.print("Enter number of queens : ");
        Q = sc.nextInt();

        int board[][] = new int[N][N];

        // Function Call to placeQueen with user inputs.
        placeQueen(board, 0, 0);

        sc.close();
    }
}
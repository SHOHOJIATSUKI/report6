package jp.ac.uryukyu.ie.e235731;
/**
 * ゲームの盤面を表すクラス。
 */
public class Board {
    private static final int ROWS = 8; // 盤面の行数
    private static final int COLS = 8; // 盤面の列数
    private char[][] board; // 盤面の状態を表す2次元配列
/**
     * Boardクラスのコンストラクタ。
     * 盤面を初期化し、初期配置を行う。
     */
    public Board() {
        initializeBoard();
    }
    public char[][] getBoard() {
        // Return a copy of the board to avoid direct modification from outside
        char[][] copy = new char[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
    }

    private void initializeBoard() {
        // 盤面を初期化するメソッド
        board = new char[ROWS][COLS];
        // とりあえず空白で初期化
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = ' ';
            }
        }
        // 初期配置（白黒２つずつ）
        board[3][3] = 'O';
        board[4][4] = 'O';
        board[3][4] = 'X';
        board[4][3] = 'X';
    }
/**
     * 盤面の状態を表示するメソッド。
     */
    public void printBoard() {
        // 盤面の状態を表示するメソッド
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
/**
     * ゲームが終了したかどうかを判定するメソッド。
     *
     * @return ゲームが終了している場合はtrue、それ以外はfalse
     */
    public boolean isValidMove(int row, int col, char player) {
        // 指定された位置に石を置けるか判定するメソッド
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS || board[row][col] != ' ') {
            // 範囲外または既に石がある場合は無効
            return false;
        }
        // 周囲を探索して裏返せるかどうかを判定
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // 同じ位置はスキップ
                if (isValidDirection(row, col, i, j, player)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidDirection(int row, int col, int dr, int dc, char player) {
        // 指定された方向に裏返せるか判定するメソッド
        int r = row + dr;
        int c = col + dc;
        boolean opponentStoneFound = false;
        while (r >= 0 && r < ROWS && c >= 0 && c < COLS) {
            if (board[r][c] == ' ') {
                return false;
            } else if (board[r][c] == player) {
                return opponentStoneFound;
            } else {
                opponentStoneFound = true;
            }
            r += dr;
            c += dc;
        }
        return false;
    }

    public void placeStone(int row, int col, char player) {
        // 指定された位置に石を置くメソッド
        board[row][col] = player;
        flipStones(row, col, player);
    }

    private void flipStones(int row, int col, char player) {
        // 石を裏返すメソッド
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (isValidDirection(row, col, i, j, player)) {
                    flipDirection(row, col, i, j, player);
                }
            }
        }
    }

    private void flipDirection(int row, int col, int dr, int dc, char player) {
        // 指定された方向に石を裏返すメソッド
        int r = row + dr;
        int c = col + dc;
        while (board[r][c] != player) {
            board[r][c] = player;
            r += dr;
            c += dc;
        }
    }

    // ゲームが終了したかどうかを判定するメソッド
    public boolean isGameOver() {
        return (countStones('O') == 0 || countStones('X') == 0 || isBoardFull());
    }

    // 盤面がすべて埋まったかどうかを判定するメソッド
    private boolean isBoardFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == ' ') {
                    return false; // 空のセルがある場合はまだ盤面が埋まっていない
                }
            }
        }
        return true; // 空のセルがない場合は盤面が埋まっている
    }
        
     /**
     * ゲームの勝者を取得するメソッド。
     *
     * @return 勝者の石 ('O'、'X')。引き分けの場合は空白文字。
     */

    public char getWinner() {
        int countO = countStones('O');
        int countX = countStones('X');

        if (countO > countX) {
            return 'O';
        } else if (countX > countO) {
            return 'X';
        } else {
            return ' '; // 引き分けの場合
        }
    }

    // 石の数を数えるメソッド
    private int countStones(char stone) {
        int count = 0;
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == stone) {
                    count++;
                }
            }
        }
        return count;
    }
}


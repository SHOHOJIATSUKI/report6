
package jp.ac.uryukyu.ie.e235731;

public class Game {
    private Board board;
    private Player player1;
    private Player player2;

    public Game() {
        this.board = new Board();
        this.player1 = new Player('O');
        this.player2 = new Player('X');
    }
    Board getBoard(){
        return board;
    }

    public void start() {
        board.printBoard();

        while (!board.isGameOver()) {
            // プレイヤー1のターン
            player1.makeMove(board);
            board.printBoard();

            if (board.isGameOver()) {
                break;
            }

            // プレイヤー2のターン
            player2.makeMove(board);
            board.printBoard();
        }

        // ゲーム終了後の処理（勝者の表示など）
        showResult();
    }

    private void showResult() {
        char winner = board.getWinner();
        if (winner == 'O' || winner == 'X') {
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}

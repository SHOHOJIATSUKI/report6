package jp.ac.uryukyu.ie.e235731;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReversiGameTest {

    @Test
    public void testPlaceStone() {
        Board board = new Board();
        assertEquals(' ', board.getWinner()); // 最初はまだ勝者がいない

        // プレイヤーOが石を置く
        board.placeStone(2, 3, 'O');
        assertEquals(' ', board.getWinner()); // まだ勝者がいない

        // プレイヤーXが石を置く
        board.placeStone(3, 2, 'X');
        assertEquals(' ', board.getWinner()); // まだ勝者がいない

        // プレイヤーOが石を置く（裏返しが発生）
        board.placeStone(3, 3, 'O');
        assertEquals(' ', board.getWinner()); // まだ勝者がいない

        // プレイヤーXが石を置く（勝者が決まる）
        board.placeStone(3, 4, 'X');
        assertEquals('X', board.getWinner()); // プレイヤーXが勝者

        // ゲームが終了したら再度石を置けない
        assertFalse(board.isValidMove(4, 3, 'O'));
    }
}

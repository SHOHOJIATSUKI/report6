package jp.ac.uryukyu.ie.e235731;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PlayerTest {

    @Test
    public void testMakeMove() {
        // プレイヤーの入力をシミュレートするためにScannerの代わりにStringを使用
        String simulatedInput = "2 3\n";
        InputStream originalSystemIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            Board board = new Board();
            Player player = new Player('O');

            // プレイヤーが有効な手を入力する
            player.makeMove(board);

            // 正しく石が置かれたか確認
            assertEquals('O', board.board[2][3]);

            // プレイヤーが無効な手を入力する（再度入力を要求）
            simulatedInput = "1 2\n";
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
            player.makeMove(board);

            // 石は置かれていないか確認
            assertEquals(' ', board.board[1][2]);

            // プレイヤーの石が正しいか確認
            assertEquals('O', player.getStone());
        } finally {
            // System.inを元に戻す
            System.setIn(originalSystemIn);
        }
    }
}



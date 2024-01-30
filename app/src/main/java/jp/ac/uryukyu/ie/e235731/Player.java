package jp.ac.uryukyu.ie.e235731;
import java.util.Scanner;

public class Player {
    private char stone; // プレイヤーが使う石（'O'または'X'）

    public Player(char stone) {
        this.stone = stone;
    }

    public char getStone() {
        return stone;
    }

    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);

        // 入力が有効な手であるか確認
        int row, col;
        do {
            System.out.println("Player " + stone + ", enter your move (row and column, e.g., 2 3):");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter integers.");
                scanner.next();
            }
            row = scanner.nextInt();
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter integers.");
                scanner.next();
            }
            col = scanner.nextInt();
        } while (!board.isValidMove(row, col, stone));

        // 石を置く
        board.placeStone(row, col, stone);

        // Scannerをクローズ
        scanner.close();
    }
}

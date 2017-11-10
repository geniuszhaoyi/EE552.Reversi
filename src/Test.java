import java.util.Scanner;

import model.Board;
import model.PlayResult;

public class Test {
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		Board board = new Board();
		
		while(board.getCurrentPlayer() != 0) {
			for(int i = 0; i < board.getWidth(); i++) {
				for(int j = 0; j < board.getHeight(); j++) {
					System.out.print(board.getPos(i, j) + " ");
				}
				System.out.println();
			}
			System.out.println("Current Player: " + board.getCurrentPlayer());
			System.out.println("Input your piece (x y): ");
			int x = cin.nextInt();
			int y = cin.nextInt();
			PlayResult result = board.play(board.getCurrentPlayer(), x, y);
			System.out.println(result.toString());
		}
		
		cin.close();
	}
}

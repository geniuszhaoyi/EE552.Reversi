package model;

public class Board {
	private int width, height;
	private int board[][];
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		board = new int[width][height];
		currentPlayer = 1;
		
		board[3][3] = 2; board[3][4] = 1;
		board[4][3] = 1; board[4][4] = 2;
	}
	public Board() {
		this(8, 8);
	}
	
	private int currentPlayer;
	/**
	 * Get current player. 0 for game over, 1 or 2 for player number. 
	 * @return player_number
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getPos(int x, int y) {
		return board[x][y];
	}
	
	private boolean isInBound(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height) 
			return false;
		return true;
	}
	public PlayResult play(int player, int x, int y) {
		return play(player, x, y, false);
	}
	public PlayResult play(int player, int x, int y, boolean dryRun) {
		if(player != currentPlayer) {
			return PlayResult.ErrNotYourTurn;
		}
		if(isInBound(x, y) == false) {
			return PlayResult.ErrTargetOutOfBound;
		}
		if(board[x][y] != 0) {
			return PlayResult.ErrTargetOccupied;
		}
		
		int dx[] = {1, 1, 1, 0, 0, -1, -1, -1};
		int dy[] = {1, 0, -1, 1, -1, 1, 0, -1};
		
		int flips = 0;
		
		for(int k = 0; k < 8; k++) {
			boolean canflip = false;
			for(int i = 1; isInBound(x + dx[k] * i, y + dy[k] * i); i++) {
				if(board[x + dx[k] * i][y + dy[k] * i] == 0) {
					break;
				}
				if(board[x + dx[k] * i][y + dy[k] * i] == player) {
					canflip = true;
					break;
				}
			}
			if(canflip) {
				for(int i = 1; isInBound(x + dx[k] * i, y + dy[k] * i); i++) {
					if(board[x + dx[k] * i][y + dy[k] * i] == 0) {
						break;
					}
					if(board[x + dx[k] * i][y + dy[k] * i] == player) {
						canflip = true;
						break;
					}
					if(dryRun == false) {
						board[x + dx[k] * i][y + dy[k] * i] = player;
					}
					flips += 1;
				}
			}
		}
		if(flips == 0) {
			return PlayResult.ErrTargetUseless;
		}
		
		if(dryRun == false) {
			board[x][y] = player;
			boolean possible = false;
			for(int i = 0; i < width; i++) if(possible == false){
				for(int j = 0; j < height; j++) if(possible == false){
					if(play(1, i, j, true) == PlayResult.OK) possible = true;
					if(play(2, i, j, true) == PlayResult.OK) possible = true;
				}
			}
//			System.err.println(possible);
			if(possible == true)
				currentPlayer = 3 - currentPlayer;
			else
				currentPlayer = 0;
		}
		return PlayResult.OK;
	}
	
	public int getWinner() {
		if(getCurrentPlayer() != 0) {
			return 0;
		}
		int c[] = new int[3];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				c[board[i][j]] += 1;
			}
		}
		if(c[1] > c[2]) {
			return 1;
		}
		if(c[1] < c[2]) {
			return 2;
		}
		return 3;
	}
}

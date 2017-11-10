package model;

public enum PlayResult {
	OK("OK. "),
	ErrNotYourTurn("Not this player's turn. "),
	ErrTargetOccupied("The target position is occupied by another piece. "),
	ErrTargetOutOfBound("The target position is out of bound (outside board). "),
	ErrTargetUseless("This move makes no flips");
	
	String str;
	PlayResult(String str) {
		this.str = str;
	}
	public String toString() {
		return str;
	}
}

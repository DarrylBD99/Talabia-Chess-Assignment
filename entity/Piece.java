public abstract class Piece {
    int playerIndex;

    abstract boolean checkValidMove(int startX, int startY, int endX, int endY);
}

public class PointPiece extends Piece {
    public PointPiece(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    boolean checkValidMove(int startX, int startY, int endX, int endY) {
        // Check if the move is within the board boundaries
        if (endX < 0 || endX >= ChessBoard.ROWS || endY < 0 || endY >= ChessBoard.COLS) {
            return false;
        }

        // Check if the move is forward
        int direction = (playerIndex == 0) ? 1 : -1; // Adjust direction based on player
        if (endX != startX + direction && endX != startX + 2 * direction) {
            return false;
        }

        // Check if the Point piece has reached the end and needs to turn around
        if (endX == ChessBoard.ROWS || endX == -1) {
            return false; // Point piece cannot go beyond the board
        }

        // Check if the destination is occupied by another piece
        if (ChessBoard.getInstance().getPiece(endX, endY) != null) {
            return false;
        }

        return true;
    }
}

public class HourGlass extends Piece {

    public HourGlass(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessView.ROWS || end_y < 0 || end_y >= ChessView.COLS) return false;

        // Check if the move is a valid 3x2 L shape
        int xDistance = Math.abs(end_x - start_x);
        int yDistance = Math.abs(end_y - start_y);

        if ((xDistance == 2 && yDistance == 3) || (xDistance == 3 && yDistance == 2)) {
            // Check if the destination is occupied by another piece of the same player
            Piece destinationPiece = ChessView.getPiece(end_x, end_y);
            return destinationPiece == null || destinationPiece.playerIndex != this.playerIndex;
        }

        return false;
    }
}

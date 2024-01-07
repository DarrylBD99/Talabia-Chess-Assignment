public class Sun extends Piece {

    public Sun(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessView.ROWS || end_y < 0 || end_y >= ChessView.COLS) {
            return false;
        }

        // Check if the move is only one step in any direction
        if (Math.abs(end_x - start_x) > 1 || Math.abs(end_y - start_y) > 1) {
            return false;
        }

        // Check if the destination is occupied by another piece of the same player
        Piece destinationPiece = ChessView.getPiece(end_x, end_y);
        return destinationPiece == null || destinationPiece.playerIndex != this.playerIndex;
    }
}

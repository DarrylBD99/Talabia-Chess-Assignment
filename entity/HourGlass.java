public class HourGlass extends Piece {

    public HourGlass(int playerIndex) {
        this.player_index = playerIndex;
    }

    @Override
    boolean check_valid_move(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessBoard.ROWS || end_y < 0 || end_y >= ChessBoard.COLS) {
            return false;
        }

        // Check if the move is a valid 3x2 L shape
        int xDistance = Math.abs(end_x - start_x);
        int yDistance = Math.abs(end_y - start_y);

        if ((xDistance == 2 && yDistance == 3) || (xDistance == 3 && yDistance == 2)) {
            // Check if the destination is occupied by another piece of the same player
            Piece destinationPiece = ChessBoard.getInstance().getPiece(end_x, end_y);
            return destinationPiece == null || destinationPiece.player_index != this.player_index;
        }

        return false;
    }
}

public class Point extends Piece {
    public Point(int playerIndex) {
        this.player_index = playerIndex;
    }

    @Override
    boolean check_valid_move(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessBoard.ROWS || end_y < 0 || end_y >= ChessBoard.COLS) {
            return false;
        }

        // Check if the move is forward
        int direction = (player_index == 0) ? 1 : -1; // Adjust direction based on player
        if (end_x != start_x + direction && end_x != start_x + 2 * direction) {
            return false;
        }

        // Check if the Point piece has reached the end and needs to turn around
        if (end_x == ChessBoard.ROWS || end_x == -1) {
            return false; // Point piece cannot go beyond the board
        }

        // Check if the destination is occupied by another piece
        if (ChessBoard.getInstance().getPiece(end_x, end_y) != null) {
            return false;
        }

        return true;
    }
}

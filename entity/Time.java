public class Time extends Piece {

    public Time(int playerIndex) {
        this.player_index = playerIndex;
    }

    @Override
    boolean check_valid_move(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessBoard.ROWS || end_y < 0 || end_y >= ChessBoard.COLS) {
            return false;
        }

        // Check if the move is diagonal
        if (Math.abs(end_x - start_x) != Math.abs(end_y - start_y)) {
            return false;
        }

        // Check if there are any pieces in the diagonal path
        int xIncrement = (end_x > start_x) ? 1 : -1;
        int yIncrement = (end_y > start_y) ? 1 : -1;

        int currentX = start_x + xIncrement;
        int currentY = start_y + yIncrement;

        while (currentX != end_x && currentY != end_y) {
            Piece piece = ChessBoard.getInstance().getPiece(currentX, currentY);
            if (piece != null) {
                return false; // There is a piece in the diagonal path
            }

            currentX += xIncrement;
            currentY += yIncrement;
        }

        // Check if the destination is occupied by another piece of the same player
        Piece destinationPiece = ChessBoard.getInstance().getPiece(end_x, end_y);
        return destinationPiece == null || destinationPiece.player_index != this.player_index;
    }
}


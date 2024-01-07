public class Plus extends Piece {

    public Plus(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessView.ROWS || end_y < 0 || end_y >= ChessView.COLS) return false;

        // Check if the move is either horizontal or vertical
        if ((start_x != end_x && start_y != end_y) || (start_x == end_x && start_y == end_y)) return false;

        // Check if there are any pieces in the path
        if (start_x == end_x) { // Vertical move
            int increment = (end_y > start_y) ? 1 : -1;

            int currentY = start_y + increment;
            while (currentY != end_y) {
                Piece piece = ChessView.getPiece(start_x, currentY);
                if (piece != null) {
                    return false; // There is a piece in the vertical path
                }
                currentY += increment;
            }
        } else { // Horizontal move
            int increment = (end_x > start_x) ? 1 : -1;

            int currentX = start_x + increment;
            while (currentX != end_x) {
                Piece piece = ChessView.getPiece(currentX, start_y);
                if (piece != null) {
                    return false; // There is a piece in the horizontal path
                }
                currentX += increment;
            }
        }

        // Check if the destination is occupied by another piece of the same player
        Piece destinationPiece = ChessView.getPiece(end_x, end_y);
        return destinationPiece == null || destinationPiece.playerIndex != this.playerIndex;
    }
}

public class Time extends Piece {

    public Time(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= Board.ROWS || end_y < 0 || end_y >= Board.COLS) {
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
            Piece piece = Board.getPiece(currentX, currentY);
            if (piece != null) {
                return false; // There is a piece in the diagonal path
            }

            currentX += xIncrement;
            currentY += yIncrement;
        }

        // Check if the destination is occupied by another piece of the same player
        Piece destinationPiece = Board.getPiece(end_x, end_y);
        return destinationPiece == null || destinationPiece.playerIndex != this.playerIndex;
    }
}


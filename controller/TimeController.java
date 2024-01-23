public class TimeController extends PieceController {

    public TimeController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
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
                Piece piece = ChessView.getPiece(currentX, currentY);
                if (piece != null) {
                    return false; // There is a piece in the diagonal path
                }

                currentX += xIncrement;
                currentY += yIncrement;
            }

            // Check if the destination is occupied by another piece of the same player
            Piece destinationPiece = ChessView.getPiece(end_x, end_y);
            return destinationPiece == null || destinationPiece.getPlayerIndex() != model.getPlayerIndex();
        }

        return false;
    }
}


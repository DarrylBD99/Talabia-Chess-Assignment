public class PointController extends PieceController {

    public PointController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is forward
        int direction = (model.getPlayerIndex() == 0) ? 1 : -1; // Adjust direction based on player
        if (end_x != start_x + direction && end_x != start_x + 2 * direction) {
            return false;
        }

        // Check if the Point piece has reached the end and needs to turn around
        if (end_x == ChessView.ROWS || end_x == -1) {
            return false; // Point piece cannot go beyond the board
        }

        // Check if the destination is occupied by another piece
        if (ChessView.getPiece(end_x, end_y) != null) {
            return false;
        }

        return super.checkValidMove(start_x, start_y, end_x, end_y);
    }
}

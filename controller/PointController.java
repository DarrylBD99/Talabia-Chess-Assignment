public class PointController extends PieceController {
    public PointController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is forward
        int direction = ((model.getPlayerIndex() % 2) == 0) ? 1 : -1; // Adjust direction based on player
        if (end_y != start_y + direction) return false;

        // Check if the Point piece has reached the end and needs to turn around
        System.out.println(end_y == ChessView.COLS || end_y == -1);
        if (end_y == ChessView.COLS || end_y == -1) {
            return false; // Point piece cannot go beyond the board
        }

        // Check if the destination is occupied by another piece
        if (ChessView.getPiece(end_x, end_y) != null) {
            return false;
        }

        return super.checkValidMove(start_x, start_y, end_x, end_y);
    }
}

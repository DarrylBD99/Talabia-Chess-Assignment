import java.awt.Color;
public class HourGlassController extends PieceController {

    public HourGlassController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the move is a valid 3x2 L shape
            int xDistance = Math.abs(end_x - start_x);
            int yDistance = Math.abs(end_y - start_y);

            if ((xDistance == 2 && yDistance == 3) || (xDistance == 3 && yDistance == 2)) {
                // Check if the destination is occupied by another piece of the same player
                Piece destinationPiece = ChessView.getPiece(end_x, end_y);
                return destinationPiece == null || destinationPiece.getPlayerIndex() != model.getPlayerIndex();
            }
        }
        return false;
    }
}

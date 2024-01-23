import javax.swing.ImageIcon;

public class PointController extends PieceController {
    boolean has_reached_end = false;
    boolean played_first_move = false;

    public PointController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is forward
        int direction = ((model.getPlayerIndex() % 2) == 0) ? 1 : -1; // Adjust direction based on player
        direction = ((has_reached_end) ? -direction : direction); 
        if (!(end_y == start_y + direction || (end_y == start_y + direction * 2 && !played_first_move))) return false;

        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the Point piece has reached the end and needs to turn around
            if ((direction > 0 && end_y == ChessView.ROWS) || (direction < 0 && end_y == 0)) {
                has_reached_end = !has_reached_end;
                rotateIcon(180);
            }
            played_first_move = true;
            return true;
        }

        return false;
    }

    public void rotateIcon(int angle) {
        int type = (has_reached_end) ? 1 : 0;
        view = new PieceView(model, type);
	}
}

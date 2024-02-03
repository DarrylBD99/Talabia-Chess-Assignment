package src.controller;

import src.ChessBoard;
import src.model.Piece;

public class PointController extends PieceController {
    private boolean has_reached_end = false;
    private boolean played_first_move = false;

    public PointController(Piece model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        // Check if the move is forward
        int direction = ((get_model().getPlayerIndex() % 2) == 0) ? 1 : -1; // Adjust direction based on player
        direction = ((has_reached_end) ? -direction : direction); 
        
        if (start_x != end_x) return false;

        if (end_y == start_y + direction * 2 && !played_first_move)
        {
            Piece piece = board.getPiece(start_x, start_y + direction);
            if (piece != null) return false;
        }
        else if (!(end_y == start_y + direction)) return false;
        

        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the Point piece has reached the end and needs to turn around
            if ((direction > 0 && end_y == (ChessBoard.ROWS - 1)) || (direction < 0 && end_y == 0)) {
                has_reached_end = !has_reached_end;
                get_model().setRotated(!get_model().getRotated());
                rotateIcon(Math.PI);
            }
            played_first_move = true;
            return true;
        }

        return false;
    }
}

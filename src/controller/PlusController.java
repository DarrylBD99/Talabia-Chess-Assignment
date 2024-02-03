package src.controller;

import src.model.Piece;

public class PlusController extends PieceController {
    public PlusController(Piece model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if there are any pieces in the path
            if (start_x == end_x) { // Vertical move
                int increment = (end_y > start_y) ? 1 : -1;

                int currentY = start_y + increment;
                while (currentY != end_y) {
                    Piece piece = board.getPiece(start_x, currentY);
                    if (piece != null) {
                        return false; // There is a piece in the vertical path
                    }
                    currentY += increment;
                }
            } else { // Horizontal move
                int increment = (end_x > start_x) ? 1 : -1;

                int currentX = start_x + increment;
                while (currentX != end_x) {
                    Piece piece = board.getPiece(currentX, start_y);
                    if (piece != null) {
                        return false; // There is a piece in the horizontal path
                    }
                    currentX += increment;
                }
            }
            
            return true;
        }
        
        return false;
    }
}

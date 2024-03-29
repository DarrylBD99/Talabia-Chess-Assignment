package src.controller;

import src.model.Piece;

public class SunController extends PieceController {

    public SunController(Piece model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the move is only one step in any direction
            if (Math.abs(end_x - start_x) > 1 || Math.abs(end_y - start_y) > 1) {
                return false;
            }

            return true;
        }
        
        return false;
    }
}

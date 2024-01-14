public abstract class PieceController {
    // declaring the variables model and view  
    protected Piece model;
    private PieceView view;

    static PieceController get_piece_controller(Piece model, PieceView view)
    {
        PieceController[] controllers = {new PointController(model, view), new HourGlassController(model, view), new TimeController(model, view), new PlusController(model, view), new SunController(model, view)};

        PieceController ret = null;

        if (model.getPieceType() < controllers.length) { ret = controllers[model.getPieceType()]; }
    
        return ret;
    }
    
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y)
    {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessView.ROWS || end_y < 0 || end_y >= ChessView.COLS) return false;
        // Check if the move is either horizontal or vertical
        if (start_x != end_x && start_y != end_y) return false;
        
        return true;
    }

    public PieceController(Piece model, PieceView view)
    {
        this.model = model;
        this.view = view;
    }
}

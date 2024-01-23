public abstract class PieceController implements Cloneable {
    // declaring the variables model and view  
    protected Piece model;
    private PieceView view;
    
    static PieceController get_piece_controller(Piece model, PieceView view)
    {
        PieceController ret = null;
        
        switch (model.getPieceType()) {
            case POINT:
                ret = new PointController(model, view);
                break;
            case PLUS:
                ret = new PlusController(model, view);
                break;
            case HOURGLASS:
                ret = new HourGlassController(model, view);
                break;
            case TIME:
                ret = new TimeController(model, view);
                break;
            case SUN:
                ret = new SunController(model, view);
                break;
        
            default:
                break;
        }
    
        return ret;
    }

    // Checks if a move from the starting coordinates to the ending coordinates is valid.
    boolean checkValidMove(int startX, int startY, int endX, int endY) {
        // Check if the move is within the board boundaries.
        if (endX < 0 || endX >= ChessView.COLS || endY < 0 || endY >= ChessView.ROWS) return false;
        // Check if the move is either horizontal or vertical.
        return startX != endX || startY != endY;
    }

    public PieceController(Piece model, PieceView view)
    {
        this.model = model;
        this.view = view;
    }

    // Overrides the clone method to create a deep copy of the piece.
    @Override
    public PieceController clone() {
        try {
            return (PieceController) super.clone();
        } catch (CloneNotSupportedException e) {
            // Throw a runtime exception if cloning is not supported.
            throw new RuntimeException("Clone not supported for " + this.getClass());
        }
    }
}

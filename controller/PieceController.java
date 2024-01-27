import java.awt.*;

public abstract class PieceController implements Cloneable {
    // declaring the variables model and view  
    private Piece model;
    private PieceView view;

    // Checks if a move from the starting coordinates to the ending coordinates is valid.
    public boolean checkValidMove(int startX, int startY, int endX, int endY) {

        // Check if it's the current player's turn
        if (model.getPlayerIndex() != ChessBoard.currentPlayer) {
            System.out.println("Not your turn!");
            return false;
        }

        // Check if the move is within the board boundaries.
        if (endX < 0 || endX >= ChessBoard.COLS || endY < 0 || endY >= ChessBoard.ROWS) return false;
        // Check if piece has the same player index.
        Piece destinationPiece = ChessBoard.getPiece(endX, endY);
        if (destinationPiece != null && destinationPiece.getPlayerIndex() == model.getPlayerIndex()) return false;

        // Check if the move is either horizontal or vertical.
        return startX != endX || startY != endY;

    }

    public PieceController(Piece model) {this.model = model;}

    // Overrides the clone method to create a deep copy of the piece.
    @Override
    public PieceController clone() {
        System.out.println("cloned");
        try {
            PieceController ret = (PieceController) super.clone();
            ret.clone_model();
            return ret;
        } catch (CloneNotSupportedException e) {
            // Throw a runtime exception if cloning is not supported.
            throw new RuntimeException("Clone not supported for " + this.getClass());
        }
    }

    private void clone_model()
    {
        PieceType pieceType = model.getPieceType();
        model = new Piece();
        model.setPieceType(pieceType);
    }

    public Image get_view()
    {
        if (view == null) view = new PieceView(model);
        if (view.icon == null) return null;
        return view.icon.getImage();
    }

    public Piece get_model()
    {
        return model;
    }

    public void rotateIcon(double angle) {
        if (view == null) view = new PieceView(model);
        view.rotateIcon(angle);
	}
}

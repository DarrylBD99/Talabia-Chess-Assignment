package src.controller;

import java.awt.*;

import src.ChessBoard;
import src.PieceType;
import src.model.Piece;
import src.view.PieceView;

public abstract class PieceController implements Cloneable {
    // declaring the variables model and view  
    private Piece model;
    private PieceView view;
    protected ChessBoard board;

    // Checks if a move from the starting coordinates to the ending coordinates is valid.
    public boolean checkValidMove(int startX, int startY, int endX, int endY) {

        // Check if it's the current player's turn
        if (model.getPlayerIndex() != board.currentPlayer) {
            System.out.println("Not your turn!");
            return false;
        }

        // Check if the move is within the board boundaries.
        if (endX < 0 || endX >= ChessBoard.COLS || endY < 0 || endY >= ChessBoard.ROWS) return false;
        // Check if piece has the same player index.
        Piece destinationPiece = board.getPiece(endX, endY);
        if (destinationPiece != null && destinationPiece.getPlayerIndex() == model.getPlayerIndex()) return false;

        // Check if the move is either horizontal or vertical.
        return startX != endX || startY != endY;

    }

    public PieceController(Piece model)
    {
        this.model = model;
    }

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

    public void set_board(ChessBoard board)
    {
        this.board = board;
    }

    public Image get_view()
    {
        if (view == null) view = new PieceView(model);
        if (view.getIcon() == null) return null;
        return view.getIcon().getImage();
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

package TalabiaChessWales.model;

import TalabiaChessWales.PieceType;

// Abstract class representing a generic chess piece.
public class Piece {
    // Player index representing the owner of the piece.
    private int playerIndex;
    // Type of the chess piece.
    private PieceType pieceType;
    // Checks if piece is rotated on board
    private Boolean is_rotated = false;

    // Retrieves the player index of the piece.
    public int getPlayerIndex() {
        return playerIndex;
    }

    // Retrieves the type of the piece.
    public PieceType getPieceType() {
        return pieceType;
    }

    // Retrieves the type of the piece.
    public Boolean getRotated() {
        return is_rotated;
    }

    // Sets the player index of the piece.
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    // Sets the piece type of the piece.
    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    // Sets the piece type of the piece.
    public void setRotated(Boolean is_rotated) {
        this.is_rotated = is_rotated;
    }
}

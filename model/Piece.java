// Abstract class representing a generic chess piece.
public class Piece {
    // Player index representing the owner of the piece.
    protected int playerIndex;
    // Type of the chess piece.
    protected PieceType pieceType;

    // Retrieves the player index of the piece.
    int getPlayerIndex() {
        return playerIndex;
    }

    // Retrieves the type of the piece.
    PieceType getPieceType() {
        return pieceType;
    }

    // Sets the player index and piece type of the piece, and loads the corresponding icon.
    void setPlayerIndex(int playerIndex, PieceType pieceType) {
        // Validate playerIndex or pieceType if needed

        // Set the player index and piece type.
        this.playerIndex = playerIndex;
        this.pieceType = pieceType;
    }
}

public abstract class Piece {
    protected int playerIndex;
    protected int PieceType;

    int getPlayerIndex() { return playerIndex; }

    void setPlayerIndex(int playerIndex) { this.playerIndex = playerIndex; }

    int getPieceType() { return PieceType; }

    void setPieceType(int PieceType) { this.PieceType = PieceType; }
}

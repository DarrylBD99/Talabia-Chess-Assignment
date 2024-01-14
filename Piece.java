public abstract class Piece {
    protected int playerIndex;
    protected int PieceType;
    /*
     * 0: Point
     * 1: HourGlass
     * 2: Time
     * 3: Plus
     * 4: Sun
     */

    int getPlayerIndex() { return playerIndex; }

    void setPlayerIndex(int playerIndex) { this.playerIndex = playerIndex; }

    int getPieceType() { return PieceType; }

    void setPieceType(int PieceType) { this.PieceType = PieceType; }

    public Piece(int playerIndex, int PieceType)
    {
        this.playerIndex = playerIndex;
        this.PieceType = PieceType;
    }
}

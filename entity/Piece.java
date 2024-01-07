public abstract class Piece {
    protected int playerIndex;

    boolean checkValidMove(int startX, int startY, int endX, int endY)
    {
        // Check if the move is within the board boundaries
        if (endX < 0 || endX >= ChessView.ROWS || endY < 0 || endY >= ChessView.COLS) return false;
        // Check if the move is either horizontal or vertical
        if (startX != endX && startY != endY) return false;
        
        return true;
    }

    int getPlayerIndex() { return playerIndex; }

    void setPlayerIndex(int playerIndex) { this.playerIndex = playerIndex; }
}

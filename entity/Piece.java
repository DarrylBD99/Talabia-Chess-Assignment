public abstract class Piece {
    protected int playerIndex;

    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y)
    {
        // Check if the move is within the board boundaries
        if (end_x < 0 || end_x >= ChessView.ROWS || end_y < 0 || end_y >= ChessView.COLS) return false;
        // Check if the move is either horizontal or vertical
        if (start_x != end_x && start_y != end_y) return false;
        
        return true;
    }

    int getPlayerIndex() { return playerIndex; }

    void setPlayerIndex(int playerIndex) { this.playerIndex = playerIndex; }
}

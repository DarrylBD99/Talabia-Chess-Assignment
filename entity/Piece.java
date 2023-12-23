public abstract class Piece {
    int playerIndex;

    abstract boolean checkValidMove(int startX, int startY, int endX, int endY);
}

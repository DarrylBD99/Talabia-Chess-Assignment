public abstract class Piece{
    int player_index;
    abstract boolean check_valid_move(int start_x, int start_y, int end_x, int end_y);
}

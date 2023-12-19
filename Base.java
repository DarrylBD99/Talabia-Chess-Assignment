public class Base {
    static final int change_turn_no = 2;
    static final int number_of_players = 2;
    
    static Piece[][] Board = new Piece[7][6];
    static int turn = 0;

    /* 
     * 
     * define pieces (set placement on board and player controlling)
     * 
     * 
     * while true
     * for (int i = 0; i < number_of_players; i++)
     * {
     *      Piece selected_piece: player picks chess piece to move
     *      int move_point_x, move_point_y: player choses point to move
     *      
     *      if piece.check_valid_move(piece x pos, piece y pos, move_point_x, move_point_y)
     *          move piece to point
     *      else:
     *          i--
     * }
     * turn++
     * if turn % change_turn_no == 0
     * {
     *      for (Piece chess_piece : Board)
     *      {
     *          change plus piece to time
     *          change time piece to plus
     *      }
     * }
     * 
     */

    public static void main(String[] args) {
        
    }
}

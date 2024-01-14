public class Main {
    public static void main(String[] args) {
        
    }
    /*
     * piece_type: 
     * 0: Point
     * 1: HourGlass
     * 2: Time
     * 3: Plus
     * 4: Sun
     */

    /*
     * to define pieces:
     * 
     * Piece model = new Piece(player_index, piece_type)
     * PieceView view = new PieceView()
     * 
     * PieceController *insert piece name* = PieceController.get_piece_controller(model, view)
     */

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
}

public class HourGlassController extends PieceController {
    static final int move_1 = 1;
    static final int move_2 = 2;

    public HourGlassController(Piece model, PieceView view) {
        super(model, view);
        //TODO Auto-generated constructor stub
    }

    @Override
    boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the move is a valid 3x2 L shape
            int xDistance = Math.abs(end_x - start_x);
            int yDistance = Math.abs(end_y - start_y);
            System.out.println(xDistance);
            System.out.println(yDistance);

            if ((xDistance == move_1 && yDistance == move_2) || (xDistance == move_2 && yDistance == move_1)) return true;
        }
        return false;
    }
}

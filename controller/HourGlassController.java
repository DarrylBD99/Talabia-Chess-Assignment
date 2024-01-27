public class HourGlassController extends PieceController {
    private static final int MOVE_1 = 1;
    private static final int MOVE_2 = 2;

    public HourGlassController(Piece model) {
        super(model);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean checkValidMove(int start_x, int start_y, int end_x, int end_y) {
        if (super.checkValidMove(start_x, start_y, end_x, end_y))
        {
            // Check if the move is a valid 3x2 L shape
            int xDistance = Math.abs(end_x - start_x);
            int yDistance = Math.abs(end_y - start_y);
            System.out.println(xDistance);
            System.out.println(yDistance);

            if ((xDistance == MOVE_1 && yDistance == MOVE_2) || (xDistance == MOVE_2 && yDistance == MOVE_1)) return true;
        }
        return false;
    }
}

import javax.swing.*;
import java.awt.*;

public class ChessView extends JFrame {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private final JPanel boardPanel;

    static final int change_turn_no = 2;
    static final int number_of_players = 2;

    private static final String graphic_file_path = "///"; 
    
    static int turn = 0;

    private static final Piece[] row_format_front = {new Point(), new Point(), new Point(), new Point(), new Point(), new Point(), new Point()};
    private static final Piece[] row_format_back = {new Plus(), new HourGlass(), new Time(), new Sun(), new Time(), new HourGlass(), new Plus()};


    private static Piece[][] board = new Piece[ROWS][COLS];

    public ChessView() {
        setTitle("Talabia Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);

        boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        initializeBoard();
        initializePieces();
        //initializePieceGraphics

        add(boardPanel);
    }

    private void initializeBoard() {
        int thickness = 2;
        boardPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153, 255), thickness));

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = j;
                gbc.gridy = i;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                boardPanel.add(square, gbc);
            }
        }
    }

    static void initializePieces()
    {
        /*
         * for loop of i < ROWS
         * {
         *      Piece[] piece_row = new Piece[COLS]
         *      if i == 0 or i == (ROWS - 1) { piece_row = row_format_back }
         *      elif i == 1 or i == (ROWS - 2) { piece_row = row_format_front }
         *      
         *      if !piece_row.is_empty()
         *      {
         *          int player_index = 0
         *          if i >= ROWS/2 { player_index = 1 }
         * 
         *          for Piece piece : piece_row { piece.setPlayerIndex(player_index) }
         *          board[i] = piece_row
         *      }
         * 
         * }
         */
    }

    public void showView() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static Piece getPiece(int x, int y) { return board[y][x]; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessView chessView = new ChessView();
            chessView.showView();
        });
    }
}

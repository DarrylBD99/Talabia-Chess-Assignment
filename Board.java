import javax.swing.*;
import java.awt.*;

public class Board {
    static final int ROWS = 6;
    static final int COLS = 7;
    private final JFrame frame;
    private final JPanel boardPanel;

    static Piece[][] board = new Piece[ROWS][COLS];

    
    public Board() {
        frame = new JFrame("Talabia Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        initializeBoard();
        frame.add(boardPanel);
    }

    private void initializeBoard() {
        int thickness = 2;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                JPanel square = new JPanel(new BorderLayout());
                square.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153, 255),thickness));
                boardPanel.add(square);
            
            }
        }
    }

    public static Piece getPiece(int x, int y)
    {
        return board[y][x];
    }

    public void show() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Board Board = new Board();
            Board.show();
        });
    }
}

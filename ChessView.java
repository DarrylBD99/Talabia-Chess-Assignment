import javax.swing.*;
import java.awt.*;

public class ChessView extends JFrame {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private final JPanel boardPanel;
    private static Piece[][] board = new Piece[ROWS][COLS];

    public ChessView() {
        setTitle("Talabia Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);

        boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        initializeBoard();
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

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

// Represents the main graphical user interface for the chess game.
public class ChessView extends JFrame {
    // Number of rows on the chess board.
    public static final int ROWS = 6;
    // Number of columns on the chess board.
    public static final int COLS = 7;
    // Panel that holds the chess board.
    private final JPanel boardPanel;

    // Constants related to turn change and number of players.
    static final int change_turn_no = 2;
    static final int number_of_players = 2;

    // Variable to keep track of the current turn.
    static int turn = 0;

    // Arrays representing the initial arrangement of pieces on the front and back rows.
    private static final PieceType[] row_format_front =
    {PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT};
    
    private static final PieceType[] row_format_back =
    {PieceType.PLUS, PieceType.HOURGLASS, PieceType.TIME, PieceType.SUN, PieceType.TIME, PieceType.HOURGLASS, PieceType.PLUS};

    // 2D array to represent the chess board and store pieces.
    private static PieceController[][] board = new PieceController[ROWS][COLS];

    // Constructor for the ChessView class.
    public ChessView() {
        // Set the title of the JFrame.
        setTitle("Talabia Chess");
        // Close the application when the JFrame is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the JFrame.
        setSize(700, 600);

        // Create a panel for the chess board using a GridLayout.
        boardPanel = new JPanel(new GridLayout(ROWS, COLS));

        // Initialize the pieces on the chess board.
        initializePieces();
        // Initialize the graphical representation of the chess board.
        initializeBoard();

        // Add the board panel to the JFrame.
        add(boardPanel);
    }

    // Initializes the graphical representation of the chess board.
    private void initializeBoard() {
        // Thickness of the borders around the squares on the chess board.
        int thickness = 2;
        boardPanel.setLayout(new GridLayout(ROWS, COLS));

        // Loop through each row and column to create labels representing chess squares.
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                // Create a JLabel to represent a chess square.
                JLabel square = new JLabel("", JLabel.CENTER);
                // Set a border around the square for visualization.
                square.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153, 255), thickness));

                // Set the icon of the label based on the piece present on the chess square.
                Piece piece = getPiece(j, i);
                if (piece != null && piece.getPieceType() != null) {
                    square.setIcon(piece.getIcon());
                } else {
                    square.setText("Empty");
                }

                // GridBagConstraints for proper positioning in the GridLayout.
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = j;
                gbc.gridy = i;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                // Add the square label to the board panel.
                boardPanel.add(square, gbc);
            }
        }
    }

    // Initializes the pieces on the chess board.
    static void initializePieces() {
        for (int i = 0; i < ROWS; i++) {
            // Create an array to represent a row of pieces.
            PieceType[] pieceRow_type = new PieceType[COLS];

            // Determine whether the row is on the front or back based on its index.
            if (i == 0 || i == (ROWS - 1)) {
                // Initialize a new array with copies of elements from row_format_back.
                pieceRow_type = Arrays.copyOf(row_format_back, row_format_back.length);
            } else if (i == 1 || i == (ROWS - 2)) {
                // Initialize a new array with copies of elements from row_format_front.
                pieceRow_type = Arrays.copyOf(row_format_front, row_format_front.length);
            }

            // Check if the pieceRow has elements.
            if (pieceRow_type.length > 0) {
                // Determine the player index based on the row's position on the board.
                int playerIndex = (i >= ROWS / 2) ? 1 : 2;

                // Loop through each piece in the row.
                PieceController[] pieceRow = new PieceController[COLS];

                for (int j = 0; j < pieceRow.length; j++) {
                    if (pieceRow_type[j] != null) {
                        // Set the player index and piece type based on the actual type of the piece.
                        Piece model = new Piece();
                        model.setPlayerIndex(playerIndex, pieceRow_type[j]);

                        PieceView view = new PieceView();
                        
                        pieceRow[j] = PieceController.get_piece_controller(model, view);
                        
                        // Clone the piece to avoid reference issues.
                        pieceRow[j] = pieceRow[j].clone();

                        // Debug prints
                        System.out.println("Initialized piece at (" + j + ", " + i + ")");
                        System.out.println("Player Index: " + pieceRow[j].model.getPlayerIndex());
                        System.out.println("Piece Type: " + pieceRow[j].model.getPieceType());
                        System.out.println("Icon: " + pieceRow[j].model.getIcon());
                    }
                }

                // Set the row of pieces on the chess board.
                board[i] = pieceRow;
            }
        }
    }

    // Displays the JFrame.
    public void showView() {
        // Center the JFrame on the screen.
        setLocationRelativeTo(null);
        // Set the JFrame as visible.
        setVisible(true);
    }

    // Retrieves the piece at the specified coordinates on the chess board.
    public static Piece getPiece(int x, int y) {
        if (board[y][x] == null) return null;
        return board[y][x].model;
    }

    // Main method to create and show the ChessView instance.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessView chessView = new ChessView();
            // Initialize the pieces again (redundant call from the constructor).
            initializePieces();
            // Show the JFrame.
            chessView.showView();
        });
    }
}

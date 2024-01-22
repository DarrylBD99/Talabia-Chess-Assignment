import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.Map.Entry;

// Represents the main graphical user interface for the chess game.
public class ChessView extends JFrame {
    // Number of rows on the chess board.
    public static final int ROWS = 6;
    // Number of columns on the chess board.
    public static final int COLS = 7;
    // Panel that holds the chess board.
    private final JPanel boardPanel;

    // Size of each square on the chessboard
    private static final int squareSize = 50;

    // Arrays representing the initial arrangement of pieces on the front and back rows.
    private static final PieceType[] row_format_front = {PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT, PieceType.POINT};
    private static final PieceType[] row_format_back = {PieceType.PLUS, PieceType.HOURGLASS, PieceType.TIME, PieceType.SUN, PieceType.TIME, PieceType.HOURGLASS, PieceType.PLUS};

    // 2D array to represent the chess board and store pieces.
    private static PieceController[][] board = new PieceController[ROWS][COLS];
    
    //hashmap for Darryl
    private static HashMap<ChessSquare, int[]> coordinateHashMap = new HashMap<ChessSquare, int[]>();

    // Coordinates of Selected Piece
    public static int[] selected_piece_coords;

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
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                // Create a JButton to represent a chess square.
                ChessSquare square = new ChessSquare();
                square.setFocusPainted(false);
                square.setBackground(Color.WHITE);

                // Set a border around the square for visualization.
                square.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153, 255), thickness));

                // Set the icon of the label based on the piece present on the chess square.
                Piece piece = getPiece(x, y);
                if (piece != null && piece.getPieceType() != null) {
                    ImageIcon icon = new ImageIcon(piece.getIcon().getImage().getScaledInstance(
                            50, 50, Image.SCALE_SMOOTH));
                    square.setIcon(icon);
                } else {
                    square.setText("Empty");
                }

                // GridBagConstraints for proper positioning in the GridLayout.
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                //Add the mouse listener to each button
                square.addMouseListener(new ChessMouseListener(x,y));

                //Add it to the hashmap
                int[] coordinates = new int[2];
                coordinates[0] = x;
                coordinates[1] = y;
                coordinateHashMap.put(square, coordinates);

                // Add the square label to the board panel.
                boardPanel.add(square, gbc);
            }
        }
    }

    // Initializes the pieces on the chess board.
    static void initializePieces() {
        for (int y = 0; y < ROWS; y++) {
            // Create an array to represent a row of pieces.
            PieceType[] pieceRow_type = new PieceType[COLS];

            // Determine whether the row is on the front or back based on its index.
            if (y == 0 || y == (ROWS - 1)) {
                // Initialize a new array with copies of elements from row_format_back.
                pieceRow_type = Arrays.copyOf(row_format_back, row_format_back.length);
            } else if (y == 1 || y == (ROWS - 2)) {
                // Initialize a new array with copies of elements from row_format_front.
                pieceRow_type = Arrays.copyOf(row_format_front, row_format_front.length);
            }

            // Check if the pieceRow has elements.
            if (pieceRow_type.length > 0) {
                // Determine the player index based on the row's position on the board.
                int playerIndex = (y >= ROWS / 2) ? 1 : 2;

                // Loop through each piece in the row.
                PieceController[] pieceRow = new PieceController[COLS];

                for (int x = 0; x < pieceRow.length; x++) {
                    if (pieceRow_type[x] != null) {
                        // Set the player index and piece type based on the actual type of the piece.
                        Piece model = new Piece();
                        model.setPlayerIndex(playerIndex, pieceRow_type[x]);

                        PieceView view = new PieceView();

                        // Create a PieceController and associate it with the model and view.
                        pieceRow[x] = PieceController.get_piece_controller(model, view);

                        // Check if the PieceController is not null before cloning.
                        if (pieceRow[x] != null) {
                            // Clone the piece to avoid reference issues.
                            pieceRow[x] = pieceRow[x].clone();
                        }

                        // Debug prints
                        System.out.println("Initialized piece at (" + x + ", " + y + ")");
                        System.out.println("Player Index: " + pieceRow[x].model.getPlayerIndex());
                        System.out.println("Piece Type: " + pieceRow[x].model.getPieceType());
                        System.out.println("Icon: " + pieceRow[x].model.getIcon());
                    } else {
                        System.out.println("PieceController is null for piece at (" + x + ", " + y + ") - PieceType: " + pieceRow_type[x]);
                    }
                }

                // Set the row of pieces on the chess board.
                board[y] = pieceRow;
            }
        }
    }

    public static void move_piece(int x, int y, int new_x, int new_y)
    {
        if (board[y][x].checkValidMove(x, y, new_x, new_y))
        {
            board[new_y][new_x] = board[y][x];
            board[y][x] = null;
        }

        for (Entry<ChessSquare, int[]> set : coordinateHashMap.entrySet())
        {
            if (set.getValue() == selected_piece_coords)
            {
                set.getKey().set_selected(false);
                selected_piece_coords = null;
                break;
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
        // Check if the board position is not null before accessing the model.
        if (board[y][x] == null) return null;

        return board[y][x].model;
    }

    // Inner class to handle mouse events.
    private class ChessMouseListener implements MouseListener {
        private int x;
        private int y;

        public ChessMouseListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (ChessView.selected_piece_coords != null)
                ChessView.move_piece(ChessView.selected_piece_coords[0], ChessView.selected_piece_coords[1], x, y);
            else if (ChessView.getPiece(x, y) != null)
            {
                ChessSquare button = (ChessSquare) e.getSource();
                button.set_selected(!button.get_selected());

                if (button.get_selected())
                {
                    ChessView.selected_piece_coords = new int[2];
                    ChessView.selected_piece_coords[0] = x;
                    ChessView.selected_piece_coords[1] = y;
                }
                else ChessView.selected_piece_coords = null;
            }
        }

        // Implement other methods of the MouseListener interface if needed.
        @Override
        public void mousePressed(MouseEvent e) {
            // ...
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // ...
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // ...
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // ...
        }
    }

    public class ChessSquare extends JButton {
        private boolean selected = false;

        public boolean get_selected() { return selected; }
        public void set_selected(boolean value) { selected = value; }
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

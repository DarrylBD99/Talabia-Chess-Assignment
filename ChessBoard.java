import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.Map.Entry;

// Represents the main graphical user interface for the chess game.
public class ChessBoard extends JFrame {
    // Number of rows on the chess board.
    public static final int ROWS = 6;
    // Number of columns on the chess board.
    public static final int COLS = 7;

    // Size of each square on the chessboard
    private static final int squareSize = 50;

    // Arrays representing the initial arrangement of pieces on the front and back rows.
    private static final PieceController[] ROW_FORMAT_FRONT = {
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller(),
        PieceFactory.get_point_controller()
    };
    
    private static final PieceController[] ROW_FORMAT_BACK = {
        PieceFactory.get_plus_controller(),
        PieceFactory.get_hourglass_controller(),
        PieceFactory.get_time_controller(),
        PieceFactory.get_sun_controller(),
        PieceFactory.get_time_controller(),
        PieceFactory.get_hourglass_controller(),
        PieceFactory.get_plus_controller()
    };

    // Array representing the types of pieces that can change after switch_turn_check is reset.
    private static final PieceType[] REPLACABLE_PIECE_TYPE = {PieceType.TIME, PieceType.PLUS};

    // Thickness of the borders around the squares on the chess board.
    private static final int THICKNESS = 2;

    // Panel that holds the chess board.
    private static JPanel boardPanel;

    // 2D array to represent the chess board and store pieces.
    private static PieceController[][] board;

    // Hashmap to store the coordinates of each square on the chessboard.
    private static HashMap<int[], ChessSquare> coordinateHashMap;

    // Coordinates of Selected Piece
    public static int[] selected_piece_coords;

    // Variable counter that checks if plus and timer piece should switch
    static int switch_turn_check;

    // Variable to keep track of the current player.
    public static int currentPlayer; // Initialize with Player 1

    // Variables that contains the Sun Pieces of both players. 
    public static Piece[] sun_pieces;

    // Variable to keep track of the rotation state of the board.
    static boolean isBoardRotated; // Keep track of the rotation state

    private static JLabel turnLabel; // JLabel to display the current player's turn

    private static JLabel notifLabel; // JLabel to display the notifications of the game
    private static JButton returnMenu; // JLabel to return to main menu

    // Constructor for the ChessView class.
    public ChessBoard() {
        // Set the title of the JFrame.
        setTitle("Talabia Chess");
        // Close the application when the JFrame is closed.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the JFrame.
        setSize(700, 600);

        // Create a panel for the chess board using a BorderLayout.
        JPanel mainPanel = new JPanel(new BorderLayout());

        //
        board = new PieceController[ROWS][COLS];
        coordinateHashMap = new HashMap<int[], ChessSquare>();
        sun_pieces = new Piece[2];
        isBoardRotated = false;
        currentPlayer = 1;
        switch_turn_check = 0;

        // Create a panel for the chess board using a GridLayout.
        boardPanel = new JPanel(new GridLayout(ROWS, COLS));

        // Initialize the pieces on the chess board.
        initializePieces();
        // Initialize the graphical representation of the chess board.
        initializeBoard();

        // Add the board panel to the main panel in the center.
        mainPanel.add(boardPanel, BorderLayout.CENTER);

        // Initialize the JLabel for turn information
        turnLabel = new JLabel("Current Turn: Player " + currentPlayer);
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the "Return to Main Menu" button under the turn label
        returnMenu = new JButton("Return to Main Menu");
        returnMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current chess game
                boardPanel.removeAll();
                dispose();

                // Launch the main menu
                SwingUtilities.invokeLater(() -> {
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setVisible(true);
                });
            }
        });

        // Create a panel for turnLabel and returnMenu in the south.
        JPanel southPanel = new JPanel(new GridLayout(2, 1));
        southPanel.add(turnLabel);
        southPanel.add(returnMenu);

        // Add the south panel to the main panel in the south.
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        // Initialize the JLabel for game information
        notifLabel = new JLabel("");
        notifLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the notifLabel to the main panel in the north.
        mainPanel.add(notifLabel, BorderLayout.NORTH);

        // Add the main panel to the JFrame.
        add(mainPanel);
    }
    // Initializes the graphical representation of the chess board.
    void initializeBoard() {
        boardPanel.setLayout(new GridLayout(ROWS, COLS));

        // Loop through each row and column to create labels representing chess squares.
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                // Create a JButton to represent a chess square.
                ChessSquare square = new ChessSquare();
                square.setFocusPainted(false);
                square.setBackground(Color.WHITE);

                // Set a border around the square for visualization.
                square.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 153, 255), THICKNESS));
                square.setBackground(Color.WHITE);

                // GridBagConstraints for proper positioning in the GridLayout.
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;

                // Add the mouse listener to each button
                square.addMouseListener(new ChessMouseListener(x, y));

                // Add it to the hashmap
                int[] coords = {x, y};
                coordinateHashMap.put(coords, square);
                System.out.println(coords);
                // Add the square label to the board panel.
                boardPanel.add(square, gbc);
            }
        }

        // Update the pieces on the board.
        UpdatePieces();
    }

    // Update the graphical representation of the pieces on the chess board.
    static void UpdatePieces() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                ChessSquare square = get_square_from_coords(x, y);

                // Set the icon of the label based on the piece present on the chess square.
                Piece piece = getPiece(x, y);
                if (piece != null && board[y][x].get_view() != null) {
                    ImageIcon icon = new ImageIcon(board[y][x].get_view().getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH));
                    square.setIcon(icon);
                } else {
                    square.setIcon(null);
                }
            }
        }
    }

    // Rotate all the pieces on the chess board.
    static void RotateAllPieces() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                // Set the icon of the label based on the piece present on the chess square.
                Piece piece = getPiece(x, y);

                // Rotate the icon
                if (piece != null) board[y][x].rotateIcon(Math.PI);
            }
            
        }
    }

    // Initializes the pieces on the chess board.
    static void initializePieces() {
        for (int y = 0; y < ROWS; y++) {
            // Checks if has existing save
            PieceController[][] pieces = SaveLoad.loadGame();
            if (pieces != null) {
                board = pieces;
                return;
            }
            
            // Create an array to represent a row of pieces.
            PieceController[] piece_row = new PieceController[COLS];
            
            // Determine whether the row is on the front or back based on its index.
            if (y == 0 || y == (ROWS - 1))
            {
                // Initialize a new array with copies of elements from ROW_FORMAT_BACK.
                for (int i = 0; i < ROW_FORMAT_BACK.length; i++)
                    piece_row[i] = ROW_FORMAT_BACK[i].clone();
            }
            else if (y == 1 || y == (ROWS - 2))
            {
                // Initialize a new array with copies of elements from ROW_FORMAT_FRONT.
                for (int i = 0; i < ROW_FORMAT_FRONT.length; i++)
                    piece_row[i] = ROW_FORMAT_FRONT[i].clone();
            }
            // Check if the pieceRow has elements.
            if (piece_row.length > 0) {
                // Determine the player index based on the row's position on the board.
                int playerIndex = (y >= ROWS / 2) ? 1 : 2;

                for (int x = 0; x < piece_row.length; x++) {
                    if (piece_row[x] != null) {
                        piece_row[x].get_model().setPlayerIndex(playerIndex);
                        if (piece_row[x].get_model().getPieceType() == PieceType.SUN)
                        {
                            int index = piece_row[x].get_model().getPlayerIndex() - 1;
                            sun_pieces[index] = piece_row[x].get_model();
                        }

                        // Debug prints
                        System.out.println("Initialized piece at (" + x + ", " + y + ")");
                        System.out.println("Player Index: " + piece_row[x].get_model().getPlayerIndex());
                        System.out.println("Piece Type: " + piece_row[x].get_model().getPieceType());
                        System.out.println("Icon: " + piece_row[x].get_view());
                    } else {
                        System.out.println("PieceController is null for piece at (" + x + ", " + y + ") - PieceType: " + piece_row[x]);
                    }
                }

                // Set the row of pieces on the chess board.
                board[y] = piece_row;
            }
        }
    }

    public static void timer_plus_swap() {
        for (int y = 0; y < ROWS; y++)
        {
            for (int x = 0; x < COLS; x++)
            {
                Piece piece = getPiece(x, y);
                if (piece != null)
                {
                    if (piece.getPieceType() == PieceType.TIME) board[y][x] = PieceFactory.get_plus_controller(piece.getPlayerIndex());
                    if (piece.getPieceType() == PieceType.PLUS) board[y][x] = PieceFactory.get_time_controller(piece.getPlayerIndex());

                    if (Arrays.stream(REPLACABLE_PIECE_TYPE).anyMatch(piece.getPieceType()::equals) && isBoardRotated) board[y][x].rotateIcon(Math.PI);
                }

            }
        }

        switch_turn_check = switch_turn_check % 2;
    }

    static boolean check_win_condition()
    {
        int index = (currentPlayer + 1) % 2;

        for (int y = 0; y < ROWS; y++)
        {
            for (int x = 0; x < COLS; x++)
            {
                if (getPiece(x, y) == sun_pieces[index]) return false;
            }
        }

        return true;
    }

    // Move a chess piece to a new position on the board.
    public static void move_piece(int x, int y, int new_x, int new_y) {
//        System.out.println(x + " " + y);
//        System.out.println(new_x + " " + new_y);

        Piece piece = getPiece(x, y);
        if (piece != null) {

        } else {
            System.out.println("Invalid move - no piece at (" + x + ", " + y + ")");
            return;
        }
        if (board[y][x].checkValidMove(x, y, new_x, new_y)) {
            board[new_y][new_x] = board[y][x];
            board[y][x] = null;

            notifLabel.setText("Player " + currentPlayer + " moves piece: " + piece.getPieceType());
            System.out.println("Player " + currentPlayer + " moves piece: " + piece.getPieceType());
            System.out.println("  Old Position: (" + x + ", " + y + ")");
            System.out.println("  New Position: (" + new_x + ", " + new_y + ")");

            // Check if player has won
            if (check_win_condition())
            {
                // insert player win code
            }

            // Switch to the next player's turn
            currentPlayer = (currentPlayer % 2) + 1;
            
            // checks if timer and plus pieces need to be switched
            if (currentPlayer == 1) switch_turn_check++;
            if (switch_turn_check >= 2) timer_plus_swap();

            // Update the turn label
            turnLabel.setText("Current Turn: Player " + currentPlayer);

            // Rotate the board
            rotateBoard();

        } else {
            System.out.println("Invalid move - not a valid move for the selected piece.");
            notifLabel.setText("Not a valid move for the selected piece.");
        }

        // Deselect the square after the move
        get_square_from_coords(x, y).set_selected(false);
        selected_piece_coords = null;
        // Update the graphical representation of the board.
        UpdatePieces();
    }

    // Rotate the chess board and all the pieces.
    private static void rotateBoard() {
        Component[] components = boardPanel.getComponents();
        boardPanel.removeAll();

        // Rotate the board components
        for (int i = components.length - 1; i >= 0; i--) {
            boardPanel.add(components[i]);
        }

        // Toggle the board rotation state
        isBoardRotated = !isBoardRotated;
        // Rotate all the pieces on the board.
        RotateAllPieces();
        boardPanel.revalidate();
        boardPanel.repaint();
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

        return board[y][x].get_model();
    }

    // Retrieves the ChessSquare from the given coordinates on the chess board.
    public static ChessSquare get_square_from_coords(int x, int y) {
        int x_curr, y_curr;
        for (Entry<int[], ChessBoard.ChessSquare> set : coordinateHashMap.entrySet()) {
            x_curr = set.getKey()[0];
            y_curr = set.getKey()[1];
            if (x == x_curr && y == y_curr) return set.getValue();
        }

        return null;
    }

    // Inner class to handle mouse events.
    private class ChessMouseListener implements MouseListener {
        private int[] coords;

        // Constructor for ChessMouseListener.
        public ChessMouseListener(int x, int y) {
            int[] coords = {x, y};
            this.coords = coords;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (ChessBoard.selected_piece_coords != null) {
                // Move the piece if a piece is selected and the target square is valid.
                ChessBoard.move_piece(ChessBoard.selected_piece_coords[0], ChessBoard.selected_piece_coords[1], coords[0], coords[1]);
            } else if (ChessBoard.getPiece(coords[0], coords[1]) != null) {
                // Select or deselect a piece based on the user's click.
                ChessSquare button = (ChessSquare) e.getSource();
                button.set_selected(!button.get_selected());

                // Set the selected piece coordinates or null if no piece is selected.
                ChessBoard.selected_piece_coords = (button.get_selected()) ? coords : null;
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

    // Inner class representing a square on the chessboard.
    public class ChessSquare extends JButton {
        private boolean selected = false;

        // Getter for the selected property.
        public boolean get_selected() {
            return selected;
        }

        // Setter for the selected property.
        public void set_selected(boolean value) {
            selected = value;
            // Change the background color based on whether the square is selected.
            Color background = (value) ? Color.RED : Color.WHITE;
            setBackground(background);
        }
    }
    
/*
    // Main method to create and show the ChessView instance.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessBoard chessView = new ChessBoard();
            // Initialize the pieces again (redundant call from the constructor).
            initializePieces();
            // Show the JFrame.
            chessView.showView();
        });
    }
*/
}

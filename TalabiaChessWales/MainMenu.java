// Class function: Handles the main menu of the game

package TalabiaChessWales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    static ChessBoard board;


    // Runs the board
    void run_board()
    {
        board = new ChessBoard();
        board.showView();
    }

    public MainMenu() {
        setTitle("Talabia Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create buttons for starting the game and exiting
        JButton continueButton = new JButton("Continue Game");
        JButton startButton = new JButton("Start New Game");
        JButton loadButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to the buttons

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the main menu
                if (board == null)
                {
                    System.err.println("Error: No game is running at this time.");
                    return;
                }
                board.showView();
                dispose();

            }
        });


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the main menu
                dispose();

                // Launch the chess game
                SwingUtilities.invokeLater(() -> {
                    if (board != null) board.dispose();

                    run_board();

                    // Initialize the pieces on the chess board.
                    board.initializePieces();
                });
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the main menu
                dispose();

                // Launch the chess game
                SwingUtilities.invokeLater(() -> {
                    run_board();

                    // Loads pieces from save.
                    if (!board.load_data_from_save())
                    {
                        System.err.println("Error: Save cannot be found. Running new game");
                        board.initializePieces();
                    }

                    board.rotateBoard(board.currentPlayer % 2 == 0);
                    board.UpdatePieces();
                });

                
            }
        });

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(continueButton);
        buttonPanel.add(startButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(exitButton);

        // Add the panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setVisible(true);
        });
    }
}

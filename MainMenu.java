import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    ChessBoard chessboard;


    // Runs the board
    void run_board()
    {
        chessboard = new ChessBoard();
        chessboard.showView();
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
                dispose();

            }
        });


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the main menu
                dispose();

                // Launch the chess game
                SwingUtilities.invokeLater(() -> run_board());
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
                SwingUtilities.invokeLater(() -> run_board());
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

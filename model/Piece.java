import javax.swing.ImageIcon;

// Abstract class representing a generic chess piece.
public class Piece {
    // Player index representing the owner of the piece.
    protected int playerIndex;
    // ImageIcon representing the visual icon of the piece.
    protected ImageIcon icon;
    // Type of the chess piece.
    protected PieceType pieceType;

    // Retrieves the player index of the piece.
    int getPlayerIndex() {
        return playerIndex;
    }

    // Retrieves the type of the piece.
    PieceType getPieceType() {
        return pieceType;
    }

    // Retrieves the visual icon of the piece.
    ImageIcon getIcon() {
        return icon;
    }

    // Sets the player index and piece type of the piece, and loads the corresponding icon.
    void setPlayerIndex(int playerIndex, PieceType pieceType) {
        // Validate playerIndex or pieceType if needed

        // Set the player index and piece type.
        this.playerIndex = playerIndex;
        this.pieceType = pieceType;

        // Set the icon based on playerIndex and pieceType.
        String imagePath = getImagePath(playerIndex, pieceType);
        icon = new ImageIcon(imagePath);

        // Print information about the loaded image.
        System.out.println("Image path: " + imagePath);
        System.out.println("Icon width: " + icon.getIconWidth());
        System.out.println("Icon height: " + icon.getIconHeight());
    }

    // Helper method to generate the image path for the piece.
    private String getImagePath(int playerIndex, PieceType pieceType) {
        // Define the base path as a constant or configuration option.
        String basePath = "src/model/resources";
        // Construct the full image path.
        return String.format("%s/%d/%s.png", basePath, playerIndex, pieceType.toString().toLowerCase());
    }
}

import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.net.URL;

public class PieceView {
    BufferedImage original_image;
    ImageIcon icon;
    URL image_url;
    
    public PieceView(Piece model)
    {
        int playerIndex = model.getPlayerIndex();
        PieceType pieceType = model.getPieceType();

        // Define the base path as a constant or configuration option.
        String path = String.format("resources/%d/%s.png", playerIndex, pieceType.toString().toLowerCase());
        image_url = getClass().getResource(path);
        icon = new ImageIcon(image_url);
        
        // Print information about the loaded image.
        System.out.println("Image path: " + image_url); 
        System.out.println("Icon width: " + icon.getIconWidth());
        System.out.println("Icon height: " + icon.getIconHeight());
    }

    public PieceView(Piece model, int type)
    {
        int playerIndex = model.getPlayerIndex();
        PieceType pieceType = model.getPieceType();

        // Define the base path as a constant or configuration option.
        String path = String.format("resources/%d/%s.png", playerIndex, pieceType.toString().toLowerCase());
        if (type > 0) path = String.format("resources/%d/%s_%d.png", playerIndex, pieceType.toString().toLowerCase(), type);

        image_url = getClass().getResource(path);
        icon = new ImageIcon(image_url);
        
        // Print information about the loaded image.
        System.out.println("Image path: " + image_url); 
        System.out.println("Icon width: " + icon.getIconWidth());
        System.out.println("Icon height: " + icon.getIconHeight());
    }
}

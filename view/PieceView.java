import javax.swing.ImageIcon;

import java.awt.Graphics2D;
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

    // Rotates Image Icon ()
    public void rotateIcon(double angle) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.rotate(angle, w / 2, h / 2);
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        icon = new ImageIcon(image);
    }
}

//Class function: Handles the icons of the pieces in the GUI 

package src.view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import src.PieceType;
import src.model.Piece;

public class PieceView {
    private ImageIcon icon;
    
    public PieceView(Piece model)
    {
        int playerIndex = model.getPlayerIndex();
        PieceType pieceType = model.getPieceType();

        // Define the base path as a constant or configuration option.
        String path = String.format("/res/%d/%s.png", playerIndex, pieceType.toString().toLowerCase());
        
        // Print information about the loaded image.
        System.out.println("Image path: " + path);

        try {
            icon = new ImageIcon(ImageIO.read(getClass().getResource(path)));

            if (model.getRotated()) rotateIcon(Math.PI);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (icon != null)
        {
            System.out.println("Icon width: " + icon.getIconWidth());
            System.out.println("Icon height: " + icon.getIconHeight());
        }
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

    public ImageIcon getIcon() { return icon; }
}

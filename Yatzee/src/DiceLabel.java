/**
 * Project: Yatzee
 * Package: PACKAGE_NAME
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * DiceLabel Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class DiceLabel extends JLabel {
    private int imageHeight, imageWidth;
    private BufferedImage image;

    DiceLabel()
    {
        super();
    }

    DiceLabel(int imageHeight, int imageWidth)
    {
        super();
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
    }


    public void setImage(String path)
    {
        // grab the image
        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // scale the image
        Image scaledImage = image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);

        // create an icon out of the image
        ImageIcon icon = new ImageIcon(scaledImage);

        this.setIcon(icon);
    }
}

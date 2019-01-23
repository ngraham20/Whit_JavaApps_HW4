/**
 * Project: Yatzee
 * Package: PACKAGE_NAME
 */

import javax.swing.*;
import java.util.Random;

/**
 * Dice Object
 * @author Nathaniel Graham
 * @version 1.0
 */
public class Dice implements Runnable {
    private JLabel imageLabel;
    private String imageName;
    private String[] images = {
            "dice_green_1.png",
            "dice_green_2.png",
            "dice_green_3.png",
            "dice_green_4.png",
            "dice_green_5.png",
            "dice_green_6.png"};

    /**
     * Constructor
     * @param label the JLabel to update with a dice image
     */
    Dice(JLabel label)
    {
        try {
            this.imageLabel = label;
            //this.imageLabel.setText("TEMP");
            System.out.println(System.getProperty("user.dir"));
            ImageIcon ic = new ImageIcon("src/dice_green_1.png");
            this.imageLabel.setIcon(ic);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * When run, the dice should roll
     */
    @Override
    public void run() {
        roll();
    }

    /**
     * Roll the dice to display random sides, eventually landing on one
     */
    private void roll()
    {
        Random rand = new Random();
        for(int i = 0; i < rand.nextInt(300)+100; i++) {
            int side = rand.nextInt(5);
            imageName = images[side];
            imageLabel.setIcon(new ImageIcon("src/"+imageName));
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

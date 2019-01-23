/**
 * Project: Yatzee
 * Package: PACKAGE_NAME
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * WindowView Object
 * @author Nathaniel Graham
 * @version 1.0
 */
class WindowView extends JFrame{
    private JLabel dice1 = new JLabel();
    private JLabel dice2 = new JLabel();
    private JLabel dice3 = new JLabel();
    private JLabel dice4 = new JLabel();
    private JLabel dice5 = new JLabel();
    private JButton rollButton = new JButton("Roll");
    private ArrayList<Dice> dice = new ArrayList<>();
    private ArrayList<JLabel> diceLabels = new ArrayList<>();

    /**
     * Default Constructor
     */
    WindowView()
    {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,400);
        this.setLayout(new FlowLayout());

        init();
    }

    /**
     * Initialize variables and objects
     */
    void init()
    {

        dice.add(new Dice(dice1));
        dice.add(new Dice(dice2));
        dice.add(new Dice(dice3));
        dice.add(new Dice(dice4));
        dice.add(new Dice(dice5));

        this.add(dice1);
        this.add(dice2);
        this.add(dice3);
        this.add(dice4);
        this.add(dice5);

        this.add(rollButton);

        listeners();
    }

    /**
     * Activate any listeners used
     */
    private void listeners()
    {
        rollButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roll();
            }
        });
    }

    /**
     * Roll all dice
     */
    private void roll()
    {
        for(Dice dice : this.dice)
        {
            Thread thread = new Thread(dice);
            thread.start();

        }
    }
}

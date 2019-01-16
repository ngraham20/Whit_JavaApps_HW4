/**
 * Project: Yatzee
 * Package: PACKAGE_NAME
 */

import javax.swing.*;
import java.awt.*;

/**
 * WindowView Object
 * @author Nathaniel Graham
 * @version 1.0
 */
class WindowView {
    private JLabel dice1 = new DiceLabel();
    private JLabel dice2 = new DiceLabel();
    private JLabel dice3 = new DiceLabel();
    private JLabel dice4 = new DiceLabel();
    private JLabel dice5 = new DiceLabel();
    private JPanel diceZone = new JPanel(new GridBagLayout());
    private JPanel buttonZone = new JPanel(new BorderLayout());

    private GridBagConstraints constraints = new GridBagConstraints();

    WindowView()
    {

    }

    void init()
    {
        constraints.gridwidth = 5;
    }
}

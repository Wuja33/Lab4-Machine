package appswing.tools;

import javax.swing.*;
import java.awt.*;

public abstract class AppTools {
    public static JPanel createPanel(JLabel label, JTextField textField, JButton button)
    {
        JPanel panel = new JPanel();
        //panel.setPreferredSize(new Dimension(100,100));
        panel.setLayout(new BorderLayout());
        panel.add(label,BorderLayout.NORTH);
        panel.add(textField,BorderLayout.CENTER);
        panel.add(button,BorderLayout.EAST);
        return panel;
    }
    public static int getValueFromText(String string, int min, int max)
    {
        int helpInt;
        try {
            helpInt = Integer.parseInt(string);
        } catch (Exception e) {
            helpInt = 0;
        }

        if (helpInt>min && helpInt<max) //sprawdź czy mieści się w zadanym przedziale
            return helpInt;
        else
            return 0;
    }
}


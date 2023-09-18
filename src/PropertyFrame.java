import javax.swing.*;
import java.awt.*;

public class PropertyFrame extends JFrame {
    JButton submit = new JButton("submit");

    JTextField width = new JTextField();
    JTextField pixelsWidth = new JTextField();

    JTextField height = new JTextField();
    JTextField pixelsHeight = new JTextField();

    PropertyFrame() {
        setSize(800,200);
        setLayout(new GridLayout(3,1));


        JPanel widthPanel = new JPanel();
        widthPanel.add(new JLabel("Enter width"));
        width.setColumns(10);
        pixelsWidth.setColumns(10);
        widthPanel.add(width);
        widthPanel.add(new JLabel("Enter amount of pixels in width"));
        widthPanel.add(pixelsWidth);
        add(widthPanel);

        JPanel heightPanel = new JPanel();
        heightPanel.add(new JLabel("Enter height"));
        height.setColumns(10);
        pixelsHeight.setColumns(10);
        heightPanel.add(height);
        heightPanel.add(new JLabel("Enter amount of pixels in height"));
        heightPanel.add(pixelsHeight);
        add(heightPanel);

        JPanel submitPanel = new JPanel();
        submitPanel.add(submit);
        submit.setActionCommand("submit");
        add(submitPanel);

        setVisible(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main implements MouseListener, ActionListener {

    PropertyFrame propertyFrame = new PropertyFrame();
    JButton propertyButton = new JButton("Set property");
    JLabel label = new JLabel("len = 0.0");
    JButton clear = new JButton("clear");

    ArrayList<Integer> allX = new ArrayList<>();
    ArrayList<Integer> allY = new ArrayList<>();

    int x,y;
    double len = 0;

    int pixelsWidthAmount = 1;
    int pixelsHeightAmount = 1;
    int widthAmount = 1;

    int heightAmount = 1;

    double widthKoef = 1;
    double heightKoef = 1;

    final JFileChooser fc = new JFileChooser();
    JButton fileButton = new JButton("File");
    JFrame frame;

    JPanel panel1;

    public Main() throws IOException {
        frame = new MainFrame();
        BufferedImage bufferedImage = ImageIO.read(new File("Picture.png"));
        Image scaled = bufferedImage.getScaledInstance(bufferedImage.getWidth(),bufferedImage.getHeight(),Image.SCALE_SMOOTH);
        frame.setSize(scaled.getWidth(null) + 100, scaled.getHeight(null) + 100);
        JLabel image = new JLabel(new ImageIcon(scaled));
        fileButton.setActionCommand("file");
        JPanel topPanel = new JPanel();
        topPanel.add(label);
        topPanel.add(clear);
        topPanel.add(fileButton);
        topPanel.add(propertyButton);
        panel1 = new JPanel();
        panel1.add(image);
        frame.add(new JMenuBar());
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel1,BorderLayout.CENTER);
        frame.getContentPane().add(topPanel,BorderLayout.NORTH);


        propertyFrame.submit.addActionListener(this);
        fileButton.addActionListener(this);
        clear.addActionListener(this);
        propertyButton.setActionCommand("property");
        propertyButton.addActionListener(this);

        frame.addMouseListener(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        if(allY.size() > 0) len += Math.sqrt((allX.get(allX.size()-1) - x) * (allX.get(allX.size()-1) - x) / (widthKoef*widthKoef) + (allY.get(allY.size()-1) - y) * (allY.get(allY.size()-1) - y)/(heightKoef*heightKoef));
        allX.add(x);
        allY.add(y);

        Graphics g = frame.getGraphics();

        g.setColor(Color.CYAN);

        g.fillRect(x-3,y-3,6,6);

        if(allY.size() > 1) g.drawLine(allX.get(allX.size()-2),allY.get(allY.size()-2),x,y);

        label.setText(String.format("len = %.2f",len));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("clear")){
            len = 0;
            allY.clear();
            allX.clear();
            label.setText("len = " + len);
            frame.repaint();
        } else if(e.getActionCommand().equals("submit")) {
            widthAmount = Integer.parseInt(propertyFrame.width.getText());
            pixelsWidthAmount = Integer.parseInt(propertyFrame.pixelsWidth.getText());
            heightAmount = Integer.parseInt(propertyFrame.height.getText());
            pixelsHeightAmount = Integer.parseInt(propertyFrame.pixelsHeight.getText());
            widthKoef = (float) pixelsWidthAmount / widthAmount;
            heightKoef = (float) pixelsHeightAmount / heightAmount;
        } else if(e.getActionCommand().equals("property")) {
            propertyFrame.setLocation( frame.getWidth()/2 + frame.getX() - propertyFrame.getWidth()/2,frame.getHeight()/2 + frame.getY() - propertyFrame.getHeight()/2);
            propertyFrame.setVisible(true);
        } else if (e.getActionCommand().equals("file")) {
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = ImageIO.read(fc.getSelectedFile());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Image scaled = bufferedImage.getScaledInstance(bufferedImage.getWidth(),bufferedImage.getHeight(),Image.SCALE_SMOOTH);
                frame.setSize(scaled.getWidth(null) + 100, scaled.getHeight(null) + 100);
                JLabel image = new JLabel(new ImageIcon(scaled));
                panel1.remove(0);
                panel1.add(image);
            }
        }
    }
}

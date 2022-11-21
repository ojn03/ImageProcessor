package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import res.RGB;

public class ImPGUI implements ImageProcessorView {

  private JFrame main = new JFrame("Image Processor by Abi and Olivier");
  private CommandsPanel commandsPanel = new CommandsPanel();
  private ImageNamesPanel namesPanel = new ImageNamesPanel();
  private HistogramPanel histogramPanel = new HistogramPanel();
  private ImagePanel imagePanel = new ImagePanel();

  public ImPGUI() {
    main.setMinimumSize(new Dimension(100, 500));
    main.setPreferredSize(new Dimension(1500, 900));
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    main.add(commandsPanel, BorderLayout.EAST);
    main.add(namesPanel, BorderLayout.WEST);
    main.add(histogramPanel, BorderLayout.SOUTH);
    main.add(imagePanel, BorderLayout.CENTER);

    main.pack();
    main.setLocationRelativeTo(null);
    main.setVisible(true);
  }


  public static void main(String[] args) {
    new ImPGUI();
  }

  /**
   * renders a message to the view console.
   *
   * @param message is the message to be conveyed
   */
  @Override
  public void renderMessage(String message) {

  }

  /**
   * updates the view to show the most recent changes.
   */
  @Override
  public void update() {

  }

}


class ImageNamesPanel extends JPanel {

  JPanel inner = new JPanel(new GridLayout(0, 1));
  JPanel viewPort = new JPanel();
  JScrollPane scroll = new JScrollPane(viewPort);


  public ImageNamesPanel() {


    Font Arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Images", 0, 0, Arial20);
    this.setBorder(border);
    scroll.setBorder(BorderFactory.createEmptyBorder());
    viewPort.setBorder(BorderFactory.createEmptyBorder());
    inner.setBorder(BorderFactory.createEmptyBorder());

    JButton b1 = new JButton("b1");
    JButton b2 = new JButton("b2");
    JButton b3 = new JButton("b3");
    inner.add(b1);
    inner.add(b2);
    inner.add(b3);

    viewPort.add(inner);


    scroll.setPreferredSize(new Dimension(100, 1000));
//    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.add(scroll);

  }


  public void addImage(String newimg) {
    JButton b = new JButton(newimg);
    JPanel p = new JPanel();
    inner.add(b);
    scroll.add(p);
  }

}

class ImagePanel extends JPanel {
  JPanel canvas;
  BufferedImage img;

  ImagePanel() {
    try {
      img = ImageIO.read(new File("/Users/olivier-john/Desktop/OOD/ImageProcessor/src/res/images/nebula.jpg"));
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }

    canvas = new JPanel() {
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, (int) canvas.getPreferredSize().getWidth() / 2, (int) canvas.getPreferredSize().getHeight() / 2, canvas);
      }
    };

    canvas.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
    JScrollPane imageScroll = new JScrollPane(canvas);
    imageScroll.setPreferredSize(new Dimension(500, 500));
    add(imageScroll, BorderLayout.CENTER);
//    setVisible(true);
    setPreferredSize(new Dimension(600, 600));
  }

  public void setImage(RGB[][] rgbs) {
    int h = rgbs.length;
    int w = rgbs[0].length;
    BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    for (int j = 0; j < w; j++) {
      for (int i = 0; i < h; i++) {
        int r = rgbs[i][j].getRed();
        int g = rgbs[i][j].getGreen();
        int b = rgbs[i][j].getBlue();

        int p = (1 << 24);
        p |= (r << 16);
        p |= (g << 8);
        p |= b;

        buf.setRGB(j, i, p);
      }
    }
    //todo ?
    this.img = buf;
    Graphics g = buf.getGraphics();
    canvas.update(g);

  }

}

class HistogramPanel extends JPanel {


}

class CommandsPanel extends JPanel {
  CommandsPanel() {
    Font Arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Commands", 0, 0, Arial20);

    setBorder(border);
    setLayout(new GridLayout(0, 1));

    //add command buttons to commandpanel
    JButton uploadButton = new JButton("Upload");
    JButton downloadButton = new JButton("Download");
    JButton visualizeButton = new JButton("Visualize");
    JButton sepiaButton = new JButton("Sepia Filter");
    JButton greyScaleButton = new JButton("GreyScale Filter");
    JButton flipVButton = new JButton("Vertical Flip");
    JButton flipHButton = new JButton("Horizontal Flip");
    JButton brightnessButton = new JButton("Adjust Brightness");
    JButton blurButton = new JButton("Blur");
    JButton sharpenButton = new JButton("Sharpen");

    add(uploadButton);
    add(downloadButton);
    add(flipVButton);
    add(flipHButton);
    add(visualizeButton);
    add(brightnessButton);
    add(sepiaButton);
    add(greyScaleButton);
    add(blurButton);
    add(sharpenButton);

  }

  public void setListener(ActionListener a) {


  }

}

class Testing {
  public void buildGUI() {
    JFrame f = new JFrame();
    f.getContentPane().add(new MyMenu());
    f.pack();
    f.setLocationRelativeTo(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new Testing().buildGUI();
      }
    });
  }
}

class MyMenu extends JPanel {
  JButton a = new JButton("test");
  JButton b = new JButton("test1");
  JButton c = new JButton("test3");
  JButton d = new JButton("test4");
  JButton e = new JButton("test5");
  JButton f = new JButton("test6");
  JButton g = new JButton("test7");
  JButton h = new JButton("test8");

  MyMenu() {
    JPanel p = new JPanel();//<-------------
    JPanel me = new JPanel();
    me.setLayout(new GridLayout(0, 2));
    me.add(a);
    me.add(b);
    me.add(c);
    me.add(d);
    me.add(e);
    me.add(f);
    me.add(g);
    me.add(h);
    p.add(me);//<-------------
    //JScrollPane spane = new JScrollPane(me);
    JScrollPane spane = new JScrollPane(p);//<-------------
    spane.setPreferredSize(new Dimension(100, 100));
    add(spane);
  }
}
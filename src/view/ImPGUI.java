package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import model.ImageProcessorModel;
import res.ImageFileUtil;
import res.RGB;

public class ImPGUI implements GuiView {

  ImageProcessorModel m;
  ActionListener a;

  private final JFrame main = new JFrame("Image Processor by Abi and Olivier");
  private final CommandsPanel commandsPanel = new CommandsPanel();
  private final ImageNamesPanel namesPanel = new ImageNamesPanel();
  private final HistogramPanel histogramPanel = new HistogramPanel();
  private final ImagePanel imagePanel = new ImagePanel();

  public ImPGUI(ImageProcessorModel m) {
    this.m = m;

    main.setMinimumSize(new Dimension(1000, 700));
    main.setPreferredSize(new Dimension(1500, 900));
    main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    main.setLayout(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();
    c.weightx = .25;
    c.weighty = .25;
    c.fill = GridBagConstraints.VERTICAL;
    c.gridx = 2;
    c.gridy = 0;
    c.gridheight = 3;
    main.add(commandsPanel, c);

    c.fill = GridBagConstraints.VERTICAL;
    c.gridx = 0;
    c.gridy = 0;
    main.add(namesPanel, c);

    c.ipady = 500;
    c.ipadx = 750;
    c.weightx = .5;
    c.gridx = 1;
    c.gridy = 0;
    c.gridheight = 2;
    main.add(imagePanel, c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 2;
    c.ipadx = 750;
    c.ipady = 400;
    c.gridheight = 1;
    main.add(histogramPanel, c);

    main.pack();
    main.setLocationRelativeTo(null);
    main.setVisible(true);
  }

  /**
   * renders a message to the view console.
   *
   * @param message is the message to be conveyed
   */
  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(main, message);
  }

  @Override
  public void addImageName(String name) {
    namesPanel.addImage(name, a);
  }

  /**
   * set the listener of all the components.
   *
   * @param a the listener which to set it to
   */
  @Override
  public void setListener(ActionListener a) {
    this.a = a;
    commandsPanel.setListener(a);
  }

  @Override
  public void setImage(String imgName) {
    //update shown Image
    RGB[][] rgbs = m.getImage(imgName);
    imagePanel.setImage(rgbs);
    histogramPanel.updateHistogram(m.getHistogram(imgName));
  }
}

class ImageNamesPanel extends JPanel {

  JPanel inner = new JPanel(new GridLayout(0, 1));
  JPanel viewPort = new JPanel();
  JScrollPane scroll = new JScrollPane(viewPort);

  public ImageNamesPanel() {

    setMinimumSize(new Dimension(100, 750));
    Font Arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Images", 0, 0, Arial20);
    this.setBorder(border);
    scroll.setBorder(BorderFactory.createEmptyBorder());
    viewPort.setBorder(BorderFactory.createEmptyBorder());
    inner.setBorder(BorderFactory.createEmptyBorder());
    viewPort.add(inner);

    this.add(scroll);

  }

  public void addImage(String newimg, ActionListener a) {
    JButton b = new JButton(newimg);
    b.setActionCommand(newimg);
    b.addActionListener(a);
    inner.add(b);
    inner.repaint();
    inner.revalidate();
  }
}

class ImagePanel extends JPanel {
  JLabel imglabel = new JLabel();

  ImagePanel() {
    BufferedImage img;
    try {
      img = ImageIO.read(new File("/Users/olivier-john/Desktop/OOD/ImageProcessor/src/res/images/nebula.jpg"));
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    ImageIcon icon = new ImageIcon(img);
//todo label/icon is slow. fix this
    JPanel jp = new JPanel(new GridBagLayout());
    jp.add(imglabel);
    JScrollPane imageScroll = new JScrollPane(jp);
    imageScroll.setPreferredSize(new Dimension(750, 500));
    this.add(imageScroll);
    imglabel.setIcon(icon);
  }

  void setImage(RGB[][] rgbs) {

    BufferedImage buf = ImageFileUtil.toBuf(rgbs);
    ImageIcon icon = new ImageIcon(buf);
    imglabel.setIcon(icon);
  }
}

class HistogramPanel extends JPanel {
  private int[][] data;
  private int maxFreq;
  private int maxVal;


  HistogramPanel() {
    this.data = new int[][]{{}, {}, {}, {}};

    this.maxFreq = calculateMaxF();
    this.maxVal = calculateMaxV();

    setPreferredSize(new Dimension(600, 300));
    setBorder(BorderFactory.createTitledBorder("Histogram"));

  }

  static int max(int[] arr) {
    int max = 0;

    for (int i : arr) {
      if (i > max) max = i;
    }
    return max;
  }

  private int calculateMaxF() {
    int rmax = data[0].length == 0 ? 0 : max(data[0]);
    int gmax = data[1].length == 0 ? 0 : max(data[1]);
    int bmax = data[2].length == 0 ? 0 : max(data[2]);
    int imax = data[3].length == 0 ? 0 : max(data[3]);
    return (Math.max(rmax, Math.max(gmax, Math.max(bmax, imax))));
  }

  private int calculateMaxV() {
    int rmaxv = data[0].length - 1;
    int gmaxv = data[1].length - 1;
    int bmaxv = data[2].length - 1;
    int imaxv = data[3].length - 1;
    return Math.max(rmaxv, Math.max(gmaxv, Math.max(bmaxv, imaxv)));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int lenGraph = getWidth() - 75;

    int topGraph = 25;
    int bottomGraph = getHeight() - 50;
    int leftGraph = 50;
    int rightGraph = 50 + lenGraph;
    int heightGraph = getHeight() - 75;
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.white);
    //setting graph background
    g2.fillRect(leftGraph, topGraph, lenGraph, heightGraph);


    //setting hatchmarks
    int xPad = lenGraph / 10;
    int ypad = (getHeight() - 75) / 10;

    g2.setColor(Color.darkGray);

    // dashed stroke
    Stroke dashed = new BasicStroke(.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{9}, 0);
    g2.setStroke(dashed);
    for (int i = 1; i < 10; i++) {
      //vertical marks
      g2.drawLine(xPad * i + 50, topGraph, xPad * i + 50, bottomGraph);

      String num = String.format("%d", (((maxVal * i) / 10) + 1));
      g2.drawString(num, xPad * i + 40, getHeight() - 25);

      //horizontal marks
      if (i < 5) {
        g2.drawLine(leftGraph, bottomGraph - ypad * 2 * i, rightGraph, bottomGraph - ypad * 2 * i);
        num = String.format("%d", (((maxFreq * i) / 5)));
        g2.drawString(num, 20, bottomGraph - ypad * 2 * i);
      }
    }




    //draws the lines
    int[] redData = data[0];
    int rmax = max(redData);
    int[] greenData = data[1];
    int[] blueData = data[2];
    int[] iData = data[3];

    g2.setColor(Color.red);
    g2.setStroke(new BasicStroke());
    for (int i = 0; i < redData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(redData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(redData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.green);
    for (int i = 0; i < greenData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(greenData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(greenData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.blue);
    for (int i = 0; i < blueData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(blueData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(blueData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.black);
    for (int i = 0; i < iData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(iData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(iData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

  }

  void updateHistogram(int[][] newHistogram) {
    this.data = newHistogram;
    this.maxFreq = calculateMaxF();
    this.maxVal = calculateMaxV();
    this.repaint();
  }
}


class CommandsPanel extends JPanel {
  JButton uploadButton;
  JButton downloadButton;
  JButton visualizeButton;
  JButton sepiaButton;
  JButton greyScaleButton;
  JButton flipVButton;
  JButton flipHButton;
  JButton brightnessButton;
  JButton blurButton;
  JButton sharpenButton;

  CommandsPanel() {
    Font Arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Commands", 0, 0, Arial20);

    setBorder(border);
    setLayout(new GridLayout(0, 1));

    //add command buttons to commandpanel, as well as their action calls
    uploadButton = new JButton("Upload");
    uploadButton.setActionCommand("/Upload");
    downloadButton = new JButton("Download");
    downloadButton.setActionCommand("/Download");
    visualizeButton = new JButton("Visualize");
    visualizeButton.setActionCommand("/Visualize");
    sepiaButton = new JButton("Sepia Filter");
    sepiaButton.setActionCommand("/Sepia");
    greyScaleButton = new JButton("GreyScale Filter");
    greyScaleButton.setActionCommand("/GreyScale");
    flipVButton = new JButton("Vertical Flip");
    flipVButton.setActionCommand("/FlipV");
    flipHButton = new JButton("Horizontal Flip");
    flipHButton.setActionCommand("/FlipH");
    brightnessButton = new JButton("Adjust Brightness");
    brightnessButton.setActionCommand("/Brightness");
    blurButton = new JButton("Blur");
    blurButton.setActionCommand("/Blur");
    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("/Sharpen");

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

  void setListener(ActionListener a) {
    uploadButton.addActionListener(a);
    downloadButton.addActionListener(a);
    visualizeButton.addActionListener(a);
    sepiaButton.addActionListener(a);
    greyScaleButton.addActionListener(a);
    flipVButton.addActionListener(a);
    flipHButton.addActionListener(a);
    brightnessButton.addActionListener(a);
    blurButton.addActionListener(a);
    sharpenButton.addActionListener(a);

  }

}

package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.ImageProcessorModel;
import res.RGB;

/**
 * A view to represent all gui operations and visuals.
 */
public class ImPGUI implements GuiView {

  private final ImageProcessorModel m;
  private ActionListener a;

  private final JFrame main = new JFrame("Image Processor by Abi and Olivier");
  private final CommandsPanel commandsPanel = new CommandsPanel();
  private final ImageNamesPanel namesPanel = new ImageNamesPanel();
  private final HistogramPanel histogramPanel = new HistogramPanel();
  private final ImagePanel imagePanel = new ImagePanel();

  /**
   * Makes a gui view for the given model.
   *
   * @param m the model to represent.
   */
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
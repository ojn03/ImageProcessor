package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

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

  protected CommandsPanel() {
    Font arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Commands", 0, 0, arial20);

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

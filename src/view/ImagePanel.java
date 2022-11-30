package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import res.ImageFileUtil;
import res.RGB;

class ImagePanel extends JPanel {
  protected JLabel imglabel = new JLabel();

  protected ImagePanel() {
    ImageIcon icon = new ImageIcon();
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

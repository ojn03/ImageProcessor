package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

class ImageNamesPanel extends JPanel {

  protected JPanel inner = new JPanel(new GridLayout(0, 1));

  public ImageNamesPanel() {

    setMinimumSize(new Dimension(200, 750));
    Font arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Images", 0, 0, arial20);
    this.setBorder(border);
    inner.setBorder(BorderFactory.createEmptyBorder());

    this.add(inner);

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

package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

class CommandsPanel extends JPanel {
  List<JButton> buttons = new ArrayList<>();


  protected CommandsPanel() {
    Font arial20 = new Font("Arial", Font.ITALIC, 20);
    Border border = BorderFactory.createTitledBorder(null, "Commands", 0, 0, arial20);

    setBorder(border);
    setLayout(new GridLayout(0, 1));

    //add command buttons to commandpanel, as well as their action calls
    buttons.add(new JButton("Upload"));
    buttons.add(new JButton("Download"));
    buttons.add(new JButton("Visualize"));
    buttons.add(new JButton("Sepia Filter"));
    buttons.add(new JButton("GreyScale Filter"));
    buttons.add(new JButton("Vertical Flip"));
    buttons.add(new JButton("Horizontal Flip"));
    buttons.add(new JButton("Adjust Brightness"));
    buttons.add(new JButton("Blur"));
    buttons.add(new JButton("Sharpen"));
    buttons.add(new JButton("DownSize"));
    for (JButton jb : buttons) {
      jb.setActionCommand("/" + jb.getText());
      add(jb);
    }
  }

  void setListener(ActionListener a) {
    for (JButton jb : buttons) {
      jb.addActionListener(a);
    }
  }
}

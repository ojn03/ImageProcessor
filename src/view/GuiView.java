package view;

import java.awt.event.ActionListener;

/**
 * Operations for the visual representation of a gui.
 */
public interface GuiView extends ImageProcessorView {

  void setListener(ActionListener a);


  void addImageName(String name);

  /**
   * changes the Image shown in the curren Gui and updates the histogram.
   *
   * @param imgName is the name of the image to update the view with.
   */
  void setImage(String imgName);
}

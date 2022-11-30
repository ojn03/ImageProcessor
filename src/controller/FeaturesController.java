package controller;

import java.awt.event.ActionListener;

/**
 * Operations to manage user input in GUIs.
 */
public interface FeaturesController extends ActionListener, ImageProcessorController {

  /**
   * Uses a file chooser to get the file path of the desired image.
   *
   * @return the file path of the image.
   */
  String openFile();

  String saveFile();


  /**
   * gets input using dialog pane.
   *
   * @return user input
   */
  String choose(String title, String message);

}

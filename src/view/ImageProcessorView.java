package view;

/**
 * visualizes an imageprocessor model and controller.
 */
public interface ImageProcessorView {

  /**
   * renders a message to the view console.
   *
   * @param message is the message to be conveyed
   */
  void renderMessage(String message);

  /**
   * updates the view the most recent changes.
   */
  void update();
}

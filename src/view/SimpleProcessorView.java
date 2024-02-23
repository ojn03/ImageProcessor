package view;

import java.io.IOException;

import model.ImageProcessorModel;

/**
 * visualizes a processor model and controller.
 */
public class SimpleProcessorView implements ImageProcessorView {
  private final Appendable destination;

  /**
   * creates a new simpleprocessor with the given model and defaults destination.
   *
   * @param model the model for which the view represents
   */
  public SimpleProcessorView(ImageProcessorModel model) {
    this(model, System.out);
  }

  /**
   * creates a new simpleprocessor with the given model and  destination.
   *
   * @param model the model for which the view represents
   * @param dest  represents where the model will be visualized
   */
  public SimpleProcessorView(ImageProcessorModel model, Appendable dest) {
    if (model == null || dest == null) {
      throw new IllegalArgumentException("null arguments");
    }
    this.destination = dest;
  }

  @Override
  public void renderMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("null arguments");
    }
    try {
      destination.append(message).append("\n");
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

}

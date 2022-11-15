package imagecommand;

import model.ImageProcessorModel;

/**
 * represents operations that can be applied on an imageprocessor model.
 */
public interface ImageProcessorCommand {

  /**
   * applies the command onto the given model.
   *
   * @param m is the model that will be modified by the command.
   */
  void runt(ImageProcessorModel m);
}

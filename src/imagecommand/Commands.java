package imagecommand;

import model.ImageProcessorModel;

/**
 * Abstract class representing all Image Commands.
 */
public abstract class Commands implements ImageProcessorCommand {

  String img;
  String newName;

  /**
   * Constructs a Command.
   *
   * @param img     the name of image the command will be applied to.
   * @param newName the new name of the image after the command was applied.
   */

  public Commands(String img, String newName) {
    this.img = img;
    this.newName = newName;
  }


  /**
   * applies the command onto the given model.
   *
   * @param m is the model that will be modified by the command.
   */
  @Override
  public abstract void runt(ImageProcessorModel m);
}

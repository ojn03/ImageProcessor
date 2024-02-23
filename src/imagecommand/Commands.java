package imagecommand;

/**
 * Abstract class representing all Image Commands.
 */
public abstract class Commands implements ImageProcessorCommand {

  protected String img;
  protected String newName;

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


}

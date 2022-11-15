package imagecommand;

import model.ImageProcessorModel;


/**
 * A command function-object to adjust the brightness of an image in a model.
 */
public class AdjustBrightness extends Commands {


  private final int adjustment;

  /**
   * A constructor to hold arguments used for the AdjustBrightness command.
   *
   * @param imgname    the name of the image to be affected
   * @param newname    the name at which the affected image will be saved
   * @param adjustment is the amount used to adjust the image
   */
  public AdjustBrightness(int adjustment, String imgname, String newname) {
    super(imgname, newname);
    this.adjustment = adjustment;
  }


  @Override
  public void runt(ImageProcessorModel m) {
    m.adjustBrightness(img, newName, adjustment);
  }

}

package imagecommand;

import model.ImageProcessorModel;
import res.ImageModel;

/**
 * represents operations that can be applied on an imageprocessor model.
 */
public interface ImageProcessorCommand {

  /**
   * creates an image with the applied command.
   *
   * @param m the model containing the image to apply the command to
   * @return an edited image
   */
  ImageModel create(ImageProcessorModel m);

  /**
   * gets newName.
   *
   * @return the newName.
   */
  String newName();

  /**
   * gets original name.
   *
   * @return imgname
   */
  String imgName();
}

package imagecommand;

import model.ImageProcessorModel;

/**
 * A command function-object to upload an image from a filepath into the processor.
 */
public class Upload implements ImageProcessorCommand {
  String filepath;
  String imgName;

  /**
   * A constructor to hold arguments used for the upload command.
   *
   * @param filepath file path from which an image will be loaded
   * @param imgName  the name given to the image in the file processor
   */
  public Upload(String filepath, String imgName) {
    this.filepath = filepath;
    this.imgName = imgName;
  }


  @Override
  public void runt(ImageProcessorModel m) {
    m.upload(filepath, imgName);
  }
}

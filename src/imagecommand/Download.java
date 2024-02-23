//package imagecommand;
//
//import model.ImageProcessorModel;
//
///**
// * A command function-object to download an image from the image processor to a filepath.
// */
//public class Download implements ImageProcessorCommand {
//  private final String filepath;
//  private final String imgName;
//
//  /**
//   * A constructor to hold arguments used for the Download command.
//   *
//   * @param filepath is the file path where the image will be downloaded
//   * @param imgname  is the name of the image to be downloaded
//   */
//  public Download(String imgname, String filepath) {
//    this.filepath = filepath;
//    this.imgName = imgname;
//  }
//
//
//  @Override
//  public void runt(ImageProcessorModel m) {
//    m.download(filepath, imgName);
//  }
//}

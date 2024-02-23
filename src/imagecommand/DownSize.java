package imagecommand;

import model.ImageProcessorModel;
import res.ImageModel;
import res.SimpleImage;


/**
 * function object to downsize an image to given dimensions.
 */
public class DownSize extends Commands {
  private final int h;
  private final int w;

  /**
   * creates a Downsize object to apply to an image.
   *
   * @param h   the new height
   * @param w   the new width
   * @param img the name of the original image
   * @param nn  the name of the new image
   */
  public DownSize(int h, int w, String img, String nn) {
    super(img, nn);
    this.h = h;
    this.w = w;
  }

  @Override
  public ImageModel create(ImageProcessorModel m) {
    return new SimpleImage(m.getImage(img)).downSize(h, w);
  }

  @Override
  public String newName() {
    return newName;
  }

  @Override
  public String imgName() {
    return img;
  }
}

package imagecommand;

import java.util.function.Function;

import model.ImageProcessorModel;
import res.ImageModel;
import res.SimpleImage;

/**
 * Applies a simple edit on to the given image.
 */
public class Edit extends Commands {
  private final Function<ImageModel, ImageModel> f;


  /**
   * constructs a command to apply an edit to an image command.
   *
   * @param img     the original image name
   * @param newName the new image name
   * @param func    the function to apply
   */
  public Edit(String img, String newName, Function<ImageModel, ImageModel> func) {
    super(img, newName);
    this.f = func;
  }

  @Override
  public ImageModel create(ImageProcessorModel m) {
    return f.apply(new SimpleImage(m.getImage(img)));
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
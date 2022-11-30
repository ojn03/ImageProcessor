package imagecommand;

import java.util.function.Function;

import model.ImageProcessorModel;
import res.ImageModel;

/**
 * Applies a simple edit on to the given image.
 */
public class Edit extends Commands {
  private final Function<ImageModel, ImageModel> f;

  public Edit(String img, String newName, Function<ImageModel, ImageModel> func) {
    super(img, newName);
    this.f = func;
  }

  public void runt(ImageProcessorModel m) {
    m.edit(img, newName, f);
  }
}

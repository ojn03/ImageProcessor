package imagecommand;

import model.ImageProcessorModel;
import res.ImageModel;
import res.RGB;
import res.SimpleImage;

/**
 * class to apply a mask over a command on an image.
 */
public class Mask implements ImageProcessorCommand {

  private final RGB[][] mask;
  private final ImageProcessorCommand cmd;

  /**
   * creates the msak command using the given command to apply the mask to.
   *
   * @param cmd  the command to mask.
   * @param mask the mask used.
   */
  public Mask(ImageProcessorCommand cmd, RGB[][] mask) {
    this.cmd = cmd;
    this.mask = mask;
  }

  @Override
  public ImageModel create(ImageProcessorModel m) {
    RGB[][] alt = cmd.create(m).getImage();
    RGB[][] og = m.getImage(cmd.imgName());

    if (alt.length != mask.length || alt[0].length != mask[0].length) {
      throw new IllegalArgumentException("mask and image must be the same dimensions");
    }

    int h = mask.length;
    int w = mask[0].length;
    RGB[][] masked = new RGB[h][w];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        if (!check(i, j)) {
          masked[i][j] = alt[i][j];
        } else {
          masked[i][j] = og[i][j];
        }
      }
    }

    return new SimpleImage(masked);
  }

  @Override
  public String newName() {
    return cmd.newName();
  }

  // checks if this rgb is black
  private boolean check(int i, int j) {
    return this.mask[i][j].getRed() != 0
            || this.mask[i][j].getGreen() != 0
            || this.mask[i][j].getBlue() != 0;
  }

  @Override
  public String imgName() {
    return cmd.imgName();
  }
}
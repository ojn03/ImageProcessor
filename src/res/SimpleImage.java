package res;

/**
 * represents a ppm image and operations to modify it.
 */
public class SimpleImage implements ImageModel {

  private final RGB[][] image;

  public SimpleImage(RGB[][] img) {
    image = img;
  }

  /**
   * Adjusts the brightness of a copy of the picture by changing each pixel's res.
   *
   * @param adjustment is the constant by which each pixel will be adjusted
   * @return a picture representation of the brighter or darker picture
   */
  public ImageModel adjustBrightness(int adjustment) throws IllegalArgumentException {

    RGB[][] adMe = new RGB[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[i].length; j++) {
        adMe[i][j] = image[i][j].adjustBrightness(adjustment);
      }
    }

    return new SimpleImage(adMe);
  }


  /**
   * Flips an image horizontally.
   *
   * @return new RGB[][] that has been flipped horizontally.
   */
  @Override
  public ImageModel horizontalFlip() {
    RGB[][] flipMe = new RGB[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        flipMe[i][j] = new RGB(image[i][image[0].length - 1 - j]);
      }
    }

    return new SimpleImage(flipMe);
  }

  /**
   * Flips an image vertically.
   *
   * @return new RGB[][] that has been flipped vertically.
   */
  @Override
  public SimpleImage verticalFlip() {
    RGB[][] flipMe = new RGB[image.length][image[0].length];

    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < (image[0].length); j++) {
        flipMe[i][j] = new RGB(image[image.length - (i + 1)][j]);
      }
    }
    return new SimpleImage(flipMe);
  }

  /**
   * visualizes the image as the  given component.
   *
   * @param component is the component to be visualized (red,green,blue, luma...)
   * @return a new greyscale image of the component.
   */
  @Override
  public ImageModel visualize(String component) {
    RGB[][] visMe = new RGB[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < (image[0].length); j++) {
        visMe[i][j] = image[i][j].visualize(component);
      }
    }
    return new SimpleImage(visMe);
  }

  @Override
  public ImageModel blur() {
    return filter(new Kernel("blur"));
  }

  /**
   * Sharpens an Image.
   *
   * @return a new Sharpened Image
   */
  @Override
  public ImageModel sharpen() {
    return filter(new Kernel("sharpen"));
  }


  private ImageModel transform(Kernel k) {
    RGB[][] trans = new RGB[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int red = image[i][j].getRed();
        int green = image[i][j].getGreen();
        int blue = image[i][j].getBlue();

        int newRed = (int) Math.round(red * k.slotAt(0, 0)
                + green * k.slotAt(0, 1) + blue * k.slotAt(0, 2));

        int newGreen = (int) Math.round(red * k.slotAt(1, 0)
                + green * k.slotAt(1, 1) + blue * k.slotAt(1, 2));

        int newBlue = (int) Math.round(red * k.slotAt(2, 0)
                + green * k.slotAt(2, 1) + blue * k.slotAt(2, 2));

        trans[i][j] = new RGB(newRed, newGreen, newBlue);
      }
    }
    return new SimpleImage(trans);
  }

  /**
   * applies sepia filter to an image.
   *
   * @return a new image with the sepia filter.
   */
  @Override

  public ImageModel transformSepia() {
    return transform(new Kernel("sepia"));
  }

  /**
   * performs a grey color transformation.
   *
   * @return a new image with the grey transformation.
   */
  @Override
  public ImageModel transformGrey() {
    return transform(new Kernel("grey"));
  }

  private ImageModel filter(Kernel kern) {
    if (kern == null) {
      throw new IllegalArgumentException("null kernel");
    }

    RGB[][] filterMe = new RGB[image.length][image[0].length];
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int startx = 0;
        int endx = kern.size - 1;
        int starty = 0;
        int endy = kern.size - 1;

        int mid = kern.size / 2;
        if (i < mid) { // i < kern.length/2
          startx = mid - i; // mid - i
        }
        if (i + mid > image.length - 1) { // i > kern.
          endx = (image.length - i) + mid / 2;
        }
        if (j < mid) {
          starty = mid - j;
        }
        if (j + mid > image[0].length - 1) {
          endy = (image[0].length - j) + mid / 2;
        }
        double red = 0;
        double green = 0;
        double blue = 0;

        for (int x = startx; x <= endx; x++) {
          for (int y = starty; y <= endy; y++) {
            red += image[i + (x - mid)][j + y - mid].getRed() * kern.slotAt(x, y);
            green += image[i + x - mid][j + y - mid].getGreen() * kern.slotAt(x, y);
            blue += image[i + x - mid][j + y - mid].getBlue() * kern.slotAt(x, y);

          }
        }

        filterMe[i][j] = new RGB((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue));
      }
    }
    return new SimpleImage(filterMe);
  }

  /**
   * Gets the image.
   *
   * @return the rgb representation of the image.
   */
  @Override
  public RGB[][] getImage() {
    return this.image.clone();
  }

  @Override
  public int[][] histogram() {
    int maxrgb = image[0][0].getMax();
    int[][] track = new int[maxrgb + 1][4];

    for (RGB[] rgbs : image) {
      for (RGB rgb : rgbs) {

        int r = rgb.getRed();
        track[r][0] += 1;
        int g = rgb.getGreen();
        track[g][1] += 1;
        int b = rgb.getBlue();
        track[b][2] += 1;
        int i = rgb.intensity();
        track[i][3] += 1;

      }
    }
    return track;
  }
}

package res;

import java.util.Objects;

/**
 * Represents a color by its rgb value.
 */
public class RGB {

  //invariant: 0<= red , green, blue <=max
  private final int red;
  private final int green;
  private final int blue;
  private final int max;


  /**
   * copies the r,g,b and max of the given rgb into a new rgb.
   *
   * @param rgb is the rgb to be copied
   */
  public RGB(RGB rgb) {
    if (rgb == null) {
      throw new IllegalArgumentException("cannot copy a null rgb");
    }
    int r = rgb.getRed();
    int g = rgb.getGreen();
    int b = rgb.getBlue();
    this.max = rgb.getMax();
    red = clamp(r);
    green = clamp(g);
    blue = clamp(b);

  }

  /**
   * creates a new RGB with a max of 255.
   *
   * @param r the red value of the RGB.
   * @param g the green value of the RGB.
   * @param b the blue value of the RGB.
   */
  public RGB(int r, int g, int b) {
    this(r, g, b, 255);
  }

  /**
   * Creates a new RGB with the given values.
   *
   * @param r   the red value of the RGB.
   * @param g   the green value of the RGB.
   * @param b   the blue value of the RGB.
   * @param max the maximum value that each value in RGB can be.
   */
  public RGB(int r, int g, int b, int max) {
    this.max = max;
    red = clamp(r);
    green = clamp(g);
    blue = clamp(b);

  }

  /**
   * gets the max of the RGB.
   *
   * @return max value of the RGB.
   */
  public int getMax() {
    return max;
  }

  /**
   * gets the Red value of the RGB.
   *
   * @return red value of the RGB.
   */
  public int getRed() {
    return red;
  }

  /**
   * gets the Blue value of the RGB.
   *
   * @return blue value of the RGB.
   */
  public int getBlue() {
    return blue;
  }

  /**
   * gets the Green value of the RGB.
   *
   * @return green value of the RGB.
   */
  public int getGreen() {
    return green;
  }

  /**
   * the average of the three components for each pixel.
   *
   * @return the average of the red, blue, and green values.
   */
  public int intensity() {
    return (red + blue + green) / 3;
  }

  /**
   * finds the luma constant of the rgb.
   *
   * @return the luma
   */
  public int luma() {
    return (int) Math.round(0.2126 * red + .7152 * green + .0722 * blue);
  }

  /**
   * calculates the value of the rgb (the max of r,g,b).
   *
   * @return the value
   */
  public int value() {
    return Math.max(red, Math.max(blue, green));
  }

  /**
   * Adjusts the brightness of the RGB by the given factor.
   *
   * @param factor the amount by which the brightness of the RGB is changing.
   * @return a new RGB with values adjusted.
   */
  public RGB adjustBrightness(int factor) {
    int r = clamp(red + factor);
    int g = clamp(green + factor);
    int b = clamp(blue + factor);
    return new RGB(r, g, b, max);
  }


  /**
   * visualizes a component of this RGB (red, blue,green, luma...).
   *
   * @param component the component to be visualized
   * @return a greyscale rgb of the component
   */
  public RGB visualize(String component) {
    switch (component) {
      case "red":
        return new RGB(this.red, this.red, this.red, this.max);
      case "green":
        return new RGB(this.green, this.green, this.green, this.max);

      case "blue":
        return new RGB(this.blue, this.blue, this.blue, this.max);

      case "value":
        int value = this.value();
        return new RGB(value, value, value, this.max);

      case "luma":
        int luma = this.luma();
        return new RGB(luma, luma, luma, this.max);

      case "intensity":
        int intensity = this.intensity();
        return new RGB(intensity, intensity, intensity, this.max);

      default:
        throw new IllegalArgumentException(
                "component must be one of red, green, blue, value, intensity, or luma");
    }

  }

  //enforces that each value remains in range [0,max]
  private int clamp(int i) {
    if (i < 0) {
      return 0;
    }
    if (i > max) {
      return max;
    }
    return i;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof RGB)) {
      return false;
    }
    RGB that = (RGB) other;
    return this.getBlue() == that.getBlue()
            && this.getGreen() == that.getGreen()
            && this.getRed() == that.getRed()
            && this.getMax() == that.getMax();
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue, max);
  }

  @Override
  public String toString() {
    return "[" + red + "," + green + "," + blue + "," + max + "]";
  }
}
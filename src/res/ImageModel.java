package res;

/**
 * represents an image and operations to modify said image.
 */
public interface ImageModel {

  /**
   * Adjusts the brightness of a copy of the picture by changing each pixel's rgbs.
   *
   * @param adjustment is the constant by which each pixel will be adjusted
   * @return a picture representation of the brighter or darker picture
   */
  ImageModel adjustBrightness(int adjustment);

  /**
   * flips this image horizontally.
   *
   * @return the flipped image
   */
  ImageModel horizontalFlip();

  /**
   * flips this image vertically.
   *
   * @return the flipped image.
   */
  ImageModel verticalFlip();

  /**
   * visualizes a component of this image.
   *
   * @param component the component to be visualized
   * @return the visualized image
   */
  ImageModel visualize(String component);

  /**
   * applies a filter which blurs the image using the following kernel.
   * [1/16,1/8,1/16]
   * [1/8, 1/4, 1/8]
   * [1/16,1/8,1/16]
   *
   * @return an image with the blur applied
   */
  ImageModel blur();

  /**
   * applies a filter which sharpens the image using the following kernel.
   * [-1/8,-1/8,-1/8,-1/8,-1/8]
   * [-1/8, 1/4, 1/4, 1/4,-1/8]
   * [-1/8, 1/4,  1,  1/4,-1/8]
   * [-1/8, 1/4, 1/4, 1/4,-1/8]
   * [-1/8,-1/8,-1/8,-1/8,-1/8]
   *
   * @return an image with sharpened effect.
   */
  ImageModel sharpen();


  ImageModel transformSepia();

  ImageModel transformGrey();

  /**
   * gets a copy of the image.
   *
   * @return the image copy
   */
  RGB[][] getImage();

  /**
   * Creates a histogram mapping the frequency of each value in the picture.
   *
   * @return returns a hashmap representing value-frequency pairs
   */
  int[][] histogram( );
}

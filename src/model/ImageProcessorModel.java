package model;

import java.util.function.Function;

import res.ImageModel;

/**
 * Represents operations to manipulate and store images.
 */
public interface ImageProcessorModel {

  /**
   * saves an image from the processor to the given filepath.
   *
   * @param filepath the filepath at which the image will be saved
   * @param imgName  the name of the image to save
   */
  void download(String filepath, String imgName);

  /**
   * Loads an image from the given filepath into the processor as the given name.
   *
   * @param filepath is the filepath from which to retrieve the image
   * @param imgName  is the name at which the image will be saved in the processor
   */
  void upload(String filepath, String imgName);


  /**
   * Edits the image with the given function and saves it at the newName.
   *
   * @param imgName the name of the image in the processor to edit
   * @param newName the name at which the edited image will be saved
   * @param func    is the ImageModel function to apply to the image
   */
  void edit(String imgName, String newName, Function<ImageModel, ImageModel> func);


  /**
   * Adjusts the brightness of the Image and saves it at the newName.
   *
   * @param imgName    the name of the image in the processor to adjust
   * @param newName    the name at which the adjusted image will be saved
   * @param adjustment the amount by which to adjust image's the brightness
   */
  void adjustBrightness(String imgName, String newName, int adjustment);


  /**
   * visualizes the given component of the image.
   *
   * @param imgName   is the name of the image to be visualized
   * @param newName   the new name at which the visualized image will be saved
   * @param component the channel or component to visualize
   */
  void visualize(String imgName, String newName, String component);


}

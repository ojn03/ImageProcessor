package model;

import java.util.HashMap;
import java.util.function.Function;

import res.ImageFileUtil;
import res.ImageModel;

/**
 * Implements operations to be used in an image processor.
 */
public class SimpleImageProcessorModel implements ImageProcessorModel {
  HashMap<String, ImageModel> images; //filename to picture;

  /**
   * creates an empty image processor.
   */
  public SimpleImageProcessorModel() {
    images = new HashMap<>();
  }

  /**
   * creates an image processor with the given images.
   *
   * @param map are the names and images to add to the image processor.
   */
  public SimpleImageProcessorModel(HashMap<String, ImageModel> map) {
    images = map;
  }


  @Override
  public void download(String filepath, String imgName) {
    if (!images.containsKey(imgName)) {
      throw new IllegalArgumentException("no such image named\"" + imgName + "\" in processor ");
    }
    ImageFileUtil.writeFile(filepath, images.get(imgName));
  }


  @Override
  public void upload(String filepath, String imgName) {
    if (images.containsKey(imgName)) {
      throw new IllegalArgumentException("an image with this name already exits in the processor");
    }
    images.put(imgName, ImageFileUtil.readFile(filepath));

  }


 @Override
  public void edit(String imgName, String newName, Function<ImageModel, ImageModel> func) {
    if (!images.containsKey(imgName)) {
      throw new IllegalArgumentException("no such image named " + imgName);
    }
    if (images.containsKey(newName)) {
      throw new IllegalArgumentException(
              "an image with the name " + newName + " already exits in the processor");
    }
    ImageModel apme = images.get(imgName);
    images.put(newName, func.apply(apme));
  }


  @Override
  public void adjustBrightness(String imgName, String newName, int adjustment) {
    if (!images.containsKey(imgName)) {
      throw new IllegalArgumentException("no such image named " + imgName);
    }
    if (images.containsKey(newName)) {
      throw new IllegalArgumentException(
              "an image with the name " + newName + " already exits in the processor");
    }
    ImageModel img = images.get(imgName);
    images.put(newName, img.adjustBrightness(adjustment));

  }


  @Override
  public void visualize(String imgName, String newName, String component) {
    if (!images.containsKey(imgName)) {
      throw new IllegalArgumentException("no such image named " + imgName);
    }
    if (images.containsKey(newName)) {
      throw new IllegalArgumentException(
              "an image with the name " + newName + " already exits in the processor");
    }
    ImageModel visMe = images.get(imgName);
    images.put(newName, visMe.visualize(component));
  }


}

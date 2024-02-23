package model;

import java.util.HashMap;
import java.util.function.Function;

import res.ImageFileUtil;
import res.ImageModel;
import res.RGB;

/**
 * Implements operations to be used in an image processor.
 */
public class SimpleImageProcessorModel implements ImageProcessorModel {
  private final HashMap<String, ImageModel> images; //filename to picture;

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
  public void download(String imgName, String filepath) {
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
  public void downSize(String imgName, String newName, int h, int w) {
    if (!images.containsKey(imgName)) {
      throw new IllegalArgumentException("no such image named " + imgName);
    }
    if (images.containsKey(newName)) {
      throw new IllegalArgumentException(
              "an image with the name " + newName + " already exits in the processor");
    }
    ImageModel visMe = images.get(imgName);
    images.put(newName, visMe.downSize(h, w));
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

  @Override
  public void addImage(ImageModel img, String newName) {
    if (this.images.containsKey(newName)) {
      throw new IllegalArgumentException("image name already exists :" + newName);
    } else {
      images.put(newName, img);
    }
  }


  @Override
  public RGB[][] getImage(String imgName) {
    if (this.images.containsKey(imgName)) {
      return images.get(imgName).getImage().clone();
    } else {
      throw new IllegalArgumentException("no such image named " + imgName);
    }
  }


  /**
   * generates a histogram representation of the image at the given name.
   *
   * @param imgName the name of the image for which to generate a histogram
   * @return the histogram representation
   */
  @Override
  public int[][] getHistogram(String imgName) {
    ImageModel m = images.get(imgName);
    return m.histogram();
  }
}
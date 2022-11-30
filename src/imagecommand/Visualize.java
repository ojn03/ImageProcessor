package imagecommand;

import model.ImageProcessorModel;

/**
 * A command function-object to visualize a component of an image in the processor.
 */
public class Visualize extends Commands {

  private final String component;

  /**
   * A constructor to hold arguments used to visualize a component in a model.
   *
   * @param imgname   the name of the image to be affected
   * @param newname   the name at which the affected image will be saved
   * @param component dictates what component of the image will be visualized
   */
  public Visualize(String component, String imgname, String newname) {
    super(imgname, newname);
    this.component = component;
  }


  @Override
  public void runt(ImageProcessorModel m) {
    m.visualize(img, newName, component);
  }
}

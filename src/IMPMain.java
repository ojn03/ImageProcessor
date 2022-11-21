import java.io.InputStreamReader;

import controller.ImageProcessorController;
import controller.SimpleImPController;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import view.ImageProcessorView;
import view.SimpleProcessorView;

/**
 * Main class of program.
 */

public class IMPMain {

  /**
   * method that takes and executes command line arguments.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    ImageProcessorModel model = new SimpleImageProcessorModel();
    ImageProcessorView view = new SimpleProcessorView(model);

    ImageProcessorController controller = new SimpleImPController(
            model, view, new InputStreamReader(System.in));
    controller.runProcessor();
  }

}

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

import controller.GuiController;
import controller.ImageProcessorController;
import controller.SimpleImPController;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import view.GuiView;
import view.ImPGUI;
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
    ImageProcessorController controller;
    if (args.length == 0) {
      GuiView view = new ImPGUI(model);
      controller = new GuiController(model, view);
      controller.runProcessor();
    } else if (args.length == 1 && args[0].equals("-text")) {
      SimpleProcessorView view = new SimpleProcessorView(model);
      controller = new SimpleImPController(model, view, new InputStreamReader(System.in));
      controller.runProcessor();
    } else if (args.length == 2 && args[0].equals("-file") && args[1].endsWith(".txt")) {
      SimpleProcessorView view = new SimpleProcessorView(model);

      Path filePath = Path.of(args[1]);
      String input = null;
      try {
        input = Files.readString(filePath);
      } catch (IOException e) {
        view.renderMessage(e.getMessage());
      }
      assert input != null;
      Readable r = new StringReader(input);
      controller = new SimpleImPController(model, view, r);
      controller.runProcessor();
    } else {
      System.out.println("Incorrect Syntax. Try again");
    }
  }
}

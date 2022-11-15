package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import imagecommand.AdjustBrightness;
import imagecommand.Download;
import imagecommand.Edit;
import imagecommand.ImageProcessorCommand;
import imagecommand.Upload;
import imagecommand.Visualize;
import model.ImageProcessorModel;
import res.ImageModel;
import view.ImageProcessorView;

/**
 * Represents a controller that accepts user input.
 * Uses a scanner to modify the given model and view.
 */
public class SimpleImPController implements ImageProcessorController {
  private final ImageProcessorModel model;
  private final ImageProcessorView view;
  private final Readable input;

  /**
   * creates a controller implementation where given input is a given readable.
   *
   * @param model is a model of the image processor.
   * @param view  is a visual of the image processor.
   * @param input is the user's input.
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public SimpleImPController(ImageProcessorModel model, ImageProcessorView view, Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("null arguments");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }


  @Override
  public void runProcessor() {
    Scanner scan = new Scanner(input);
    HashMap<String, Function<Scanner, ImageProcessorCommand>> commands = new HashMap<>();
    commands.put("upload", s -> new Upload(s.next(), s.next()));
    commands.put("download", s -> new Download(s.next(), s.next()));
    commands.put("visualize", s -> new Visualize(s.next(), s.next(), s.next()));
    commands.put("brightness", s -> new AdjustBrightness(s.nextInt(), s.next(), s.next()));
    commands.put("flipv", s -> new Edit(s.next(), s.next(), ImageModel::verticalFlip));
    commands.put("fliph", s -> new Edit(s.next(), s.next(), ImageModel::horizontalFlip));
    commands.put("blur", s -> new Edit(s.next(), s.next(), ImageModel::blur));
    commands.put("sepia", s -> new Edit(s.next(), s.next(), ImageModel::transformSepia));
    commands.put("grey", s -> new Edit(s.next(), s.next(), ImageModel::transformGrey));
    commands.put("sharpen", s -> new Edit(s.next(), s.next(), ImageModel::sharpen));

    view.renderMessage("Image processor is running");
    ImageProcessorCommand c;
    while (scan.hasNext()) {
      String in = scan.next().toLowerCase();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        view.renderMessage("Processor Quit!");
        return;
      }
      Function<Scanner, ImageProcessorCommand> cmd = commands.getOrDefault(in, null);
      if (cmd == null) {
        view.renderMessage("unknown command");
      } else {
        try {
          c = cmd.apply(scan);
          c.runt(model);
          view.renderMessage("command was executed successfully!");
        } catch (InputMismatchException | IllegalArgumentException e) {
          view.renderMessage(e.getMessage());
        }
      }

    }
  }

}

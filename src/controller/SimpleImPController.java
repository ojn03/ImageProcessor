package controller;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

import imagecommand.AdjustBrightness;
import imagecommand.DownSize;
import imagecommand.Edit;
import imagecommand.ImageProcessorCommand;
import imagecommand.Mask;
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
  private final Scanner scan;

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
    scan = new Scanner(input);
  }


  @Override
  public void runProcessor() {
    HashMap<String, Function<Scanner, ImageProcessorCommand>> commands = new HashMap<>();
    commands.put("visualize", s -> new Visualize(s.next(), s.next(), s.next()));
    commands.put("brightness", s -> new AdjustBrightness(s.nextInt(), s.next(), s.next()));
    commands.put("flipv", s -> new Edit(s.next(), s.next(), ImageModel::verticalFlip));
    commands.put("fliph", s -> new Edit(s.next(), s.next(), ImageModel::horizontalFlip));
    commands.put("blur", s -> new Edit(s.next(), s.next(), ImageModel::blur));
    commands.put("sepia", s -> new Edit(s.next(), s.next(), ImageModel::transformSepia));
    commands.put("grey", s -> new Edit(s.next(), s.next(), ImageModel::transformGrey));
    commands.put("sharpen", s -> new Edit(s.next(), s.next(), ImageModel::sharpen));
    commands.put("downsize", s -> new DownSize(s.nextInt(), s.nextInt(), s.next(), s.next()));
    commands.put("-mask", s -> {
      String mask = s.next(), cmd = s.next();
      return new Mask(commands.get(cmd).apply(s), model.getImage(mask));
    });

    view.renderMessage("Image processor is running");
    ImageProcessorCommand c;
    while (scan.hasNext()) {
      String in = scan.next().toLowerCase();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        view.renderMessage("Processor Quit!");
        return;
      } else if (in.equalsIgnoreCase("upload")) {
        tryU();
        continue;
      } else if (in.equalsIgnoreCase("download")) {
        tryD();
        continue;
      }
      Function<Scanner, ImageProcessorCommand> cmd = commands.getOrDefault(in, null);
      if (cmd == null) {
        view.renderMessage("unknown command");
      } else {
        try {
          c = cmd.apply(scan);
          model.addImage(c.create(model), c.newName());
          view.renderMessage("command was executed successfully!");
        } catch (InputMismatchException | IllegalArgumentException e) {
          view.renderMessage(e.getMessage());
        }
      }

    }
  }

  private void tryD() {
    try {
      model.download(scan.next(), scan.next());
      view.renderMessage("Successful download!");
    } catch (IllegalArgumentException e) {
      view.renderMessage(e.getMessage());
    }
  }

  private void tryU() {
    try {
      model.upload(scan.next(), scan.next());
      view.renderMessage("Successful upload!");
    } catch (IllegalArgumentException e) {
      view.renderMessage(e.getMessage());
    }
  }


}

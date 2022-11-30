package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageProcessorModel;
import res.ImageModel;
import view.GuiView;

/**
 * A controller to manage gui inputs.
 */
public class GuiController implements FeaturesController {
  private final ImageProcessorModel m;
  private final GuiView v;
  private String currentImage = null;

  /**
   * constructs the controller for the GUI.
   *
   * @param m is the model
   * @param v is the view
   */
  public GuiController(ImageProcessorModel m, GuiView v) {
    this.m = m;
    this.v = v;
    v.setListener(this);
  }

  /**
   * accepts inputs from the client and makes the relevant updates to the view and model.
   */
  @Override
  public void runProcessor() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, and PNG Images", "jpg", "png", "ppm");
    fchooser.setFileFilter(filter);
    fchooser.setDialogTitle("You must Upload an image to Begin using the Processor");
    String name;
    while (true) {
      int status = fchooser.showOpenDialog(null);
      if (status == JFileChooser.APPROVE_OPTION) {
        try {
          File f = fchooser.getSelectedFile();
          name = choose("Choose Name", "choose a name for this image");
          m.upload(f.getAbsolutePath(), name);
          v.addImageName(name);
          currentImage = name;
        } catch (IllegalArgumentException ignored) {
          continue;
        }
        break;

      }
    }

  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {

      case "/Upload": {
        String fp;
        try {
          fp = openFile();
          String newName = choose("Choose New Name", "Choose a name for your Image");
          m.upload(fp, newName);
          v.addImageName(newName);
          v.renderMessage("Image Uploaded!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/Download": {
        String savePath;
        try {
          savePath = saveFile();
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
          break;
        }
        try {
          m.download(savePath, currentImage);
          v.renderMessage("Image Downloaded!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/Visualize": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");
          String component = choose("Choose Component",
                  "Choose a component (red, green, blue, value, intensity, or luma");
          m.visualize(currentImage, newName, component);
          v.addImageName(newName);
          v.renderMessage("Image Component has been Visualized");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/Sepia": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");

          m.edit(currentImage, newName, ImageModel::transformSepia);
          v.addImageName(newName);
          v.renderMessage("Sepia Filter Applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/GreyScale": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");

          m.edit(currentImage, newName, ImageModel::transformGrey);
          v.addImageName(newName);
          v.renderMessage("GreyScaled Filter applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }

      break;
      case "/FlipV": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");

          m.edit(currentImage, newName, ImageModel::verticalFlip);
          v.addImageName(newName);
          v.renderMessage("Vertical Flip applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }

      break;
      case "/FlipH": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");
          m.edit(currentImage, newName, ImageModel::horizontalFlip);
          v.addImageName(newName);
          v.renderMessage("Horizontal Flip applied!");

        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/Brightness": {
        int val = 0;

        try {
          String vals = choose("Choose Adjustment",
                  "Choose an adjustment value. Negative values darken the image");
          val = Integer.parseInt(vals);
          String newName = choose("Choose New Name", "Choose a name for your Image");
          m.adjustBrightness(currentImage, newName, val);
          v.addImageName(newName);
          v.renderMessage("Adjustment has been applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }

        break;
      }
      case "/Blur": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");

          m.edit(currentImage, newName, ImageModel::blur);
          v.addImageName(newName);
          v.renderMessage("Blur has been applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      case "/Sharpen": {
        try {
          String newName = choose("Choose New Name", "Choose a name for your Image");

          m.edit(currentImage, newName, ImageModel::sharpen);
          v.addImageName(newName);
          v.renderMessage("Sharpen Filter has been applied!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage(ie.getMessage());
        }
      }
      break;
      default:
        this.currentImage = e.getActionCommand();
        v.setImage(e.getActionCommand());

    }
  }

  @Override
  public String openFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, and PNG Images", "jpg", "jpeg", "png", "ppm");
    fchooser.setFileFilter(filter);
    int status = fchooser.showOpenDialog(null);

    if (status == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    throw new IllegalArgumentException("Cancelled");
  }


  @Override
  public String saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, ppm, & png Images", "jpg", "ppm", "png", "jpeg");
    fchooser.setFileFilter(filter);
    int status = fchooser.showSaveDialog(null);
    if (status == 0) {
      return fchooser.getSelectedFile().getAbsolutePath();
    }
    throw new IllegalArgumentException("Cancelled");
  }


  @Override
  public String choose(String title, String message) {
    String ret = JOptionPane.showInputDialog(null, message,
            title, JOptionPane.QUESTION_MESSAGE);

    if (ret == null || ret.equals("")) {
      throw new IllegalArgumentException("Cancelled");
    } else if (ret.charAt(0) == '/') {
      throw new IllegalArgumentException("name cant start with \"/\"");
    } else {
      return ret;
    }
  }
}

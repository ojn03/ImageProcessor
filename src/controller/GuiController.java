package controller;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ImageProcessorModel;
import res.ImageModel;
import view.GuiView;

public class GuiController implements FeaturesController {
  ImageProcessorModel m;
  GuiView v;
  String currentImage;
  //todo set Current Image


  public GuiController(ImageProcessorModel m, GuiView v) {
    this.m = m;
    this.v = v;
  }

  /**
   * accepts inputs from the client and makes the relevant updates to the view and m.
   */
  @Override
  public void runProcessor() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, and PNG Images", "jpg", "png", "ppm");
    fchooser.setFileFilter(filter);
    fchooser.setDialogTitle("Choose an Image to Upload");
    int status = fchooser.showOpenDialog(null);
    if (status == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      String name = choose("Choose Name", "choose a name for this image");
      m.upload(f.getAbsolutePath(), name);
      v.addImageName(name);
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

      case "/Upload":
      {
        String fp;
        try {
          fp = openFile();
          String newName = choose("Choose New Name", "Choose a name for your Image");
          m.upload(fp, newName);
          v.addImageName(newName);
          v.renderMessage("Image Uploaded!");
        } catch (IllegalArgumentException ie) {
          v.renderMessage("cancelled");
        }
      }
      break;
      case "/Download": {
        String savePath;
        try {
          savePath = saveFile();
        } catch (IllegalArgumentException ie) {
          v.renderMessage("cancelled");
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
          String component = choose("Choose Component", "Choose a component (red, green, blue, value, intensity, or luma");
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
          ;
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
          ;
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
          ;
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
        String vals = choose("Choose Adjustment", "Choose an adjustment value. Negative values darken the image");
        try {
          val = Integer.parseInt(vals);
          String newName = choose("Choose New Name", "Choose a name for your Image");
          m.adjustBrightness(currentImage, newName, val);
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
          v.renderMessage("Adjustment has been applied!");
          ;
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
          ;
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
            "JPG, PPM, and PNG Images", "jpg", "png", "ppm");
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
            "JPG & GIF Images", "jpg", "gif");
    int status = fchooser.showSaveDialog(null);
    if (status == 0)
      return fchooser.getSelectedFile().getAbsolutePath();
    throw new IllegalArgumentException("Cancelled");
  }

  /**
   * @return
   */
  @Override
  public String choose(String title, String message) {
    String ret= JOptionPane.showInputDialog(null, message,
            title, JOptionPane.QUESTION_MESSAGE);

    if (ret == null||ret == ""){
      throw new IllegalArgumentException("Cancelled");
    } else if (ret.substring(0,1) =="/") {
      throw new IllegalArgumentException("name cant start with \"/\"");
    }else{
      return ret;
    }
  }



//  /**
//   *
//   */
//  @Override
//  public void openFile() {
//
//  }
//
//  /**
//   *
//   */
//  @Override
//  public void saveFile() {
//
//  }
//
//  /**
//   * @param title
//   * @param Message
//   */
//  @Override
//  public void choose(String title, String Message) {
//
//  }
}

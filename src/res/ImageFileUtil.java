package res;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * operations for modifying and operating on Image files.
 */
public class ImageFileUtil {

  /**
   * Read an image file in the PPM format.
   *
   * @param filename the path of the file.
   */
  public static ImageModel readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("file " + filename + " was not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    RGB[][] grid = new RGB[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        grid[i][j] = new RGB(r, g, b, maxValue);
      }
    }
    return new SimpleImage(grid);
  }

  /**
   * Writes to a file given a ppm image.
   *
   * @param filepath is the file to be written to (the destination)
   * @param img      is the image to be converted to the file
   */
  public static void writeFile(String filepath, ImageModel img) {
    int indexE = filepath.lastIndexOf('.');
    String extension = filepath.substring(indexE + 1);
    if (extension.equals("ppm")) {
      writePPM(filepath, img);
    } else {
      writeOther(filepath, extension, img);
    }

  }

  private static void writeOther(String fp, String extension, ImageModel img) {
    RGB[][] rgbs = img.getImage();
    BufferedImage buf = toBuf(rgbs);

    File file = new File(fp);
    try {
      ImageIO.write(buf, extension, file);
    } catch (IOException e) {
      throw new IllegalArgumentException("could not write to path: " + fp);
    }
  }

  /**
   * converts a 2d RGB array into a buffered image.
   *
   * @param rgbs the array to be converted
   * @return a buffered image representation of rgbs
   */
  public static BufferedImage toBuf(RGB[][] rgbs) {
    int h = rgbs.length;
    int w = rgbs[0].length;
    BufferedImage buf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    for (int j = 0; j < w; j++) {
      for (int i = 0; i < h; i++) {
        int r = rgbs[i][j].getRed();
        int g = rgbs[i][j].getGreen();
        int b = rgbs[i][j].getBlue();

        int p = (1 << 24);
        p |= (r << 16);
        p |= (g << 8);
        p |= b;
        buf.setRGB(j, i, p);
      }
    }
    return buf;
  }

  private static void writePPM(String filepath, ImageModel img) {
    tryCreate(filepath);
    String newline = System.lineSeparator();
    StringBuilder builder = new StringBuilder();
    builder.append("P3").append(newline);
    builder.append(img.getImage()[0].length).append(" ");
    builder.append(img.getImage().length).append(newline);
    builder.append(img.getImage()[0][0].getMax()).append(newline);
    for (RGB[] row : img.getImage()) {
      for (RGB color : row) {
        builder.append(color.getRed()).append(newline);
        builder.append(color.getGreen()).append(newline);
        builder.append(color.getBlue()).append(newline);
      }
    }
    tryWrite(filepath, builder.toString());
  }

  /**
   * Reads a file.
   *
   * @param filepath filepath of desired file.
   * @return ImageModel
   */
  public static ImageModel readFile(String filepath) {
    int indexE = filepath.lastIndexOf('.');
    String extension = filepath.substring(indexE + 1);
    if (extension.equals("ppm")) {
      return readPPM(filepath);
    } else {
      return readOther(filepath);
    }
  }

  private static ImageModel readOther(String filepath) {
    BufferedImage img;
    try {
      img = ImageIO.read(new File(filepath));
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    int h = img.getHeight();
    int w = img.getWidth();
    RGB[][] dest = new RGB[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        Color color = new Color(img.getRGB(j, i));
        dest[i][j] = new RGB(color.getRed(), color.getGreen(), color.getBlue());
      }
    }
    return new SimpleImage(dest);
  }

  private static void tryCreate(String filepath) {
    try {
      File file = new File(filepath);
      if (!file.createNewFile()) {
        throw new IllegalArgumentException("File already exists");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void tryWrite(String filepath, String fileContents) {
    try {
      FileWriter writer = new FileWriter(filepath);
      writer.write(fileContents);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}


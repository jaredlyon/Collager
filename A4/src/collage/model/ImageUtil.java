package collage.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import collage.model.pixel.RGBPixel;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   */
  public static ProjConstPPM readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println(System.getProperty("user.dir"));
      System.out.println("File " + filename + " not found!");
      return null;
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
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

    ArrayList<Integer> rgbVals = new ArrayList<Integer>();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        rgbVals.add(r);
        int g = sc.nextInt();
        rgbVals.add(g);
        int b = sc.nextInt();
        rgbVals.add(b);
      }
    }

    return new ProjConstPPM(width, height, maxValue, rgbVals);
  }

  /**
   * Write an image file in the PPM format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   */
  public static void writePPM(String filename, ArrayList<ArrayList<RGBPixel>> image) {
    // TODO: implement
  }

  /**
   * Read an image file in the JPEG format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   */
  public static ProjConstPPM readJPEG(String filename) {
    // TODO: implement
  }

  /**
   * Write an image file in the JPEG format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   */
  public static void writeJPEG(String filename, ArrayList<ArrayList<RGBPixel>> image) {
    // TODO: implement
  }

  /**
   * Read an image file in the PNG format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   */
  public static ProjConstPPM readPNG(String filename) {
    // TODO: implement
  }

  /**
   * Write an image file in the PNG format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   */
  public static void writePNG(String filename, ArrayList<ArrayList<RGBPixel>> image) {
    // TODO: implement
  }

  /**
   * A demo code block.
   *
   * @param args - argument inputs
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }

}


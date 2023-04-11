package collage.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;

import collage.model.pixel.RGBPixel;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Represents the helper methods for BufferedImage processing.
   */
  static class ImageUtilHelpers {
    /**
     * Converts an ArrayList<ArrayList<RGBPixel>> to a BufferedImage.
     * @param image the image to be converted
     * @return a BufferedImage
     */
    private static BufferedImage imageToBufImage(ArrayList<ArrayList<RGBPixel>> image) {
      int height = image.size();
      int width = image.get(0).size();
      BufferedImage bufImage = new BufferedImage(width, height, TYPE_INT_ARGB);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          RGBPixel pixel = image.get(i).get(j);
          int argb = (pixel.getAlpha() << 24) + (pixel.getRed() << 16) + (pixel.getGreen() << 8)
                  + pixel.getBlue();
          bufImage.setRGB(i, j, argb);
        }
      }
      return bufImage;
    }
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   * @throws FileNotFoundException if the file is not found.
   */
  public static ProjConstPPM readPPM(String filename) throws IOException {
    Scanner sc;
    sc = new Scanner(new FileInputStream(filename));

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

    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<RGBPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGBPixel pixel = new RGBPixel(255, r, g, b);
        row.add(pixel);
      }
      image.add(row);
    }

    return new ProjConstPPM(width, height, maxValue, image);
  }

  /**
   * Write an image file in the PPM format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   * @throws IOException if the file cannot be written.
   */
  public static void writePPM(String filename, ArrayList<ArrayList<RGBPixel>> image, int maxVal)
          throws IOException {
    StringBuilder rgbVals = new StringBuilder();
    int width = image.get(0).size();
    int height = image.size();

    for (ArrayList<RGBPixel> row : image) {
      for (RGBPixel p : row) {
        double alpha = p.getAlpha();
        double red = p.getRed();
        double green = p.getGreen();
        double blue = p.getBlue();

        double redPrime = red * (alpha / 255.0);
        double greenPrime = green * (alpha / 255.0);
        double bluePrime = blue * (alpha / 255.0);

        rgbVals.append("\n").append((int) redPrime).append(" ").append((int) greenPrime).append(" ")
                .append((int) bluePrime).append(" ");
      }
    }

    // save the file
    if (filename == null) {
      throw new IllegalArgumentException("File path cannot be null");
    }

    FileWriter writer = null;
    // P3
    // width height
    // maxVal
    // rgbVals

    writer = new FileWriter(filename + ".ppm");
    writer.write("P3");
    writer.write("\n" + width + " " + height);
    writer.write("\n" + maxVal);
    writer.write(rgbVals + "\n");
    writer.close();
  }

  /**
   * Read an image file in the JPEG format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   * @throws IOException if the file cannot be read.
   */
  public static ProjConstPPM readJPG(String filename) throws IOException {
    File file = new File(filename);
    BufferedImage bufImage = ImageIO.read(file);
    int height = bufImage.getHeight();
    int width = bufImage.getWidth();
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<RGBPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rgb = bufImage.getRGB(j, i);
        int a = bufImage.getColorModel().getAlpha(rgb);
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb) & 0xFF;
        row.add(new RGBPixel(a, r, g, b));
      }
      image.add(row);
    }
    return new ProjConstPPM(width, height, 255, image);
  }

  /**
   * Write an image file in the JPEG format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   * @throws IOException if the file cannot be written.
   * @throws IllegalArgumentException if the file path is null.
   */
  public static void writeJPEG(String filename, ArrayList<ArrayList<RGBPixel>> image)
          throws IOException, IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("File path cannot be null");
    }
    BufferedImage bufImage = ImageUtilHelpers.imageToBufImage(image);

    File file = new File(filename);
    ImageIO.write(bufImage, "JPG", file);
  }

  /**
   * Read an image file in the PNG format and print the colors.
   *
   * @param filename the path of the file.
   * @return a ProjConstPPM object containing the image data.
   * @throws IOException if the file is not found.
   */
  public static ProjConstPPM readPNG(String filename) throws IOException {
    File file = new File(filename);
    BufferedImage bufImage = ImageIO.read(file);
    int height = bufImage.getHeight();
    int width = bufImage.getWidth();
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<RGBPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rgb = bufImage.getRGB(j, i);
        int alpha = (rgb >> 24) & 0xFF;
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = (rgb >> 0) & 0xFF;
        RGBPixel pixel = new RGBPixel(alpha, red, green, blue);
        row.add(pixel);
      }
      image.add(row);
    }
    return new ProjConstPPM(width, height, 255, image);
  }

  /**
   * Write an image file in the PNG format.
   *
   * @param filename the path of the file.
   * @param image the image data.
   * @throws IOException if the file cannot be written.
   * @throws IllegalArgumentException if the file path is null.
   */
  public static void writePNG(String filename, ArrayList<ArrayList<RGBPixel>> image)
          throws IOException, IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("File path cannot be null");
    }
    BufferedImage bufImage = ImageUtilHelpers.imageToBufImage(image);

    File file = new File(filename);
    ImageIO.write(bufImage, "PNG", file);
  }
}


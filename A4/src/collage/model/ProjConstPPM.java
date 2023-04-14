package collage.model;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a class that holds PPM data for the model to process.
 */
public class ProjConstPPM {
  private final int width;
  private final int height;
  private final int maxVal;
  private final ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Generates this data class.
   * @param width - the width of the image
   * @param height - the height of the image
   * @param maxVal - the maxVal of the pixels
   * @param image - the image data
   * @throws IllegalArgumentException if any args are null
   */
  public ProjConstPPM(int width, int height, int maxVal, ArrayList<ArrayList<RGBPixel>> image)
          throws IllegalArgumentException {
    if (image == null || width < 1 || height < 1 || maxVal < 1) {
      throw new IllegalArgumentException("Args cannot be null for ProjConstPPM.");
    }

    this.width = width;
    this.height = height;
    this.maxVal = maxVal;
    this.image = image;
  }

  /**
   * Returns the width of this image.
   * @return an int
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Returns the height of this image.
   * @return an int
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Returns the maxVal of this image.
   * @return an int
   */
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Returns the image data.
   * @return an arraylist of arraylist of pixel
   */
  public ArrayList<ArrayList<RGBPixel>> getImage() {
    return this.image;
  }
}

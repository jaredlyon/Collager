package collage.model;

import java.util.ArrayList;

/**
 * Represents a class that holds PPM data for the model to process.
 */
public class ProjConstPPM {
  private int width;
  private int height;
  private int maxVal;
  private ArrayList<Integer> rgbVals;

  /**
   * Generates this data class.
   * @param width - the width of the image
   * @param height - the height of the image
   * @param maxVal - the maxVal of the pixels
   * @param rgbVals - the RGB values of the pixels
   */
  public ProjConstPPM(int width, int height, int maxVal, ArrayList<Integer> rgbVals)
          throws IllegalArgumentException {
    if (rgbVals == null || width < 1 || height < 1 || maxVal < 1) {
      throw new IllegalArgumentException("Args cannot be null for ProjConstPPM.");
    }

    this.width = width;
    this.height = height;
    this.maxVal = maxVal;
    this.rgbVals = rgbVals;
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
   * Returns the RGB values of the image.
   * @return an ArrayList of RGB vals
   */
  public ArrayList<Integer> getRgbVals() {
    return this.rgbVals;
  }
}

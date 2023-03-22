package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only uses the blue component of each pixel.
 */
public class BlueComponentFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a BlueComponentFilter.
   *
   * @param image the image to apply this filter to
   */
  public BlueComponentFilter(ArrayList<ArrayList<RGBPixel>> image) {
    this.image = image;
  }

  /**
   * Applies this filter to the given image.
   *
   * @return the filtered image
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> apply() {
    for (ArrayList<RGBPixel> row : this.image) {
      for (RGBPixel pixel : row) {
        pixel.setGreen(0);
        pixel.setRed(0);
      }
    }
    return this.image;
  }
}

package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only darkens the value of each pixel.
 */
public class DarkenValueFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a DarkenValueFilter.
   *
   * @param image the image to apply this filter to
   */
  public DarkenValueFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        pixel.setRed(Math.max((pixel.getRed() - 255), 0));
        pixel.setGreen(Math.max((pixel.getGreen() - 255), 0));
        pixel.setBlue(Math.max((pixel.getBlue() - 255), 0));
      }
    }
    return this.image;
  }
}

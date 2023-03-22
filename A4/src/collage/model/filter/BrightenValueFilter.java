package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only brightens the value of each pixel.
 */
public class BrightenValueFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a BrightenValueFilter.
   *
   * @param image the image to apply this filter to
   */
  public BrightenValueFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        pixel.setRed(Math.min((pixel.getRed() + 255), 255));
        pixel.setGreen(Math.min((pixel.getGreen() + 255), 255));
        pixel.setBlue(Math.min((pixel.getBlue() + 255), 255));
      }
    }
    return this.image;
  }
}

package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only darkens the intensity of each pixel.
 */
public class DarkenIntensityFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a DarkenIntensityFilter.
   *
   * @param image the image to apply this filter to
   */
  public DarkenIntensityFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
        pixel.setRed(Math.max((pixel.getRed() - avg), 0));
        pixel.setGreen(Math.max((pixel.getGreen() - avg), 0));
        pixel.setBlue(Math.max((pixel.getBlue() - avg), 0));
      }
    }
    return this.image;
  }
}

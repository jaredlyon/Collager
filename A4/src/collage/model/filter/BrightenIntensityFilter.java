package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only brightens the intensity of each pixel.
 */
public class BrightenIntensityFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a BrightenIntensityFilter.
   *
   * @param image the image to apply this filter to
   */
  public BrightenIntensityFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        pixel.setRed(Math.min((pixel.getRed() + avg), 255));
        pixel.setGreen(Math.min((pixel.getGreen() + avg), 255));
        pixel.setBlue(Math.min((pixel.getBlue() + avg), 255));
      }
    }
    return this.image;
  }
}

package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only brightens the luma of each pixel.
 */
public class BrightenLumaFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a BrightenLumaFilter.
   *
   * @param image the image to apply this filter to
   */
  public BrightenLumaFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        int luma = (int) ((0.2126 * pixel.getRed())
                + (0.7152 * pixel.getGreen())
                + (0.0722 * pixel.getBlue()));
        pixel.setRed(Math.min((pixel.getRed() + luma), 255));
        pixel.setGreen(Math.min((pixel.getGreen() + luma), 255));
        pixel.setBlue(Math.min((pixel.getBlue() + luma), 255));
      }
    }
    return this.image;
  }
}

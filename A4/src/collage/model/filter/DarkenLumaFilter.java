package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only darkens the luma of each pixel.
 */
public class DarkenLumaFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> image;

  /**
   * Constructs a DarkenLumaFilter.
   *
   * @param image the image to apply this filter to
   */
  public DarkenLumaFilter(ArrayList<ArrayList<RGBPixel>> image) {
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
        pixel.setRed(Math.max((pixel.getRed() - luma), 0));
        pixel.setGreen(Math.max((pixel.getGreen() - luma), 0));
        pixel.setBlue(Math.max((pixel.getBlue() - luma), 0));
      }
    }
    return this.image;
  }
}

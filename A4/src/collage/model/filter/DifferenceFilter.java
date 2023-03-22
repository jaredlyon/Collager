package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only uses inversion blending.
 */
public class DifferenceFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> topImage;
  private ArrayList<ArrayList<RGBPixel>> botImage;

  /**
   * Constructs a DifferenceFilter.
   * @param topImage the top layer
   * @param botImage the bottom layer
   */
  public DifferenceFilter(ArrayList<ArrayList<RGBPixel>> topImage,
                          ArrayList<ArrayList<RGBPixel>> botImage) {
    this.topImage = topImage;
    this.botImage = botImage;
  }

  /**
   * Applies this filter to the given image.
   *
   * @return the filtered image
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> apply() {

  }

  /**
   * Generates a new pixel based on two combined pixels.
   * @param p1 the top pixel
   * @param p2 the bottom pixel
   * @return a new filtered pixel
   */
  private RGBPixel generateNewPixel(RGBPixel p1, RGBPixel p2) {

  }
}

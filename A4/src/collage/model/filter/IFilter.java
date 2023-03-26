package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter.
 */
public interface IFilter {
  /**
   * Applies this filter to the given image.
   *
   * @return the filtered image
   */
  ArrayList<ArrayList<RGBPixel>> apply();
}

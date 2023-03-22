package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter.
 */
public interface IFilter {
  ArrayList<ArrayList<RGBPixel>> apply();
}

package collage.model.filter;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only uses inversion blending.
 */
public class DifferenceFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> topImage;
  private ArrayList<ArrayList<RGBPixel>> botImageCumulative;

  /**
   * Constructs a DifferenceFilter.
   * @param topImage the top layer
   * @param botImageCumulative the cumulative image below
   */
  public DifferenceFilter(ArrayList<ArrayList<RGBPixel>> topImage,
                          ArrayList<ArrayList<RGBPixel>> botImageCumulative) {
    // assert that the images are the same size
    if (topImage.size() != botImageCumulative.size()
            || topImage.get(0).size() != botImageCumulative.get(0).size()) {
      throw new IllegalArgumentException("Images must be the same size");
    }
    this.topImage = topImage;
    this.botImageCumulative = botImageCumulative;
  }

  /**
   * Applies this filter to the given image.
   *
   * @return the filtered image
   */
  @Override
  public ArrayList<ArrayList<RGBPixel>> apply() {
    ArrayList<ArrayList<RGBPixel>> newImage = new ArrayList<>();
    for (int i = 0; i < topImage.size(); i++) {
      ArrayList<RGBPixel> row = new ArrayList<>();
      for (int j = 0; j < topImage.get(i).size(); j++) {
        RGBPixel p1 = topImage.get(i).get(j);
        RGBPixel p2 = botImageCumulative.get(i).get(j);
        RGBPixel newPixel = generateNewPixel(p1, p2);
        row.add(newPixel);
      }
      newImage.add(row);
    }
    return newImage;
  }

  /**
   * Generates a new pixel based on two combined pixels.
   * @param p1 the top pixel
   * @param p2 the bottom pixel
   * @return a new filtered pixel
   */
  private RGBPixel generateNewPixel(RGBPixel p1, RGBPixel p2) {
    RGBPixel newPixel = new RGBPixel(
            Math.abs(p1.getAlpha() - p2.getAlpha()),
            Math.abs(p1.getRed() - p2.getRed()),
            Math.abs(p1.getGreen() - p2.getGreen()),
            Math.abs(p1.getBlue() - p2.getBlue())
    );
    return newPixel;
  }
}

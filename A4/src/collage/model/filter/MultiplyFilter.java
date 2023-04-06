package collage.model.filter;

import java.util.ArrayList;

import collage.Utils;
import collage.model.pixel.HSLPixel;
import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only uses multiply blending.
 */
public class MultiplyFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> topImage;
  private ArrayList<ArrayList<RGBPixel>> botImageCumulative;

  /**
   * Constructs a MultiplyFilter.
   *
   * @param topImage           the top layer
   * @param botImageCumulative the bottom layer
   */
  public MultiplyFilter(ArrayList<ArrayList<RGBPixel>> topImage,
                        ArrayList<ArrayList<RGBPixel>> botImageCumulative) {
    // assert that the images are the same size
    if (topImage.size() != botImageCumulative.size()
            || topImage.get(0).size() != botImageCumulative.get(0).size()) {
      throw new IllegalArgumentException("The images are not the same size.");
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
        RGBPixel newPixel = this.generateNewPixel(p1, p2, p1.getAlpha());
        row.add(newPixel);
      }
      newImage.add(row);
    }
    return newImage;
  }

  /**
   * Generates a new pixel based on two combined pixels.
   *
   * @param p1 the top pixel
   * @param p2 the bottom pixel
   * @return a new filtered pixel
   */
  private RGBPixel generateNewPixel(RGBPixel p1, RGBPixel p2, int value) {
    HSLPixel hsl1 = Utils.rgbToHsl(p1);
    HSLPixel hsl2 = Utils.rgbToHsl(p2);
    HSLPixel transformedPixelHSL = new HSLPixel(
            hsl1.getHue(),
            hsl1.getSaturation(),
            hsl1.getLightness() * hsl2.getLightness()
    );
    return Utils.hslToRgb(transformedPixelHSL, value);
  }
}

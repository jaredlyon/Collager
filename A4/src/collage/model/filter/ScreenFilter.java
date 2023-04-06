package collage.model.filter;

import java.util.ArrayList;

import collage.Utils;
import collage.model.pixel.HSLPixel;
import collage.model.pixel.RGBPixel;

/**
 * Represents a filter that only uses screen blending.
 */
public class ScreenFilter implements IFilter {
  private ArrayList<ArrayList<RGBPixel>> topImage;
  private ArrayList<ArrayList<RGBPixel>> botImageCumulative;

  /**
   * Constructs a ScreenFilter.
   * @param topImage the top layer
   * @param botImageCumulative the bottom layer
   */
  public ScreenFilter(ArrayList<ArrayList<RGBPixel>> topImage,
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
        RGBPixel newPixel = this.generateNewPixel(p1, p2);
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
    System.out.println("in generate new pixel");
    HSLPixel hsl1 = Utils.RGBToHSL(p1);
    System.out.println(String.format("hsl1: %f, %f, %f", hsl1.getHue(), hsl1.getSaturation(),
            hsl1.getLightness()));
    System.out.println("p2: " + p2.convertToPPMRepresentation());
    HSLPixel hsl2 = Utils.RGBToHSL(p2);
    System.out.println(String.format("hsl2: %f, %f, %f", hsl2.getHue(), hsl2.getSaturation(),
            hsl2.getLightness()));
    double newLightness = 1 - ((1 - hsl1.getLightness()) * (1 - hsl2.getLightness()));
    System.out.println(String.format("hue: %f, sat: %f, light: %f", hsl1.getHue(),
            hsl1.getSaturation(), newLightness));
    HSLPixel transformedPixelHSL = new HSLPixel(
            hsl1.getHue(),
            hsl1.getSaturation(),
            newLightness
    );
    RGBPixel newPixel = Utils.HSLToRGB(transformedPixelHSL);
    return newPixel;
  }
}

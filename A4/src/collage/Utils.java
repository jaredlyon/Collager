package collage;

import collage.model.pixel.RGBPixel;
import collage.model.pixel.HSLPixel;

/**
 * Represents a collection of utility methods for the collager project.
 */
public class Utils {

  /**
   * Converts an RGB pixel to an HSL pixel.
   *
   * @param pixel the RGB pixel to convert
   * @return the HSL pixel
   */
  public static HSLPixel RGBToHSL(RGBPixel pixel) {
    double r = pixel.getRed();
    double g = pixel.getGreen();
    double b = pixel.getBlue();
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue, saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }

    return new HSLPixel(hue, saturation, lightness);
  }

  /**
   * Converts an HSL pixel to an RGB pixel.
   *
   * @param pixel the HSL pixel to convert
   * @return the RGB pixel
   */
  public static RGBPixel HSLToRGB(HSLPixel pixel) {
    double var0 = pixel.getHue();
    double var2 = pixel.getSaturation();
    double var4 = pixel.getLightness();
    double var6 = convertFn(var0, var2, var4, 0) * 255.0;
    double var8 = convertFn(var0, var2, var4, 8) * 255.0;
    double var10 = convertFn(var0, var2, var4, 4) * 255.0;
    return new RGBPixel(255, (int)var6, (int)var8, (int)var10);
  }

  /**
   * Converts an HSL pixel to an RGB pixel.
   * This method is used by the HSLToRGB method.
   * @param var0 the hue
   * @param var2 the saturation
   * @param var4 the lightness
   * @param var6 the offset
   * @return the RGB pixel
   */
  private static double convertFn(double var0, double var2, double var4, int var6) {
    double var7 = ((double)var6 + var0 / 30.0) % 12.0;
    double var9 = var2 * Math.min(var4, 1.0 - var4);
    return var4 - var9 * Math.max(-1.0, Math.min(var7 - 3.0, Math.min(9.0 - var7, 1.0)));
  }
}
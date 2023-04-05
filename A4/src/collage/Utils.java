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
    double var0 = pixel.getRed();
    double var2 = pixel.getGreen();
    double var4 = pixel.getBlue();
    double var6 = Math.max(var0, Math.max(var2, var4));
    double var8 = Math.min(var0, Math.min(var2, var4));
    double var10 = var6 - var8;
    double var12 = (var6 + var8) / 2.0;
    double var16;
    double var14;
    if (var10 == 0.0) {
      var14 = 0.0;
      var16 = 0.0;
    } else {
      var16 = var10 / (1.0 - Math.abs(2.0 * var12 - 1.0));
      var14 = 0.0;
      if (var6 == var0) {
        var14 = (var2 - var4) / var10;
        var14 %= 6.0;
      } else if (var6 == var2) {
        var14 = (var4 - var0) / var10;
        var14 += 2.0;
      } else if (var6 == var4) {
        var14 = (var0 - var2) / var10;
        var14 += 4.0;
      }

      var14 *= 60.0;
    }

    return new HSLPixel(var14, var16, var12);
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
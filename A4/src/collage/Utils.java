package collage;

import collage.model.ProjConstPPM;
import collage.model.pixel.RGBPixel;
import collage.model.pixel.HSLPixel;

/**
 * Represents a collection of utility methods.
 */
public class Utils {
  public static ProjConstPPM readPPM(String path) {

  }

  public static void writePPM(String content) {

  }

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

  public static RGBPixel HSLToRGB(HSLPixel pixel) {
    double convertFn(double hue, double saturation, double lightness, int n) {
      double k = (n + (hue / 30)) % 12;
      double a = saturation * Math.min(lightness, 1 - lightness);

      return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
    }
    double hue = pixel.getHue();
    double saturation = pixel.getHue();
    double lightness = pixel.getHue();
    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;

    return new RGBPixel(r, g, b);
  }
}
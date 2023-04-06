import org.junit.Test;

import collage.Utils;
import collage.model.pixel.HSLPixel;
import collage.model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the test suite for the Utils class.
 */
public class UtilsTest {

  @Test
  public void testRGBToHSL() {
    RGBPixel p1 = new RGBPixel(255, 34, 67, 55);
    HSLPixel p2 = Utils.RGBToHSL(p1);
    assertEquals(158.1, p2.getHue(), 0.1);
    assertEquals(0.327, p2.getSaturation(), 0.1);
    assertEquals(0.198, p2.getLightness(), 0.1);
  }

  @Test
  public void testHSLToRGB() {
    HSLPixel p1 = new HSLPixel(158.1, 0.327, 0.198);
    RGBPixel p2 = Utils.HSLToRGB(p1, 255);
    assertEquals(33, p2.getRed());
    assertEquals(67, p2.getGreen());
    assertEquals(54, p2.getBlue());
  }
}

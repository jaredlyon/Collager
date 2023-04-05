package FilterTests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.GreenComponentFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the GreenComponentFilter class.
 */
public class GreenComponentFilterTest {

  /**
   * Tests the apply method.
   */
  @Test
  public void testApply() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(1, 255, 255, 255));
    image.add(row);
    IFilter filter = new GreenComponentFilter(image);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    assertEquals(0, newImage.get(0).get(0).getRed());
    assertEquals(255, newImage.get(0).get(0).getGreen());
    assertEquals(0, newImage.get(0).get(0).getBlue());
  }
}

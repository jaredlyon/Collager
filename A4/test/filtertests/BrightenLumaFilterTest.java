package filtertests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.BrightenLumaFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the BrightenLumaFilter class.
 */
public class BrightenLumaFilterTest {
  /**
   * Tests the apply method.
   */
  @Test
  public void testApply() {
    ArrayList<ArrayList<RGBPixel>> image1 = new ArrayList<>();
    ArrayList<RGBPixel> row1 = new ArrayList<>();
    row1.add(new RGBPixel(1, 255, 255, 255));
    image1.add(row1);
    IFilter filter = new BrightenLumaFilter(image1);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    assertEquals(255, newImage.get(0).get(0).getRed());
    assertEquals(255, newImage.get(0).get(0).getGreen());
    assertEquals(255, newImage.get(0).get(0).getBlue());
  }
}

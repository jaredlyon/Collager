package filtertests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.ScreenFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the ScreenFilter class.
 */
public class ScreenFilterTest {
  /**
   * Tests the apply method.
   */
  @Test
  public void testApply() {
    ArrayList<ArrayList<RGBPixel>> image1 = new ArrayList<>();
    ArrayList<RGBPixel> row1 = new ArrayList<>();
    row1.add(new RGBPixel(1, 255, 255, 255));
    image1.add(row1);
    ArrayList<ArrayList<RGBPixel>> image2 = new ArrayList<>();
    ArrayList<RGBPixel> row2 = new ArrayList<>();
    row2.add(new RGBPixel(1, 255, 255, 255));
    image2.add(row2);
    IFilter filter = new ScreenFilter(image1, image2);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    assertEquals(255, newImage.get(0).get(0).getRed());
    assertEquals(255, newImage.get(0).get(0).getGreen());
    assertEquals(255, newImage.get(0).get(0).getBlue());
  }
}

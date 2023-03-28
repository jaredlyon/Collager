package FilterTests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.DarkenLumaFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the DarkenLumaFilter class.
 */
public class DarkenLumaFilterTest {
  /**
   * Tests the apply method.
   */
  @Test
  public void testApply() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(1, 255, 255, 255));
    row.add(new RGBPixel(1, 255, 255, 255));
    row.add(new RGBPixel(1, 255, 255, 255));
    image.add(row);
    IFilter filter = new DarkenLumaFilter(image);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    ArrayList<ArrayList<RGBPixel>> expectedImage = new ArrayList<>();
    ArrayList<RGBPixel> expectedRow = new ArrayList<>();
    expectedRow.add(new RGBPixel(1, 0, 0, 0));
    expectedRow.add(new RGBPixel(1, 0, 0, 0));
    expectedRow.add(new RGBPixel(1, 0, 0, 0));
    expectedImage.add(expectedRow);
    assertEquals(expectedImage, newImage);
  }
}

package FilterTests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.NormalFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the NormalFilter class.
 */
public class NormalFilterTest {
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
    ArrayList<ArrayList<RGBPixel>> expectedImage = new ArrayList<>();
    ArrayList<RGBPixel> expectedRow = new ArrayList<>();
    row.add(new RGBPixel(1, 255, 255, 255));
    row.add(new RGBPixel(1, 255, 255, 255));
    row.add(new RGBPixel(1, 255, 255, 255));
    expectedImage.add(expectedRow);
    expectedImage.add(row);
    IFilter filter = new NormalFilter(image);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    assertEquals(expectedImage, newImage);
  }
}

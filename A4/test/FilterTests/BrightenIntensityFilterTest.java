package FilterTests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.BrightenIntensityFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the BrightenIntensityFilter class.
 */
public class BrightenIntensityFilterTest {
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
    IFilter filter = new BrightenIntensityFilter(image);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();
    ArrayList<ArrayList<RGBPixel>> expectedImage = new ArrayList<>();
    ArrayList<RGBPixel> expectedRow = new ArrayList<>();
    expectedRow.add(new RGBPixel(1, 255, 255, 255));
    expectedRow.add(new RGBPixel(1, 255, 255, 255));
    expectedRow.add(new RGBPixel(1, 255, 255, 255));
    expectedImage.add(expectedRow);
    assertEquals(expectedImage, newImage);
  }
}

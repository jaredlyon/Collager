package filtertests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import collage.model.filter.MultiplyFilter;
import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Tests the MultiplyFilter class.
 */
public class MultiplyFilterTest {
  /**
   * Tests the apply method.
   */
  @Test
  public void testApply() {
    // top image
    ArrayList<ArrayList<RGBPixel>> image1 = new ArrayList<>();
    ArrayList<RGBPixel> row1 = new ArrayList<>();
    row1.add(new RGBPixel(1, 255, 255, 255));
    image1.add(row1);

    // bot image
    ArrayList<ArrayList<RGBPixel>> image2 = new ArrayList<>();
    ArrayList<RGBPixel> row2 = new ArrayList<>();
    row2.add(new RGBPixel(1, 255, 255, 255));
    image2.add(row2);

    // define filter + apply
    IFilter filter = new MultiplyFilter(image1, image2);
    ArrayList<ArrayList<RGBPixel>> newImage = filter.apply();

    // check vals
    assertEquals(255, newImage.get(0).get(0).getRed());
    assertEquals(255, newImage.get(0).get(0).getGreen());
    assertEquals(255, newImage.get(0).get(0).getBlue());
  }
}

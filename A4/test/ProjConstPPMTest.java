import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import collage.model.ProjConstPPM;
import collage.model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Represents the test suite for the PROJConstPPM.
 */
public class ProjConstPPMTest {
  private ProjConstPPM p1;

  @Before
  public void init() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(255, 1, 2, 3));
    p1 = new ProjConstPPM(1, 1, 1, image);
  }

  @Test
  public void testConstructor() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(255, 1, 2, 3));
    ProjConstPPM constructorTest = new ProjConstPPM(1, 2, 3, image);
    assertNotNull(constructorTest);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(255, 1, 2, 3));
    ProjConstPPM badConstructorTest1 = new ProjConstPPM(-1, 2, 3, image);
    ProjConstPPM badConstructorTest2 = new ProjConstPPM(1, -2, 3, image);
    ProjConstPPM badConstructorTest3 = new ProjConstPPM(1, 2, -3, image);
    ProjConstPPM badConstructorTest4 = new ProjConstPPM(1, 2, 3, null);
  }

  @Test
  public void testGetWidth() {
    assertEquals(1, this.p1.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(1, this.p1.getHeight());
  }

  @Test
  public void testGetMaxVal() {
    assertEquals(1, this.p1.getMaxVal());
  }

  @Test
  public void testGetRGBVals() {
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<>();
    ArrayList<RGBPixel> row = new ArrayList<>();
    row.add(new RGBPixel(255, 1, 2, 3));
    assertEquals(image, this.p1.getImage());
  }
}

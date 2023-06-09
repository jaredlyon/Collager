import org.junit.Before;
import org.junit.Test;

import collage.model.pixel.HSLPixel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the test suite for the HSLPixel class.
 */
public class HSLPixelTest {
  private HSLPixel p1;

  @Before
  public void init() {
    p1 = new HSLPixel(1.0, 1.0, 1.0);
  }

  @Test
  public void testConstructor() {
    HSLPixel constructorTest = new HSLPixel(1.0, 0.87, 0.3);
    assertEquals(1.0, constructorTest.getHue(), 0.001);
    assertEquals(0.3, constructorTest.getLightness(), 0.001);
    assertEquals(0.87, constructorTest.getSaturation(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    HSLPixel badConstructorTest1 = new HSLPixel(-1, 1, 1);
    HSLPixel badConstructorTest2 = new HSLPixel(1, -1, 1);
    HSLPixel badConstructorTest3 = new HSLPixel(1, 1, -1);
  }

  @Test
  public void testConvertToPPMRepresentation() {
    HSLPixel pixel = new HSLPixel(306.2, 0.74, 0.35);
    assertEquals("155 2 77", pixel.convertToPPMRepresentation());
  }

  @Test
  public void testSetHue() {
    assertEquals(1, this.p1.getHue(), 0.001);
    this.p1.setHue(2);
    assertEquals(2, this.p1.getHue(), 0.001);
  }

  @Test
  public void testSetSaturation() {
    assertEquals(1.0, this.p1.getSaturation(), 0.001);
    this.p1.setSaturation(0.2);
    assertEquals(0.2, this.p1.getSaturation(), 0.001);
  }

  @Test
  public void testSetLightness() {
    assertEquals(1.0, this.p1.getLightness(), 0.001);
    this.p1.setLightness(0.2);
    assertEquals(0.2, this.p1.getLightness(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadVals() {
    this.p1.setSaturation(-1);
    this.p1.setHue(-1);
    this.p1.setLightness(-1);
  }

  @Test
  public void testGetHue() {
    assertEquals(1.0, this.p1.getHue(), 0.001);
  }

  @Test
  public void testGetSaturation() {
    assertEquals(1.0, this.p1.getSaturation(), 0.001);
  }

  @Test
  public void testGetLightness() {
    assertEquals(1.0, this.p1.getLightness(), 0.001);
  }

  @Test
  public void testGetVals() {
    assertEquals("1.0 1.0 1.0", this.p1.getVals());
  }
}

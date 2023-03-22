import org.junit.Before;
import org.junit.Test;

import collage.model.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;

/**
 * Represents the test suite for the Pixel class.
 */
public class RGBPixelTest {
  private RGBPixel p1;

  @Before
  public void init() {
    p1 = new RGBPixel(1, 1, 1, 1);
  }

  @Test
  public void testConstructor() {
    RGBPixel constructorTest = new RGBPixel(1, 2, 3, 4);
    assertEquals(1, constructorTest.getAlpha());
    assertEquals(2, constructorTest.getRed());
    assertEquals(3, constructorTest.getGreen());
    assertEquals(4, constructorTest.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    RGBPixel badConstructorTest1 = new RGBPixel(-1, 2, 3, 4);
    RGBPixel badConstructorTest2 = new RGBPixel(1, -2, 3, 4);
    RGBPixel badConstructorTest3 = new RGBPixel(1, 2, -3, 4);
    RGBPixel badConstructorTest4 = new RGBPixel(1, 2, 3, -4);
  }

  @Test
  public void testConvertToPPMRepresentation() {
    p1.convertToPPMRepresentation();
    assertEquals(1, this.p1.getAlpha());
    assertEquals(2, this.p1.getRed());
    assertEquals(3, this.p1.getGreen());
    assertEquals(4, this.p1.getBlue());
  }

  @Test
  public void testSetAlpha() {
    assertEquals(1, this.p1.getAlpha());
    this.p1.setAlpha(2);
    assertEquals(2, this.p1.getAlpha());
  }

  @Test
  public void testSetRed() {
    assertEquals(1, this.p1.getRed());
    this.p1.setRed(2);
    assertEquals(2, this.p1.getRed());
  }

  @Test
  public void testSetGreen() {
    assertEquals(1, this.p1.getGreen());
    this.p1.setGreen(2);
    assertEquals(2, this.p1.getGreen());
  }

  @Test
  public void testSetBlue() {
    assertEquals(1, this.p1.getBlue());
    this.p1.setBlue(2);
    assertEquals(2, this.p1.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBadVals() {
    this.p1.setAlpha(-1);
    this.p1.setRed(-1);
    this.p1.setGreen(-1);
    this.p1.setBlue(-1);
  }

  @Test
  public void testGetAlpha() {
    assertEquals(1, this.p1.getAlpha());
  }

  @Test
  public void testGetRed() {
    assertEquals(1, this.p1.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(1, this.p1.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(1, this.p1.getBlue());
  }

  @Test
  public void testGetVals() {
    assertEquals("1 1 1 ", this.p1.getVals());
  }
}

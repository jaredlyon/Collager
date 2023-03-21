import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import collage.model.ProjConstPPM;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Represents the test suite for the PROJConstPPM.
 */
public class ProjConstPPMTest {
  private ProjConstPPM p1;

  @Before
  public void init() {
    ArrayList<Integer> rgbVals = new ArrayList<Integer>();
    rgbVals.add(1);
    rgbVals.add(2);
    rgbVals.add(3);
    p1 = new ProjConstPPM(1, 1, 1, rgbVals);
  }

  @Test
  public void testConstructor() {
    ArrayList<Integer> rgbVals = new ArrayList<Integer>();
    rgbVals.add(1);
    rgbVals.add(2);
    rgbVals.add(3);
    ProjConstPPM constructorTest = new ProjConstPPM(1, 2, 3, rgbVals);
    assertNotNull(constructorTest);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    ArrayList<Integer> rgbVals = new ArrayList<Integer>();
    rgbVals.add(1);
    rgbVals.add(2);
    rgbVals.add(3);
    ProjConstPPM badConstructorTest1 = new ProjConstPPM(-1, 2, 3, rgbVals);
    ProjConstPPM badConstructorTest2 = new ProjConstPPM(1, -2, 3, rgbVals);
    ProjConstPPM badConstructorTest3 = new ProjConstPPM(1, 2, -3, rgbVals);
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
    ArrayList<Integer> rgbVals = new ArrayList<Integer>();
    rgbVals.add(1);
    rgbVals.add(2);
    rgbVals.add(3);
    assertEquals(rgbVals, this.p1.getRgbVals());
  }
}

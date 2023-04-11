import org.junit.Test;
import org.junit.Assert;
import collage.model.ImageUtil;
import collage.model.ProjConstPPM;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Represents the test suite for the ImageUtil class.
 */
public class ImageUtilTest {
  @Test
  public void testReadPPM() {
    ProjConstPPM ppm = null;
    try {
      ppm = ImageUtil.readPPM("./A4/res/tako.ppm");
    } catch (Exception e) {
      fail();
    }
    assertNotNull(ppm);
    Assert.assertEquals(225, ppm.getWidth());
    Assert.assertEquals(300, ppm.getHeight());
    Assert.assertEquals(255, ppm.getMaxVal());
    assertNotNull(ppm.getImage());
  }

  @Test
  public void testReadJPG() {
    ProjConstPPM ppm = null;
    try {
      ppm = ImageUtil.readJPG("./A4/res/jpgtest.jpg");
    } catch (Exception e) {
      fail();
    }
    assertNotNull(ppm);
    Assert.assertEquals(100, ppm.getWidth());
    Assert.assertEquals(87, ppm.getHeight());
    Assert.assertEquals(255, ppm.getMaxVal());
    assertNotNull(ppm.getImage());
  }

  @Test
  public void testReadPNG() {
    ProjConstPPM ppm = null;
    try {
      ppm = ImageUtil.readPNG("./A4/res/pngtest.png");
    } catch (Exception e) {
      System.out.println(e);
    }

    assertNotNull(ppm);
    Assert.assertEquals(100, ppm.getWidth());
    Assert.assertEquals(87, ppm.getHeight());
    Assert.assertEquals(255, ppm.getMaxVal());
    assertNotNull(ppm.getImage());
  }
}
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
  public void testWritePPM() {
    // TODO: implement
  }

  @Test
  public void testReadJPEG() {
    ProjConstPPM ppm = null;
    try {
      ppm = ImageUtil.readJPEG("./A4/res/test.jpeg");
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
  public void testWriteJPEG() {
    // TODO: implement
  }

  @Test
  public void testReadPNG() {
    ProjConstPPM ppm = null;
    try {
      ppm = ImageUtil.readJPEG("./A4/res/test.png");
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
  public void testWritePNG() {
    // TODO: implement
  }
}
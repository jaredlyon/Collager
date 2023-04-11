import org.junit.Test;
import org.junit.Assert;
import collage.model.ImageUtil;
import collage.model.ProjConstPPM;

import static org.junit.Assert.assertNotNull;

/**
 * Represents the test suite for the ImageUtil class.
 */
public class ImageUtilTest {

  @Test
  public void testReadPPM() {
    ProjConstPPM ppm = ImageUtil.readPPM("./A4/res/tako.ppm");

    assertNotNull(ppm);
    Assert.assertEquals(225, ppm.getWidth());
    Assert.assertEquals(300, ppm.getHeight());
    Assert.assertEquals(255, ppm.getMaxVal());
    assertNotNull(ppm.getRgbVals());
  }

  @Test
  public void testWritePPM() {
    // TODO: implement
  }

  @Test
  public void testReadJPEG() {
    // TODO: implement
  }

  @Test
  public void testWriteJPEG() {
    // TODO: implement
  }

  @Test
  public void testReadPNG() {
    // TODO: implement
  }

  @Test
  public void testWritePNG() {
    // TODO: implement
  }
}
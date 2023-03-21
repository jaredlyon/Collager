import org.junit.Test;

import collage.model.Model;
import collage.view.IView;
import collage.view.View;

import static org.junit.Assert.assertNotNull;

/**
 * Represents the test suite for the view.
 */
public class ViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IView invalidView = new View(null);
  }

  @Test
  public void testValidConstructor() {
    IView validView = new View(new Model());
    assertNotNull(validView);
  }
}

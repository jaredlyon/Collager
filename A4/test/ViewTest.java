import org.junit.Test;

import collage.model.Model;
import collage.view.IScriptView;
import collage.view.ScriptView;

import static org.junit.Assert.assertNotNull;

/**
 * Represents the test suite for the view.
 */
public class ViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    IScriptView invalidView = new ScriptView(null);
  }

  @Test
  public void testValidConstructor() {
    IScriptView validView = new ScriptView(new Model());
    assertNotNull(validView);
  }
}

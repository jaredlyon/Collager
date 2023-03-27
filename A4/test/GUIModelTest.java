import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

import collage.model.GUIModel;

/**
 * Tests the GUIModel class.
 */
public class GUIModelTest {
  class MockGUIModel extends GUIModel {
    Appendable log;
    public MockGUIModel(Appendable log) {
      super();
      this.log = log;
    }

    @Override
    public void loadProject(String fileName) throws IllegalArgumentException, IOException {
      this.log.append("loadProject called with " + fileName);
    }
  }
  @Test
  public void testLoadProject() {
    StringBuilder log = new StringBuilder();
    MockGUIModel model = new MockGUIModel(log);
    try {

      model.loadProject("test");
      assertEquals("loadProject called with test", log.toString());
    } catch (IOException e) {
    }
  }

  @Test
  public void testNewProject() {
    // TODO: implement
  }

  @Test
  public void testGetRenderContent() {
    // TODO: implement
  }

  @Test
  public void testSelectLayer() {
    // TODO: implement
  }
}

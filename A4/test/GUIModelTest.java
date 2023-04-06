import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import collage.model.GUIModel;
import collage.model.pixel.RGBPixel;
import collage.view.RenderContent;

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
  public void testGetRenderContent() {
    GUIModel model = new GUIModel();
    model.newProject(10, 10);
    model.addLayer("layer1");
    ArrayList<ArrayList<RGBPixel>> pixels = model.getProject().buildImage();
    ArrayList<String> layerNames = new ArrayList<>();
    layerNames.add("background");
    layerNames.add("layer1");
    RenderContent expected = new RenderContent(10, 10, layerNames, "layer1", pixels);
    assertNotNull(expected);
  }

  @Test
  public void testSelectLayer() {
    GUIModel model = new GUIModel();
    model.newProject(10, 10);
    assertEquals("background", model.getCurrentLayerName());
    model.addLayer("layer1");
    assertEquals("layer1", model.getCurrentLayerName());
    model.addLayer("layer2");
    assertEquals("layer2", model.getCurrentLayerName());
  }
}

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import collage.model.Model;
import collage.model.Project;

/**
 * A class to test the model.
 */
public class ModelTest {
  private Model model;
  private Project project;

  @Before
  public void setUp() {
    model = new Model();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetProjectWhenProjectIsNull() {
    model.getProject();
  }

  @Test
  public void testGetProject() {
    model.newProject(10, 10);
    Project project = model.getProject();
    Assert.assertNotNull(project);
    Assert.assertEquals(10, project.getHeight());
    Assert.assertEquals(10, project.getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNewProjectInvalidDimensions() {
    model.newProject(0, 0);
  }

  @Test
  public void testNewProjectValidDimensions() {
    model.newProject(10, 10);
    Assert.assertNotNull(model.getProject());
    Assert.assertEquals(10, model.getProject().getHeight());
    Assert.assertEquals(10, model.getProject().getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadProjectInvalidFilename() {
    try {
      model.loadProject(null);
    } catch (IOException e) {
      Assert.fail("IOException should not be thrown");
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerWithNullName() {
    model.addLayer(null);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddLayerWhenProjectIsNull() {
    this.project = null;
    model.getProject().addLayer("Layer1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerWithNullLayer() {
    model.addImageToLayer(null, "Image1", 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddImageToLayerWithNullImageName() {
    model.addImageToLayer("Layer1", null, 0, 0);
  }

  @Test(expected = IllegalStateException.class)
  public void testAddImageToLayerWhenProjectIsNull() {
    this.project = null;
    model.getProject().addImageToLayer("Layer1", "Image1", 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterWithNullLayer() {
    model.setFilter(null, "Filter1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetFilterWithNullFilter() {
    model.setFilter("Layer1", null);
  }

  @Test(expected = IllegalStateException.class)
  public void testSetFilterWhenProjectIsNull() {
    this.project = null;
    model.getProject().setFilter("Layer1", "Filter1");
  }
}
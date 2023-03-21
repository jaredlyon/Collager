import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;


import collage.model.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Represents the test suite for the Project class.
 */
public class ProjectTest {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testCreateProject() {
    Project project = new Project(100, 200);
    assertNotNull(project);
  }

  @Test
  public void testCreateProjectFromFile() {
    Project project = new Project("res/tako.ppm");
    assertNotNull(project);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveImageNullFilename() {
    Project project = new Project(100, 200);
    project.saveImage(null);
  }

  @Test
  public void testAddLayer() {
    Project project = new Project(100, 200);
    project.addLayer("Layer 1");
    assertEquals(2, project.getLayers().size());
  }

  @Test
  public void testGetWidth() {
    Project project = new Project(100, 200);
    assertEquals(200, project.getWidth());
  }

  @Test
  public void testGetHeight() {
    Project project = new Project(100, 200);
    assertEquals(100, project.getHeight());
  }

  @Test
  public void testGetMaxVal() {
    Project project = new Project(100, 200);
    assertEquals(255, project.getMaxVal());
  }

  @Test
  public void testAddImageToLayer() {
    Project project = new Project(100, 100);
    project.addLayer("layer1");
    project.addImageToLayer("layer1", "res/tako.ppm", 0, 0);
    assertEquals("layer1", project.getLayers().get(1).getName());
  }

  @Test
  public void testSetFilter() {
    Project project = new Project(100, 100);
    project.addLayer("layer1");
    project.setFilter("layer1", "BLUE_COMPONENT");
    assertEquals("BLUE_COMPONENT", project.getLayers().get(1).getFilter());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testSetInvalidFilter() {
    Project project = new Project(100, 100);
    project.addLayer("layer1");
    project.setFilter("layer1", "INVALID_FILTER");
    assertEquals("INVALID_FILTER", project.getLayers().get(1).getFilter());
  }

  @Test
  public void testSaveImage() throws Exception {
    Project project = new Project(100, 100);
    project.addLayer("Layer 1");

    // Create a temporary test image file
    File imageFile = folder.newFile("test_image.ppm");
    FileWriter writer = new FileWriter(imageFile);
    writer.write("P3\n");
    writer.write("2 2\n");
    writer.write("255\n");
    writer.write("255 0 0 0 255 0 0 0 255 255 255 255\n");
    writer.close();

    project.addImageToLayer("Layer 1", imageFile.getAbsolutePath(), 0, 0);
    File file = folder.newFile("test_image_output.ppm");
    project.saveImage(file.getAbsolutePath());

    // Assert that file was created
    assertTrue(file.exists());

    // Assert that file has content
    String content = new String(Files.readAllBytes(file.toPath()));
    assertNotNull(content);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveProjectNullFilename() {
    Project project = new Project(100, 100);
    project.saveProject(null);
  }

  @Test
  public void testSaveProject() throws Exception {
    Project project = new Project(100, 100);
    project.addLayer("Layer 1");

    // Create a temporary test image file
    File imageFile = folder.newFile("test_image.ppm");
    FileWriter writer = new FileWriter(imageFile);
    writer.write("P3\n");
    writer.write("2 2\n");
    writer.write("255\n");
    writer.write("255 0 0 0 255 0 0 0 255 255 255 255\n");
    writer.close();

    project.addImageToLayer("Layer 1", imageFile.getAbsolutePath(), 0, 0);
    File file = folder.newFile("test_project");
    project.saveProject(file.getAbsolutePath());

    // Assert that file was created
    assertTrue(file.exists());

    // Assert that file has content
    String content = new String(Files.readAllBytes(file.toPath()));
    assertNotNull(content);
  }
}


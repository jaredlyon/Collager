package collage.model;

import java.io.IOException;
import java.util.ArrayList;

import collage.view.IRenderContent;
import collage.view.RenderContent;

/**
 * Represents the GUI model for the collage program.
 */
public class GUIModel extends Model {
  private String currentLayerName;

  /**
   * Loads a project.
   *
   * @param fileName the name of the file to load
   * @throws IllegalArgumentException if the file name is null
   */
  public void loadProject(String fileName) throws IllegalArgumentException, IOException {
    try {
      super.loadProject(fileName);
    } catch (IOException e) {
      throw new IOException("File not found");
    }
    this.currentLayerName = this.project.getLayers().get(
            this.project.getLayers().size() - 1).getName();
  }

  /**
   * Creates a new project.
   *
   * @param width  the width of the project
   * @param height the height of the project
   * @throws IllegalArgumentException if the dimensions are negative
   */
  public void newProject(int height, int width) throws IllegalArgumentException {
    super.newProject(height, width);
    this.currentLayerName = "background";
  }

  /**
   * Gets the render content for the current project.
   * This will be passed to the view via the controller for the user.
   * @return a RenderContent object representing the current project
   * @throws IllegalStateException if the project is null
   */
  public IRenderContent getRenderContent() throws IllegalStateException {
    ArrayList<String> layerNames = new ArrayList<>();
    for (Layer layer : this.project.getLayers()) {
      layerNames.add(layer.getName());
      System.out.println(layer.getName());
    }
    IRenderContent content = new RenderContent(
            this.project.getWidth(),
            this.project.getHeight(),
            layerNames,
            this.currentLayerName,
            this.project.buildImage()
    );
    return content;
  }

  /**
   * Selects a layer.
   *
   * @param layerName the name of the layer to select
   */
  public void selectLayer(String layerName) {
    this.currentLayerName = layerName;
  }

  /**
   * Adds a layer to the project.
   *
   * @param layerName the name of the layer to add
   */
  public void addLayer(String layerName) {
    super.addLayer(layerName);
    this.currentLayerName = layerName;
  }

  /**
   * Gets the name of the current layer.
   *
   * @return a String representing the name of the current layer
   */
  public String getCurrentLayerName() {
    return this.currentLayerName;
  }
}


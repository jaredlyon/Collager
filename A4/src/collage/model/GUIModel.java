package collage.model;

import java.io.IOException;
import java.util.ArrayList;

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
  public void newProject(int width, int height) throws IllegalArgumentException {
    super.newProject(width, height);
    this.currentLayerName = "background";
  }

  /**
   * Gets the content needed to render the current project.
   */
  public RenderContent getRenderContent() throws IllegalStateException {
    ArrayList<String> layerNames = new ArrayList<>();
    for (Layer layer : this.project.getLayers()) {
      layerNames.add(layer.getName());
    }
    RenderContent content = new RenderContent(
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
   * @param layerName the name of the layer to select
   */
  public void selectLayer(String layerName) {
    this.currentLayerName = layerName;
  }

  /**
   * Adds a layer to the project.
   * @param layerName the name of the layer to add
   */
  public void addLayer(String layerName) {
    super.addLayer(layerName);
    this.currentLayerName = layerName;
  }

  /**
   * Gets the name of the current layer.
   * @return a String representing the name of the current layer
   */
  public String getCurrentLayerName() {
    return this.currentLayerName;
  }

  /**
   * Sets the name of the current layer.
   * @param currentLayerName the name of the current layer
   */
  public void setCurrentLayerName(String currentLayerName) {
    this.currentLayerName = currentLayerName;
  }
}


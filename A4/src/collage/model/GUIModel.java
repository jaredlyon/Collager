package collage.model;

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
  public void loadProject(String fileName) throws IllegalArgumentException {
    super.loadProject(fileName);
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
}

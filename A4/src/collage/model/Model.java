package collage.model;

import java.io.IOException;

/**
 * Represents the model of the collage program.
 */
public class Model implements IModel {
  protected Project project;

  /**
   * Creates a new project.
   * @param height - Represents the height of this project
   * @param width  - Represents the width of this project
   * @throws IllegalArgumentException if the dimensions are negative
   */
  @Override
  public void newProject(int height, int width) throws IllegalArgumentException {
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("Invalid project dimensions");
    }
    this.project = new Project(height, width);
  }

  /**
   * Loads an existing file into a project.
   * @param filename - the new name of the file
   * @throws IllegalArgumentException if the filename is null
   * @throws IOException              if the file is not found
   */
  @Override
  public void loadProject(String filename) throws IllegalArgumentException, IOException {
    if (filename == null) {
      throw new IllegalArgumentException("File cannot be null.");
    }
    this.project = new Project(filename);
  }

  /**
   * Saves the project file.
   * @param filename - the new name of the file
   * @throws IllegalArgumentException if the filename is null
   */
  @Override
  public void saveProject(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("File cannot be null.");
    }
    this.project.saveProject(filename);
  }

  /**
   * Saves the image to a file.
   * @param filename - the new name of the file
   * @throws IllegalArgumentException if the filename is null
   */
  @Override
  public void saveImage(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("File cannot be null.");
    }
    this.project.saveImage(filename);
  }

  /**
   * Adds a layer to the collage.
   * @param name - represents the new layer
   * @throws IllegalArgumentException if the argument is null
   */
  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("layer name is null.");
    }
    this.project.addLayer(name);
  }

  /**
   * Adds an image to a layer.
   * @param layer     - the layer to be added to
   * @param imageName - the image being added
   * @param posX      - the target x Pos
   * @param posY      - the target y Pos
   * @throws IllegalArgumentException if the layer or image are null
   */
  @Override
  public void addImageToLayer(String layer, String imageName, int posX, int posY)
          throws IllegalArgumentException {
    if (layer == null || imageName == null) {
      throw new IllegalArgumentException("layer or imageName are null");
    }
    this.project.addImageToLayer(layer, imageName, posX, posY);
  }

  /**
   * Sets the filter of a layer.
   * @param layer  - the target layer
   * @param filter - the intended filter
   * @throws IllegalArgumentException if the layer or filter are null
   */
  @Override
  public void setFilter(String layer, String filter) throws IllegalArgumentException {
    if (layer == null || filter == null) {
      throw new IllegalArgumentException("layer or filer are null.");
    }
    this.project.setFilter(layer, filter);
  }

  /**
   * Gets the project from the model.
   * @return the Project
   * @throws IllegalStateException if there is no project
   */
  public Project getProject() throws IllegalStateException {
    if (project == null) {
      throw new IllegalStateException();
    }
    return this.project;
  }
}

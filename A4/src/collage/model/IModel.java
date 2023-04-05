package collage.model;

import java.io.IOException;

/**
 * Represents the model for the collage project.
 */
public interface IModel {

  /**
   * Generates a new collage project.
   * Should be a white background by default.
   *
   * @param height - Represents the height of this project
   * @param width  - Represents the width of this project
   * @throws IllegalArgumentException if the height/width args are invalid
   */
  void newProject(int height, int width) throws IllegalArgumentException;

  /**
   * Loads an existing file into the program.
   *
   * @param filename - the new name of the file
   * @throws IllegalArgumentException if the file does not exist
   * @throws IOException              if the file cannot be loaded
   */
  void loadProject(String filename) throws IllegalArgumentException, IOException;

  /**
   * Saves the existing project to a file.
   *
   * @param filename - the new name of the file
   * @throws IllegalArgumentException if the filename is invalid
   */
  void saveProject(String filename) throws IllegalArgumentException;

  /**
   * Saves the collage to a file.
   * @param filename - the file path
   * @throws IllegalArgumentException if the filename is invalid
   */
  void saveImage(String filename) throws IllegalArgumentException;

  /**
   * Manipulates the RGB values of a given collage.
   *
   * @param name - represents the new layer
   * @throws IllegalArgumentException if the layer is incorrect
   */
  void addLayer(String name) throws IllegalArgumentException;

  /**
   * Adds an image to a given layer based on an inputted pos.
   *
   * @param layer     - the layer to be added to
   * @param imageName - the image being added
   * @param posX      - the target x Pos
   * @param posY      - the target y Pos
   * @throws IllegalArgumentException if the layer or image don't exist or the position is invalid
   */
  void addImageToLayer(String layer, String imageName, int posX, int posY)
          throws IllegalArgumentException;

  /**
   * Sets the filter of a given layer.
   *
   * @param layer  - the target layer
   * @param filter - the intended filter
   * @throws IllegalArgumentException if the layer or filter do not exist
   */
  void setFilter(String layer, String filter) throws IllegalArgumentException;
}

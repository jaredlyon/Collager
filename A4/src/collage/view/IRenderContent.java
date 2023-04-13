package collage.view;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a class that carries relevant data for the view to render.
 */
public interface IRenderContent {
  /**
   * Gets the width of the project.
   *
   * @return the width of the project
   */
  int getWidth();

  /**
   * Gets the height of the project.
   *
   * @return the height of the project
   */
  int getHeight();

  /**
   * Gets the names of the layers in the project.
   *
   * @return the names of the layers in the project
   */
  ArrayList<String> getLayers();

  /**
   * Gets the name of the current layer.
   *
   * @return the name of the current layer
   */
  String getCurrentLayer();

  /**
   * Gets the pixels of the project.
   *
   * @return the pixels of the project
   */
  ArrayList<ArrayList<RGBPixel>> getPixels();
}

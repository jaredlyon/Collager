package collage.view;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a class that carries relevant data for the view to render.
 */
public class RenderContent {
  private final int width;
  private final int height;
  private final ArrayList<String> layerNames;
  private final String currentLayer;
  private final ArrayList<ArrayList<RGBPixel>> pixels;

  /**
   * Constructs a RenderContent.
   *
   * @param width        the width of the project
   * @param height       the height of the project
   * @param layerNames   the names of the layers in the project
   * @param currentLayer the name of the current layer
   * @param pixels       the pixels of the project
   * @throws IllegalArgumentException if any of the arguments are null or the dimensions are
   *     negative
   * @throws IllegalStateException    if the project is not loaded
   */
  public RenderContent(int width, int height, ArrayList<String> layerNames, String currentLayer,
                ArrayList<ArrayList<RGBPixel>> pixels) throws IllegalArgumentException {
    if (layerNames == null || currentLayer == null || pixels == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    } else if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Dimensions cannot be negative.");
    }
    this.width = width;
    this.height = height;
    this.layerNames = layerNames;
    this.currentLayer = currentLayer;
    this.pixels = pixels;
  }

  /**
   * Gets the width of the project.
   *
   * @return the width of the project
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of the project.
   *
   * @return the height of the project
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the names of the layers in the project.
   *
   * @return the names of the layers in the project
   */
  public ArrayList<String> getLayers() {
    return this.layerNames;
  }

  /**
   * Gets the name of the current layer.
   *
   * @return the name of the current layer
   */
  public String getCurrentLayer() {
    return this.currentLayer;
  }

  /**
   * Gets the pixels of the project.
   *
   * @return the pixels of the project
   */
  public ArrayList<ArrayList<RGBPixel>> getPixels() {
    return this.pixels;
  }
}

package collage.view;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a class that carries relevant data for the view to render.
 */
public class RenderContent {
  private int width;
  private int height;
  private ArrayList<String> layerNames;
  private String currentLayer;
  private ArrayList<ArrayList<RGBPixel>> pixels;

  public RenderContent(int width, int height, ArrayList<String> layerNames, String currentLayer,
                ArrayList<ArrayList<RGBPixel>> pixels) {
    this.width = width;
    this.height = height;
    this.layerNames = layerNames;
    this.currentLayer = currentLayer;
    this.pixels = pixels;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public ArrayList<String> getLayers() {
    return this.layerNames;
  }

  public String getCurrentLayer() {
    return this.currentLayer;
  }

  public ArrayList<ArrayList<RGBPixel>> getPixels() {
    return this.pixels;
  }
}

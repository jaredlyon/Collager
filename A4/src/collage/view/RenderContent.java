package collage.view;

import java.util.ArrayList;

import collage.model.pixel.RGBPixel;

/**
 * Represents a class that carries relevant data for the view to render.
 */
public class RenderContent {
  private int width;
  private int height;
  private String[] layers;
  private String currentLayer;
  private ArrayList<ArrayList<RGBPixel>> pixels;

  RenderContent(int width, int height, String[] layers, String currentLayer,
                ArrayList<ArrayList<RGBPixel>> pixels) {
    this.width = width;
    this.height = height;
    this.layers = layers;
    this.currentLayer = currentLayer;
    this.pixels = pixels;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public String[] getLayers() {
    return this.layers;
  }

  public String getCurrentLayer() {
    return this.currentLayer;
  }

  public ArrayList<ArrayList<RGBPixel>> getPixels() {
    return this.pixels;
  }
}

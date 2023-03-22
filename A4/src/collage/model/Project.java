package collage.model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import collage.model.filter.BlueComponentFilter;
import collage.model.filter.BrightenIntensityFilter;
import collage.model.filter.BrightenLumaFilter;
import collage.model.filter.BrightenValueFilter;
import collage.model.filter.DarkenIntensityFilter;
import collage.model.filter.DarkenLumaFilter;
import collage.model.filter.DarkenValueFilter;
import collage.model.filter.DifferenceFilter;
import collage.model.filter.GreenComponentFilter;
import collage.model.filter.IFilter;
import collage.model.filter.MultiplyFilter;
import collage.model.filter.NormalFilter;
import collage.model.filter.RedComponentFilter;
import collage.model.filter.ScreenFilter;
import collage.model.pixel.RGBPixel;

/**
 * Represents a collage project.
 */
public class Project {
  private String name;
  private int height;
  private int width;
  private int maxVal;
  private ArrayList<Layer> layers = new ArrayList<Layer>();

  /**
   * Generates a new project.
   *
   * @param height - the project height
   * @param width  - the project width
   */
  public Project(int height, int width) {
    this.height = height;
    this.width = width;
    this.maxVal = 255;
    this.layers.add(new Layer("background", height, width, 255));
  }

  /**
   * Loads a project from an existing file.
   *
   * @param filename - the file name
   */
  public Project(String filename) throws IllegalArgumentException {
    try {
      ProjConstPPM imageData = ImageUtil.readPPM(filename);
      this.height = imageData.getHeight();
      this.width = imageData.getWidth();
      this.maxVal = imageData.getMaxVal();
      this.layers.add(new Layer(filename));
    } catch (Exception e) {
      throw new IllegalArgumentException("File does not exist!");
    }
  }

  /**
   * Adds a layer to this project.
   *
   * @param name - the layer name
   */
  public void addLayer(String name) {
    this.layers.add(new Layer(name, this.height, this.width, this.maxVal));
  }

  /**
   * Adds an image to a layer in this project.
   *
   * @param layer     - The layer to be added to
   * @param imageName - the filepath of the image
   * @param posX      - the pos X of the image
   * @param posY      - the pos Y of the image
   */
  public void addImageToLayer(String layer, String imageName, int posX, int posY) {
    for (Layer iterLayer : this.layers) {
      if (layer.equals(iterLayer.getName())) {
        iterLayer.addImageToLayer(imageName, posX, posY);
      }
    }
  }

  /**
   * Sets the filter of a layer in this project.
   *
   * @param layer  - the layer to be set
   * @param filter - the filter
   */
  public void setFilter(String layer, String filter) throws IllegalArgumentException {
    boolean found = false;
    for (Layer iterLayer : this.layers) {
      if (layer.equals(iterLayer.getName())) {
        iterLayer.setFilter(filter);
        found = true;
      }
    }

    if (!found) {
      throw new IllegalArgumentException("Layer does not exist!");
    }
  }

  /**
   * Gets the width of this project.
   *
   * @return an int
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of this project.
   *
   * @return an int
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the maxVal of this project.
   *
   * @return an int
   */
  public int getMaxVal() {
    return this.maxVal;
  }

  /**
   * Saves this project to a file.
   *
   * @param filename - the filepath
   * @throws IllegalArgumentException if the filepath is null
   * @throws IllegalStateException    if there are no layers or the layers dont match dimensions
   */
  public void saveImage(String filename) throws IllegalArgumentException, IllegalStateException {
    // Check if the project can be condensed
    if (layers.size() == 0) {
      throw new IllegalStateException("Project has no layers.");
    }

    // check if layers are the same size
    int width = layers.get(0).getWidth();
    int height = layers.get(0).getHeight();

    for (Layer layer : layers) {
      if (layer.getWidth() != width || layer.getHeight() != height) {
        throw new IllegalStateException("Layers have different dimensions.");
      }
    }

    // TODO: assert somewhere that 2-layer-dependent filters cant be applied to the 0 index layer
    // apply the filters
    for (int i = 0; i < layers.size(); i++) {
      Layer curLayer = layers.get(i);
      IFilter filter = null;
      switch (curLayer.getFilter()) {
        case "NORMAL":
          filter = new NormalFilter(curLayer.getPixels());
          break;
        case "RED_COMPONENT":
          filter = new RedComponentFilter(curLayer.getPixels());
          break;
        case "GREEN_COMPONENT":
          filter = new GreenComponentFilter(curLayer.getPixels());
          break;
        case "BLUE_COMPONENT":
          filter = new BlueComponentFilter(curLayer.getPixels());
          break;
        case "BRIGHTEN_VALUE":
          filter = new BrightenValueFilter(curLayer.getPixels());
          break;
        case "BRIGHTEN_INTENSITY":
          filter = new BrightenIntensityFilter(curLayer.getPixels());
          break;
        case "BRIGHTEN_LUNA":
          filter = new BrightenLumaFilter(curLayer.getPixels());
          break;
        case "DARKEN_VALUE":
          filter = new DarkenValueFilter(curLayer.getPixels());
          break;
        case "DARKEN_INTENSITY":
          filter = new DarkenIntensityFilter(curLayer.getPixels());
          break;
        case "DARKEN_LUNA":
          filter = new DarkenLumaFilter(curLayer.getPixels());
          break;
        case "DIFFERENCE":
          filter = new DifferenceFilter(curLayer.getPixels(), this.layers.get(i - 1));
          break;
        case "MULTIPLY":
          filter = new MultiplyFilter(curLayer.getPixels(), this.layers.get(i - 1));
          break;
        case "SCREEN":
          filter = new ScreenFilter(curLayer.getPixels(), this.layers.get(i - 1));
          break;
        default:
          throw new IllegalStateException("Layer does not have a filter!");
      }

      ArrayList<ArrayList<RGBPixel>> newPixels = filter.apply();
      curLayer.setPixels(newPixels);
    }


    for (Layer layer : layers) {
      layer.filter.apply();
    }

    ArrayList<ArrayList<RGBPixel>> layerPixels = new ArrayList<ArrayList<RGBPixel>>();
    for (Layer layer : layers) {
      layerPixels.add(layer.getRawPixels());
    }

    for (int i = 0; i <= this.layers.size() - 2; i++) {
      ArrayList<RGBPixel> top = layerPixels.get(i + 1); //1
      ArrayList<RGBPixel> bot = layerPixels.get(i); //0

      for (int j = 0; j < top.size() - 1; j++) {
        RGBPixel topPixel = top.get(j); //1
        // r g b a
        double topR = topPixel.getRed();
        double topG = topPixel.getGreen();
        double topB = topPixel.getBlue();
        double topA = topPixel.getAlpha();

        RGBPixel botPixel = bot.get(j); //0
        // dr dg db da
        double botR = botPixel.getRed();
        double botG = botPixel.getGreen();
        double botB = botPixel.getBlue();
        double botA = botPixel.getAlpha();

        // a''
        double percentAlpha = ((topA / 255.0) + (botA / 255.0) * (1 - (topA / 255.0)));
        // r' b' g' a'
        int a = (int) percentAlpha * 255;
        int r = (int) (((topA / 255.0) * topR + (botR * (botA / 255.0) * (1 - (topA / 255.0))))
                * (1 / percentAlpha));
        int g = (int) (((topA / 255.0) * topG + (botG * (botA / 255.0) * (1 - (topA / 255.0))))
                * (1 / percentAlpha));
        int b = (int) (((topA / 255.0) * topB + (botB * (botA / 255.0) * (1 - (topA / 255.0))))
                * (1 / percentAlpha));

        top.set(j, new RGBPixel(a, r, g, b));
      }
    }

    ArrayList<RGBPixel> condensedPixels = layerPixels.get(layerPixels.size() - 1);
    StringBuilder rgbVals = new StringBuilder();

    for (RGBPixel p : condensedPixels) {
      p.setRed(p.getRed() * (p.getAlpha() / 255));
      p.setGreen(p.getGreen() * (p.getAlpha() / 255));
      p.setBlue(p.getBlue() * (p.getAlpha() / 255));
      rgbVals.append(p.getVals());
    }

    // save the file
    if (filename == null) {
      throw new IllegalArgumentException("File path cannot be null");
    }

    FileWriter writer = null;
    // P3
    // width height
    // maxVal
    // rgbVals

    try {
      writer = new FileWriter(filename + ".ppm");
      writer.write("P3\n");
      writer.write(this.getWidth() + " " + this.getHeight() + "\n");
      writer.write(this.getMaxVal() + "\n");
      writer.write(rgbVals.toString() + "\n");
      writer.close();
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  /**
   * Saves this collage project.
   *
   * @param filename - the filepath
   * @throws IllegalArgumentException if the filepath is null
   */
  public void saveProject(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("File path cannot be null");
    }

    FileWriter writer = null;

    try {
      writer = new FileWriter(filename + ".ppm");
      writer.write("C1\n");
      writer.write(this.getWidth() + " " + this.getHeight() + "\n");
      writer.write(this.getMaxVal() + "\n");

      for (Layer layer : this.layers) {
        writer.write(layer.getName() + " " + layer.getFilter() + "\n");

        for (int i = 0; i < layer.getWidth(); i++) {
          for (int j = 0; j < layer.getHeight(); j++) {
            writer.write(layer.getPixelData(j, i));
          }
        }

        writer.write("\n");
      }

      writer.close();
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  /**
   * Gets the layers of the project.
   *
   * @return an ArrayList of layer
   */
  public ArrayList<Layer> getLayers() {
    return this.layers;
  }
}

package collage.model;

import java.util.ArrayList;

import collage.model.filter.IFilter;
import collage.model.pixel.RGBPixel;

/**
 * Represents a layer within a project.
 */
public class Layer {
  private final String name;
  private final int height;
  private final int width;
  private final int maxVal;
  private ArrayList<ArrayList<RGBPixel>> pixels = new ArrayList<ArrayList<RGBPixel>>();
  private String filter;

  /**
   * Generates this layer.
   *
   * @param name   - the name of this layer
   * @param height - the height of this layer
   * @param width  - the width of this layer
   */
  public Layer(String name, int height, int width, int maxVal) throws IllegalArgumentException {
    if (name == null || height < 1 || width < 1 || maxVal < 1) {
      throw new IllegalArgumentException("Illegal layer arguments!");
    }
    this.name = name;
    this.height = height;
    this.width = width;
    this.maxVal = maxVal;
    this.pixels = new ArrayList<ArrayList<RGBPixel>>();
    this.setFilter("NORMAL");

    for (int i = 0; i < height; i++) {
      ArrayList<RGBPixel> row = new ArrayList<RGBPixel>();
      for (int j = 0; j < width; j++) {
        row.add(new RGBPixel(0, 255, 255, 255));
      }
      this.pixels.add(row);
    }
  }

  /**
   * Reads a file to generate a layer with an image.
   * @param filename - the image file path
   * @throws IllegalArgumentException if the filepath is null
   */
  public Layer(String filename) throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("Illegal layer arguments!");
    }

    ProjConstPPM imageData = ImageUtil.readPPM(filename);
    this.name = "background";
    this.height = imageData.getHeight();
    this.width = imageData.getWidth();
    this.maxVal = imageData.getMaxVal();
    this.setFilter("NORMAL");

    // convert the rgb values into pixels
    ArrayList<RGBPixel> pixels = new ArrayList<RGBPixel>();
    for (int i = 0; i < imageData.getRgbVals().size(); i += 3) {
      pixels.add(new RGBPixel(this.maxVal,
              imageData.getRgbVals().get(i),
              imageData.getRgbVals().get(i + 1),
              imageData.getRgbVals().get(i + 2)));
    }

    // send pixels to row/col list
    for (int i = 0; i < pixels.size() / this.width; i++) {
      ArrayList<RGBPixel> row = new ArrayList<RGBPixel>(pixels.subList(this.width * i,
              this.width * (i + 1)));
      this.pixels.add(row);
    }
  }

  /**
   * Sets the filter of this layer.
   *
   * @param filter - the intended filter
   * @throws IllegalArgumentException if the filter does not exist
   */
  public void setFilter(String filter) throws IllegalArgumentException {
    boolean found = false;

    String[] filters = {
            "NORMAL",
            "RED_COMPONENT",
            "GREEN_COMPONENT",
            "BLUE_COMPONENT",
            "BRIGHTEN_INTENSITY",
            "BRIGHTEN_LUMA",
            "BRIGHTEN_VALUE",
            "DARKEN_INTENSITY",
            "DARKEN_LUMA",
            "DARKEN_VALUE",
            "DIFFERENCE",
            "MULTIPLY",
            "SCREEN"};

    for (String f : filters) {
      if (filter.equals(f)) {
        found = true;
        this.filter = filter;
        break;
      }
    }

    if (!found) {
      throw new IllegalArgumentException("Invalid filter; please re-enter.");
    }
  }

  /**
   * Gets the filter on this layer.
   * @return a String representing a filter's name
   */
  public String getFilter() {
    return this.filter;
  }

  /**
   * Adds an image to this layer.
   * @param imageName - the file path of the image
   * @param posX - the x pos of where it needs to be added
   * @param posY - the y pos of where it needs to be added
   * @throws IllegalArgumentException if any arguments are invalid
   * @throws IllegalStateException if the image data is null
   */
  public void addImageToLayer(String imageName, int posX, int posY)
          throws IllegalArgumentException, IllegalStateException {

    if (imageName == null) {
      throw new IllegalArgumentException("imageName is null for addImageToLayer");
    }

    // Check if the position is valid
    if (posX < 0 || posY < 0 || posX > this.width || posY > this.height) {
      throw new IllegalArgumentException("Invalid position.");
    }

    ProjConstPPM imageData = ImageUtil.readPPM(imageName);

    if (imageData == null) {
      throw new IllegalStateException("imageData is null.");
    }

    int width = imageData.getWidth();
    int height = imageData.getHeight();
    int maxVal = imageData.getMaxVal();
    ArrayList<Integer> rgbVals = imageData.getRgbVals();
    ArrayList<ArrayList<RGBPixel>> image = new ArrayList<ArrayList<RGBPixel>>();

    // convert the rgb values into pixels
    ArrayList<RGBPixel> pixels = new ArrayList<RGBPixel>();
    for (int i = 0; i < rgbVals.size(); i += 3) {
      pixels.add(new RGBPixel(maxVal,
              imageData.getRgbVals().get(i),
              imageData.getRgbVals().get(i + 1),
              imageData.getRgbVals().get(i + 2)));
    }

    // send pixels to row/col list
    for (int i = 0; i < pixels.size() / width; i++) {
      ArrayList<RGBPixel> row = new ArrayList<RGBPixel>(pixels.subList(width * i, width * (i + 1)));
      image.add(row);
    }

    for (int i = 0; i < image.size() - 1; i++) {
      for (int j = 0; j < image.get(0).size() - 1; j++) {
        try {
          this.pixels.get(i + posY).set(j + posX, image.get(i).get(j));
        } catch (IndexOutOfBoundsException e) {
          // do nothing because this indicates an overhang
        }
      }
    }
  }

  /**
   * Gets the name of this layer.
   * @return a String
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the width of this layer.
   * @return a String
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Gets the height of this layer.
   * @return a String.
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Gets the data from a pixel in this layer.
   * @param i - the pixel height coord
   * @param j - the pixel width coord
   * @return a String of pixel data
   */
  public String getPixelData(int i, int j) {
    return this.pixels.get(i).get(j).getRed() + " "
            + this.pixels.get(i).get(j).getGreen() + " "
            + this.pixels.get(i).get(j).getBlue() + " "
            + this.pixels.get(i).get(j).getAlpha() + " ";
  }

  /**
   * Gets the pixels within this layer.
   * @return an ArrayList of pixels
   */
  public ArrayList<RGBPixel> getRawPixels() {
    ArrayList<RGBPixel> output = new ArrayList<RGBPixel>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        output.add(this.pixels.get(i).get(j));
      }
    }

    return output;
  }

  /**
   * Gets the pixels within this layer.
   * @return an ArrayList of ArrayList of pixels
   */
  public ArrayList<ArrayList<RGBPixel>> getPixels() {
    return this.pixels;
  }

  /**
   * Sets the pixels within this layer.
   * @param pixels - the pixels to set
   */
  public void setPixels(ArrayList<ArrayList<RGBPixel>> pixels) {
    this.pixels = pixels;
  }
}

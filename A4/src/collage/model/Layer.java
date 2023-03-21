package collage.model;

import java.util.ArrayList;

/**
 * Represents a layer within a project.
 */
public class Layer {
  private String name;
  private int height;
  private int width;
  private int maxVal;
  private ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
  private Filter filter;

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
    this.pixels = new ArrayList<ArrayList<Pixel>>();
    this.setFilter("NORMAL");

    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        row.add(new Pixel(0, 255, 255, 255));
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
    ArrayList<Pixel> pixels = new ArrayList<Pixel>();
    for (int i = 0; i < imageData.getRgbVals().size(); i += 3) {
      pixels.add(new Pixel(this.maxVal,
              imageData.getRgbVals().get(i),
              imageData.getRgbVals().get(i + 1),
              imageData.getRgbVals().get(i + 2)));
    }

    // send pixels to row/col list
    for (int i = 0; i < pixels.size() / this.width; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>(pixels.subList(this.width * i,
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

    for (Filter f : Filter.values()) {
      if (filter.equals(f.toString())) {
        found = true;
        this.filter = f;
      }
    }

    if (!found) {
      throw new IllegalArgumentException("Filter does not exist");
    }
  }

  /**
   * Gets the filter on this layer.
   * @return a String representing a filter's name
   */
  public String getFilter() {
    return this.filter.toString();
  }

  /**
   * Applies a filter to this layer.
   * @throws IllegalStateException if the filter has not been set
   */
  public void applyFilter() throws IllegalStateException {
    if (this.filter == null) {
      throw new IllegalStateException("Filter has not been set.");
    }
    switch (this.filter) {
      case NORMAL:
        // no action needed
        break;
      case RED_COMPONENT:
        // set red component to max and green/blue to zero
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            pixel.setGreen(0);
            pixel.setBlue(0);
          }
        }
        break;
      case GREEN_COMPONENT:
        // set green component to max and red/blue to zero
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            pixel.setRed(0);
            pixel.setBlue(0);
          }
        }
        break;
      case BLUE_COMPONENT:
        // set blue component to max and red/green to zero
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            pixel.setRed(0);
            pixel.setGreen(0);
          }
        }
        break;
      case BRIGHTEN_VALUE:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            pixel.setRed(Math.min((pixel.getRed() + this.maxVal), this.maxVal));
            pixel.setGreen(Math.min((pixel.getGreen() + this.maxVal), this.maxVal));
            pixel.setBlue(Math.min((pixel.getBlue() + this.maxVal), this.maxVal));
          }
        }
        break;
      case BRIGHTEN_INTENSITY:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
            pixel.setRed(Math.min((pixel.getRed() + avg), this.maxVal));
            pixel.setGreen(Math.min((pixel.getGreen() + avg), this.maxVal));
            pixel.setBlue(Math.min((pixel.getBlue() + avg), this.maxVal));
          }
        }
        break;
      case BRIGHTEN_LUMA:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            int luma = (int) ((0.2126 * pixel.getRed())
                    + (0.7152 * pixel.getGreen())
                    + (0.0722 * pixel.getBlue()));
            pixel.setRed(Math.min((pixel.getRed() + luma), this.maxVal));
            pixel.setGreen(Math.min((pixel.getGreen() + luma), this.maxVal));
            pixel.setBlue(Math.min((pixel.getBlue() + luma), this.maxVal));
          }
        }
        break;
      case DARKEN_VALUE:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            pixel.setRed(Math.max((pixel.getRed() - this.maxVal), 0));
            pixel.setGreen(Math.max((pixel.getGreen() - this.maxVal), 0));
            pixel.setBlue(Math.max((pixel.getBlue() - this.maxVal), 0));
          }
        }
        break;
      case DARKEN_INTENSITY:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            int avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
            pixel.setRed(Math.max((pixel.getRed() - avg), 0));
            pixel.setGreen(Math.max((pixel.getGreen() - avg), 0));
            pixel.setBlue(Math.max((pixel.getBlue() - avg), 0));
          }
        }
        break;
      case DARKEN_LUMA:
        for (ArrayList<Pixel> row : this.pixels) {
          for (Pixel pixel : row) {
            int luma = (int) ((0.2126 * pixel.getRed())
                    + (0.7152 * pixel.getGreen())
                    + (0.0722 * pixel.getBlue()));
            pixel.setRed(Math.max((pixel.getRed() - luma), 0));
            pixel.setGreen(Math.max((pixel.getGreen() - luma), 0));
            pixel.setBlue(Math.max((pixel.getBlue() - luma), 0));
          }
        }
        break;
      default:
        throw new IllegalStateException("Filter is incorrect.");
    }
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
    ArrayList<ArrayList<Pixel>> image = new ArrayList<ArrayList<Pixel>>();

    // convert the rgb values into pixels
    ArrayList<Pixel> pixels = new ArrayList<Pixel>();
    for (int i = 0; i < rgbVals.size(); i += 3) {
      pixels.add(new Pixel(maxVal,
              imageData.getRgbVals().get(i),
              imageData.getRgbVals().get(i + 1),
              imageData.getRgbVals().get(i + 2)));
    }

    // send pixels to row/col list
    for (int i = 0; i < pixels.size() / width; i++) {
      ArrayList<Pixel> row = new ArrayList<Pixel>(pixels.subList(width * i, width * (i + 1)));
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
  public ArrayList<Pixel> getPixels() {
    ArrayList<Pixel> output = new ArrayList<Pixel>();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        output.add(this.pixels.get(i).get(j));
      }
    }

    return output;
  }
}

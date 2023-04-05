package collage.model.pixel;

/**
 * Represents a pixel in the RGB model.
 */
public class RGBPixel implements IPixel {
  private int a; //alpha
  private int r; //red
  private int g; //green
  private int b; //blue

  /**
   * Generates this pixel.
   *
   * @param a - the alpha value
   * @param r - the red value
   * @param g - the green value
   * @param b - the blue value
   */
  public RGBPixel(int a, int r, int g, int b) throws IllegalArgumentException {
    if (a < 0 || r < 0 || g < 0 || b < 0) {
      throw new IllegalArgumentException("Pixel vals cannot be negative.");
    }
    this.a = a;
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Converts this pixel to the 3-val PPM model.
   * @return a string of the PPM representation
   */
  @Override
  public String convertToPPMRepresentation() {
    int red = this.r * this.a / 255;
    int green = this.g * this.g / 255;
    int blue = this.b * this.b / 255;
    return red + " " + green + " " + blue;
  }

  /**
   * Sets the alpha value of this pixel.
   *
   * @param i - the new value
   */
  public void setAlpha(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("Cannot set ARGB negative.");
    }
    this.a = i;
  }

  /**
   * Sets the red value of this pixel.
   *
   * @param i - the new value
   */
  public void setRed(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("Cannot set ARGB negative.");
    }
    this.r = i;
  }

  /**
   * Sets the green value of this pixel.
   *
   * @param i - the new value
   */
  public void setGreen(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("Cannot set ARGB negative.");
    }
    this.g = i;
  }

  /**
   * Sets the blue value of this pixel.
   *
   * @param i - the new value
   */
  public void setBlue(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("Cannot set ARGB negative.");
    }
    this.b = i;
  }

  /**
   * Gets the alpha value of this pixel.
   *
   * @return an int
   */
  public int getAlpha() {
    return this.a;
  }

  /**
   * Gets the red value of this pixel.
   *
   * @return an int
   */
  public int getRed() {
    return this.r;
  }

  /**
   * Gets the green value of this pixel.
   *
   * @return an int
   */
  public int getGreen() {
    return this.g;
  }

  /**
   * Gets the blue value of this pixel.
   *
   * @return an int
   */
  public int getBlue() {
    return this.b;
  }

  /**
   * Returns the formatted RGB vals for the projectdata method.
   *
   * @return a string of RGB vals
   */
  @Override
  public String getVals() {
    return this.r + " " + this.g + " " + this.b + " ";
  }
}

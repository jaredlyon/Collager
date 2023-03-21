package collage.model;

/**
 * Represents a pixel within an image.
 */
public class Pixel {
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
  public Pixel(int a, int r, int g, int b) throws IllegalArgumentException {
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
   */
  public void convertToPPM() {
    this.r = r * a / 255;
    this.g = g * a / 255;
    this.b = b * a / 255;
    this.a = -a;
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
  public String getVals() {
    return this.r + " " + this.g + " " + this.b + " ";
  }
}

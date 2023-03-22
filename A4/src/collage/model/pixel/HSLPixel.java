package collage.model.pixel;

import collage.Utils;

public class HSLPixel implements IPixel {
  private double h; // hue
  private double s; // saturation
  private double l; // lightness

  /**
   * Generates this pixel.
   *
   * @param h - the hue value
   * @param s - the saturation value
   * @param l - the lightness value
   * @throws IllegalArgumentException if the values are out of range
   */
  public HSLPixel(double h, double s, double l) throws IllegalArgumentException {
    if (h < 0.0 || h > 360.0) {
      throw new IllegalArgumentException("Hue must be between 0.0 and 360.");
    } else if (s < 0.0 || s > 1.0) {
      throw new IllegalArgumentException("Saturation must be between 0.0 and 1.0");
    } else if (l < 0.0 || l > 1.0) {
      throw new IllegalArgumentException("Lightness must be between 0.0 and 1.0");
    }
    this.h = h;
    this.s = s;
    this.l = l;
  }

  /**
   * Converts this pixel to the 3-val PPM model.
   */
  @Override
  public String convertToPPMRepresentation() {
    return Utils.HSLToRGB(this).convertToPPMRepresentation();
  }

  /**
   * Sets the hue value of this pixel.
   *
   * @param h - the new value
   */
  public void setHue(double h) throws IllegalArgumentException {
    if (h < 0.0 || h > 360.0) {
      throw new IllegalArgumentException("Hue must be between 0.0 and 360.");
    }
    this.h = h;
  }

  /**
   * Sets the saturation value of this pixel.
   *
   * @param s - the new value
   */
  public void setSaturation(double s) throws IllegalArgumentException {
    if (s < 0.0 || s > 1.0) {
      throw new IllegalArgumentException("Saturation must be between 0.0 and 1.0");
    }
    this.s = s;
  }

  /**
   * Sets the lightness value of this pixel.
   *
   * @param l - the new value
   */
  public void setLightness(double l) throws IllegalArgumentException {
    if (l < 0.0 || l > 1.0) {
      throw new IllegalArgumentException("Lightness must be between 0.0 and 1.0");
    }
    this.l = l;
  }

  /**
   * Gets the hue value of this pixel.
   *
   * @return a double
   */
  public double getHue() {
    return this.h;
  }

  /**
   * Gets the saturation value of this pixel.
   *
   * @return a double
   */
  public double getSaturation() {
    return this.s;
  }

  /**
   * Gets the lightness value of this pixel.
   *
   * @return a double
   */
  public double getLightness() {
    return this.l;
  }

  /**
   * Returns the formatted HSL vals for the projectdata method.
   *
   * @return a string of HSL vals
   */
  @Override
  public String getVals() {
    return this.h + " " + this.s + " " + this.l;
  }
}

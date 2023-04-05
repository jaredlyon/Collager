package collage.model.pixel;

/**
 * Represents a pixel.
 * This will either be an RGB or HSL Pixel.
 */
public interface IPixel {

  /**
   * Converts this pixel to the 3-val PPM model.
   * @return a string of the PPM representation
   */
  String convertToPPMRepresentation();

  /**
   * Gets the values of this pixel.
   * @return a string of the values
   */
  String getVals();
}

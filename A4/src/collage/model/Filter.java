package collage.model;

/**
 * Represents the possible filters for an image layer.
 */
public enum Filter {
  // does nothing to the image
  NORMAL,

  // when applied, only uses the red portion of the RGB.
  RED_COMPONENT,
  // when applied, only uses the green portion of the RGB.
  GREEN_COMPONENT,
  // when applied, only uses the blue portion of the RGB.
  BLUE_COMPONENT,

  // when applied, adds the brightness value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  BRIGHTEN_VALUE,
  // when applied, ups the intensity value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  BRIGHTEN_INTENSITY,
  // when applied, ups the luma value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  BRIGHTEN_LUMA,

  // when applied, removes the brightness value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  DARKEN_VALUE,
  // when applied, removes the intensity value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  DARKEN_INTENSITY,
  // when applied, removes the luma value pixel by pixel according to value from the
  // corresponding pixel on the current layer.
  // Only affects the current layer.
  DARKEN_LUMA;

  /**
   * Searches these enums for a given filter - returns false if invalid.
   * @param filter - the filter to search for
   * @return a boolean
   */
  public static boolean isValid(Filter filter) {
    if (filter == null) {
      return false;
    }
    switch (filter) {
      case NORMAL:
      case RED_COMPONENT:
      case GREEN_COMPONENT:
      case BLUE_COMPONENT:
      case BRIGHTEN_VALUE:
      case BRIGHTEN_INTENSITY:
      case BRIGHTEN_LUMA:
      case DARKEN_VALUE:
      case DARKEN_INTENSITY:
      case DARKEN_LUMA:
        return true;
      default:
        return false;
    }
  }
}

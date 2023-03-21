import org.junit.Test;

import collage.model.Filter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Represents the test suite for the filter class.
 */
public class FilterTest {

  @Test(expected = IllegalArgumentException.class)
  public void testIsValid() {
    assertTrue(Filter.isValid(Filter.NORMAL));
    assertTrue(Filter.isValid(Filter.RED_COMPONENT));
    assertTrue(Filter.isValid(Filter.GREEN_COMPONENT));
    assertTrue(Filter.isValid(Filter.BLUE_COMPONENT));
    assertTrue(Filter.isValid(Filter.BRIGHTEN_VALUE));
    assertTrue(Filter.isValid(Filter.BRIGHTEN_INTENSITY));
    assertTrue(Filter.isValid(Filter.BRIGHTEN_LUMA));
    assertTrue(Filter.isValid(Filter.DARKEN_VALUE));
    assertTrue(Filter.isValid(Filter.DARKEN_INTENSITY));
    assertTrue(Filter.isValid(Filter.DARKEN_LUMA));

    assertFalse(Filter.isValid(null));
    Filter.valueOf("INVALID_FILTER");
  }
}
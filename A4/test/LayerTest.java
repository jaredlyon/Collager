import org.junit.Before;
import org.junit.Test;

import collage.model.Layer;

import static org.junit.Assert.assertEquals;

/**
 * Represents the test suite for the Layer class.
 */
public class LayerTest {
  private Layer l1;

  @Before
  public void init() {
    this.l1 = new Layer("test", 100, 100, 100);
  }

  @Test
  public void testConstructor() {
    Layer constructorTest = new Layer("test", 1, 1, 1);
    assertEquals("test", constructorTest.getName());
    assertEquals(1, constructorTest.getHeight());
    assertEquals(1, constructorTest.getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructor() {
    Layer badConstructorTest1 = new Layer(null, 1, 1, 1);
    Layer badConstructorTest2 = new Layer("test", -1, 1, 1);
    Layer badConstructorTest3 = new Layer("test", 1, -1, 1);
    Layer badConstructorTest4 = new Layer("test", 1, 1, 1);
  }

  @Test
  public void testSetFilter() {
    this.l1.setFilter("RED_COMPONENT");
    assertEquals("RED_COMPONENT", this.l1.getFilter());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadSetFilter() {
    this.l1.setFilter("KAHGBEFK");
  }

  @Test
  public void testGetFilter() {
    assertEquals("NORMAL", this.l1.getFilter());
  }

  @Test
  public void testGetWidth() {
    assertEquals(100, this.l1.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(100, this.l1.getHeight());
  }

  @Test
  public void testGetName() {
    assertEquals("test", this.l1.getName());
  }
}
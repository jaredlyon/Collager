import org.junit.Test;

import java.io.StringReader;

import collage.controller.Controller;
import collage.controller.IController;
import collage.model.IModel;
import collage.model.Model;
import collage.view.IView;
import collage.view.View;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Represents the test suite for the controller.
 */
public class ControllerTest {

  @Test
  public void testFullScript1() {
    Readable in = new StringReader("load-project res/tako.ppm\n" +
            "add-layer tako-blue\n" +
            "set-filter tako-blue BLUE_COMPONENT\n" +
            "quit\n"); // quit
    Appendable log = new StringBuilder();
    IModel model = new Model();
    IView view = new View(model, log);
    IController controller = new Controller(model, view, in);

    try {
      controller.startCollage();
    } catch (IllegalStateException e) {
      fail("Unexpected IllegalStateException");
    }

    String[] lines = log.toString().split("\\r?\\n");

    assertEquals("Starting collage program...", lines[0]);
    assertEquals("Script options:", lines[1]);
    assertEquals("new-project <height> <width>", lines[2]);
    assertEquals("load-project <file path>", lines[3]);
    assertEquals("save-project <file path>", lines[4]);
    assertEquals("save-image <file path>", lines[5]);
    assertEquals("add-layer <name>", lines[6]);
    assertEquals("set-filter <layer name> <filter>", lines[7]);
    assertEquals("add-image-to-layer <layer name> <file path> <X pos> <Y pos>", lines[8]);
    assertEquals("quit", lines[9]);
    assertEquals("Input command:", lines[10]);
    assertEquals("Loading project with file path res/tako.ppm...", lines[11]);
    assertEquals("Adding layer tako-blue...", lines[22]);
    assertEquals("Setting filter BLUE_COMPONENT onto tako-blue...", lines[33]);
    assertEquals("Ending collage program...", lines[44]);
  }

  @Test
  public void testFullScript2() {
    Readable in = new StringReader("load-project res/tako.ppm\n" +
            "add-layer cp\n" +
            "add-image-to-layer cp res/cp_goat.ppm 0 0\n" +
            "set-filter cp BLUE_COMPONENT\n" +
            "quit\n"); // quit
    Appendable log = new StringBuilder();
    IModel model = new Model();
    IView view = new View(model, log);
    IController controller = new Controller(model, view, in);

    try {
      controller.startCollage();
    } catch (IllegalStateException e) {
      fail("Unexpected IllegalStateException");
    }

    String[] lines = log.toString().split("\\r?\\n");

    assertEquals("Starting collage program...", lines[0]);
    assertEquals("Script options:", lines[1]);
    assertEquals("new-project <height> <width>", lines[2]);
    assertEquals("load-project <file path>", lines[3]);
    assertEquals("save-project <file path>", lines[4]);
    assertEquals("save-image <file path>", lines[5]);
    assertEquals("add-layer <name>", lines[6]);
    assertEquals("set-filter <layer name> <filter>", lines[7]);
    assertEquals("add-image-to-layer <layer name> <file path> <X pos> <Y pos>", lines[8]);
    assertEquals("quit", lines[9]);
    assertEquals("Input command:", lines[10]);
    assertEquals("Loading project with file path res/tako.ppm...", lines[11]);
    assertEquals("Adding layer cp...", lines[22]);
    assertEquals("Adding res/cp_goat.ppm to cp at 0, 0...", lines[33]);
    assertEquals("Setting filter BLUE_COMPONENT onto cp...", lines[44]);
    assertEquals("Ending collage program...", lines[55]);
  }
}

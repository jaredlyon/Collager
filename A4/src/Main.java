import java.io.InputStreamReader;

import collage.controller.ScriptController;
import collage.controller.IController;
import collage.model.IModel;
import collage.model.Model;
import collage.view.IView;
import collage.view.View;

/**
 * Represents the main method for the collage program.
 */
public final class Main {
  /**
   * Runs the collage program.
   * @param args - a String[] determining which version to play
   */
  public static void main(String[] args) {
    IModel model = new Model();
    IView view = new View(model);
    Readable in = new InputStreamReader(System.in);
    IController controller = new ScriptController(model, view, in);
    controller.startCollage();
  }
}
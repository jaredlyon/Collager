import java.io.InputStreamReader;

import collage.controller.GUIController;
import collage.controller.ScriptController;
import collage.controller.IController;
import collage.model.GUIModel;
import collage.model.IModel;
import collage.model.Model;
import collage.view.IView;
import collage.view.JFrameView;
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
    if (args.length > 0) {
      for (String argument : args) {
        if (argument.equals("script")) {
          IModel model = new Model();
          IView view = new View(model);
          Readable in = new InputStreamReader(System.in);
          IController controller = new ScriptController(model, view, in);
          controller.startCollage();
        } else if (argument.equals("gui")) {
          GUIModel model = new GUIModel();
          JFrameView view = new JFrameView();
          GUIController controller = new GUIController(model, view);
          view.setController(controller);
        }
      }
    } else {
      System.out.println("No arguments found.");
    }
  }
}
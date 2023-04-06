import java.io.InputStreamReader;

import collage.controller.GUIController;
import collage.controller.ScriptController;
import collage.controller.IScriptController;
import collage.model.GUIModel;
import collage.model.IModel;
import collage.model.Model;
import collage.view.IScriptView;
import collage.view.JFrameView;
import collage.view.ScriptView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Scanner;

/**
 * Represents the main method for the collage program.
 */
public final class Main {
  /**
   * Runs the collage program.
   * @param args - a String[] determining which version to play
   */
  public static void main(String[] args) {
    if (args[0].equals("-file")) {
      Readable reader;
      try {
        reader = new FileReader(args[1]);
        Scanner scan = new Scanner(reader);
        IModel model = new Model();
        IScriptView view = new ScriptView(model);
        StringBuilder executable = new StringBuilder();
        while (scan.hasNextLine()) {
          executable.append(scan.nextLine()).append("\n");
        }
        Readable in = new StringReader(executable.toString());
        IScriptController controller = new ScriptController(model, view, in);
        controller.startCollage();
      } catch (FileNotFoundException ex) {
        System.out.println("File not found.");
      }
    } else if (args[0].equals("-text")) {
      // starts the script version
      IModel model = new Model();
      IScriptView view = new ScriptView(model);
      Readable in = new InputStreamReader(System.in);
      IScriptController controller = new ScriptController(model, view, in);
      controller.startCollage();
    }

    // else starts gui version
    GUIModel model = new GUIModel();
    JFrameView view = new JFrameView();
    GUIController controller = new GUIController(model, view);
    view.setController(controller);
  }
}
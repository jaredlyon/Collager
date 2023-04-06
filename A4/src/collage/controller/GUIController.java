package collage.controller;

import java.io.IOException;

import collage.model.GUIModel;
import collage.view.JFrameView;

/**
 * A controller designed to work with a JFrame GUI.
 */
public class GUIController implements IGUIController {
  private final GUIModel model;
  private final JFrameView view;

  /**
   * Generates a new instance of this GUIController.
   * @param model - the model to use
   * @param view - the view to use
   * @throws IllegalArgumentException if any arguments are null
   */
  public GUIController(GUIModel model, JFrameView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model, view, or input is null within controller "
              + "implementation!");
    }

    this.model = model;
    this.view = view;
  }

  /**
   * Based on a String returned from the view based on user input, instructs the model to perform.
   * @param command - the command coming from the view
   * @throws IllegalStateException if the model cannot perform the command
   */
  public void executeCommand(String command) throws IllegalStateException {
    String[] commands = command.split(" ");

    switch (commands[0]) {
      case "new-project" : {
        try {
          this.model.newProject(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "load-project" : {
        try {
          this.model.loadProject(commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "save-project" : {
        try {
          this.model.saveProject(commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "save-image" : {
        try {
          this.model.saveImage(commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "add-layer" : {
        try {
          this.model.addLayer(commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "select-layer" : {
        try {
          this.model.selectLayer(commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "set-filter" : {
        try {
          this.model.setFilter(this.model.getCurrentLayerName(), commands[1]);
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      case "add-image-to-layer" : {
        try {
          this.model.addImageToLayer(commands[2], commands[1],
                  Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
          this.view.render(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (IOException e) {
            throw new IllegalStateException(e);
          }
        }
        break;
      }
      default : {
        try {
          this.view.renderMessage("Controller failed to match input with a command!");
        } catch (IOException e) {
          throw new IllegalArgumentException(e);
        }
        break;
      }
    }
  }
}

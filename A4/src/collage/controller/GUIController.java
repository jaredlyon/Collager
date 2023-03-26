package collage.controller;

import collage.model.IModel;
import collage.view.JFrameView;

/**
 * Represents the controller for the collage program.
 */
public class GUIController {
  private final IModel model;
  private final JFrameView view;

  public GUIController(IModel model, JFrameView view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model, view, or input is null within controller "
              + "implementation!");
    }

    this.model = model;
    this.view = view;
  }

  public void executeCommand(String command) throws IllegalStateException {
    String[] commands = command.split(" ");

    switch (commands[0]) {
      case "new-project" -> {
        try {
          this.model.newProject(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "load-project" -> {
        try {
          this.model.loadProject(commands[1]);
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "save-project" -> {
        try {
          this.model.saveProject(commands[1]);
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "save-image" -> {
        try {
          this.model.saveImage(commands[1]);
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "add-layer" -> {
        try {
          this.model.addLayer(commands[1]);
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "set-filter" -> {
        try {
          this.model.setFilter(commands[1], commands[2]);
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      case "add-image-to-layer" -> {
        try {
          this.model.addImageToLayer(commands[1], commands[2],
                  Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
          this.view.updateContent(this.model.getRenderContent());
        } catch (Exception ex) {
          try {
            this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
          } catch (Exception e) {
            throw new IllegalStateException(e);
          }
        }
      }
      default -> {
        try {
          this.view.renderMessage("Controller failed to match input with a command!");
        } catch (Exception e) {
          throw new IllegalArgumentException(e);
        }
      }
    }
  }
}

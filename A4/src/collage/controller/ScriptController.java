package collage.controller;

import java.io.IOException;
import java.util.Scanner;

import collage.model.IModel;
import collage.view.IScriptView;

/**
 * Represents the controller for the collage program.
 */
public class ScriptController implements IController {
  private final IModel model;
  private final IScriptView view;
  private final Readable in;
  private boolean quit = false;

  /**
   * Constructs a new controller using arguments.
   *
   * @param model - a model of a SetGame
   * @param view  - a view of a SetGame
   * @param in    - inputs in the form of a readable
   * @throws IllegalArgumentException if any arguments are null
   */
  public ScriptController(IModel model, IScriptView view, Readable in)
          throws IllegalArgumentException {
    if (model == null || view == null || in == null) {
      throw new IllegalArgumentException("Model, view, or input is null within controller "
              + "implementation!");
    }

    this.model = model;
    this.view = view;
    this.in = in;
  }

  /**
   * Starts the collage program.
   *
   * @throws IllegalStateException if the controller cannot communicate with the view
   */
  @Override
  public void startCollage() throws IllegalStateException {
    Scanner sc = new Scanner(this.in);

    try {
      this.view.renderMessage("Starting collage program...\n");
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }

    while (!quit) {
      try {
        this.view.renderMessage("Script options:\n" +
                "new-project <height> <width>\n" +
                "load-project <file path>\n" +
                "save-project <file path>\n" +
                "save-image <file path>\n" +
                "add-layer <name>\n" +
                "set-filter <layer name> <filter>\n" +
                "add-image-to-layer <layer name> <file path> <X pos> <Y pos>\n" +
                "quit\n" +
                "Input command:\n");
      } catch (IOException e) {
        throw new IllegalStateException(e);
      }

      String command = sc.nextLine();
      String[] commands = command.split(" ");

      switch (commands[0]) {
        case "new-project":
          try {
            this.model.newProject(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]));
            try {
              this.view.renderMessage("Generating new project with dimensions " +
                      commands[1] + "x" + commands[2] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "load-project":
          try {
            this.model.loadProject(commands[1]);
            try {
              this.view.renderMessage("Loading project with file path " +
                      commands[1] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "save-project":
          try {
            this.model.saveProject(commands[1]);
            try {
              this.view.renderMessage("Saving project to path " + commands[1] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "save-image":
          try {
            this.model.saveImage(commands[1]);
            try {
              this.view.renderMessage("Saving image to path " + commands[1] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "add-layer":
          try {
            this.model.addLayer(commands[1]);
            try {
              this.view.renderMessage("Adding layer " + commands[1] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "set-filter":
          try {
            this.model.setFilter(commands[1], commands[2]);
            try {
              this.view.renderMessage("Setting filter " + commands[2]
                      + " onto " + commands[1] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "add-image-to-layer":
          try {
            this.model.addImageToLayer(commands[1], commands[2],
                    Integer.parseInt(commands[3]), Integer.parseInt(commands[4]));
            try {
              this.view.renderMessage("Adding " + commands[2] + " to "
                      + commands[1] + " at " + commands[3] + ", " + commands[4] + "...\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          } catch (Exception ex) {
            try {
              this.view.renderMessage("Script failed with trace:\n" + ex + "\n");
            } catch (Exception e) {
              throw new IllegalStateException(e);
            }
          }
          break;
        case "quit":
          try {
            this.view.renderMessage("Ending collage program...\n");
            this.quit = true;
          } catch (Exception e) {
            throw new IllegalArgumentException(e);
          }
          break;
        default:
          try {
            this.view.renderMessage("Bad command, go again...\n");
          } catch (Exception e) {
            throw new IllegalArgumentException(e);
          }
      }
    }
  }
}

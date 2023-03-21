package collage.view;

import java.io.IOException;

import collage.model.IModel;

/**
 * Represents the view for the collage model.
 */
public class View implements IView {
  public IModel model;
  public Appendable view;

  /**
   * Initializes the viewer such that System.out is the default.
   * @param model - The game's ModelState, aka current behavior
   * @throws IllegalArgumentException when the ModelState is null
   */
  public View(IModel model) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null.");
    }

    this.view = System.out;
    this.model = model;
  }

  /**
   * Initializes the viewer (this one is for testing).
   * @param model - The game's ModelState, aka the current behavior
   * @param view - A 'view', which is an Appendable that rendered messages are sent to
   * @throws IllegalArgumentException if any arguments are null
   */
  public View(IModel model, Appendable view) throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("State or view is null.");
    }

    this.model = model;
    this.view = view;
  }

  /**
   * Renders a message from the controller.
   * @param message - the controller's message
   * @throws IOException if the view cannot communicate with the out
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.view.append(message);
  }
}

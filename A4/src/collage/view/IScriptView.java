package collage.view;

import java.io.IOException;

/**
 * Represents the view for the collage program.
 */
public interface IScriptView {

  /**
   * Renders a message for the user.
   * @throws IOException if the view cannot communicate with the model/controller
   */
  void renderMessage(String message) throws IOException;
}

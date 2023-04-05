package collage.view;

import java.io.IOException;

/**
 * Represents the GUI view of the collage program.
 */
public interface IGUIView extends IScriptView {

  /**
   * Renders the given content in a dialog for the user.
   * @throws IOException if the view cannot communicate with the model/controller
   */
  void render(RenderContent content) throws IOException;
}

package collage.view;

import java.io.IOException;

public interface IGUIView extends IScriptView {

  /**
   * Renders the given content.
   * @throws IOException if the view cannot communicate with the model/controller
   */
  void render(RenderContent content) throws IOException;
}

package collage.controller;

/**
 * Represents a CLI controller for the collage program.
 */
public interface IScriptController {

  /**
   * Starts the controller for the collage.
   * @throws IllegalStateException if the collage program is not ready to start.
   */
  void startCollage() throws IllegalStateException;
}

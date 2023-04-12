package collage.controller;

/**
 * Represents a CLI controller for the collage program.
 */
public interface IScriptController {

  /**
   * Starts a controller loop that listens for user script inputs.
   * @throws IllegalStateException if the collage program is not ready to start.
   */
  void startCollage() throws IllegalStateException;
}

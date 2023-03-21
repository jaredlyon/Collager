package collage.controller;

/**
 * Represents the controller for the collage program.
 */
public interface IController {

  /**
   * Starts the controller for the collage.
   * @throws IllegalStateException if the collage program is not ready to start.
   */
  void startCollage() throws IllegalStateException;
}

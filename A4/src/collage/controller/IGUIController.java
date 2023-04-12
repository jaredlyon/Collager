package collage.controller;

/**
 * Represents a controller for the GUI version of the collager project.
 */
public interface IGUIController {

  /**
   * Executes a command given from the view by instructing the model.
   * @param command - the command to execute
   * @throws IllegalStateException if the command is not valid
   */
  void executeCommand(String command) throws IllegalStateException;
}

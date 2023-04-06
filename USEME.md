## .jar File Instructions
The .jar file is located in the /res folder. To run the program, open a terminal and navigate to the /res folder. Then, run any of the following commands:
- `java -jar Program.jar -file path-of-script-file`: when invoked in this manner the program should open the script file, execute it and then shut down. A sample script file is provided in the /res folder.
- `java -jar Program.jar -text`: when invoked in this manner the ScriptController and ScriptView from A4 will take control of the program and the user will be able to interact with the program through the command line.
- `java -jar Program.jar`: when invoked in this manner the GUIController and JFrameView from A5 will take control of the program and the user will be able to interact with the program through a GUI.

### A4: Script Instructions (-script)
The ScriptController will present you with instructions on what commands you can run each time you start it or execute a command. Below is a list of scripts that you can use to generate a small collage. You can run the Main function to start the MVC collage program, and input these commands into the CLI.

- new-project 500 500
- add-layer jersey
- add-image-to-layer jersey jp_jersey_2017.ppm 0 0
- set-filter jersey RED_COMPONENT
- add-layer cp
- add-image-to-layer cp cp_goat.ppm 250 250
- set-filter cp DARKEN_LUMA
- save-image <your file path>

The user can create a new project, load a project, add a layer to the project, add an image to the layer, set a filter on a layer, save the final image, and save the project for future use.

### A5: GUI Instructions
The GUI will spawn two separate windows:
- The larger window is the composite image screen - this will show you how the project looks as you work on it.
- The smaller window is the control panel - a line of buttons controls all collager functions, and each will request certain arguments such as file paths, layer names, and/or positioning values. These dialogs will instruct you on the proper argument format, and some will provide examples! This control panel will also list the current project's layers, as well as the current layer that is being edited.

As shown below, the panels may be moved around independently of one another for your convenience.
<img width="1606" alt="Screen Shot 2023-04-06 at 5 29 46 PM" src="https://user-images.githubusercontent.com/29807461/230497524-3180c1a9-fb06-415f-9b1a-8f16eb7b1faf.png">


### Possible Errors
Some users experienced success with different file paths. The test suite is designed to work using the `./A4/res/image.ppm` path, but some users reported only using the `./res/image.ppm` path.

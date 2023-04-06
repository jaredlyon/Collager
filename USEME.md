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
The GUI will present you with a line of buttons across the top. All collage functions are accomplished through the use of these buttons. Each button will invite you to input the necessary arguments to execute the command, some buttons will provide examples on how to use them. As shown below, the view will also show the composite image that is currently being worked on, as well as the project layers and which layer is currently being worked on.
![Screen Shot 2023-04-05 at 10.39.15 PM.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2F_f%2Fv38cfpbn36bc4n752_czh7ch0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_BfQDRh%2FScreen%20Shot%202023-04-05%20at%2010.39.15%20PM.png)

### Possible Errors
Some users experienced success with different file paths. The test suite is designed to work using the `./A4/res/image.ppm` path, but some users reported only using the `./res/image.ppm` path.
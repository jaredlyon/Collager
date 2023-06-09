# CS3500 A7: v1.0 ReadMe Release
- This is the final release of the Collager program. It is a collage program that allows the user to create a collage from a series of images. The user can create a new project, load a project, add a layer to the project, add an image to layers, set a filter on layers, save the final image, and save the project for future use. These features are available via three methods: a graphical user interface, a command line interface, and via script `.jar` commands. Through these interfaces, the aforementioned features are available via buttons, text commands, and/or script files, respectively.
- Usage is outlined within this project's `USEME.md` file located in the root directory.
- This code compiles in Java 11 or higher and uses a JUnit4 test suite.

# Version History
Below is outlined the version history of this project. Each version corresponds to a respective assignment release tag, beginning with A4. The version history is outlined in ascending chronological order, with the **A6** tag below representing the most recent update to functionality. The overall class diagram may be found in the `res/` folder, labeled `class_diagram.png`.
### CS3500 A4: Collage Maker
This is a collage program! It uses an MVC design wherein a controller instructs a model whilst a view renders results for the user. The controller calls methods belonging to the model, which then passes these function calls down to its project field. 
- A project contains information regarding the width, height, and maxVal, as well as an ArrayList of layers. This is the class that also contains the code to save an image and/or project - it does this by condensing the ArrayList of layers it contains.
- Each layer object also contains width, height, and maxVal data, but it also contains an ArrayList<ArrayList<Pixel>> which is essentially a matrix of the pixel data. 
- Each pixel object contains RGBA values.
- Each layer class also contains a filter field, which refers to an enum class that defines all the possible filters; however, the method that applies a filter is within the layer class and is called by the saveImage method when the user wants to generate the image file.
- The ProjConstPPM class is an intermittent object that exists solely to hand data off to a layer from the imageUtil class. Essentially, the imageUtil class generates the data from an image and places it into a ProjConstPPM object, and this object is then read by the layer class's constructor to produce a layer.
- The imageUtil class is used by the layer class to read files and generate the pixel matrix; as mentioned above, it first hands off the data to a ProjConstPPM object which is read by the layer class.

### CS3500 A5: Collage Maker v2
- A graphic user interface was added; this functionality relies on an all-new controller class, view class, and new model class which extends the original model (GUIController, JFrameView, GUIModel). To maintain separation between the view and model, the view passes all commands to the controller for interpretation before the controller instructs the model's behavior. Additionally, the view will ask the controller for the updated data to render, which the model will then pass back through the controller for the view.
- The passage of render data from the model to the view is handled by the RenderContent class, which carries the needed data from the model for the view to render to the user.
- The original view and controller classes were renamed to ScriptView and ScriptController, respectively. These classes are still used to run the program in a CLI environment.
- The filters were updated to use the command design pattern, each filter comes in its own class with a specific `apply` method used to alter the pixel matrix. The filter classes are all stored in the `filters` package.
- Three new filters were added: screen, difference, and multiply.
- The `saveImage` and composite image view now use the `buildImage` function, which systematically condenses all layers in a project to produce a new image.
- A new pixel implementation was added (HSLPixel) which uses the HSL color model to represent color. This pixel implementation is used by some of the new filters, and relied on conversion methods defined in the new Utils class.
- A .jar file now comes in the /res folder, more info on this can be found in the USEME.md file.

### CS3500 A6: Collage Maker v3
- The model was updated to allow for the import and export of PNG and JPG images - this change is reflected by the presence of new switch statements throughout that point to the ImageUtils class.
- The ImageUtils class was updated to allow for the import and export of PNG and JPG images. This class is now responsible for reading and writing images, and it does so by using the javax.imageio package.
- All program parts are complete and support script commands, which are also reflected in the example .jar file.

## Decoupling Demonstration
To show that the views are decoupled, we created a new package called DecouplingPackage with the following files:
- IScriptView: The CLI view.
- ScriptView: The implementation for the CLI view.
- IGUIView: The GUI view.
- JFrameView: The implementation for the GUI view.
- IRenderContent: Used to pass structured information between the model, controller, and view.
- RenderContent: The implementation for IRenderContent.
- IGUIController: The GUI controller.
- GUIController: The implementation for the GUI controller.
- IModel: The model.
- IGUIModel: The GUI implementation for the model.
- RGBPixel: The RGB pixel. This is the only class that is explicitly needed.

### Image Citation
The three example images provided, cp_goat.ppm, jp_jersey_2017.ppm & jareshandr.png, are three images created by Jared Lyon and Jaron R. in 2017 when we were coming up with a fake Club Penguin International tournament. We have authorized the use of these images.

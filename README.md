## CS3500 A4
This is a collage program! It uses an MVC design wherein a controller instructs a model whilst a view renders results for the user. The controller calls methods belonging to the model, which then passes these function calls down to its project field. 
- A project contains information regarding the width, height, and maxVal, as well as an ArrayList of layers. This is the class that also contains the code to save an image and/or project - it does this by condensing the ArrayList of layers it contains.
- Each layer object also contains width, height, and maxVal data, but it also contains an ArrayList<ArrayList<Pixel>> which is essentially a matrix of the pixel data. 
- Each pixel object contains RGBA values.
- Each layer class also contains a filter field, which refers to an enum class that defines all the possible filters; however, the method that applies a filter is within the layer class and is called by the saveImage method when the user wants to generate the image file.
- The ProjConstPPM class is an intermittent object that exists solely to hand data off to a layer from the imageUtil class. Essentially, the imageUtil class generates the data from an image and places it into a ProjConstPPM object, and this object is then read by the layer class's constructor to produce a layer.
- The imageUtil class is used by the layer class to read files and generate the pixel matrix; as mentioned above, it first hands off the data to a ProjConstPPM object which is read by the layer class.

### Script Instructions
The controller will present you with instructions on what commands you can run each time you start it or execute a command. Below is a list of scripts that you can use to generate a small collage. You can run the Main function to start the MVC collage program, and input these commands into the CLI.

- new-project 500 500
- add-layer jersey
- add-image-to-layer jersey jp_jersey_2017.ppm 0 0
- set-filter jersey RED_COMPONENT
- add-layer cp
- add-image-to-layer cp cp_goat.ppm 250 250
- set-filter cp DARKEN_LUMA
- save-image <your file path>

The user can create a new project, load a project, add a layer to the project, add an image to the layer, set a filter on a layer, save the final image, and save the project for future use.

### Image Citation
The two example images provided, cp_goat.ppm & jp_jersey_2017.ppm, are two images created by Jared Lyon and Jaron R. in 2017 when we were coming up with a fake Club Penguin International tournament. We have authorized the use of these images.

### TODO
  - tests
    - project
    - filter
  - implementation
    - fix build image

  - optimizations (optional)
    - better looking gui

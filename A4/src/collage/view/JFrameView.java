package collage.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import collage.controller.GUIController;
import collage.model.pixel.RGBPixel;

/**
 * Represents a class that renders the view of the collage program.
 * This class is a JFrame that contains a JPanel with a JLabel that contains an image.
 * The image is generated from the RenderContent object.
 * This view DOES NOT interact with the model in any way, all actions and data must instead be
 * passed through the controller in order to prevent unwanted mutable errors.
 */
public class JFrameView extends JFrame implements IView, ActionListener {
  private GUIController controller;
  private final JButton newProjectButton;
  private final JButton loadProjectButton;
  private final JButton saveProjectButton;
  private final JButton saveImageButton;
  private final JButton addLayerButton;
  private final JButton selectLayerButton;
  private final JButton setFilterButton;
  private final JButton addImageToLayerButton;
  private RenderContent content;

  /**
   * Constructs a new JFrameView.
   */
  public JFrameView() {
    super();

    // generate the base frame
    this.setTitle("Collager");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 1200);

    // configure the frame with scrollers
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    try {
      // show the composite image with a scrollbar
      JPanel imagePanel = new JPanel();
      imagePanel.setBorder(BorderFactory.createTitledBorder("Composite Project Image"));
      mainPanel.add(imagePanel);
      imagePanel.add(new JLabel(new ImageIcon(createImageFromScratch(this.content.getWidth(),
              this.content.getHeight(),
              this.content.getPixels()))));

      // display the project layers
      ArrayList<String> layers = this.content.getLayers();
      String layersOutput = "";
      for (String layer : layers) {
        layersOutput += layer + "\n";
      }
      JTextArea layerText = new JTextArea(layersOutput);
      layerText.setBorder(BorderFactory.createTitledBorder("Project Layers"));
      mainPanel.add(layerText);

      // tell the use which layer they're currently on
      JTextArea currentLayer = new JTextArea(this.content.getCurrentLayer());
      currentLayer.setBorder(BorderFactory.createTitledBorder("Current Layer"));
      mainPanel.add(currentLayer);
    } catch (Exception e) {
      // do nothing
    }

    // generate the buttons for the user to interact with
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("User Controls"));
    this.newProjectButton = new JButton("New Project");
    this.newProjectButton.addActionListener(this);
    buttonPanel.add(this.newProjectButton);
    this.loadProjectButton = new JButton("Load Project");
    this.loadProjectButton.addActionListener(this);
    buttonPanel.add(this.loadProjectButton);
    this.saveProjectButton = new JButton("Save Project");
    this.saveProjectButton.addActionListener(this);
    buttonPanel.add(this.saveProjectButton);
    this.saveImageButton = new JButton("Save Image");
    this.saveImageButton.addActionListener(this);
    buttonPanel.add(this.saveImageButton);
    this.addLayerButton = new JButton("Add Layer");
    this.addLayerButton.addActionListener(this);
    buttonPanel.add(this.addLayerButton);
    this.selectLayerButton = new JButton("Select Layer");
    this.selectLayerButton.addActionListener(this);
    buttonPanel.add(this.selectLayerButton);
    this.setFilterButton = new JButton("Set Filter");
    this.setFilterButton.addActionListener(this);
    buttonPanel.add(this.setFilterButton);
    this.addImageToLayerButton = new JButton("Add Image to Layer");
    this.addImageToLayerButton.addActionListener(this);
    buttonPanel.add(this.addImageToLayerButton);
    mainPanel.add(buttonPanel);
    this.setVisible(true);
  }

  /**
   * Sets the controller of this view.
   *
   * @param controller - the controller of this view
   * @throws IllegalArgumentException if the controller is null
   */
  public void setController(GUIController controller) {
    this.controller = controller;
  }

  /**
   * Creates an image from scratch using the given arguments.
   *
   * @param width - the width of the image
   * @param height - the height of the image
   * @param pixels - the pixels of the image
   * @return an image created from scratch
   */
  private Image createImageFromScratch(int width, int height, ArrayList<ArrayList<RGBPixel>> pixels) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    // convert pixels to 1D arrayList
    ArrayList<RGBPixel> pixels1D = new ArrayList<RGBPixel>();
    for (ArrayList<RGBPixel> row : pixels) {
      pixels1D.addAll(row);
    }

    int pixelIndex = 0;
    //Iterating so x moves to the right and y moves down
    for(int x = 0; x < image.getWidth(); x++) {
      for(int y = 0; y < image.getHeight(); y++) {
        // get the pixel data
        int r = pixels1D.get(pixelIndex).getRed();
        int g = pixels1D.get(pixelIndex).getGreen();
        int b = pixels1D.get(pixelIndex).getBlue();
        int a = pixels1D.get(pixelIndex).getAlpha();

        // set the pixel data
        int argb = a << 24;
        argb |= r << 16;
        argb |= g << 8;
        argb |= b;
        image.setRGB(x, y, argb);
        pixelIndex++;
      }
    }
    return image;
  }

  /**
   * Renders a message to the user.
   *
   * @param message - the message to be rendered
   * @throws IOException if the message cannot be rendered
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      JOptionPane.showMessageDialog(this, message);
    } catch (Exception e) {
      throw new IOException("Error rendering message");
    }
  }

  /**
   * Updates the content that the view renders for the user.
   *
   * @param content - the content to be rendered
   * @throws IOException if the content cannot be rendered
   */
  public void updateContent(RenderContent content) throws IOException {
    try {
      this.content = content;
      this.invalidate();
      this.validate();
      this.repaint();
      SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception e) {
      this.renderMessage("Failed with error trace: " + e.getMessage());
    }
  }

  /**
   * Handles the user's interaction with the buttons.
   *
   * @param e - the event that the user triggers
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.newProjectButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Enter the width and height of the project, separated by a space:",
              "New Project",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "500 500");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("new-project " + s);
      }
    } else if (e.getSource() == this.loadProjectButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Enter file path to load project from:",
              "Load Project",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "/");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("load-project " + s);
      }
    } else if (e.getSource() == this.saveProjectButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Enter file path to save project to:",
              "Save Project",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "/");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("save-project " + s);
      }
    } else if (e.getSource() == this.saveImageButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Enter file path to save image to:",
              "Save Image",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "/");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("save-image " + s);
      }
    } else if (e.getSource() == this.addLayerButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "New layer name:",
              "Add Layer",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("add-layer " + s);
      }
    } else if (e.getSource() == this.selectLayerButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Target layer name:",
              "Select Layer",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("select-layer " + s);
      }
    } else if (e.getSource() == this.setFilterButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Filter name:",
              "Set Filter",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("set-filter " + s);
      }
    } else if (e.getSource() == this.addImageToLayerButton) {
      String s = (String)JOptionPane.showInputDialog(
              this,
              "Enter file path to load image from as well as target layer and" +
                      " positional coordinates separated by spaces",
              "Add Image to Layer",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "/path/to/image.png layer-name 100 100");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("add-image-to-layer " + s);
      }
    }
  }
}

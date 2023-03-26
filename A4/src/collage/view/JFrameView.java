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

public class JFrameView extends JFrame implements IView, ActionListener {
  private GUIController controller;
  private RenderContent content;
  private JButton saveProjectButton;
  private JButton saveImageButton;
  private JButton addLayerButton;
  private JButton selectLayerButton;
  private JButton setFilterButton;
  private JButton addImageToLayerButton;

  public JFrameView(GUIController controller) {
    super();
    this.controller = controller;

    // generate the base frame
    this.setTitle("Collager");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(6000, 1200);
    this.setVisible(true);

    // configure the frame with scrollers
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    // show the composite image with a scrollbar
    JPanel imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Composite Project Image"));
    mainPanel.add(imagePanel);
    imagePanel.add(new JLabel(new ImageIcon(createImageFromScratch(this.content.getWidth(),
            this.content.getHeight(),
            this.content.getPixels()))));

    // display the project layers
    String[] layers = this.content.getLayers();
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

    // generate the buttons for the user to interact with
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createTitledBorder("User Controls"));
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
  }

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

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      JOptionPane.showMessageDialog(this, message);
    } catch (Exception e) {
      throw new IOException("Error rendering message");
    }
  }

  public void updateContent(RenderContent content) {
    this.removeAll();
    this.content = content;
    this.repaint();
    this.revalidate();
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.saveProjectButton) {
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
              "Layer name:",
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
              "Layer name:",
              "Add Layer",
              JOptionPane.PLAIN_MESSAGE,
              null,
              null,
              "");
      if ((s != null) && (s.length() > 0)) {
        this.controller.executeCommand("add-layer " + s);
      }
    } else if (e.getSource() == this.setFilterButton) {

    } else if (e.getSource() == this.addImageToLayerButton) {

    }
  }
}

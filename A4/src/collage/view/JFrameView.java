package collage.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import collage.controller.IController;
import collage.model.pixel.RGBPixel;

public class JFrameView extends JFrame implements IView {
  private RenderContent content;


  public JFrameView(IController controller) {
    super();

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

  }

  private Image createImageFromScratch(int width, int height, ArrayList<ArrayList<RGBPixel>> pixels) {
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

    // convert pixels to 1D arrayList
    ArrayList<RGBPixel> pixels1D = new ArrayList<RGBPixel>();
    for (ArrayList<RGBPixel> row : pixels) {
      for (RGBPixel pixel : row) {
        pixels1D.add(pixel);
      }
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

  public void setListener(ActionListener listener) {
    echoButton.addActionListener(listener); // Rather than adding *this* as a listener,
  }
}

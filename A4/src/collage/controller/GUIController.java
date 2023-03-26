package collage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import collage.model.IModel;
import collage.view.JFrameView;

public class GUIController implements ActionListener {
  private IModel model;
  private JFrameView view;

  public GUIController(IModel model, JFrameView view) {
    this.model = model;
    this.view = view;
    view.setListener(this);
    //view.display();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Echo Button": ...  // same code as before, but now
      case "Exit Button": ...  // it's extracted out of the view
    }
  }

}

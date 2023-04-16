# Decoupling the Controller

- IScriptView: The CLI view.
- ScriptView: The implementation for the CLI view.
- IGUIView: The GUI view.
- JFrameView: The implementation for the GUI view.
- IRenderContent: Used to pass structured information between the model, controller, and view.
- RenderContent: The implementation for IRenderContent.
- IGUIController: The GUI controller.
- GUIController: The implementation for the GUI controller which has access to GUIModel and JFrameView.
- IModel: The model.
- IGUIModel: The GUI implementation for the model.
- RGBPixel: The RGB pixel. This is the only class that is explicitly needed.

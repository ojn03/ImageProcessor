import controller.FeaturesController;
import controller.GuiController;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import view.GuiView;
import view.ImPGUI;

public class GUIMain {


  public static void main(String[] args){
    ImageProcessorModel m = new SimpleImageProcessorModel();
    GuiView v = new ImPGUI(m);
    FeaturesController c = new GuiController(m,v);
    v.setListener(c);
    c.runProcessor();
  }
}

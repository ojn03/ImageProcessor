//import org.junit.Test;
//
//import java.io.File;
//import java.io.StringReader;
//import java.util.HashMap;
//
//import controller.ImageProcessorController;
//import controller.SimpleImPController;
//import model.ImageProcessorModel;
//import model.SimpleImageProcessorModel;
//import res.ImageFileUtil;
//import res.ImageModel;
//import res.RGB;
//import view.ImageProcessorView;
//import view.SimpleProcessorView;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//
///**
// * testing public methods relevant to image processor.
// */
//public class TestImageProcessor {
//
//
//  Readable illegalinput = new StringReader("load-inexistentfile-f\n ");
//  Readable sampleinput = new StringReader(
//          "Upload src/res/images/fruit.png fruit\n"
//                  + "Upload src/res/images/nebula.jpg nebula\n"
//                  + "Sepia fruit sf\n"
//                  + "Blur fruit bf\n"
//                  + "Sharpen nebula sn\n"
//                  + "Grey nebula gn\n"
//
//                  + "Download gn src/res/images/gn.png\n"
//                  + "Download sf src/res/images/sf.png\n"
//                  + "Download bf src/res/images/bf.png\n"
//                  + "Download sn src/res/images/sn.png\n"
//  );
//
//  HashMap<String, ImageModel> map = new HashMap<>();
//  StringBuilder str = new StringBuilder();
//  ImageProcessorModel m0 = new SimpleImageProcessorModel(map);
//  ImageProcessorView v0 = new SimpleProcessorView(m0, str);
//
//  @Test
//  public void testRunCommands() {
//    //illegal input
//    ImageProcessorController c0 = new SimpleImPController(m0, v0, illegalinput);
//    c0.runProcessor();
//    assertEquals("Image processor is running\nunknown command\n", str.toString());
//
//    //test upload, filter, download
//    c0 = new SimpleImPController(m0, v0, sampleinput);
//    c0.runProcessor();
//    String gn = "src/res/images/gn.png";
//    String sf = "src/res/images/sf.png";
//    String bf = "src/res/images/bf.png";
//    String sn = "src/res/images/sn.png";
//
//    //create files of each downloaded image
//    File fgn = new File(gn);
//    File fsf = new File(sf);
//    File fbf = new File(bf);
//    File fsn = new File(sn);
//
//    //read what was downloaded
//    RGB[][] arrbf = ImageFileUtil.readFile(bf).getImage();
//    RGB[][] arrsn = ImageFileUtil.readFile(sn).getImage();
//    RGB[][] arrgn = ImageFileUtil.readFile(gn).getImage();
//    RGB[][] arrsf = ImageFileUtil.readFile(sf).getImage();
//
//    //check against correct copies
//    assertArrayEquals(arrbf,
//            ImageFileUtil.readFile("src/res/images/blurFruit.png").getImage());
//    assertArrayEquals(arrsn,
//            ImageFileUtil.readFile("src/res/images/sepiaNebula.png").getImage());
//    assertArrayEquals(arrgn,
//            ImageFileUtil.readFile("src/res/images/greyNebula.png").getImage());
//    assertArrayEquals(arrsf,
//            ImageFileUtil.readFile("src/res/images/sepiaFruit.png").getImage());
//
//    //delete files to return to original state
//    fgn.delete();
//    fsf.delete();
//    fbf.delete();
//    fsn.delete();
//
//  }
//
//}

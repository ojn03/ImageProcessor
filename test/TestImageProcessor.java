import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;

import controller.ImageProcessorController;
import controller.SimpleImPController;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import res.ImageModel;
import view.ImageProcessorView;
import view.SimpleProcessorView;

/**
 * testing public methods relevant to image processor.
 */
public class TestImageProcessor {
  Readable uploadFliprgbv = new StringReader(
          "Upload src/res/images/rgbv.ppm rgbv"
                  + " FlipV rgbv bvrg"
                  + " FlipH rgbv grvb"
                  + " FlipV grvb vbgr Quit");

  Readable downloadgrvb_bvrg = new StringReader(
          "Download grvb src/res/images/grvb.ppm"
                  + " Download bvrg src/res/images/bvrg.ppm Quit");


  Readable illegalinput = new StringReader("load grvb");
  Readable sampleinput = new StringReader(
          "Upload /Users/olivier-john/Desktop/nebula.jpg nebula\n"
                  + "Upload /Users/olivier-john/Desktop/fruit.png fruit\n"
                  + "Sepia fruit sf\n"
                  + "Grey fruit gf\n"
                  + "Blur fruit bf\n"
                  + "Sharpen fruit sf2\n"
                  + "Blur nebula bn\n"
                  + "Sharpen nebula sn\n"
                  + "Grey nebula gn\n"
                  + "Sepia nebula sn2\n"

                  + "Download sf2 /Users/olivier-john/Desktop/sf2.png\n"
                  + "Download bn /Users/olivier-john/Desktop/bn.png\n"
                  + "Download gn /Users/olivier-john/Desktop/gn.png\n"
                  + "Download sf /Users/olivier-john/Desktop/sf.png\n"
                  + "Download gf /Users/olivier-john/Desktop/gf.png\n"
                  + "Download bf /Users/olivier-john/Desktop/bf.png\n"
                  + "Download sn /Users/olivier-john/Desktop/sn.png\n"
                  + "Download sn2 /Users/olivier-john/Desktop/sn2.png\n"
  );

  HashMap<String, ImageModel> map = new HashMap<>();
  StringBuilder str = new StringBuilder();
  ImageProcessorModel m0 = new SimpleImageProcessorModel(map);
  ImageProcessorView v0 = new SimpleProcessorView(m0, str);

  @Test
  public void testRunCommands() {
    //illegal input
    ImageProcessorController c0 = new SimpleImPController(m0, v0, sampleinput);
    c0.runProcessor();
//    assertEquals("Image processor is running\nUnknown command load\nUnknown command grvb\n", str.toString());


//    //test upload and rgbv flips
//    RGB[][] expectedrgbv = {
//            {new RGB(255, 0, 0), new RGB(0, 255, 0)},
//            {new RGB(0, 0, 255), new RGB(255, 0, 255)}};
//    RGB[][] expectedbvrg = {
//            {new RGB(0, 0, 255), new RGB(255, 0, 255)},
//            {new RGB(255, 0, 0), new RGB(0, 255, 0)}};
//
//    RGB[][] expectedvbgr = {
//            {new RGB(255, 0, 255), new RGB(0, 0, 255)},
//            {new RGB(0, 255, 0), new RGB(255, 0, 0)}};
//
//    RGB[][] expectedgrvb = {
//            {new RGB(0, 255, 0), new RGB(255, 0, 0)},
//            {new RGB(255, 0, 255), new RGB(0, 0, 255)}};
//    c0 = new SimpleImPController(m0, v0, uploadFliprgbv);
//    c0.runProcessor();
//    assertTrue(map.containsKey("rgbv") && map.containsKey("bvrg") && map.containsKey("vbgr")
//            && map.containsKey("grvb"));
//    assertArrayEquals(expectedrgbv, map.get("rgbv").getImage());
//    assertArrayEquals(expectedgrvb, map.get("grvb").getImage());
//    assertArrayEquals(expectedvbgr, map.get("vbgr").getImage());
//    assertArrayEquals(expectedbvrg, map.get("bvrg").getImage());
//
//    //test illegal upload
//
//    //test download
//    c0 = new SimpleImPController(m0, v0, downloadgrvb_bvrg);
//    c0.runProcessor();
//    String fp = "src/res/images/grvb.ppm";
//    String fp1 = "src/res/images/bvrg.ppm";
//
//    File f = new File(fp);
//    File f1 = new File(fp1);
//
//    assertTrue(f.exists() && !f.isDirectory());
//    assertTrue(f1.exists() && !f1.isDirectory());
//    RGB[][] filecontentsfp = ImageFileUtil.readFile(fp).getImage();
//    RGB[][] filecontentsfp1 = ImageFileUtil.readFile(fp1).getImage();
//    assertArrayEquals(expectedgrvb, filecontentsfp);
//    assertArrayEquals(expectedbvrg, filecontentsfp1);
//
//
//    //files are deleted to return contents to original state
//    f.delete();
//    f1.delete();
//
//    str.setLength(0);
//
//    //test sample
//    c0 = new SimpleImPController(m0, v0, sampleinput);
//    c0.runProcessor();
//    String sfh = "src/res/images/sfh.ppm";
//    String sfv = "src/res/images/sfv.ppm";
//    String sfhv = "src/res/images/sfhv.ppm";
//    String sb50 = "src/res/images/sb50.ppm";
//    String sd50 = "src/res/images/sd50.ppm";
//
//    File fsfh = new File(sfh);
//    File fsfv = new File(sfv);
//    File fsfhv = new File(sfhv);
//    File fsb50 = new File(sb50);
//    File fsd50 = new File(sd50);
//
//    RGB[][] sfhcont = ImageFileUtil.readFile(sfh).getImage();
//    RGB[][] sfvcont = ImageFileUtil.readFile(sfv).getImage();
//    RGB[][] sfhvcont = ImageFileUtil.readFile(sfhv).getImage();
//    RGB[][] sb50cont = ImageFileUtil.readFile(sb50).getImage();
//    RGB[][] sd50cont = ImageFileUtil.readFile(sd50).getImage();
//
//    assertArrayEquals(sfhcont,
//            ImageFileUtil.readFile("src/res/images/sampleFlipH.ppm").getImage());
//    assertArrayEquals(sfvcont,
//            ImageFileUtil.readFile("src/res/images/sampleFlipV.ppm").getImage());
//    assertArrayEquals(sfhvcont,
//            ImageFileUtil.readFile("src/res/images/sampleFlipVH.ppm").getImage());
//    assertArrayEquals(sb50cont,
//            ImageFileUtil.readFile("src/res/images/sampleBright50.ppm").getImage());
//    assertArrayEquals(sd50cont,
//            ImageFileUtil.readFile("src/res/images/sampleDark50.ppm").getImage());
//
//    fsfh.delete();
//    fsfhv.delete();
//    fsb50.delete();
//    fsfv.delete();
//    fsd50.delete();

  }

}

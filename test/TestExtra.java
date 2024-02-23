import org.junit.Before;
import org.junit.Test;

import imagecommand.DownSize;
import imagecommand.Edit;
import imagecommand.ImageProcessorCommand;
import imagecommand.Mask;
import imagecommand.Visualize;
import model.ImageProcessorModel;
import model.SimpleImageProcessorModel;
import res.ImageFileUtil;
import res.ImageModel;
import res.RGB;

import static org.junit.Assert.assertArrayEquals;

public class TestExtra {
  ImageProcessorModel m = new SimpleImageProcessorModel();

  @Before
  public void init(){
    m.upload("src/res/images/swing.jpg", "swing");
    m.upload("src/res/images/edges.png", "edges");
    m.upload("src/res/images/circleMaskSmall.png", "small");
    m.upload("src/res/images/diamondMask.png", "diamond");
  }

  @Test
  public void testMask() {

    //testing edges mask w/ visred
    RGB[][] expected1 = ImageFileUtil.readFile("src/res/images/visRedMasked.png").getImage();

    ImageProcessorCommand mask1 =
            new Mask(
                    new Visualize("red", "swing", "visred"),
                    m.getImage("edges"));
    RGB[][] m1 = mask1.create(m).getImage();
    assertArrayEquals(expected1,m1);

    //testing small circle mask w/ sepia
    RGB[][] expected2 = ImageFileUtil.readFile("src/res/images/sepMasked.png").getImage();

    ImageProcessorCommand mask2 =
            new Mask(
                    new Edit("swing", "sepMask", ImageModel::transformSepia),
                    m.getImage("small"));
    RGB[][] m2 = mask2.create(m).getImage();

    assertArrayEquals(expected2,m2);
  }
  @Test
  public void testDownSize() {
    RGB[][] expected1 =  ImageFileUtil.readFile("src/res/images/300x100.png").getImage();

    ImageProcessorCommand ds1 = new DownSize(300,100,"swing","1kx300");
    RGB[][] down31 = ds1.create(m).getImage();
    assertArrayEquals(expected1, down31);

    RGB[][] expected2 =  ImageFileUtil.readFile("src/res/images/300x300.png").getImage();

    ImageProcessorCommand ds2 = new DownSize(300,300,"swing","300x300");
    RGB[][] down33 = ds2.create(m).getImage();
    assertArrayEquals(expected2, down33);
  }
}

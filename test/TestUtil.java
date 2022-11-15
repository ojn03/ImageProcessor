import org.junit.Test;

import res.ImageFileUtil;
import res.ImageModel;
import res.RGB;

import static org.junit.Assert.assertArrayEquals;


public class TestUtil {

@Test
 public void testRead() {

   ImageModel png =ImageFileUtil.readFile("/Users/olivier-john/Desktop/fruit.png");
  ImageModel ppm =ImageFileUtil.readFile("/Users/olivier-john/Desktop/fruit.ppm");
assertArrayEquals(png.getImage(), ppm.getImage());

}
}

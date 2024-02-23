//import org.junit.Test;
//
//import java.awt.image.BufferedImage;
//import java.util.Arrays;
//
//import res.ImageFileUtil;
//import res.ImageModel;
//import res.RGB;
//import res.SimpleImage;
//
//import static org.junit.Assert.assertArrayEquals;
//
///**
// * Testing Public Methods in SimpleImage.
// */
//public class TestSimpleImage {
//  @Test
//  public void testAdjustBrig() {
//    RGB[][] img = {
//            {new RGB(1, 2, 3), new RGB(3, 4, 5)},
//            {new RGB(3, 2, 1), new RGB(5, 4, 3)}};
//    ImageModel m = new SimpleImage(img);
//
//    RGB[][] adjustedImage = {
//            {new RGB(3, 4, 5), new RGB(5, 6, 7)},
//            {new RGB(5, 4, 3), new RGB(7, 6, 5)}};
//
//    assertArrayEquals(m.adjustBrightness(2).getImage(), adjustedImage);
//
//    RGB[][] img1 = {
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)},
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(1, 2, 3), new RGB(2, 3, 4), new RGB(3, 4, 5)}};
//
//    ImageModel m1 = new SimpleImage(img1);
//
//    RGB[][] adjustedImg1 = {{new RGB(13, 23, 33), new RGB(8, 13, 18),
//            new RGB(6, 9, 12)}, {new RGB(6, 9, 12), new RGB(8, 13, 18),
//            new RGB(13, 23, 33)}, {new RGB(4, 5, 6), new RGB(5, 6, 7),
//            new RGB(6, 7, 8)}};
//
//
//    assertArrayEquals(m1.adjustBrightness(3).getImage(), adjustedImg1);
//  }
//
//  @Test
//  public void testHorizontalFlip() {
//    RGB[][] notFlipped1 = {
//            {new RGB(1, 2, 3), new RGB(3, 4, 5)},
//            {new RGB(3, 2, 1), new RGB(5, 4, 3)}};
//
//    RGB[][] flipped1 = {
//            {new RGB(3, 4, 5), new RGB(1, 2, 3)},
//            {new RGB(5, 4, 3), new RGB(3, 2, 1)}};
//    ImageModel mod1 = new SimpleImage(notFlipped1);
//    assertArrayEquals(mod1.horizontalFlip().getImage(), flipped1);
//
//    RGB[][] notFlipped2 = {
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)},
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(1, 2, 3), new RGB(2, 3, 4), new RGB(3, 4, 5)}};
//
//    RGB[][] flipped2 = {
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)},
//            {new RGB(3, 4, 5), new RGB(2, 3, 4), new RGB(1, 2, 3)}};
//    ImageModel mod2 = new SimpleImage(notFlipped2);
//    assertArrayEquals(mod2.horizontalFlip().getImage(), flipped2);
//  }
//
//  @Test
//  public void TestVerticalFlip() {
//    RGB[][] notFlipped1 = {
//            {new RGB(1, 2, 3), new RGB(3, 4, 5)},
//            {new RGB(3, 2, 1), new RGB(5, 4, 3)}};
//    RGB[][] flipped1 = {
//            {new RGB(3, 2, 1), new RGB(5, 4, 3)},
//            {new RGB(1, 2, 3), new RGB(3, 4, 5)}};
//    ImageModel mod1 = new SimpleImage(notFlipped1);
//    assertArrayEquals(mod1.verticalFlip().getImage(), flipped1);
//
//    RGB[][] notFlipped2 = {
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)},
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(1, 2, 3), new RGB(2, 3, 4), new RGB(3, 4, 5)}};
//    RGB[][] flipped2 = {
//            {new RGB(1, 2, 3), new RGB(2, 3, 4), new RGB(3, 4, 5)},
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)}};
//    ImageModel mod2 = new SimpleImage(notFlipped2);
//    assertArrayEquals(mod2.verticalFlip().getImage(), flipped2);
//  }
//
//  @Test
//  public void testVisualize() {
//    RGB[][] example1 = {
//            {new RGB(1, 2, 3), new RGB(3, 4, 5)},
//            {new RGB(3, 2, 1), new RGB(5, 4, 3)}};
//
//    RGB[][] example1Red = {
//            {new RGB(1, 1, 1), new RGB(3, 3, 3)},
//            {new RGB(3, 3, 3), new RGB(5, 5, 5)}};
//
//    RGB[][] example1Green = {
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)},
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)}};
//
//    RGB[][] example1Blue = {
//            {new RGB(3, 3, 3), new RGB(5, 5, 5)},
//            {new RGB(1, 1, 1), new RGB(3, 3, 3)}};
//
//    RGB[][] example1Value = {
//            {new RGB(3, 3, 3), new RGB(5, 5, 5)},
//            {new RGB(3, 3, 3), new RGB(5, 5, 5)}};
//
//    RGB[][] example1Luma = {
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)},
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)}};
//
//    RGB[][] example1Intensity = {
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)},
//            {new RGB(2, 2, 2), new RGB(4, 4, 4)}};
//
//    ImageModel example1img = new SimpleImage(example1);
//    assertArrayEquals(example1img.visualize("red").getImage(), example1Red);
//    assertArrayEquals(example1img.visualize("green").getImage(), example1Green);
//    assertArrayEquals(example1img.visualize("blue").getImage(), example1Blue);
//    assertArrayEquals(example1img.visualize("value").getImage(), example1Value);
//    assertArrayEquals(example1img.visualize("luma").getImage(), example1Luma);
//    assertArrayEquals(example1img.visualize("intensity").getImage(), example1Intensity);
//
//    RGB[][] example2 = {
//            {new RGB(10, 20, 30), new RGB(5, 10, 15), new RGB(3, 6, 9)},
//            {new RGB(3, 6, 9), new RGB(5, 10, 15), new RGB(10, 20, 30)},
//            {new RGB(1, 2, 3), new RGB(2, 3, 4), new RGB(3, 4, 5)}};
//
//    RGB[][] example2Red = {
//            {new RGB(10, 10, 10), new RGB(5, 5, 5), new RGB(3, 3, 3)},
//            {new RGB(3, 3, 3), new RGB(5, 5, 5), new RGB(10, 10, 10)},
//            {new RGB(1, 1, 1), new RGB(2, 2, 2), new RGB(3, 3, 3)}};
//
//    RGB[][] example2Green = {
//            {new RGB(20, 20, 20), new RGB(10, 10, 10), new RGB(6, 6, 6)},
//            {new RGB(6, 6, 6), new RGB(10, 10, 10), new RGB(20, 20, 20)},
//            {new RGB(2, 2, 2), new RGB(3, 3, 3), new RGB(4, 4, 4)}};
//
//    RGB[][] example2Blue = {
//            {new RGB(30, 30, 30), new RGB(15, 15, 15), new RGB(9, 9, 9)},
//            {new RGB(9, 9, 9), new RGB(15, 15, 15), new RGB(30, 30, 30)},
//            {new RGB(3, 3, 3), new RGB(4, 4, 4), new RGB(5, 5, 5)}};
//
//    RGB[][] example2Value = {
//            {new RGB(30, 30, 30), new RGB(15, 15, 15), new RGB(9, 9, 9)},
//            {new RGB(9, 9, 9), new RGB(15, 15, 15), new RGB(30, 30, 30)},
//            {new RGB(3, 3, 3), new RGB(4, 4, 4), new RGB(5, 5, 5)}};
//
//    RGB[][] example2Luma = {
//            {new RGB(19, 19, 19), new RGB(9, 9, 9), new RGB(6, 6, 6)},
//            {new RGB(6, 6, 6), new RGB(9, 9, 9), new RGB(19, 19, 19)},
//            {new RGB(2, 2, 2), new RGB(3, 3, 3), new RGB(4, 4, 4)}};
//
//    RGB[][] example2Intensity = {
//            {new RGB(20, 20, 20), new RGB(10, 10, 10), new RGB(6, 6, 6)},
//            {new RGB(6, 6, 6), new RGB(10, 10, 10), new RGB(20, 20, 20)},
//            {new RGB(2, 2, 2), new RGB(3, 3, 3), new RGB(4, 4, 4)}};
//
//    ImageModel example2img = new SimpleImage(example2);
//    assertArrayEquals(example2img.visualize("red").getImage(), example2Red);
//    assertArrayEquals(example2img.visualize("green").getImage(), example2Green);
//    assertArrayEquals(example2img.visualize("blue").getImage(), example2Blue);
//    assertArrayEquals(example2img.visualize("value").getImage(), example2Value);
//    assertArrayEquals(example2img.visualize("luma").getImage(), example2Luma);
//    assertArrayEquals(example2img.visualize("intensity").getImage(), example2Intensity);
//  }
//
//  @Test
//  public void testBlur() {
//
//    RGB[][] pic1 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}};
//    RGB[][] expected1 = {
//            {new RGB(45, 54, 63), new RGB(69, 81, 93), new RGB(51, 60, 69)},
//            {new RGB(87, 99, 111), new RGB(121, 137, 153), new RGB(81, 93, 105)},
//            {new RGB(63, 72, 81), new RGB(99, 111, 123), new RGB(81, 90, 99)}};
//
//    ImageModel picModel1 = new SimpleImage(pic1);
//    ImageModel blurred1 = picModel1.blur();
//    assertArrayEquals(expected1, blurred1.getImage());
//
//    RGB[][] pic2 = {
//            {new RGB(16, 32, 48), new RGB(54, 63, 72)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144)}};
//    RGB[][] expected2 = {
//            {new RGB(44, 52, 60), new RGB(52, 59, 66)},
//            {new RGB(86, 98, 110), new RGB(98, 109, 120)},
//            {new RGB(63, 72, 81), new RGB(72, 81, 90)}};
//
//    ImageModel picModel2 = new SimpleImage(pic2);
//    ImageModel blurred2 = picModel2.blur();
//
//    assertArrayEquals(expected2, blurred2.getImage());
//
//    RGB[][] pic3 = {
//            {new RGB(64, 80, 96)},
//            {new RGB(160, 176, 192)},
//            {new RGB(64, 80, 96)}};
//    RGB[][] expected3 = {
//            {new RGB(36, 42, 48)},
//            {new RGB(56, 64, 72)},
//            {new RGB(36, 42, 48)}};
//
//    ImageModel picModel3 = new SimpleImage(pic3);
//    ImageModel blurred3 = picModel3.blur();
//
//    assertArrayEquals(expected3, blurred3.getImage());
//  }
//
//  @Test
//  public void testSharpen() {
//    RGB[][] pic1 = {
//            {new RGB(16, 32, 48)},
//            {new RGB(160, 176, 192)},
//            {new RGB(64, 80, 96)}
//    };
//
//    RGB[][] expected1 = {
//            {new RGB(48, 66, 84)},
//            {new RGB(180, 204, 228)},
//            {new RGB(102, 120, 138)}
//    };
//
//    ImageModel picModel1 = new SimpleImage(pic1);
//    ImageModel sharpened1 = picModel1.sharpen();
//    assertArrayEquals(expected1, sharpened1.getImage());
//    System.out.print(Arrays.deepToString(sharpened1.getImage()));
//
//    RGB[][] pic2 = {
//            {new RGB(60, 78, 96), new RGB(144, 174, 204, 255), new RGB(114, 132, 150, 255)},
//            {new RGB(234, 255, 255), new RGB(255, 255, 255), new RGB(162, 192, 222)},
//            {new RGB(132, 150, 168), new RGB(252, 255, 255), new RGB(2240, 255, 255)}
//    };
//
//    RGB[][] expected2 = {
//            {new RGB(104, 126, 143), new RGB(255, 255, 255), new RGB(138, 163, 192)},
//            {new RGB(255, 255, 255), new RGB(255, 255, 255), new RGB(255, 255, 255)},
//            {new RGB(225, 237, 243), new RGB(255, 255, 255), new RGB(255, 255, 255)}
//    };
//
//    ImageModel picModel2 = new SimpleImage(pic2);
//    ImageModel sharpened2 = picModel2.sharpen();
//    assertArrayEquals(expected2, sharpened2.getImage());
//
//    RGB[][] pic3 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}
//    };
//    RGB[][] expected3 = {
//            {new RGB(60, 78, 96), new RGB(144, 174, 204), new RGB(114, 132, 150)},
//            {new RGB(186, 210, 234), new RGB(255, 255, 255), new RGB(114, 138, 162)},
//            {new RGB(150, 174, 198), new RGB(255, 255, 255), new RGB(255, 255, 255)},
//            {new RGB(36, 54, 72), new RGB(228, 255, 255), new RGB(252, 255, 255)}
//
//    };
//
//    ImageModel picModel3 = new SimpleImage(pic3);
//    ImageModel sharpened3 = picModel3.sharpen();
//    assertArrayEquals(expected3, sharpened3.getImage());
//
//  }
//
//  @Test
//  public void testSepia() {
//    RGB[][] pic1 = {
//            {new RGB(16, 32, 48)},
//            {new RGB(160, 176, 192)},
//            {new RGB(64, 80, 96)}
//    };
//    RGB[][] expected1 = {
//            {new RGB(40, 36, 28)},
//            {new RGB(235, 209, 163)},
//            {new RGB(105, 93, 73)}
//    };
//
//    SimpleImage picModel1 = new SimpleImage(pic1);
//    ImageModel sepia1 = picModel1.transformSepia();
//    assertArrayEquals(expected1, sepia1.getImage());
//
//    RGB[][] pic2 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}
//    };
//
//    RGB[][] expected2 = {
//            {new RGB(40, 36, 28), new RGB(105, 93, 73), new RGB(170, 151, 118)},
//            {new RGB(235, 209, 163), new RGB(255, 255, 208), new RGB(40, 36, 28)},
//            {new RGB(105, 93, 73), new RGB(170, 151, 118), new RGB(255, 255, 208)}
//    };
//
//    SimpleImage picModel2 = new SimpleImage(pic2);
//    ImageModel sepia2 = picModel2.transformSepia();
//    //System.out.print(Arrays.deepToString(picModel2.transformSepia().getImage()));
//    assertArrayEquals(expected2, sepia2.getImage());
//
//    RGB[][] pic3 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}
//    };
//    RGB[][] expected3 = {
//            {new RGB(40, 36, 28), new RGB(105, 93, 73), new RGB(170, 151, 118)},
//            {new RGB(235, 209, 163), new RGB(255, 255, 208), new RGB(40, 36, 28)},
//            {new RGB(105, 93, 73), new RGB(170, 151, 118), new RGB(255, 255, 208)},
//            {new RGB(105, 93, 73), new RGB(170, 151, 118), new RGB(255, 255, 208)}
//    };
//
//    SimpleImage picModel3 = new SimpleImage(pic3);
//    ImageModel sepia3 = picModel3.transformSepia();
//    //System.out.print(Arrays.deepToString(picModel3.transformSepia().getImage()));
//    assertArrayEquals(expected3, sepia3.getImage());
//  }
//
//  @Test
//  public void testGrey() {
//    RGB[][] pic1 = {
//            {new RGB(16, 32, 48)},
//            {new RGB(160, 176, 192)},
//            {new RGB(64, 80, 96)}
//    };
//    RGB[][] expected1 = {
//            {new RGB(30, 30, 30)},
//            {new RGB(174, 174, 174)},
//            {new RGB(78, 78, 78)}
//    };
//
//    SimpleImage picModel1 = new SimpleImage(pic1);
//    ImageModel grey1 = picModel1.transformGrey();
//    //System.out.print(Arrays.deepToString(picModel1.transformGrey().getImage()));
//    assertArrayEquals(expected1, grey1.getImage());
//
//    RGB[][] pic2 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}
//    };
//
//    RGB[][] expected2 = {
//            {new RGB(30, 30, 30), new RGB(78, 78, 78), new RGB(126, 126, 126)},
//            {new RGB(174, 174, 174), new RGB(222, 222, 222), new RGB(30, 30, 30)},
//            {new RGB(78, 78, 78), new RGB(126, 126, 126), new RGB(222, 222, 222)}
//    };
//
//    SimpleImage picModel2 = new SimpleImage(pic2);
//    ImageModel grey2 = picModel2.transformGrey();
//    //System.out.print(Arrays.deepToString(picModel2.transformGrey().getImage()));
//    assertArrayEquals(expected2, grey2.getImage());
//
//    RGB[][] pic3 = {
//            {new RGB(16, 32, 48), new RGB(64, 80, 96), new RGB(112, 128, 144)},
//            {new RGB(160, 176, 192), new RGB(208, 224, 240), new RGB(16, 32, 48)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)},
//            {new RGB(64, 80, 96), new RGB(112, 128, 144), new RGB(208, 224, 240)}
//    };
//    RGB[][] expected3 = {
//            {new RGB(30, 30, 30), new RGB(78, 78, 78), new RGB(126, 126, 126)},
//            {new RGB(174, 174, 174), new RGB(222, 222, 222), new RGB(30, 30, 30)},
//            {new RGB(78, 78, 78), new RGB(126, 126, 126), new RGB(222, 222, 222)},
//            {new RGB(78, 78, 78), new RGB(126, 126, 126), new RGB(222, 222, 222)}
//    };
//
//    SimpleImage picModel3 = new SimpleImage(pic3);
//    ImageModel grey3 = picModel3.transformGrey();
//    assertArrayEquals(expected3, grey3.getImage());
//  }
//}

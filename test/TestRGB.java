import org.junit.Test;

import res.RGB;

import static org.junit.Assert.assertEquals;

/**
 * Testing RGB.
 */
public class TestRGB {
  @Test
  public void TestConstructor1() {
    RGB rgb1 = new RGB(10, 20, 30);
    RGB rgb2 = new RGB(10, 20, 30, 255);

    assertEquals(rgb1, rgb2);
  }

  @Test
  public void TestConstructor2() {
    RGB rgb1 = new RGB(10, 20, 30);
    RGB rgb2 = new RGB(rgb1);

    assertEquals(rgb1, rgb2);
  }

  @Test
  public void TestIllegalConstructor1() {
    try {
      RGB rgb3 = new RGB(200, 200, 200, 180);
    } catch (IllegalArgumentException e) {
      assertEquals("RGB values must be in range [0,180]", e.getMessage());
    }
  }

  @Test
  public void TestIllegalConstructor2() {
    try {
      RGB rgb3 = new RGB(200, 200, 300);
    } catch (IllegalArgumentException e) {
      assertEquals("RGB values must be in range [0,255]", e.getMessage());
    }
  }

  @Test
  public void TestAdjustBrightness25() {

    RGB rgb1 = new RGB(1, 2, 3);
    RGB rgb1Adjusted25 = new RGB(26, 27, 28);

    assertEquals(rgb1.adjustBrightness(25), rgb1Adjusted25);
  }

  @Test
  public void TestAdjustBrightness20() {

    RGB rgb2 = new RGB(1, 2, 3);
    RGB rgb1Adjusted20 = new RGB(21, 22, 23);

    assertEquals(rgb2.adjustBrightness(20), rgb1Adjusted20);
  }


  @Test
  public void TestIllegalAdjustBrightness() {
    RGB rgb3 = new RGB(200, 200, 200);
    try {
      rgb3.adjustBrightness(100);
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "e");
    }
  }

  @Test
  public void testGetMax1() {
    RGB rgb1 = new RGB(1, 2, 3, 10);

    assertEquals(rgb1.getMax(), 10);
  }

  @Test
  public void testGetMax2() {
    RGB rgb2 = new RGB(1, 2, 3);

    assertEquals(rgb2.getMax(), 255);
  }

  @Test
  public void testGetMax3() {
    RGB rgb2 = new RGB(1, 2, 3);
    assertEquals(rgb2.getMax(), 255);
  }

  @Test
  public void testGetRed1() {
    RGB rgb2 = new RGB(1, 2, 3);

    assertEquals(rgb2.getRed(), 1);
  }

  @Test
  public void testGetRed2() {
    RGB rgb2 = new RGB(100, 2, 3);

    assertEquals(rgb2.getRed(), 100);
  }

  @Test
  public void testGetRed3() {
    RGB rgb1 = new RGB(14, 2, 3, 14);

    assertEquals(rgb1.getRed(), 14);
  }

  @Test
  public void testGetGreen1() {
    RGB rgb2 = new RGB(1, 2, 3);

    assertEquals(rgb2.getGreen(), 2);
  }

  @Test
  public void testGetGreen2() {
    RGB rgb2 = new RGB(100, 20, 3);

    assertEquals(rgb2.getGreen(), 20);
  }

  @Test
  public void testGetGreen3() {
    RGB rgb2 = new RGB(100, 24, 3, 160);

    assertEquals(rgb2.getGreen(), 24);
  }

  @Test
  public void testGetBlue1() {
    RGB rgb2 = new RGB(1, 2, 3);

    assertEquals(rgb2.getBlue(), 3);
  }

  @Test
  public void testGetBlue2() {
    RGB rgb2 = new RGB(1, 2, 3);
    RGB rgb3 = new RGB(rgb2);

    assertEquals(rgb3.getBlue(), 3);
  }

  @Test
  public void testGetBlue3() {
    RGB rgb2 = new RGB(100, 24, 30, 160);

    assertEquals(rgb2.getGreen(), 24);
  }

  @Test
  public void testIntensity1() {
    RGB rgb2 = new RGB(3, 3, 3);

    assertEquals(rgb2.intensity(), 3);
  }

  @Test
  public void testIntensity2() {
    RGB rgb2 = new RGB(1, 2, 3);
    RGB rgb3 = new RGB(rgb2);

    assertEquals(rgb3.intensity(), 2);
  }

  @Test
  public void testIntensity3() {
    RGB rgb2 = new RGB(102, 24, 30, 160);

    assertEquals(rgb2.intensity(), 52);
  }

  @Test
  public void testValue1() {
    RGB rgb1 = new RGB(3, 3, 3);

    assertEquals(rgb1.value(), 3);
  }

  @Test
  public void testValue2() {
    RGB rgb2 = new RGB(1, 2, 3);
    RGB rgb3 = new RGB(rgb2);

    assertEquals(rgb3.value(), 3);
  }

  @Test
  public void testValue3() {
    RGB rgb2 = new RGB(102, 24, 30, 160);

    assertEquals(rgb2.value(), 102);
  }

  @Test
  public void TestToString1() {
    RGB rgb1 = new RGB(103, 104, 100);

    assertEquals(rgb1.toString(), "[103,104,100,255]");
  }

  @Test
  public void TestToString2() {
    RGB rgb1 = new RGB(10, 14, 109, 140);

    assertEquals(rgb1.toString(), "[10,14,109,140]");
  }

  @Test
  public void testToString3() {
    RGB rgb2 = new RGB(1, 2, 3);
    RGB rgb3 = new RGB(rgb2);

    assertEquals(rgb3.toString(), "[1,2,3,255]");
  }
}

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class HistogramPanel extends JPanel {
  private int[][] data;
  private int maxFreq;
  private int maxVal;


  HistogramPanel() {
    this.data = new int[][]{{}, {}, {}, {}};

    this.maxFreq = calculateMaxF();
    this.maxVal = calculateMaxV();

    setPreferredSize(new Dimension(600, 300));
    setBorder(BorderFactory.createTitledBorder("Histogram"));

  }

  static int max(int[] arr) {
    int max = 0;

    for (int i : arr) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  private int calculateMaxF() {
    int rmax = data[0].length == 0 ? 0 : max(data[0]);
    int gmax = data[1].length == 0 ? 0 : max(data[1]);
    int bmax = data[2].length == 0 ? 0 : max(data[2]);
    int imax = data[3].length == 0 ? 0 : max(data[3]);
    return (Math.max(rmax, Math.max(gmax, Math.max(bmax, imax))));
  }

  private int calculateMaxV() {
    int rmaxv = data[0].length - 1;
    int gmaxv = data[1].length - 1;
    int bmaxv = data[2].length - 1;
    int imaxv = data[3].length - 1;
    return Math.max(rmaxv, Math.max(gmaxv, Math.max(bmaxv, imaxv)));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int lenGraph = getWidth() - 75;

    int topGraph = 25;
    int bottomGraph = getHeight() - 50;
    int leftGraph = 50;
    int rightGraph = 50 + lenGraph;
    int heightGraph = getHeight() - 75;
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.white);
    //setting graph background
    g2.fillRect(leftGraph, topGraph, lenGraph, heightGraph);


    //setting hatchmarks
    int xPad = lenGraph / 10;
    int ypad = (getHeight() - 75) / 10;

    g2.setColor(Color.darkGray);

    // dashed stroke
    Stroke dashed = new BasicStroke(.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
            0, new float[]{9}, 0);
    g2.setStroke(dashed);
    for (int i = 1; i < 10; i++) {
      //vertical marks
      g2.drawLine(xPad * i + 50, topGraph, xPad * i + 50, bottomGraph);

      String num = String.format("%d", (((maxVal * i) / 10) + 1));
      g2.drawString(num, xPad * i + 40, getHeight() - 25);

      //horizontal marks
      if (i < 5) {
        g2.drawLine(leftGraph, bottomGraph - ypad * 2 * i, rightGraph, bottomGraph - ypad * 2 * i);
        num = String.format("%d", (((maxFreq * i) / 5)));
        g2.drawString(num, 20, bottomGraph - ypad * 2 * i);
      }
    }


    //draws the lines
    int[] redData = data[0];
    int[] greenData = data[1];
    int[] blueData = data[2];
    int[] iData = data[3];

    g2.setColor(Color.red);
    g2.setStroke(new BasicStroke());
    for (int i = 0; i < redData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(redData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(redData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.green);
    for (int i = 0; i < greenData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(greenData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(greenData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.blue);
    for (int i = 0; i < blueData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(blueData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(blueData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

    g2.setColor(Color.black);
    for (int i = 0; i < iData.length - 1; i++) {
      int fromx = (int) Math.round(i * (lenGraph / (maxVal * 1.0)) + 50);
      int fromy = bottomGraph - (int) Math.round(iData[i] * (heightGraph / (maxFreq * 1.0)));
      int tox = (int) Math.round((i + 1) * (lenGraph / (maxVal * 1.0)) + 50);
      int toy = bottomGraph - (int) Math.round(iData[i + 1] * (heightGraph / (maxFreq * 1.0)));
      g2.drawLine(fromx, fromy, tox, toy);
    }

  }

  void updateHistogram(int[][] newHistogram) {
    this.data = newHistogram;
    this.maxFreq = calculateMaxF();
    this.maxVal = calculateMaxV();
    this.repaint();
  }
}

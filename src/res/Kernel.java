package res;

/**
 * Represents a kernel.
 */
public class Kernel {
  private final double[][] filter;

  //purposely made a public field rather than a
  // getter method since this is an integer and is immutable
  /**
   * Size of the kernel.
   */
  public final int size;

  /**
   * Constructs a kernel.
   * @param k array of doubles.
   */
  public Kernel(double[][] k) {
    if (k == null || k.length != k[0].length || k.length % 2 != 1) {
      throw new IllegalArgumentException("no null values. array must be an odd square");
    }
    filter = k;
    size = k.length;
  }

  /**
   * Constructs a kernel.
   * @param s a String that represents the type of filter.
   */
  public Kernel(String s) {

    // todo command hashmap?
    if (s == null) {
      throw new IllegalArgumentException("no null values");
    } else if (s.equalsIgnoreCase("blur")) {
      filter = new double[][]{
              {.0625, .125, .0625},
              {.125, .25, .125},
              {.0625, .125, .0625}};
      size = 3;
    } else if (s.equalsIgnoreCase("sharpen")) {
      filter = new double[][]{
              {-.125, -.125, -.125, -.125, -.125},
              {-.125, .25, .25, .25, -.125},
              {-.125, .25, 1.0, .25, -.125},
              {-.125, .25, .25, .25, -.125},
              {-.125, -.125, -.125, -.125, -.125}

      };
      size = 5;
    } else if (s.equalsIgnoreCase("sepia")) {
      filter = new double[][]{
              {.393, .769, .189},
              {.349, .686, .168},
              {.272, .534, .131}
      };
      size = 3;
    } else if (s.equalsIgnoreCase("grey")) {
      filter = new double[][]{
              {.2126, .7152, .0722},
              {.2126, .7152, .0722},
              {.2126, .7152, .0722}
      };
      size = 3;
    } else {
      throw new IllegalArgumentException("Unknown Kernel: "+ s);
    }
  }

  /**
   * Gets the filtered value at the given coordinates, throws IllegalArgumentException if given
   * position is out of bounds of the array.
   * @param i the row of the array at the desired coordinates.
   * @param j the column of the array at the desired coordinates.
   * @return double of the given value.
   * @throws IllegalArgumentException when index is out of bounds.
   */
  double slotAt(int i, int j) {
    try {
      return filter[i][j];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("position " + i + " " + j + " out of bounds");
    }
  }
}
//todo make kernel interface and abstract to account for filters and color transformations
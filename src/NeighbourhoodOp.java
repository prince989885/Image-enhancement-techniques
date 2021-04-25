import java.awt.image.*;


public class NeighbourhoodOp extends StandardGreyOp {
 
  /** Indicates that no processing of borders is to be done. */
  public static final int NO_BORDER_OP = 1;

  /** Indicates that border pixels are to be copied from source image. */
  public static final int COPY_BORDER_PIXELS = 2;

  /** Indicates that invalid coordinates are reflected into the image. */
  public static final int REFLECTED_INDEXING = 3;

  /** Indicates that invalid coordinates wrap around the image. */
  public static final int CIRCULAR_INDEXING = 4;
 
  /** Width of neighbourhood. */
  protected int width;

  /** Height of neighbourhood. */
  protected int height;

  /** Number of pixels in neighbourhood. */
  protected int size;

  /** Strategy to use when dealing with image borders. */
  protected int borderStrategy;
  
  public NeighbourhoodOp(int w, int h, int strategy) {
    if (w < 1 || h < 1 || w%2 == 0 || h%2 == 0)
      throw new ImagingOpException("invalid neighbourhood dimensions");
    width = w;
    height = h;
    size = w*h;
    borderStrategy = strategy;
  }


  /**
   * @return width of neighbourhood.
   */

  public int getWidth() {
    return width;
  }


  /**
   * @return height of neighbourhood.
   */

  public int getHeight() {
    return height;
  }


  /**
   * @return number of pixels in neighbourhood.
   */

  public int getNumPixels() {
    return size;
  }


  /**
   * @return strategy used to deal with image borders.
   */

  public int getBorderStrategy() {
    return borderStrategy;
  }



  public static final int refIndex(int i, int n) {
    if (i < 0)
      return -i-1;
    else if (i >= n)
      return 2*n-i-1;
    else
      return i;
  }


 
  public static final int circIndex(int i, int n) {
    if (i < 0)
      return i+n;
    else if (i >= n)
      return i-n;
    else
      return i;
  }



  protected void copyBorders(Raster src, WritableRaster dest) {
    int w = src.getWidth();
    int h = src.getHeight();
    int m = width/2;
    int n = height/2;
    for (int x = 0; x < w; ++x) {    // copy top and bottom
      for (int y = 0; y < n; ++y)
        dest.setSample(x, y, 0, src.getSample(x, y, 0));
      for (int y = h-n; y < h; ++y)
        dest.setSample(x, y, 0, src.getSample(x, y, 0));
    }
    for (int y = 0; y < h; ++y) {    // copy left and right
      for (int x = 0; x < m; ++x)
        dest.setSample(x, y, 0, src.getSample(x, y, 0));
      for (int x = w-m; x < w; ++x)
        dest.setSample(x, y, 0, src.getSample(x, y, 0));
    }
  }


}

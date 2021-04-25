




import java.awt.image.*;



public class MinimumFilterOp extends RankFilterOp {


  /**
   * Constructs a MinimumFilterOp for a 3x3 neighbourhood.  No special
   * processing will be done at the image borders.
   */

  public MinimumFilterOp() {
    super(1, 3, 3);
  }

  /**
   * Constructs a MinimumFilterOp with the given neighbourhood
   * dimensions.  No special processing will be done at the image
   * borders.
   * @param w width of neighbourhood
   * @param h height of neighbourhood
   */

  public MinimumFilterOp(int w, int h) {
    super(1, w, h);
  }

  /**
   * Constructs a MinimumFilterOp with the specified neighbourhood
   * dimensions and border processing strategy.
   * @param w width of neighbourhood
   * @param h height of neighbourhood
   * @param strategy border processing strategy
   */

  public MinimumFilterOp(int w, int h, int strategy) {
    super(1, w, h, strategy);
  }


  /**
   * Performs minimum filtering of an image.
   * @param src source image
   * @param dest destination image, or null
   * @return processed image.
   */

  public BufferedImage filter(BufferedImage src, BufferedImage dest) {

    checkImage(src);
    if (dest == null)
      dest = createCompatibleDestImage(src, null);

    int w = src.getWidth();
    int h = src.getHeight();
    Raster srcRaster = src.getRaster();
    WritableRaster destRaster = dest.getRaster();

    int m = width/2;
    int n = height/2;
    int minimum, value;
    switch (borderStrategy) {

      case REFLECTED_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            minimum = 255;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(refIndex(x+j, w), refIndex(y+k, h), 0);
                if (value < minimum)
                  minimum = value;
              }
            destRaster.setSample(x, y, 0, minimum);
          }
        break;

      case CIRCULAR_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            minimum = 255;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(circIndex(x+j, w), circIndex(y+k, h), 0);
                if (value < minimum)
                  minimum = value;
              }
            destRaster.setSample(x, y, 0, minimum);
          }
        break;

      case COPY_BORDER_PIXELS:
        copyBorders(srcRaster, destRaster);
        // fall through...

      default:
        for (int y = n; y < h-n; ++y)
          for (int x = m; x < w-m; ++x) {
            minimum = 255;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value = srcRaster.getSample(x+j, y+k, 0);
                if (value < minimum)
                  minimum = value;
              }
            destRaster.setSample(x, y, 0, minimum);
          }
        break;

    }

    return dest;

  }


}

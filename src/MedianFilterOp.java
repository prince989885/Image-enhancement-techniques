import java.awt.image.*;
public class MedianFilterOp extends RankFilterOp {



  /** Histogram of grey levels in neighbourhood. */
  private int[] histogram = new int[256];



  
  public MedianFilterOp() {
    super(5, 3, 3);
  }

 
  public MedianFilterOp(int w, int h) {
    super((w*h/2)+1, w, h);
  }


  public MedianFilterOp(int w, int h, int strategy) {
    super((w*h/2)+1, w, h, strategy);
  }


 
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
    int value;
    switch (borderStrategy) {

      case REFLECTED_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(refIndex(x+j, w), refIndex(y+k, h), 0);
                histogram[value]++;
              }
            destRaster.setSample(x, y, 0, getMedian());
          }
        break;

      case CIRCULAR_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(circIndex(x+j, w), circIndex(y+k, h), 0);
                histogram[value]++;
              }
            destRaster.setSample(x, y, 0, getMedian());
          }
        break;

      case COPY_BORDER_PIXELS:
        copyBorders(srcRaster, destRaster);
        // fall through...

      default:
        // NO_BORDER_OP
        for (int y = n; y < h-n; ++y)
          for (int x = m; x < w-m; ++x) {
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j)
                ++histogram[srcRaster.getSample(x+j, y+k, 0)];
            destRaster.setSample(x, y, 0, getMedian());
          }
        break;

    }

    return dest;

  }


    /**
   * Searches neighbourhood histogram for median.
   * @return median in neighbourhood.
   */

  private int getMedian() {

    int sum = 0, median = 0;
    boolean found = false;

    for (int i = 0; i < 256; ++i) {
      if (!found) {
        sum += histogram[i];
        if (sum >= rank) {
          median = i;
          found = true;
        }
      }
      histogram[i] = 0;
    }

    return median;

  }


}

import java.awt.image.*;
import java.util.Arrays;
public class RankFilterOp extends NeighbourhoodOp {

  /** Rank of filter. */
  protected int rank;

  /** Array holding values from neighbourhood. */
  protected int[] neighbourhood;
 
  public RankFilterOp(int n) {
    this(n, 3, 3, NO_BORDER_OP);
  }


  public RankFilterOp(int n, int w, int h) {
    this(n, w, h, NO_BORDER_OP);
  }

   public RankFilterOp(int n, int w, int h, int strategy) {
    super(w, h, strategy);
    neighbourhood = new int[size];
    if (n < 1 || n > size)
      throw new ImagingOpException("rank must lie between 1 and " + size);
    rank = n;
  }


  /**
   * @return rank of filter.
   */

  public int getRank() {
    return rank;
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
    switch (borderStrategy) {

      case REFLECTED_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            int i = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j, ++i)
                neighbourhood[i] =
                 srcRaster.getSample(refIndex(x+j, w), refIndex(y+k, h), 0);
            Arrays.sort(neighbourhood);
            destRaster.setSample(x, y, 0, neighbourhood[rank-1]);
          }
        break;

      case CIRCULAR_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            int i = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j, ++i)
                neighbourhood[i] =
                 srcRaster.getSample(circIndex(x+j, w), circIndex(y+k, h), 0);
            Arrays.sort(neighbourhood);
            destRaster.setSample(x, y, 0, neighbourhood[rank-1]);
          }
        break;

      case COPY_BORDER_PIXELS:
        copyBorders(srcRaster, destRaster);
        // fall through...

      default:
        // NO_BORDER_OP
        for (int y = n; y < h-n; ++y)
          for (int x = m; x < w-m; ++x) {
            int i = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j, ++i)
                neighbourhood[i] = srcRaster.getSample(x+j, y+k, 0);
            Arrays.sort(neighbourhood);
            destRaster.setSample(x, y, 0, neighbourhood[rank-1]);
          }
        break;

    }

    return dest;

  }


}

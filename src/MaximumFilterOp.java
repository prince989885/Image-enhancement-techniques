





import java.awt.image.*;


public class MaximumFilterOp extends RankFilterOp {



  public MaximumFilterOp() {
    super(9, 3, 3);
  }

  public MaximumFilterOp(int w, int h) {
    super(w*h, w, h);
  }

  public MaximumFilterOp(int w, int h, int strategy) {
    super(w*h, w, h, strategy);
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
    int maximum, value;
    switch (borderStrategy) {

      case REFLECTED_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            maximum = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(refIndex(x+j, w), refIndex(y+k, h), 0);
                if (value > maximum)
                  maximum = value;
              }
            destRaster.setSample(x, y, 0, maximum);
          }
        break;

      case CIRCULAR_INDEXING:
        for (int y = 0; y < h; ++y)
          for (int x = 0; x < w; ++x) {
            maximum = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value =
                 srcRaster.getSample(circIndex(x+j, w), circIndex(y+k, h), 0);
                if (value > maximum)
                  maximum = value;
              }
            destRaster.setSample(x, y, 0, maximum);
          }
        break;

      case COPY_BORDER_PIXELS:
        copyBorders(srcRaster, destRaster);
        // fall through...

      default:
        // NO_BORDER_OP
        for (int y = n; y < h-n; ++y)
          for (int x = m; x < w-m; ++x) {
            maximum = 0;
            for (int k = -n; k <= n; ++k)
              for (int j = -m; j <= m; ++j) {
                value = srcRaster.getSample(x+j, y+k, 0);
                if (value > maximum)
                  maximum = value;
              }
            destRaster.setSample(x, y, 0, maximum);
          }
        break;

    }

    return dest;

  }


}

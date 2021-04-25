import java.awt.RenderingHints;
import java.awt.geom.*;
import java.awt.image.*;

public class StandardGreyOp implements BufferedImageOp {
  public BufferedImage filter(BufferedImage src, BufferedImage dest) {
    checkImage(src);
    if (dest == null)
      dest = createCompatibleDestImage(src, null);
    WritableRaster raster = dest.getRaster();
    src.copyData(raster);
    return dest;
  }

  public BufferedImage createCompatibleDestImage(BufferedImage src,
   ColorModel destModel) {
    if (destModel == null)
      destModel = src.getColorModel();
    int width = src.getWidth();
    int height = src.getHeight();
    BufferedImage image = new BufferedImage(destModel,
     destModel.createCompatibleWritableRaster(width, height),
     destModel.isAlphaPremultiplied(), null);
    return image;
  }
  public Rectangle2D getBounds2D(BufferedImage src) {
    return src.getRaster().getBounds();
  }

  public Point2D getPoint2D(Point2D srcPoint, Point2D destPoint) {
    if (destPoint == null)
      destPoint = new Point2D.Float();
    destPoint.setLocation(srcPoint.getX(), srcPoint.getY());
    return destPoint;
  }


  /**
   * @return rendering hints for this operation.
   */

  public RenderingHints getRenderingHints() {
    return null;
  }

  public void checkImage(BufferedImage src) {
    if (src.getType() != BufferedImage.TYPE_BYTE_GRAY)
      throw new ImagingOpException("operation requires an 8-bit grey image");
  }


}

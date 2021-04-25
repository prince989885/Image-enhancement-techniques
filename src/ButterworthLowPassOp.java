import java.awt.image.*;
public class ButterworthLowPassOp extends StandardGreyOp {

  /** Order of filter. */
  protected int order;

  /** Radius of filter. */
  protected double radius;

    public ButterworthLowPassOp(double r) {
    this(1, r);
  }

  
  public ButterworthLowPassOp(int n, double r) {
    if (n < 1 || r < 0.0)
      throw new ImagingOpException("invalid filter parameters");
    order = n;
    radius = r;
  }


  
  public int getOrder() {
    return order;
  }


  
  public double getRadius() {
    return radius;
  }
 
  public BufferedImage filter(BufferedImage src, BufferedImage dest) {

    checkImage(src);
    if (dest == null)
      dest = createCompatibleDestImage(src, null);

    try {
      ImageFFT fft = new ImageFFT(src);
      fft.transform();
      fft.butterworthLowPassFilter(order, radius);
      fft.transform();
      fft.toImage(dest);
    }
    catch (FFTException e) {
      throw new ImagingOpException("cannot filter image");
    }

    return dest;

  }


}

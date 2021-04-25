import java.awt.image.*;
public class MinFilter {
   public double min(String infile,String out,int w,int h) {
double time=0;
      try {
        ImageDecoder input = ImageFile.createImageDecoder(infile);
        ImageEncoder output = ImageFile.createImageEncoder(out);
        BufferedImage inputImage = input.decodeAsBufferedImage();
        BufferedImageOp op = new MinimumFilterOp(w, h);
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        BufferedImage outputImage = op.filter(inputImage, null);
       time=timer.stop();
         output.encode(outputImage);
              }
      catch (Exception e) {
        System.err.println(e);
      }
 return time;
  }
}

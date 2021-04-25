import java.awt.image.*;
import java.awt.*;
public class MaxFilter {
 public double max(String infile,String out,int w,int h) {
double time=0;
      try {
                ImageDecoder input = ImageFile.createImageDecoder(infile);
             ImageEncoder output = ImageFile.createImageEncoder(out);
              BufferedImage inputImage = input.decodeAsBufferedImage();
                 BufferedImageOp op = (BufferedImageOp) new MaximumFilterOp(w, h);
           IntervalTimer timer = new IntervalTimer();
            timer.start();
        BufferedImage outputImage = op.filter(inputImage, null);
        output.encode(outputImage);
       time=timer.stop();   
                }
      catch (Exception e) {
        System.err.println(e);
       }
return time;
   }
}

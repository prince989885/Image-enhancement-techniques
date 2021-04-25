import java.awt.image.*;
public class MeanFilter {
//  public static void main(String[] argv) {
   // if (argv.length > 3) 
              public double mf(String infile,String out,int w,int h){
double time=0;
      try {
        ImageDecoder input = ImageFile.createImageDecoder(infile);
        ImageEncoder output = ImageFile.createImageEncoder(out);
                BufferedImage inputImage = input.decodeAsBufferedImage();
        Kernel kernel = new MeanKernel(w, h);
        ConvolveOp blurOp = new ConvolveOp(kernel);
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        BufferedImage outputImage = blurOp.filter(inputImage, null);
       time=timer.stop();
        output.encode(outputImage);
        

      }
      catch (Exception e) {
        System.err.println(e);

      }
return time;
    }
}

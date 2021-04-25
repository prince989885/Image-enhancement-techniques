import java.awt.image.*;
public class Bwhp {
      public double bw(String in,String out,int order,float radius,int bias){
double time=0;
      try {

        ImageDecoder input = ImageFile.createImageDecoder(in);
          System.out.println(input);
        ImageEncoder output = ImageFile.createImageEncoder(out);
        int n = order;
        float r = Math.max(0.05f, Math.min(0.95f,radius));
        BufferedImage inputImage = input.decodeAsBufferedImage();
          System.out.println(inputImage);
        ImageFFT fft = new ImageFFT(inputImage);
        System.out.println("Order " + n + " filter, radius = " + r + "...");
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        fft.transform();
        fft.butterworthHighPassFilter(n, r);
        fft.transform();
time=timer.stop();

        BufferedImage outputImage = fft.toImage(null, bias);
        output.encode(outputImage);
      }
      catch (Exception e) {
        System.err.println(e);
           }
return time;
  }
}

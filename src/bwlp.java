import java.awt.image.*;
public class bwlp {
public double bl(String infile,String out,int order, float radius)
{double time=0;
      try {

        ImageDecoder input = ImageFile.createImageDecoder(infile);
        ImageEncoder output = ImageFile.createImageEncoder(out);
        int n = order;
        float r =Math.max(0.05f, Math.min(0.95f,radius));

        BufferedImage inputImage = input.decodeAsBufferedImage();
        ImageFFT fft = new ImageFFT(inputImage);
        System.out.println("Order " + n + " filter, radius = " + r + "...");
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        fft.transform();
        fft.butterworthLowPassFilter(n, r);
        fft.transform();
        time=timer.stop();
        BufferedImage outputImage = fft.toImage(null);
        output.encode(outputImage);
           }
      catch (Exception e) {
        System.err.println(e);
            }
return time;
  }
}

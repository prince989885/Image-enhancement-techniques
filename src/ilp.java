import java.awt.image.*;


public class ilp {
 // public static void main(String[] argv) {
   // if (argv.length > 2) 
        public double il(String infile,String out,float rad){
double time=0;
           try {

        ImageDecoder input = ImageFile.createImageDecoder(infile);
        ImageEncoder output = ImageFile.createImageEncoder(out);
        float radius = Math.max(0.05f, Math.min(0.95f, rad));
        BufferedImage inputImage = input.decodeAsBufferedImage();
        ImageFFT fft = new ImageFFT(inputImage);
        System.out.println("Filter radius = " + radius + "...");
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        fft.transform();
        fft.idealLowPassFilter(radius);
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

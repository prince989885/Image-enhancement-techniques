import java.awt.image.*;
public class ihp {
         public double ih(String infile,String out,float rad,int bias){
double time=0;
      try {

        ImageDecoder input = ImageFile.createImageDecoder(infile);
        ImageEncoder output = ImageFile.createImageEncoder(out);
        float radius =Math.max(0.05f, Math.min(0.95f,rad));
   //     int bias = Integer.parseInt(argv[3]);

        BufferedImage inputImage = input.decodeAsBufferedImage();
        ImageFFT fft = new ImageFFT(inputImage);
        System.out.println("Filter radius = " + radius + "...");
        IntervalTimer timer = new IntervalTimer();
        timer.start();
        fft.transform();
        fft.idealHighPassFilter(radius);
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

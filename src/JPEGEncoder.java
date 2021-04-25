import java.io.*;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;

public class JPEGEncoder implements ImageEncoder {



  private JPEGImageEncoder encoder;


  
  

  public JPEGEncoder() {
    this(System.out);
  }

 
  public JPEGEncoder(OutputStream out) {
    encoder = JPEGCodec.createJPEGEncoder(out);
  }

  public JPEGEncoder(String imgfile) throws FileNotFoundException {
    this(new FileOutputStream(imgfile));
  }


  public void encode(BufferedImage image)
   throws IOException, JPEGEncoderException {
    try {
      encoder.encode(image);
    }
    catch (ImageFormatException e) {
      throw new JPEGEncoderException(e.getMessage());
    }
  }


}

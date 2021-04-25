import java.io.*;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;

public class JPEGDecoder implements ImageDecoder {

    private JPEGImageDecoder decoder;
  public JPEGDecoder() {
    this(System.in);
  }

 
  public JPEGDecoder(InputStream in) {
    decoder = JPEGCodec.createJPEGDecoder(in);
  }


  public JPEGDecoder(String imgfile) throws FileNotFoundException {
    this(new FileInputStream(imgfile));
  }


  
  public BufferedImage decodeAsBufferedImage()
   throws IOException, JPEGDecoderException {
    try {
      return decoder.decodeAsBufferedImage();
    }
    catch (ImageFormatException e) {   // catch and convert exception
      throw new JPEGDecoderException(e.getMessage());
    }
  }


}

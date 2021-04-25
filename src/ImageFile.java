import java.io.IOException;
public class ImageFile {
 public static ImageEncoder createImageEncoder(String filename)
   throws IOException, ImageEncoderException {

           if (filename.endsWith(".jpg")
                 || filename.endsWith(".jpeg"))
              return new JPEGEncoder(filename);
    else
      throw new ImageEncoderException("cannot determine file format");

  }
  public static ImageDecoder createImageDecoder(String filename)
   throws IOException, ImageDecoderException {

        if (filename.endsWith(".jpg")
     || filename.endsWith(".jpeg"))
      return new JPEGDecoder(filename);
    else
      throw new ImageDecoderException("cannot determine file format");

  }


}

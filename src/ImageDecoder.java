import java.awt.image.BufferedImage;
import java.io.IOException;
public interface ImageDecoder {
  BufferedImage decodeAsBufferedImage()
   throws IOException, ImageDecoderException;
}

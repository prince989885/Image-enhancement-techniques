import java.awt.image.BufferedImage;
import java.io.IOException;
public interface ImageEncoder {
  void encode(BufferedImage image)
   throws IOException, ImageEncoderException;
}

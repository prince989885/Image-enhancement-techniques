import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Image;
import java.awt.geom.*;

public class rotate
{
int iw,ih;
BufferedImage bimage=null,simage=null;
public rotate()
{}

public Image rimage(Image img)
{
GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
try
{
int transparency=Transparency.OPAQUE;
GraphicsDevice gs=ge.getDefaultScreenDevice();
GraphicsConfiguration gc=gs.getDefaultConfiguration();
bimage=gc.createCompatibleImage(img.getWidth(null),img.getHeight(null),transparency);
}catch(Exception e){}

if(bimage==null)
{
int type=BufferedImage.TYPE_INT_RGB;
bimage=new BufferedImage(img.getWidth(null),img.getHeight(null),type);
}
Graphics g=bimage.createGraphics();
g.drawImage(img,0,0,null);
g.dispose();

AffineTransform tx=new AffineTransform();
tx.rotate(0.125,bimage.getWidth()/2,bimage.getHeight()/2);

AffineTransformOp op=new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
bimage=op.filter(bimage,null);

return Toolkit.getDefaultToolkit().createImage(bimage.getSource());
}
}
 
        

import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class grey
{
int outpixels[];
public grey()
{}
public int[] greyimage(int[] inpixels,int width,int height)
{
outpixels=new int[width*height];
for(int i=0;i<(width*height);i++)
{
int p=inpixels[i];
int r=0xff&(p>>16);
int g=0xff&(p>>8);
int b=0xff&(p);
int y=(int)(.33*r+.56*g+.11*b);          //grayscale image = ( (0.3 * R) + (0.59 * G) + (0.11 * B) ).

r=(int)y;
g=(int)y;
b=(int)y;
outpixels[i]=(255<<24)|(r<<16)|(g<<8)|b;
}
return outpixels;
}
}
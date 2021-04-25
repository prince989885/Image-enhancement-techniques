import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class Negator
{
int outpixels[];
public void Negator()
{}

public int[] negatepixels(int inpixels[],int width,int height)
{
outpixels=new int[width*height];
for(int i=0;i<(width*height);i++)
{
int p=inpixels[i];

int r=0xff&(p>>16);
r=(int)255-r;

int g=0xff&(p>>8);
g=(int)255-g;

int b=0xff&(p);
b=(int)255-b;
outpixels[i]=(255<<24)|(r<<16)|(g<<8)|b;
}
return outpixels;
}
}
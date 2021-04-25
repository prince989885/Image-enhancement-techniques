import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class thresholding
{
int outpixels[];
int thr;

public thresholding(int th)
{
thr=th;
}

public int[]  tsimage(int[] inpixels,int width,int height)
{
  outpixels=new int[width*height];
  for(int i=0;i<(width*height);i++)
  {
     int p=inpixels[i];
     int r=0xff&(p>>16);
     if(r>=thr)
       r=255;
     else
       r=0;
     int g=0xff&(p>>8);
     if(g>=thr)
       g=255;
     else
       g=0;
     int b=0xff&(p);
     if(b>=thr)
       b=255;
     else
       b=0;
      outpixels[i]=(255<<24)|(r<<16)|(g<<8)|b;
   }
   return outpixels;
}
}    
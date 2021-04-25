import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class LevelSlicing
{
int vs1,vs2,vs3,vs4;
public int outpixels[];

public LevelSlicing(int sd1,int sd2,int sd3,int sd4)
{
vs1=sd1;
vs2=sd2;
vs3=sd3;
vs4=sd4;
}

public int[] slicedImage(int[] inpixels,int width,int height)
{
outpixels=new int[width*height];
for(int i=0;i<(width*height);i++)
{
int p=inpixels[i];
int r=0xff&(p>>16);

if((r>=vs1)&&(r<=vs2))
r=vs4;
else
r=vs3;

int g=0xff&(p>>8);
if((g>=vs1)&&(g<=vs2))
g=vs4;
else
g=vs3;

int b=0xff&(p);
if((b>=vs1)&&(b<=vs2))
b=vs4;
else
b=vs3;

outpixels[i]=(255<<24)|(r<<16)|(g<<8)|b;
}
return outpixels;
}
}
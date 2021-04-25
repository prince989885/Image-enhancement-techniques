import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class contrast
{
int r1,s1,r2,s2;
int slp1,slp2,slp3;
public int outpixels[];

public contrast(int sd1,int sd2,int sd3,int sd4)
{
r1=sd1;
r2=sd2;
s1=sd3;
s2=sd4;

slp1=(int)(s1/r1);
slp2=(int)((s2-s1)/(r2-r1));
slp3=(int)((255-s2)/(255-r2));
System.out.println("the value of spl1 is" +slp1);
System.out.println("the value of spl2 is" +slp2);
System.out.println("the value of spl3 is" +slp3);
}

public int[] csimage(int[] inpixels,int width,int height)
{
outpixels=new int[width*height];
for(int i=0;i<(width*height);i++)
{
int p=inpixels[i];
int r=0xff&(p>>16);
if((r>0)&&(r<=r1))
r=(int)(slp1*r);

else if((r>r1)&&(r<r2))
r=(int)(slp2*(r-r1)+s1);

else if((r>=r2)&&(r<=255))
r=(int)(slp3*(r-r2)+s2);

int g=0xff&(p>>8);

if((g>=0)&&(g<=r1))
g=(int)(slp1*g);

else if((g>r1)&&(g<r2))
g=(int)(slp2*(g-r1)+s1);

else if((g>=r2)&&(g<=255))
g=(int)(slp3*(g-r2)+s2);

int b=0xff&(p);

if((b>=0)&&(b<=r1))
b=(int)(slp1*b);

else if((b>r1)&&(b<r2))
b=(int)(slp2*(b-r1)+s1);

else if((b>=r2)&&(b<=255))
b=(int)(slp3*(b-r2)+s2);

outpixels[i]=(255<<24)|(r<<16)|(g<<8)|b;
}

return outpixels;
}
}

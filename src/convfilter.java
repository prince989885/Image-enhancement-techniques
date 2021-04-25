import java.awt.*;
import java.awt.Component;
import java.awt.image.*;
class convfilter implements ImageObserver
{
int[] oldpixels,newpixels;
int w,h;
PixelGrabber pg;
MemoryImageSource mis;
int index=0;
int i00,i01,i02,i10,i11,i12,i20,i21,i22;
int p00,p01,p02,p10,p11,p12,p20,p21,p22;
int w00,w01,w02,w10,w11,w12,w20,w21,w22;

public convfilter(int[] matrix)
{
w00=matrix[0];
w01=matrix[1];
w02=matrix[2];
w10=matrix[3];
w11=matrix[4];
w12=matrix[5];
w20=matrix[6];
w21=matrix[7];
w22=matrix[8];
}
public boolean imageUpdate(Image img,int infoflags,int x,int y,int width,int height)
{
w=width;
h=height;
if(w!=-1&&h!=-1)
{
return false;
}
return true;
}

public ImageProducer filteredImage(Image source,double mult)
{
boolean success;
w=source.getWidth(null);
h=source.getHeight(null);
oldpixels=new int[w*h];
newpixels=new int[w*h];
pg=new PixelGrabber(source.getSource(),0,0,w,h,oldpixels,0,w);
try
{
success=pg.grabPixels(0);
}catch(Exception e)
{
System.out.println("Error in grabbing"+e);
}
index=w+1;
for(int y=1;y<h-1;y++)
{
calc3x3offsets();
for(int x=1;x<w-1;x++)
{
p00=oldpixels[i00];
p01=oldpixels[i01];
p02=oldpixels[i02];
p10=oldpixels[i10];
p11=oldpixels[i11];
p12=oldpixels[i12];
p20=oldpixels[i20];
p21=oldpixels[i21];
p22=oldpixels[i22];

int newRed=applyWeights(16,mult);
int newGreen=applyWeights(8,mult);
int newBlue=applyWeights(0,mult);

newpixels[index++]=255<<24|newRed|newGreen|newBlue;

i00++;
i01++;
i02++;
i10++;
i11++;
i12++;
i20++;
i21++;
i22++;
}
index+=2;
}
 mis=new MemoryImageSource(w,h,newpixels,0,w);
return mis;
}

final void calc3x3offsets()
{
i00=index-w-1;
i01=i00+1;
i02=i00+2;
i10=index-1;
i11=index;
i12=index+1;
i20=index+w-1;
i21=i20+1;
i22=i20+2;
}

final int applyWeights(int shift,double multfactor)
{
double total=0;
total+=((p00>>shift)&0xFF)*w00;
total+=((p01>>shift)&0xFF)*w01;
total+=((p02>>shift)&0xFF)*w02;
total+=((p10>>shift)&0xFF)*w10;
total+=((p11>>shift)&0xFF)*w11;
total+=((p12>>shift)&0xFF)*w12;
total+=((p20>>shift)&0xFF)*w20;
total+=((p21>>shift)&0xFF)*w21;
total+=((p22>>shift)&0xFF)*w22;

total=total*multfactor;
if(total>255)
total=255;
if(total<0)
total=0;
return((int)total)<<shift;
}
}


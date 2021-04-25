import java.awt.*;
import java.awt.image.*;

public class he
{
int dest[];
public int[] equalize(int src[],int w,int h)
{
dest=new int[w*h];
int result;
float a1,a2,a3;

int array[]=new int[256];
int rgb;

for(int i=0;i<256;i++)
array[i]=0;
for(int i=0;i<w*h;i++)
{
rgb=src[i]&0x000000ff;
array[rgb]++;
}

for(int i=0;i<w*h;i++)
{
rgb=src[i]&0x000000ff;
a3=0;

for(int j=0;j<(rgb+1);j++)
{
a1=(float)array[j];
a2=(float)(w*h);
a3=a3+(a1/a2);
}

result=(int)(a3*255);

if(result>255)
result=255;

if(result<0)
result=0;

dest[i]=0xff000000|(result+(result<<16)+(result<<8));
}
return dest;
}

}



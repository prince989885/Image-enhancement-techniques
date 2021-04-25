
import java.applet.*;
import java.awt.*;
import java.awt.Event;
import java.awt.image.*;

public class pck extends myframe
{
Insets insets;
Image src,dst;
ImageProducer filtered;
String filename=null;
double time;
String msg=" ";
public pck()
{}

public void paint(Graphics g)
{
if(filename!=null)
g.drawImage(dst,insets.left+10,insets.top+50,this);  //10 mm fromleft and 50 mm from top

}

public static void main(String a[])
{
pck f=new pck();
f.show();
}
public void view(String out)
{
Image im=Toolkit.getDefaultToolkit().getImage(out);
MediaTracker m=new MediaTracker(this);
m.addImage(im,0);
try{
   
m.waitForID(0);
}catch(Exception e)
{
System.out.println("Image Loading Error!"+e);
System.exit(0);
}
dst=im;
repaint();
}
public void abc()
{
if(filename!=null)
{
src=Toolkit.getDefaultToolkit().getImage(filename);
MediaTracker mt=new MediaTracker(this);
mt.addImage(src,0);
try
{
mt.waitForID(0);
}catch(Exception e)
{
System.out.println("Image Loading Error!"+e);
System.exit(0);
}
dst=src;
msg=" ";
repaint();
}
}



public void addNotify()
{
super.addNotify();
insets=getInsets();
setBounds(50,50,750+insets.left,500+insets.top);
}

public void open()
{
FileDialog fd=new FileDialog(this,"Open",FileDialog.LOAD);
fd.setVisible(true);
String dir=fd.getDirectory();
String fname=fd.getFile();
filename=dir+fname;
abc();
}

public void org()
{
if(filename!=null)
{
dst=src;
msg=" ";
repaint();
}
}
public void rot()
{
if(filename!=null)
{
rotate rt=new rotate();
dst=rt.rimage(dst);
repaint();
}
}
public void gs()
{
if(filename!=null)
{
int iw,ih;
int pixels[];
int out[];
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
pg.grabPixels();
}catch(InterruptedException e){};
grey g=new grey();
out=g.greyimage(pixels,iw,ih);
dst=createImage(new MemoryImageSource(iw,ih,out,0,iw));
repaint();
}
}
public void neg()
{
if(filename!=null)
{
int image_width,image_height;
int pixels[];
int out[];
image_width=dst.getWidth(null);
image_height=dst.getHeight(null);
pixels=new int[image_width*image_height];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,image_width,image_height,pixels,0,image_width);
pg.grabPixels();
}catch(InterruptedException e){};
Negator negator=new Negator();
out=negator.negatepixels(pixels,image_width,image_height);
dst=createImage(new MemoryImageSource(image_width,image_height,out,0,image_width));
repaint();
}
}
public void ls()
{
if(filename!=null)
{
int iw,ih;
int j1,j2,j3,j4;
int pixels[];
int out[];
int errflag=0;
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
pg.grabPixels();
}catch(InterruptedException e){};

mydialog1 md=new mydialog1(this,"Level Slicing",true,1);
md.setSize(250,180);
md.setVisible(true);

if((md.val1!=null)&&(md.val2!=null)&&(md.val3!=null)&&(md.val4!=null))
{
j1=Integer.parseInt(md.val1);
if((j1<0)||(j1>255))
errflag=1;

j2=Integer.parseInt(md.val2);
if((j2<0)||(j2>255))
errflag=1;

j3=Integer.parseInt(md.val3);
if((j3<0)||(j3>255))
errflag=1;

j4=Integer.parseInt(md.val4);
if((j4<0)||(j4>255))
errflag=1;

if(errflag==0)
{
LevelSlicing level=new LevelSlicing(j1,j2,j3,j4);
out=level.slicedImage(pixels,iw,ih);
dst=createImage(new MemoryImageSource(iw,ih,out,0,iw));
}
else
{
errdialog1 ed=new errdialog1(this,"Error!",true);
ed.setSize(200,100);
ed.setVisible(true);
}
}
repaint();
}
}

public void cs()
{
if(filename!=null)
{
int iw,ih;
int j1,j2,j3,j4;
int pixels[];
int out[];
int errflag=0;
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
pg.grabPixels();
}catch(InterruptedException e){};

mydialog1 md=new mydialog1(this,"Contrast Stretching",true,2);
md.setSize(250,180);
md.setVisible(true);

if((md.val1!=null)&&(md.val2!=null)&&(md.val3!=null)&&(md.val4!=null))
{
j1=Integer.parseInt(md.val1);
if((j1<0)||(j1>255))
errflag=1;

j2=Integer.parseInt(md.val2);
if((j2<0)||(j2>255))
errflag=1;

j3=Integer.parseInt(md.val3);
if((j3<0)||(j3>255))
errflag=1;

j4=Integer.parseInt(md.val4);
if((j4<0)||(j4>255))
errflag=1;

if(errflag==0)
{
contrast c=new contrast(j1,j2,j3,j4);
out=c.csimage(pixels,iw,ih);
dst=createImage(new MemoryImageSource(iw,ih,out,0,iw));
}
else
{
errdialog1 ed=new errdialog1(this,"Error!",true);
ed.setSize(200,100);
ed.setVisible(true);
}
}
repaint();
}
}
public void hist()
{
if(filename!=null)
{
int iw,ih;
int pixels[];
int out[];
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
pg.grabPixels();
}catch(InterruptedException e){};
Histogram h=new Histogram(this,"Histogram");
h.set(pixels,iw,ih);
h.setSize(320,480);
h.setVisible(true);
}
}
public void heq()
{
if(filename!=null)
{
int iw,ih;
int pixels[];
int out[];
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
pg.grabPixels();
}catch(InterruptedException e){};
he heq=new he();
out=heq.equalize(pixels,iw,ih);
dst=createImage(new MemoryImageSource(iw,ih,out,0,iw));
repaint();
}
}
public void thr()
{
if(filename!=null)
{
int iw,ih;
int pixels[];
int out[];
int j1;
iw=dst.getWidth(null);
ih=dst.getHeight(null);
pixels=new int[iw*ih];
try
{
PixelGrabber pg=new PixelGrabber(dst,0,0,iw,ih,pixels,0,iw);
/*
  public PixelGrabber(Image img,int x,int y,int w,int h,int pix[],int off,int scansize)

x - the x coordinate of the upper left corner of the rectangle of pixels to retrieve from the image, relative to the default (unscaled) size of the image
y - the y coordinate of the upper left corner of the rectangle of pixels to retrieve from the image
w - the width of the rectangle of pixels to retrieve
h - the height of the rectangle of pixels to retrieve
pix - the array of integers which are to be used to hold the RGB pixels retrieved from the image
off - the offset into the array of where to store the first pixel
scansize - the distance from one row of pixels to the next in the array
*/
pg.grabPixels();
}catch(InterruptedException e){};

mydialog1 md=new mydialog1(this,"Thresholding",true,0);
md.setSize(200,130);
md.setVisible(true);

if(md.val1!=null)
{
j1=Integer.parseInt(md.val1);
if((j1<0)||(j1>255))
{
errdialog1 ed=new errdialog1(this,"Error!",true);
ed.setSize(200,100);
ed.setVisible(true);
}
else
{
thresholding level=new thresholding(j1);
out=level.tsimage(pixels,iw,ih);
dst=createImage(new MemoryImageSource(iw,ih,out,0,iw));
}
}
//MODIFIED
repaint();
}
}

public void lp()
{
if(filename!=null)
{
int[] filter={1,1,1,1,1,1,1,1,1};
double multiplier=0.11111;
convfilter cv=new convfilter(filter);
filtered=cv.filteredImage(dst,multiplier);
dst=createImage(filtered);
msg=" ";
repaint();
}
}
public void bh()
{
  if(filename!=null)
  {

int errflag=0;
   String in=filename;
try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
  String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw.jpg";
   int order,bias;
   float rad;
       mydialog md=new mydialog(this,"Butter Worth High Pass",true,1); 
     md.setSize(250,180); 
     md.setVisible(true);
     System.out.println("b4 values");   
   if((md.val1!=null)&&(md.val2!=null)&&(md.val3!=null))
    {
     order=Integer.parseInt(md.val1);
     bias=Integer.parseInt(md.val2);
     rad= Float.valueOf(md.val3).floatValue();
System.out.println("order,bias,rad");         
     if(errflag==0)
    {
System.out.println("order,bias,rad2");         
      Bwhp bw=new Bwhp();
      time=bw.bw(in,save,order,rad,bias);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}
public void bl()
{
  if(filename!=null)
  {
int errflag=0;
   String in=filename;
try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
 String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw1.jpg";
   int order;
   float rad;
       mydialog md=new mydialog(this,"Butter Worth Low Pass",true,2); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     order=Integer.parseInt(md.val1);
     rad= Float.valueOf(md.val2).floatValue();
         
     if(errflag==0)
    {
      bwlp bw=new bwlp();
      time=bw.bl(in,save,order,rad);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}
public void hp()
{
if(filename!=null)
{
int[] filter={-1,-1,-1,
                  -1,8,-1,
				      -1,-1,-1};
double multiplier=1;
convfilter cv=new convfilter(filter);
filtered=cv.filteredImage(dst,multiplier);
dst=createImage(filtered);
msg=" ";
repaint();
}
}

public void hb()
{
if(filename!=null)
{
int[] filter={-1,-1,-1,
					-1,9,-1,
							-1,-1,-1};
double multiplier=1;
convfilter cv=new convfilter(filter);
filtered=cv.filteredImage(dst,multiplier);
dst=createImage(filtered);
msg=" ";
repaint();
}
}
public void ilp()
{
  if(filename!=null)
  {
    int errflag=0;
   String in=filename;
  try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
   String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw2.jpg";
    float rad;
    mydialog md=new mydialog(this,"Ideal Low Pass",true,3); 
     md.setSize(250,180); 
     md.setVisible(true);

   if(md.val1!=null)
    {
        rad= Float.valueOf(md.val1).floatValue();
         
     if(errflag==0)
    {
      ilp bw=new ilp();
      time=bw.il(in,save,rad);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}
public void ihp()
{
  if(filename!=null)
  {
int errflag=0;
   String in=filename;
  try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
 String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw3.jpg";
   int bias;
   float rad;
       mydialog md=new mydialog(this,"Ideal High Pass",true,4); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     bias=Integer.parseInt(md.val1);
     rad= Float.valueOf(md.val2).floatValue();
         
     if(errflag==0)
    {
      ihp bw=new ihp();
      time=bw.ih(in,save,rad,bias);
         msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}
public void mf()
{
  if(filename!=null)
  {
int errflag=0;
   String in=filename;
 String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw4.jpg";
   int width,height;
       mydialog md=new mydialog(this,"Mean Filter",true,5); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     width=Integer.parseInt(md.val1);
     height=Integer.parseInt(md.val2);
         
     if(errflag==0)
    {
      MeanFilter bw=new MeanFilter();
      time=bw.mf(in,save,width,height);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}

public void med()
{
try{
  if(filename!=null)
  {
int errflag=0;
   String in=filename;
  try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
 String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw5.jpg";
   int width,height;
       mydialog md=new mydialog(this,"Median Filter",true,6); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     width=Integer.parseInt(md.val1);
     height=Integer.parseInt(md.val2);
         
     if(errflag==0)
    {
      MedianFilter bw=new MedianFilter();
      time=bw.mf(in,save,width,height);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}catch(ImagingOpException e){
}
}

public void max()
{
  if(filename!=null)
  {
int errflag=0;
   
try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
String in=filename;
   String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw6.jpg";
   int width,height;
       mydialog md=new mydialog(this,"Max Filter",true,7); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     width=Integer.parseInt(md.val1);
     height=Integer.parseInt(md.val2);
     
     if(errflag==0)
    {
      MaxFilter bw=new MaxFilter();
     time=bw.max(in,save,width,height);
      msg="Filtering finished in "+time+" seconds.";
       view(save);
    }
}
repaint();
}    
}

public void min()
{
  if(filename!=null)
  {
int errflag=0;
   String in=filename;
try{
   pck p=new pck();
    check();
   }catch (Exception e){ }
 String save="C:\\Users\\91926\\Documents\\NetBeansProjects\\image engancement\\images\\bw7.jpg";
   
   int width,height;
     mydialog md=new mydialog(this,"Min Filter",true,8); 
     md.setSize(250,180); 
     md.setVisible(true);

   if((md.val1!=null)&&(md.val2!=null))
    {
     width=Integer.parseInt(md.val1);
     height=Integer.parseInt(md.val2);
         
     if(errflag==0)
    {
      MinFilter bw=new MinFilter();
      time=bw.min(in,save,width,height);
      msg="Filtering finished in "+time+" seconds.";
      view(save);
    }
}
repaint();
}    
}

public void check()throws Exception
{
             ImageDecoder input = ImageFile.createImageDecoder(filename);
                  System.out.println("check");
                BufferedImage inputImage = input.decodeAsBufferedImage();
                  System.out.println("check1");
             if (inputImage.getType() != BufferedImage.TYPE_BYTE_GRAY) {
                     System.out.println("check");
                    errdialog ed=new errdialog(this,"Error!",true,1);
                   ed.setSize(200,100);
                  ed.setVisible(true);}
}

    
}
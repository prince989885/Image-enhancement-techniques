import java.awt.*;
import java.applet.*;
import java.awt.image.*;
import java.awt.event.*;

public class Histogram extends Dialog implements WindowListener
{
int max_hist=0;
int hist[]=new int[256];
public Histogram(Frame p,String title)
{
super(p,title,false);
//setSize(300,200);
addWindowListener(this);
}

public void set(int[] inpixels,int w,int h)
{
for(int i=0;i<h*w;i++)
{
int p=inpixels[i];
int r=0xff&(p>>16);
int g=0xff&(p>>8);
int b=0xff&(p);

int y=(int)(.33*r+.56*g+.11*b);
hist[y]++;
}

for(int i=0;i<256;i++)
{
if(hist[i]>max_hist)
max_hist=hist[i];
}
}
public void paint(Graphics g)
{
int x=25;
g.setColor(Color.blue);
g.drawLine(25,250,280,250);
g.setColor(Color.red);
for(int i=0;i<256;i++)
{
int p=200*hist[i]/max_hist;
int y=250-p;
g.fillRect(x,y,1,p);
/*
x	The x-coordinate of the upper-left corner of the rectangle	
y	The y-coordinate of the upper-left corner of the rectangle	
width	The width of the rectangle, in pixels	
height	The height of the rectangle, in pixels
*/
x++;
}
}


public void windowClosed(WindowEvent we)
{}

public void windowDeiconified(WindowEvent we)
{}

public void windowIconified(WindowEvent we)
{}

public void windowActivated(WindowEvent we)
{}

public void windowDeactivated(WindowEvent we)
{}

public void windowOpened(WindowEvent we)
{}

public void windowClosing(WindowEvent we)
{
dispose();
}

}

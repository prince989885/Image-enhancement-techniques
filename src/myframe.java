//V
import java.awt.*;
import java.awt.event.*;

abstract class myframe extends Frame implements ActionListener,WindowListener
{
MenuItem op,cls;
MenuItem org,lp,hp,hb,bh,bl,ih,il,mf,mef,maxf,minf,rf,mmsef,neg,gs,rot,ls,cs,thr,hist,heq;

public myframe()
{
super("Image Inhancement Techniques -By 18BCE183|18BCE142");
MenuBar mb1=new MenuBar();
setMenuBar(mb1);
Menu file=new Menu("File");
Menu sd=new Menu("SPATIAL FILTERS");
Menu fd=new Menu("FREQUENCY DOMAIN FILTERS");

mb1.add(file);
mb1.add(sd);
mb1.add(sd);
mb1.add(fd);
//mb1.add(test);

file.add(op=new MenuItem("Open"));
file.add(org=new MenuItem("Original Image"));
file.add(cls=new MenuItem("Exit"));
sd.add(neg=new MenuItem("Negate"));
sd.add(gs=new MenuItem("Grey Scale"));
sd.add(rot=new MenuItem("Rotate"));
sd.add(ls=new MenuItem("Level Slicing"));
sd.add(mf=new MenuItem("Mean Filter"));
sd.add(mef=new MenuItem("Median Filter"));
sd.add(maxf=new MenuItem("Max Filter"));
sd.add(minf=new MenuItem("Min Filter"));
sd.add(cs=new MenuItem("Constrast Stretching"));
sd.add(thr=new MenuItem("Thresholding"));
sd.add(hist=new MenuItem("Histogram"));
sd.add(heq=new MenuItem("Histogram Equalizing"));
fd.add(hb=new MenuItem("High Boost Filter"));
fd.add(lp=new MenuItem("Low Pass Filter"));
fd.add(hp=new MenuItem("High Pass Filter"));
fd.add(ih=new MenuItem("Ideal High Pass Filter"));
fd.add(il=new MenuItem("Ideal Low Pass Filter"));
fd.add(bh=new MenuItem("Butter Worth High Pass Filter"));
fd.add(bl=new MenuItem("Butter Worth Low Pass Filter"));

op.addActionListener(this);
cls.addActionListener(this);
org.addActionListener(this);
lp.addActionListener(this);
hp.addActionListener(this);
hb.addActionListener(this);
bh.addActionListener(this);
bl.addActionListener(this);
ih.addActionListener(this);
il.addActionListener(this);
mf.addActionListener(this);
mef.addActionListener(this);
maxf.addActionListener(this);
minf.addActionListener(this);
neg.addActionListener(this);
gs.addActionListener(this);
rot.addActionListener(this);
ls.addActionListener(this);
cs.addActionListener(this);
thr.addActionListener(this);
hist.addActionListener(this);
heq.addActionListener(this);
addWindowListener(this);
}

public void actionPerformed(ActionEvent ae)
{
String s=ae.getActionCommand();

if(ae.getSource() instanceof MenuItem)
{
if(s.equals("Exit"))
System.exit(0);

else

if(s.equals("Open"))
open();

else

if(s.equals("Original Image"))
org();

else if(s.equals("Low Pass Filter"))
lp();

else if(s.equals("High Pass Filter"))
hp();

else if(s.equals("High Boost Filter"))
hb();
else if(s.equals("Butter Worth High Pass Filter")) 
bh();

else if(s.equals("Butter Worth Low Pass Filter"))
bl();

else if(s.equals("Ideal Low Pass Filter"))
ilp();

else if(s.equals("Ideal High Pass Filter"))
ihp();

else if(s.equals("Mean Filter"))
mf();

else if(s.equals("Median Filter"))
med();

else if(s.equals("Max Filter"))
max();

else if(s.equals("Min Filter"))
min();
else if(s.equals("Negate"))
neg();
else if(s.equals("Grey Scale"))
gs();
else if(s.equals("Rotate"))
rot();
else if(s.equals("Level Slicing"))
ls();
else if(s.equals("Constrast Stretching"))
cs();
else if(s.equals("Thresholding"))
thr();
else if(s.equals("Histogram"))
hist();
else if(s.equals("Histogram Equalizing"))
heq();


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
System.exit(0);
}

abstract void open();
abstract void org();
abstract void lp();
abstract void hp();
abstract void hb();
abstract void bh();
abstract void bl();
abstract void ilp();
abstract void ihp();
abstract void mf();
abstract void med();
abstract void max();
abstract void min();
abstract void neg();
abstract void ls();
abstract void rot();
abstract void gs();
abstract void cs();
abstract void thr();
abstract void hist();
abstract void heq();
}


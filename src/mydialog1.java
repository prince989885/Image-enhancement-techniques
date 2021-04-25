import java.awt.*;
import java.awt.event.*;

class mydialog1 extends Dialog implements ActionListener
{
TextField thr,ls1,ls2,ls3,ls4;
Label m1,a1;
Button ok,can;
String val1=null,val2=null,val3=null,val4=null;
int t;
public mydialog1(Frame p,String title,boolean m,int i)
{
super(p,title,m);
t=i;
Panel p1=new Panel();
Panel p2=new Panel();
Panel p3=new Panel();

setLayout(new BorderLayout());
ok=new Button("Ok");
can=new Button("Cancel");

p2.setLayout(new FlowLayout());
p3.setLayout(new FlowLayout());
//MODIFIED
p3.add(ok);
p3.add(can);

if(i==1)
a1=new Label("Enter Min and Max values of Output:");
else
if(i==2)
a1=new Label("Enter range of output Grey levels:");

if((i==1)||(i==2))
{
m1=new Label("Enter range of Grey levels to be changed:");

ls1=new TextField(4);

ls1.setText("100");
ls2=new TextField(4);

ls2.setText("200");
ls3=new TextField(4);

ls3.setText("0");
ls4=new TextField(4);

ls4.setText("255");

p1.add(m1);
p2.add(ls1);
p2.add(ls2);
p2.add(a1);
p2.add(ls3);
p2.add(ls4);
}
else if(i==0)
{
m1=new Label("Enter Threshold value(0-255):");
ls1=new TextField(4);
p1.add(m1);
p2.add(ls1);
}
else if(i==3){
m1=new Label("Enter Diameter: ");
ls1=new TextField(4);
p1.add(m1);
p2.add(ls1);
}

add("North",p1);
add("Center",p2);
add("South",p3);

ok.addActionListener(this);
can.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{
String s=ae.getActionCommand();

if(ae.getSource() instanceof Button)
{
if(s.equals("Ok"))							
{
val1=ls1.getText();
if((t==1)||(t==2))
{
val2=ls2.getText();
val3=ls3.getText();
val4=ls4.getText();
}
dispose();
}
else
if(s.equals("Cancel"))
dispose();
}
}

}

import java.awt.*;
import java.awt.event.*;

class mydialog extends Dialog implements ActionListener
{
TextField thr,ls1,ls2,ls3,ls4;
Label m1,a1,a2;
Button ok,can;
String val1=null,val2=null,val3=null,val4=null;
int t;
public mydialog(Frame p,String title,boolean m,int i)
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
{
  
a1=new Label("Enter Bias Value:");
a2=new Label("Enter Radius :");
m1=new Label("Enter Order");
ls1=new TextField(4);
ls1.setText("1");
ls2=new TextField(4);
ls2.setText("200");
ls3=new TextField(4);
ls3.setText("0.0");
p1.add(m1);
p1.add(ls1);
p2.add(a1);
p2.add(ls2);
p2.add(a2);
p2.add(ls3);
}
else if(i==2)
{
System.out.println("i==2");   
a2=new Label("Enter Radius :");
m1=new Label("Enter Order");
ls1=new TextField(4);
ls1.setText("1");
ls2=new TextField(4);
ls2.setText("0.0");
p1.add(m1);
p1.add(ls1);
p2.add(a2);
p2.add(ls2);
}
else if(i==3)
{
a2=new Label("Enter Radius :");
ls1=new TextField(4);
ls1.setText("0.0");
p1.add(a2);
p1.add(ls1);
}
else if(i==4)
{
a2=new Label("Enter Radius :");
m1=new Label("Enter Bias");
ls1=new TextField(4);
ls1.setText("200");
ls2=new TextField(4);
ls2.setText("0.0");
p1.add(m1);
p1.add(ls1);
p2.add(a2);
p2.add(ls2);
}
else if(i==5||i==6||i==7||i==8)
{
a2=new Label("Enter Neighbourhood Dimensions :");
//m1=new Label("Enter height");
ls1=new TextField(4);
ls1.setText("3");
ls2=new TextField(4);
ls2.setText("3");
p1.add(a2);
p2.add(ls1);
//p2.add(m1);
p2.add(ls2);
}
else if(i==9||i==10)
{
a2=new Label("Enter Neighbourhood dimensions :");
if(i==9)
   m1=new Label("Enter Rank :");
else
  m1=new Label("Enter Noise Variance");
ls1=new TextField(4);
ls1.setText("3");
ls2=new TextField(4);
ls2.setText("3");
ls3=new TextField(4);
ls3.setText("0");
p1.add(a2);
p2.add(ls1);
p2.add(ls2);
p2.add(m1);
p2.add(ls3);
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
if(t==1||t==2||t==4||t==5||t==6||t==7||t==8||t==9||t==10)
{
val1=ls1.getText();
val2=ls2.getText();
}
if(t==1||t==9||t==10)
val3=ls3.getText();
if(t==3)
val1=ls1.getText();
dispose();
}
else
if(s.equals("Cancel"))
dispose();
}
}

}

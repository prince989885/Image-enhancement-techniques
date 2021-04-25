import java.awt.*;
import java.awt.event.*;

class errdialog extends Dialog implements ActionListener
{
Label m1,a1;
Button ok;

public errdialog(Frame p,String title,boolean m,int i)
{
super(p,title,m);
Panel  p1=new Panel();
Panel  p2=new Panel();

setLayout(new BorderLayout());
if(i==1)
      m1=new Label("Image should be 8-bit grey scale."); 
else
      m1=new Label("Rank should be from 1-9"); 
     ok=new Button("Ok");
p1.add(m1);
p2.add(ok);

add("North",p1);
add("Center",p2);

ok.addActionListener(this);
}

public void actionPerformed(ActionEvent ae)
{
String s=ae.getActionCommand();
if(ae.getSource() instanceof Button)
{
if(s.equals("Ok"))
dispose();
}
}

}

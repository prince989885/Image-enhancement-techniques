import java.awt.*;
import java.awt.event.*;

class errdialog1 extends Dialog implements ActionListener
{
Label m1,a1;
Button ok;
    private Frame p;
    private String error;
public errdialog1(Frame p,String title,boolean m)
{
super(p,title,m);
Panel  p1=new Panel();
Panel  p2=new Panel();

setLayout(new BorderLayout());
m1=new Label("Values should be with in 0-255");
ok=new Button("Ok");
p1.add(m1);
p2.add(ok);

add("North",p1);
add("Center",p2);

ok.addActionListener(this);
}

public errdialog1(Frame p, String error, boolean b, int i) {
        super(p,error,b);
        this.error = error;
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

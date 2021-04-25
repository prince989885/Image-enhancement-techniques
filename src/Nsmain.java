import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

class Nsmain extends JFrame implements ActionListener
{
     Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
     public Nsmain()
     {
        JPanel jp1=new JPanel();
        JPanel jp3=new JPanel();
        JMenuBar bar=new JMenuBar();
        JMenuItem tmp;
        JMenu menus1 = new JMenu("IMAGE FILTERS");
        menus1.setMnemonic('N');
        menus1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
         JMenuItem menu2=new JMenuItem("Image Filtering Techniques");
        menu2.setMnemonic('F');
        menu2.addActionListener(this);
        menu2.setActionCommand("Image Filtering Techniques");
        menus1.add(menu2);


        JMenu menus4=new JMenu("EXIT");
        menus4.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        menus4.setMnemonic('E');
        tmp=new JMenuItem("CLOSE");
        tmp.addActionListener(this);
        tmp.setActionCommand("CLOSE");tmp.setMnemonic('C');
         menus4.add(tmp);

        bar.add(menus1);bar.add(menus4);

        Font font=new Font("Serif",Font.BOLD,30);
        JLabel sibar=new JLabel("IMAGE FILTERS");
        sibar.setFont(font);sibar.setForeground(Color.black);
        jp1.setBackground(Color.gray);
        jp1.add(sibar);jp1.setBackground(Color.lightGray);
        jp1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jp3.add(bar);jp3.setBackground(Color.gray);
        //jp1.setBackground(new Color(250,250,230));

        JPanel jp=new JPanel();
        jp.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();

        ImageIcon ii= new ImageIcon("Lil.jpg");
        JLabel jl = new JLabel(ii,JLabel.CENTER);
        JLabel jl3 = new JLabel(ii);
        JLabel jl4 = new JLabel(ii);
        JLabel jl1 = new JLabel(ii,JLabel.LEFT);
        JLabel jl2 = new JLabel(ii,JLabel.RIGHT);
        jp.add(jl1);jp.add(jl3);jp.add(jl);jp.add(jl4);jp.add(jl2);
        jp.setBackground(Color.black);

        gbc.fill=GridBagConstraints.BOTH;
        gbc.gridx=2;gbc.gridy=0;gbc.gridwidth=1;gbc.gridheight=1;

        Font fo=new Font("Serif",Font.BOLD,27);
        JLabel jlll=new JLabel("WELCOME TO IMAGE FILTERS");
        jlll.setFont(fo);
        jlll.setForeground(new Color(100,200,200));
        jp.add(jlll,gbc);

        Font fo1=new Font("Serif",Font.BOLD,27);
        JLabel jll=new JLabel(" WELCOME TO IMAGE FILTERS");
        jll.setForeground(Color.gray);
        jll.setFont(fo1);

        gbc.gridx=2;gbc.gridy=0;gbc.gridwidth=1;gbc.gridheight=1;
        jp.add(jll,gbc);

        JPanel jp2=new JPanel();
        jp2.setBackground(new Color(250,250,230));
        jp2.setLayout(new FlowLayout(FlowLayout.CENTER,200,5));
        jp2.add(jp1);jp2.add(jp3);jp2.add(jp);

        Container c=getContentPane();
        c.add(jp2);
        addWindowListener(new WindowAdapter()
        {
         public void windowClosing(WindowEvent w)
         {
            System.exit(0);
         }
        });


     }
     public void actionPerformed(ActionEvent ae)
     {
          String str=ae.getActionCommand().trim();
          if(str.equals("Image Filtering Techniques"))
          {
             pck pc=new pck();
             pc.setSize(d.width,d.height);
             pc.setVisible(true);
          }
          else
          if(str.equals("Registration"))
          {
             //Newuser sp=new Newuser();
             //sp.setSize(d.width,d.height);
             //sp.setVisible(true);
          }
          else
          if(str.equals("CLOSE"))
          {
              System.exit(0);
          }
     }
    public static void main(String args[])
    {
      Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
      Nsmain sb = new Nsmain();
      sb.setSize(d.width,d.height);
      sb.setVisible(true);
    }

}



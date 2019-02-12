import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Options extends JFrame implements ItemListener, ActionListener {


    JPanel panel = new JPanel();
    JButton play = new JButton("Play");
    JButton back = new JButton("Back");

    JRadioButton lv1 = new JRadioButton("Easy");
    JRadioButton lv2 = new JRadioButton("Medium");
    JRadioButton lv3 = new JRadioButton("Hard");
    JRadioButton tr1 = new JRadioButton("500x500");
    JRadioButton tr2 = new JRadioButton("600x600");
    JRadioButton tr3 = new JRadioButton("700x700");

    public static int delay;

    public static int borderXsize;
    public static int borderYsize;

    public int getDelay() {
        return this.delay;
    }

    public int getBorderXsize()
    {
        return this.borderXsize;
    }
    public int getBorderYsize()
    {
        return this.borderYsize;
    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.blue);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Select level",100,20);
    }
    public Options()
    {

        setSize(500,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        panel.setLayout(null);
        panel.setBackground(Color.green);

        lv1.addItemListener(this);
        lv1.setBounds(120,100,100,40);
        lv2.addItemListener(this);
        lv2.setBounds(220,100,100,40);
        lv3.addItemListener(this);
        lv3.setBounds(320,100,100,40);
        tr1.addItemListener(this);
        tr1.setBounds(110,200,100,40);
        tr2.addItemListener(this);
        tr2.setBounds(210,200,100,40);
        tr3.addItemListener(this);
        tr3.setBounds(310,200,100,40);
        play.addActionListener(this);
        play.setBounds(110,360,100,30);
        back.addActionListener(this);
        back.setBounds(290,360,100,30);

        panel.add(lv1);
        panel.add(lv2);
        panel.add(lv3);
        panel.add(tr1);
        panel.add(tr2);
        panel.add(tr3);
        panel.add(play);
        panel.add(back);
        add(panel);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource()==lv1)
        {
            this.delay=600;
        }

        if(e.getSource()==lv2)
        {
            this.delay=400;

        }

        if(e.getSource()==lv3)
        {
            this.delay=200;
        }

        if(e.getSource()==tr1)
        {
            this.borderXsize=400;
            this.borderYsize=400;
        }

        if(e.getSource()==tr2)
        {
            this.borderXsize=500;
            this.borderYsize=500;
        }

        if(e.getSource()==tr3)
        {
            this.borderXsize=600;
            this.borderYsize=600;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == play)
        {
            JFrame frame = new JFrame();
            Play start = new Play();
            frame.setBounds(0, 0, borderXsize, borderYsize);
            frame.setBackground(Color.CYAN);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.add(start);
            setVisible(false);
            this.dispose();

        }
        if(obj==back)
        {
            Menu menu = new Menu();
            this.dispose();
        }
    }
}

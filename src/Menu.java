import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JButton options = new JButton("Options");
    JButton exit = new JButton("Exit");

    public ImageIcon logo;

    public void paint(Graphics g) {
        super.paint(g);
        logo = new ImageIcon("logo.jpg");
        logo.paintIcon(this, g, 125, 100);
    }

    public Menu()
    {

        setSize(500,500);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        panel.setLayout(null);
        panel.setBackground(Color.green);

        panel.add(options);
        panel.add(exit);
        options.addActionListener(this);
        options.setBounds(180,300,140,30);
        exit.addActionListener(this);
        exit.setBounds(180,360,140,30);
        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj==options)
        {
            Options opt = new Options();
            this.dispose();
        }
        if(obj==exit)
        {
            System.exit(0);
        }
    }
}

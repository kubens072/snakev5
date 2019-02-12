import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Play extends JPanel implements ActionListener, KeyListener {
    Options opt = new Options();
    int delay = opt.getDelay();
    int borderXsize=opt.getBorderXsize();
    int borderYsize=opt.getBorderYsize();


    public int[] snakeXlenght = new int[this.borderXsize];
    public int[] snakeYlenght = new int[this.borderYsize];

    public boolean left=false;
    public boolean right=false;
    public boolean up=false;
    public boolean down=false;
    public int score=0;
    public int moves = 0;
    public ImageIcon face;

    public int lengthOfSnake = 3;

    public int[] enemyxpos=new int[borderXsize/25];
    public int[] enemyypos=new int[borderYsize/25];

    public ImageIcon enemyImage;
    public Random random = new Random();

    public int xpos=random.nextInt(borderXsize/25);
    public int ypos=random.nextInt(borderYsize/25);

    public Timer timer;
    public ImageIcon snakeImage;

    public Play()
    {
        for(int i=0; i<enemyxpos.length; i++)
        {
            enemyxpos[i]=25*i;
        }
        for(int i=0; i<enemyypos.length; i++)
        {
            enemyypos[i]=25*i;
        }


        addKeyListener(this);
        setFocusable(true);


        timer = new Timer(delay,this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        if(moves==0)
        {
            snakeXlenght[2]=50;
            snakeXlenght[1]=75;
            snakeXlenght[0]=100;

            snakeYlenght[2]=100;
            snakeYlenght[1]=100;
            snakeYlenght[0]=100;
        }

        //draw background for the gamelay

        g.setColor(Color.CYAN);
        g.fillRect(0,0,borderXsize,borderYsize);

        //draw scores
        g.setColor(Color.blue);
        g.setFont(new Font("arial", Font.PLAIN, 14));
        g.drawString("Scores = "+ score,borderXsize-150,30);

        face =new ImageIcon("head.png");
        face.paintIcon(this, g, snakeXlenght[0], snakeYlenght[0]);

        for(int i = 0; i<lengthOfSnake; i++)
        {
            if(i==0)
            {
                face =new ImageIcon("head.png");
                face.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }

            if(i!=0)
            {
                snakeImage=new ImageIcon("tail.png");
                snakeImage.paintIcon(this, g, snakeXlenght[i], snakeYlenght[i]);
            }
        }

        enemyImage=new ImageIcon("target.png");

        if((enemyxpos[xpos]==snakeXlenght[0]) && (enemyypos[ypos]==snakeYlenght[0]))
        {
            lengthOfSnake++;
            score++;
            xpos= random.nextInt(borderXsize/25);
            ypos=random.nextInt(borderYsize/25);
            System.out.println(xpos+", "+ypos);

        }
        enemyImage.paintIcon(this,g,enemyxpos[xpos],enemyypos[ypos]);

        for(int b=1; b<lengthOfSnake; b++)
        {
            if(snakeXlenght[b]==snakeXlenght[0] && snakeYlenght[b]==snakeYlenght[0])
            {
                left=false;
                right=false;
                up=false;
                down=false;

                g.setColor(Color.red);
                g.setFont(new Font("arial",Font.BOLD,20));
                g.drawString("Game over, your score is "+score,(borderXsize/2)-100,borderYsize/2);
            }
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(right)
        {
            for(int i = lengthOfSnake-1; i>=0; i--)
            {
                snakeYlenght[i+1]=snakeYlenght[i];
            }
            for(int i = lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeXlenght[i]=snakeXlenght[i]+25;
                }
                else
                {
                    snakeXlenght[i]=snakeXlenght[i-1];
                }
                if(snakeXlenght[i]>borderXsize-25)
                {
                    System.exit(0);
                }
            }
            repaint();
        }
        if(left)
        {
            for(int i = lengthOfSnake-1; i>=0; i--)
            {
                snakeYlenght[i+1]=snakeYlenght[i];
            }
            for(int i = lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeXlenght[i]=snakeXlenght[i]-25;
                }
                else
                {
                    snakeXlenght[i]=snakeXlenght[i-1];
                }
                if(snakeXlenght[i]<0)
                {
                    System.exit(0);
                }
            }
            repaint();
        }
        if(up)
        {
            for(int i = lengthOfSnake-1; i>=0; i--)
            {
                snakeXlenght[i+1]=snakeXlenght[i];
            }
            for(int i = lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeYlenght[i]=snakeYlenght[i]-25;
                }
                else
                {
                    snakeYlenght[i]=snakeYlenght[i-1];
                }
                if(snakeYlenght[i]<0)
                {
                    System.exit(0);
                }
            }
            repaint();
        }
        if(down)
        {
            for(int i = lengthOfSnake-1; i>=0; i--)
            {
                snakeXlenght[i+1]=snakeXlenght[i];
            }
            for(int i = lengthOfSnake; i>=0; i--)
            {
                if(i==0)
                {
                    snakeYlenght[i]=snakeYlenght[i]+25;
                }
                else
                {
                    snakeYlenght[i]=snakeYlenght[i-1];
                }
                if(snakeYlenght[i]>borderYsize-25)
                {
                    System.exit(0);
                }
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            moves++;
            right=true;
            if(!left)
            {
                right=true;
            }
            else
            {
                right=false;
                left=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            moves++;
            left=true;
            if(!right)
            {
                left=true;
            }
            else
            {
                left=false;
                right=true;
            }
            up=false;
            down=false;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP)
        {
            moves++;
            up=true;
            if(!down)
            {
                up=true;
            }
            else
            {
                up=false;
                down=true;
            }
            left=false;
            right=false;

        }
        if(e.getKeyCode()== KeyEvent.VK_DOWN)
        {
            moves++;
            down=true;
            if(!up)
            {
                down=true;
            }
            else
            {
                down=false;
                up=true;
            }
            left=false;
            right=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
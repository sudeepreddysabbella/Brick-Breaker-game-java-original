import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

import javax.management.timer.TimerMBean;
public class Gameplay {
    public Gameplay extends JPanel implements KeyListener,ActionListner {
        private boolean play=false;
        private int score =0;
        private int totalbricks=21;
        private javax.management.timer.Timer timer;
        private int delay=0;
        private int playerX=310;
        private int ballposX=120;
        private int ballposY=350;
        private int ballXdir=-1;
        private int ballYdir=-2;
        private MapGenerator map;
    }
        
        public Gameplay(){
            map=new MapGenerator(ballXdir, ballYdir);
            addKeyListener(this);
            setFocusable(true);
            setFocusTransversalKeyEnabled(false);
            timer=new Timer(delay,this);
            timer.start();
        }
        public void paint(Graphics g){
            g.setColor(Color.black);
            g.fillRect(1, 1, 692, 592);
            map.draw((Graphics2D) g);
            g.setColor(Color.yellow);
            g.fillRect(0, 0, 3, 592);
            g.fillRect(0, 0, 592, 3);
            g.fillRect(691, 0, 3, 592);
            g.setColor(Color.white);
            g.setFont(new Font("serif",Font.BOLD,25));
            g.drawString(""+score, 590, 30);
            g.setColor(Color.yellow);
            g.fillRect(playerX, 550, 100, 8);
            g.setColor(Color.green);
            g.fillOval(ballposX, ballposY, 20, 20);

            if(ballposY>570){
                play=false;
                ballXdir=0;
                ballYdir=0;
                g.setColor(Color.red);
                g.setFont(new Font("serif",Font.BOLD,30));
                g.drawString("Game Over... Score ="+score, 190, 300);
                g.setFont(new Font("serif",Font.BOLD,30));
                g.drawString("Press Enter To Restart", 190, 340);
            }
            if(totalbricks==0){
                play=false;
                ballYdir=-1;
                ballXdir=-2;
                g.setColor(color.red);
                g.setFont(new Font("serif",Font.BOLD,30));
                g.drawString("Press Enter To Restart", 190, 340);
            }
            g.dispose();
        }
        public void actionPerformed(ActionEvent e){
            timer.start();
            if(play){
                if(new Rectangle(ballXdir,ballYdir,20,20).intersects(new Rectangle(playerX,550,100,0))){
                    ballYdir-=ballYdir;
                }
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<=map.map[0];j++){
                    if(map.map[i][j]>0){
                        int brickX =j*map.brickwidth + 80;
                        int brickY=j*map.brickheight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight=map.brickHeight;
                        Rectangle rect= new Rectangle(brickX,brickY,brickWidth,brickHeight)
                        Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
                        Rectangle brickrect=rect;
                        if(ballrect.intersects(brickrect)){
                            map.setBrickvalue(0,i,j);
                            totalbricks--;
                            score+=5;
                            if(ballposX+19 <= brickrect.x || ballposX+1>=brickrect.x+brickWidth){
                                ballXdir=-ballXdir;
                            }
                            else{
                                ballYdir=-ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposX+=ballXdir;
            ballposY+=ballYdir;
            if(ballposX<0){
                ballXdir =-ballXdir;
            }
            if(ballposY<0){
                ballYdir=-ballYdir;
            }
            repaint();
            }
        }
        public void KeyPressed(KeyEvent e){
            if(e.getKeyWord()==KeyEvent.VK_RIGHT){
                if(playerX>=600){
                    playerX=600;
                }
                else{
                    moveRight();
                }
            }
            if(e.getKeyCode()==KeyEvent.VK_LEFT){
                if(!play){
                    ballposX=120;
                    ballposY=320;
                    ballXdir=-1;
                    ballYdir=-2;
                    score=0;
                    playerX=310;
                    totalbricks=21;
                    map= new MapGenerator(3,7);
                    repaint();

                }
            }
        }
        public void moveRight(){
            play=true;
            playerX+=20;
        }
        public void moveLeft(){
            play=true;
            playerX-=20;
        }
    }
    
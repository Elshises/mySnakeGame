 import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
import java.util.Random;



public class GamePanel extends JPanel {
static final int  PANEL_WIDTH = 600;
static final int PANEL_HEIGHT = 600;
static final int GAME_UNITSIZE = 20;
static final int GAME_UNITS = (PANEL_HEIGHT*PANEL_WIDTH)/GAME_UNITSIZE;
static final int Deelay  = 75;
static  int bodyparts = 2;
static int cakesEaten;
static char direction = 'R';

int CakeX ;
int CakeY;
int BodyX[]= new int [GAME_UNITS];
int BodyY[] = new int [GAME_UNITS];
Random random;
Timer timer = new Timer (Deelay,new ActionListener(){
public void actionPerformed(ActionEvent e){
if(running){
    move();
    checkCake();
    checkGameover();
    
}
repaint();


}
         
            
        });

boolean running = false;


//constructor
GamePanel(){
this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT ));
this.setBackground(Color.BLACK);
this.setFocusable(true);
this.addKeyListener(new myAdapter());
random = new Random();

startGame();

}



//drawing method
public void paintComponent(Graphics g){
    super.paintComponent(g);
    draw(g);
}


//drawing method
public void draw(Graphics g){
  if(running)  {
    for(int i=PANEL_HEIGHT;i>0;i--){
g.drawLine(0,GAME_UNITSIZE*i, PANEL_WIDTH,GAME_UNITSIZE*i);
g.drawLine(GAME_UNITSIZE*i,0, GAME_UNITSIZE*i, PANEL_HEIGHT);
    }
    g.setColor(Color.red);
    g.fillOval(CakeX, CakeY, GAME_UNITSIZE, GAME_UNITSIZE);
    g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
    for (int i = 0; i<bodyparts;i++){
if(BodyX[i]==0&&BodyY[i]==0){
    g.setColor(Color.magenta);
    g.fillRect(BodyX[i], BodyY[i], GAME_UNITSIZE, GAME_UNITSIZE);
}
g.fillRect(BodyX[i], BodyY[i], GAME_UNITSIZE, GAME_UNITSIZE);

}
  }
  else{
    g.setFont(new Font("Verdana",Font.BOLD,25));
    g.setColor(Color.green);
    g.drawString("Game Over",220,300);
    g.drawString("Score : "+cakesEaten,230,340);
  }

}

 


//start game method
public void startGame(){
    
        running = true;
        newCake();
        timer.start();
       //checkGameover();
        
}



//method for generating a new cake
public void newCake(){
CakeX = random.nextInt((int)(PANEL_WIDTH/GAME_UNITSIZE))*GAME_UNITSIZE;
    CakeY = random.nextInt((int)(PANEL_HEIGHT/GAME_UNITSIZE))*GAME_UNITSIZE;
}


//method for checking whether a cake has been eaten
public void checkCake(){

    for(int i = bodyparts;i>0;i--){
        if((BodyX[0]==CakeX)&&(BodyY[0]==CakeY)){
            bodyparts++;
            cakesEaten++;
            newCake();
            
        }
      
    }
}

//method for checking when a game is over
public void checkGameover(){
for(int i = bodyparts;i>0;i--){
    //checking snake collissions with bodyparts
    if(BodyX[i]==BodyX[0]&&BodyY[i]==BodyY[0]){
        running = false;
    }
    //checking snake collissions with the right wall
        if(BodyX[0]<0){
           running = false;
        } 
        //checking snake collission with the left wall   
        if(BodyY[0]<0){
           running = false;
        }  
        //checking snake collision with the upper wall
        if(BodyX[0]>=PANEL_WIDTH){
           running = false;
        }   
        //checking snake collissions with the lower wall
        if(BodyY[0]>=PANEL_HEIGHT){
           running = false;
        }  

        if(!running) {
            timer.stop();
        }

}
}


//action performed method



//method for moving the snake around the panel
public void move(){
    for(int i = bodyparts;i>0;i--){
        BodyX[i]=BodyX[i-1];
        BodyY[i]=BodyY[i-1];
    }
        if(direction == 'R'){
            BodyX[0]+=GAME_UNITSIZE;

        }
        else if(direction =='L'){

            BodyX[0]-=GAME_UNITSIZE;
        }
        else if(direction=='D'){
            BodyY[0]=BodyY[0]+GAME_UNITSIZE;
        }
        else if(direction=='U'){
            BodyY[0]-=GAME_UNITSIZE;
        }
    


}

//private class for controlling the snake using the arrow keys
private class myAdapter extends KeyAdapter{
@Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
            if(direction!='R'){
            direction = 'L';
            }
            break;
            case KeyEvent.VK_RIGHT:
            if(direction!='L'){
            direction = 'R';
            }
            break;
            case  KeyEvent.VK_UP:
            if(direction!='D'){
            direction = 'U';
            }
            break;
            case KeyEvent.VK_DOWN:
            if(direction!='U'){
            direction = 'D';
            }
            break;
            default:
            break;

        }

    }
}

    
}

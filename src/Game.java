import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JPanel implements ActionListener {

    private final int size = 320;
    private final int pointSize = 16;
    private final int allPoint = 400;

    private Image point;
    private Image man;

    private int manX;
    private int manY;

    private  int[] x = new int[allPoint];
    private int[] y = new int[allPoint];

    private int anPoints;
    private Timer time;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean play = true;




    public Game(){
        setBackground(Color.pink);
        imgLoad();
        gameInit();
        addKeyListener(new GameKeyListener());
        setFocusable(true);
    }

    public void gameInit(){
        anPoints = 3;
        for (int i = 0; i < anPoints; i++) {
            x[i] = 48 - i*pointSize;
            y[i] = 48;
        }
        time = new Timer(250,this);
        time.start();
        createMan();
    }

    public void createMan(){
        manX= new Random().nextInt(20)*pointSize;
        manY= new Random().nextInt(20)*pointSize;
    }

    public void imgLoad(){
        ImageIcon mann = new ImageIcon("man.png");
        man = mann.getImage();

        ImageIcon pointt = new ImageIcon("point.png");
        point = pointt.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(play){
            g.drawImage(man,manX,manY,this);
            for (int i = 0; i < anPoints; i++){
                g.drawImage(point, x[i],y[i],this);
            }

        } else {
            String GO = "Игра окончена! ";
            //Font fnt = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.white);
            //g.setFont(fnt);
            g.drawString(GO,125,size/2);

        }
    }

    public void move(){
        for (int i = anPoints; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(left){
            x[0] -= pointSize;
        }

        if(right){
            x[0] += pointSize;
        }

        if(up){
            y[0] -= pointSize;
        }

        if(down){
            y[0] += pointSize;
        }
    }

    public void  checkMan(){
        if(x[0] == manX && y[0] == manY){
            anPoints++;
            createMan();
        }
    }

    public void checkColl(){

        for (int i = anPoints; i >0 ; i--) {
            if(i>4 && x[0] == x[i] && y[0] == y[i]){
                play = false;
            }
        }

        if(x[0]>size){
            play = false;
        }
        if(x[0]<0){
            play = false;
        }
        if(y[0]>size){
            play = false;
        }
        if(y[0]<0){
            play = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(play){
            checkMan();
            checkColl();
            move();
        }

        repaint();
    }

    class GameKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }


        }
    }
}

import javax.swing.*;

public class MainGameWin extends JFrame {

    public MainGameWin(){

        setTitle("Анаконда");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320,345);
        setLocation(400,400);
        add(new Game());
        setVisible(true);
    }

    public static void main(String[] args){

        MainGameWin mainW = new MainGameWin();
    }


}

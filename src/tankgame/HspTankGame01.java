package tankgame;

import javax.swing.*;

public class HspTankGame01 extends JFrame {
    public final static int HEIGHT = 800;
    public final static int WIDTH = 1200;
    MyPanel mp = null;
    public static void main(String[] args) {
        HspTankGame01 hspTankGame01 = new HspTankGame01();

    }
    public HspTankGame01(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mp);
        new Thread(mp).start();
    }
}

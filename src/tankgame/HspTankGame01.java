package tankgame;

import javax.swing.*;

/**
 * 主界面的设置
 */
public class HspTankGame01 extends JFrame {
    public final static int HEIGHT = 800;
    public final static int WIDTH = 1200;
    MyPanel mp;
    public static void main(String[] args) {
        new HspTankGame01("坦克大战");
    }
    public HspTankGame01(String title){
        super(title);
        mp = new MyPanel();
        this.add(mp);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(mp);
        new Thread(mp).start();
    }
}

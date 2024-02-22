package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Vector;

/**
 * 坦克大战的绘图区域
 */
public class MyPanel extends JPanel implements KeyListener,Runnable {
    MyTank myTank; // 我的坦克

    Vector<EnemyTank> enemyTankVector = new Vector<>(); // 敌人的坦克集合
    private final static int ENEMYTANKSIZE = 5; // 敌人的坦克数量

    Vector<Bomb> bombs = new Vector<>(); // 爆炸效果集合
    Image []image = new Image[3]; // 爆炸效果图片

    /**
     * 构造器，初始化坦克等
     */
    public MyPanel() {
        myTank = new MyTank(600, 600, TankDirect.valueOf("UP"));
        for (int i = 0; i < ENEMYTANKSIZE; i++) {
            EnemyTank enemyTank = new EnemyTank(new Random().nextInt(HspTankGame01.WIDTH),new Random().nextInt(HspTankGame01.HEIGHT),TankDirect.randomDirect());
            enemyTankVector.add(enemyTank);
            new Thread(enemyTank).start();
        }
        //不用Toolkit，不然第一次加载不出来
        for (int i = 0; i < 3; i++) {
            image[i] = new ImageIcon("src\\images\\bomb" + i + ".png").getImage();
        }
    }

    /**
     * 绘制主画面
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        // 设置画布背景颜色
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, HspTankGame01.WIDTH, HspTankGame01.HEIGHT);

        //绘制子弹数量
        g.setColor(Color.white);
        g.setFont(new Font("宋体", Font.PLAIN, 25));
        g.drawString("剩余子弹数量："+ (MyTank.MAX_SHOT - myTank.shots.size()),10,50);

        //画出我的坦克和子弹
        if(myTank.getIsLive()) {
            drawTank(myTank.getX(), myTank.getY(), g, myTank.getDirect(), myTank.getType());
            drawShot(myTank, g);
        }
        //画出敌人的坦克和子弹
        for (EnemyTank enemyTank : enemyTankVector) {
            if(!enemyTank.getIsLive()) continue;
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), enemyTank.getType());
            drawShot(enemyTank, g);
        }
        //画出爆炸效果
        if(!bombs.isEmpty())
            drawBomb(g);
        System.out.println(111);
    }

    /**
     * 画出坦克的子弹
     * @param tank 坦克类
     * @param g 画笔
     */
    public void drawShot(Tank tank,Graphics g){
        if(tank instanceof MyTank){
            g.setColor(Color.cyan);
        }else if(tank instanceof EnemyTank){
            g.setColor(Color.red);
        }
        for(int i = 0;i < tank.shots.size();i++){
            Shot shot = tank.shots.get(i);
            if(shot.getIsLive()){
                g.fill3DRect(shot.getX(),shot.getY(),Shot.getLENGTH(),Shot.getLENGTH(),false);
            }else{
                tank.shots.remove(shot);
            }
        }
    }

    /** 画出坦克
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 坦克的方向(上下左右)
     */
    public void drawTank(int x, int y, Graphics g, TankDirect direct,String type) {
        switch (type) {
            case "MY_TANK" -> g.setColor(Color.cyan);
            case "ENEMY_TANK" -> g.setColor(Color.red);
        }
        switch (direct) {
            case UP -> {
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.drawLine(x + 20, y + 30, x + 20, y);
                g.fillOval(x + 10, y + 20, 19, 19);
            }
            case DOWN -> {
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                g.fillOval(x + 10, y + 20, 19, 19);
            }
            case LEFT -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.drawLine(x + 30, y + 20, x, y + 20);
                g.fillOval(x + 20, y + 10, 19, 19);
            }
            case RIGHT -> {
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                g.fillOval(x + 20, y + 10, 19, 19);
            }
        }

    }

    /**
     * 画出爆炸效果
     * @param g 画笔
     */
    public void drawBomb(Graphics g){
        final int LENGTH = 60;
        for (int i = 0;i < bombs.size();i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.getLife() > 8){
                g.drawImage(image[0],bomb.getX(),bomb.getY(),LENGTH,LENGTH,this);
            }else if(bomb.getLife() > 4){
                g.drawImage(image[1],bomb.getX(),bomb.getY(),LENGTH,LENGTH,this);
            }else if(bomb.getLife() > 0){
                g.drawImage(image[2],bomb.getX(),bomb.getY(),LENGTH,LENGTH,this);
            }else{
                bombs.remove(bomb);
                CheckDeath();
            }
            bomb.lifeDown();
        }
    }

    /**
     *  判断子弹是否击中坦克
     * @param shot 子弹
     * @param tank 坦克
     */
    public void hitTank(Shot shot ,Tank tank){
        switch (tank.getDirect()){
            case UP,DOWN ->{
                if(shot.getX() > tank.getX() && shot.getX() < tank.getX() + 40
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 60){
                    shot.setIsLive(false);
                    tank.setIsLive(false);
                    bombs.add(new Bomb(tank.getX(),tank.getY()));
                }
            }
            case LEFT,RIGHT ->{
                if(shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 40){
                    shot.setIsLive(false);
                    tank.setIsLive(false);
                    bombs.add(new Bomb(tank.getX(),tank.getY()));
                }
            }
        }
    }

    /**
     * 判断自己是否死亡
     */
    public void CheckDeath(){
        if(!myTank.getIsLive()){
            JOptionPane.showMessageDialog(null,"游戏结束");
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 处理键盘按下事件(wasd)
     * @param e 事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("x = " + myTank.getX() + ", y = " + myTank.getY());
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP,KeyEvent.VK_W ->{
                myTank.setDirect(TankDirect.valueOf("UP"));
                myTank.moveUp();
            }
            case KeyEvent.VK_DOWN,KeyEvent.VK_S ->{
                myTank.setDirect(TankDirect.valueOf("DOWN"));
                myTank.moveDown();
            }
            case KeyEvent.VK_LEFT,KeyEvent.VK_A  ->{
                myTank.setDirect(TankDirect.valueOf("LEFT"));
                myTank.moveLeft();
            }
            case KeyEvent.VK_RIGHT,KeyEvent.VK_D ->{
                myTank.setDirect(TankDirect.valueOf("RIGHT"));
                myTank.moveRight();
            }
            case KeyEvent.VK_J -> {
                if(myTank.shots.size() < MyTank.MAX_SHOT) {
                    myTank.shotEnemyTank();
                }
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * 线程,用于不断重绘画面,并判断子弹是否击中坦克
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < myTank.shots.size(); i++) {
                Shot shot = myTank.shots.get(i);
                if(shot != null && shot.getIsLive()){
                    for(int j = 0;j < enemyTankVector.size();j++){
                        EnemyTank enemyTank = enemyTankVector.get(j);
                        hitTank(shot,enemyTank);
                        if(!enemyTank.getIsLive()){
                            enemyTankVector.remove(enemyTank);
                        }
                    }
                }
            }
            for (EnemyTank enemytank : enemyTankVector) {
                for (Shot shot : enemytank.shots) {
                    if (shot != null && shot.getIsLive()) {
                        hitTank(shot, myTank);
                    }
                }
            }

            repaint();
        }
    }
}

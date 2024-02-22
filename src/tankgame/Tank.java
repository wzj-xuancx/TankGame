package tankgame;

import java.util.Vector;

/**
 * 坦克父类
 */
public class Tank {
    private int x; // 坦克的横坐标
    private int y; // 坦克的纵坐标
    private TankDirect direct; // 坦克的方向
    private int speed = 6; // 坦克的速度
    private boolean isLive = true;
    public Vector<Shot> shots = new Vector<>();
    public Tank(int x, int y, TankDirect direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void moveUp() {
        if(y - speed >= 0)
            y -= speed;
    }
    public void moveDown() {
        if(y + speed <= HspTankGame01.HEIGHT - 100)
            y += speed;
    }
    public void moveLeft() {
        if(x - speed >= 0)
            x -= speed;
    }
    public void moveRight() {
        if(x + speed <= HspTankGame01.WIDTH - 70)
            x += speed;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public TankDirect getDirect() {
        return direct;
    }
    public void setDirect(TankDirect direct) {
        this.direct = direct;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }
    public boolean getIsLive() {
        return isLive;
    }
}

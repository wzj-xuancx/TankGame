package tankgame;

import java.util.Vector;

/**
 * 坦克父类
 */
public class Tank {
    private int x; // 坦克的横坐标
    private int y; // 坦克的纵坐标
    private TankDirect direct; // 坦克的方向
    private static final int SPEED = 6; // 坦克的速度
    private boolean isLive = true;
    public Vector<Shot> shots = new Vector<>();
    public Tank(int x, int y, TankDirect direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void moveUp() {
        if(y - SPEED >= 0)
            y -= SPEED;
    }
    public void moveDown() {
        if(y + SPEED <= HspTankGame01.HEIGHT - 100)
            y += SPEED;
    }
    public void moveLeft() {
        if(x - SPEED >= 0)
            x -= SPEED;
    }
    public void moveRight() {
        if(x + SPEED <= HspTankGame01.WIDTH - 70)
            x += SPEED;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public TankDirect getDirect() {
        return direct;
    }
    public void setDirect(TankDirect direct) {
        this.direct = direct;
    }
    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }
    public boolean getIsLive() {
        return isLive;
    }
}

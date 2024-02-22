package tankgame;

/**
 * 子弹类
 * 并且有子弹的移动线程，将每一颗子弹的移动放到一个线程中
 */
public class Shot implements Runnable{
    private int x; // 子弹的横坐标
    private int y; // 子弹的纵坐标
    private final TankDirect direct; // 子弹的方向
    private static final int SPEED = 5; // 子弹的速度
    private static final int LENGTH = 3; // 子弹的长宽
    private boolean isLive = true; // 子弹是否存活
    public Shot(int x, int y, TankDirect direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public void setIsLive(boolean isLive) {
        this.isLive = isLive;
    }
    public boolean getIsLive() {
        return isLive;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public static int getLENGTH() {
        return LENGTH;
    }
    @Override
    public void run() {
        while (isLive) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case UP -> y -= SPEED;
                case DOWN -> y += SPEED;
                case LEFT -> x -= SPEED;
                case RIGHT -> x += SPEED;
            }

            if (x < 0 || x > HspTankGame01.WIDTH || y < 0 || y > HspTankGame01.HEIGHT) {
                isLive = false;
            }
            if(!isLive) {
                break;
            }
        }
    }

}

package tankgame;

/**
 * 爆炸效果设置
 */
public class Bomb {
    private int x,y; //炸弹的横纵坐标
    private int life = 12; //生命周期
    private boolean isLive = true; //炸弹是否存活
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //生命周期减少
    public void lifeDown() {
        if(life > 0) {
            life--;
        } else {
            isLive = false;
        }
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

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

}

package tankgame;

import java.io.Serializable;
import java.util.Vector;

/**
 * 坦克父类
 */
public class Tank implements Serializable {
    private int x; // 坦克的横坐标
    private int y; // 坦克的纵坐标
    private TankDirect direct; // 坦克的方向
    private static final int SPEED = 6; // 坦克的速度
    private boolean isLive = true;
    public transient Vector<Shot> shots = new Vector<>();
    public Vector<Tank> otherTanks = new Vector<>();

    public Tank(int x, int y, TankDirect direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public boolean checkIsTouchOtherTank(Tank tank){
        for(Tank otherTank : otherTanks){
//            System.out.println(tank.x + " " + tank.y + " " + tank.direct +  " " + otherTank.x + " " + otherTank.y + " " + otherTank.direct);
            switch (tank.direct){
                case UP,DOWN -> {
                    switch (otherTank.direct){
                        case UP,DOWN -> {
//                            System.out.println(tank.x + " " + tank.y + " " + tank.direct +  " " + otherTank.x + " " + otherTank.y + " " + otherTank.direct);
                            if(tank.x > otherTank.x - 40
                                    && tank.x < otherTank.x + 40
                                    && tank.y > otherTank.y - 60
                                    && tank.y < otherTank.y + 60){
                                return true;
                            }
                        }
                        case LEFT,RIGHT -> {
                            if(tank.x > otherTank.x - 40
                                    && tank.x < otherTank.x + 60
                                    && tank.y > otherTank.y - 60
                                    && tank.y < otherTank.y + 40){
                                return true;
                            }
                        }
                    }
                }

                case LEFT,RIGHT -> {
                    switch (otherTank.direct){
                        case UP,DOWN -> {
                            if(tank.x > otherTank.x - 60
                                    && tank.x < otherTank.x + 40
                                    && tank.y > otherTank.y - 40
                                    && tank.y < otherTank.y + 60){
                                return true;
                            }
                        }
                        case LEFT,RIGHT -> {
                            if(tank.x > otherTank.x - 60
                                    && tank.x < otherTank.x + 60
                                    && tank.y > otherTank.y - 40
                                    && tank.y < otherTank.y + 40){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp() {
        if(y - SPEED >= 0){
            if(direct == TankDirect.LEFT || direct == TankDirect.RIGHT) {
                if (!checkIsTouchOtherTank(new Tank(x + 10, y - 10 - SPEED, TankDirect.UP))) {
                    direct = TankDirect.UP;
                    x += 10;
                    y += -10 - SPEED;
                }
            }else{
                if (!checkIsTouchOtherTank(new Tank(x, y - SPEED, TankDirect.UP))) {
                    direct = TankDirect.UP;
                    y -= SPEED;
                }
            }
        }
    }
    public void moveDown() {
        if(y + SPEED <= HspTankGame01.HEIGHT - 100){
            if(direct == TankDirect.LEFT || direct == TankDirect.RIGHT) {
                if (!checkIsTouchOtherTank(new Tank(x + 10, y - 10 + SPEED, TankDirect.DOWN))) {
                    direct = TankDirect.DOWN;
                    x += 10;
                    y += SPEED - 10;
                }
            }else{
                if (!checkIsTouchOtherTank(new Tank(x, y + SPEED, TankDirect.DOWN))) {
                    direct = TankDirect.DOWN;
                    y += SPEED;
                }
            }
        }
    }
    public void moveLeft() {
        if (x - SPEED >= 0) {
            if(direct == TankDirect.UP || direct == TankDirect.DOWN) {
                if (!checkIsTouchOtherTank(new Tank(x - 10 - SPEED, y + 10, TankDirect.LEFT))) {
                    direct = TankDirect.LEFT;
                    x += -10 - SPEED;
                    y += 10;
                }
            }else{
                if (!checkIsTouchOtherTank(new Tank(x - SPEED, y, TankDirect.LEFT))) {
                    direct = TankDirect.LEFT;
                    x -= SPEED;
                }
            }
        }
    }
    public void moveRight() {
        if (x + SPEED <= HspTankGame01.WIDTH - 60) {
            if(direct == TankDirect.UP || direct == TankDirect.DOWN) {
                if (!checkIsTouchOtherTank(new Tank(x - 10 + SPEED, y + 10, TankDirect.RIGHT))) {
                    direct = TankDirect.RIGHT;
                    x += SPEED - 10;
                    y += 10;
                }
            }else{
                if (!checkIsTouchOtherTank(new Tank(x + SPEED, y, TankDirect.RIGHT))) {
                    direct = TankDirect.RIGHT;
                    x += SPEED;
                }
            }
        }
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
    public int getSpeed(){return SPEED;}
}

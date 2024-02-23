package tankgame;

import java.util.Vector;

/**
 * 敌方坦克
 * 实现移动的线程
 */
public class EnemyTank extends Tank implements Runnable{
    private final static String Type = "ENEMY_TANK";
    private final int SLEEP_TIME = 500 + (int)(Math.random() * 100 - 50);
    private final int MOVE_TIME = 20 + (int)(Math.random() * 4 - 2);
    private final int MOVE_SLEEP_TIME = 60 + (int)(Math.random() * 12 - 6);

    public String getType() {
        return Type;
    }
    public EnemyTank(int x, int y, TankDirect direct) {
        super(x, y, direct);
    }
    public boolean canMove(TankDirect direct){
        //判断是否和边界碰撞，以及和其他坦克碰撞
        switch (direct){
            case UP -> {
                if(getY() - getSpeed() >= 0){
                    if(getDirect() == TankDirect.LEFT || getDirect() == TankDirect.RIGHT) {
                        if (!checkIsTouchOtherTank(new Tank(getX() + 10, getY() - 10 - getSpeed(), TankDirect.UP))) {
                            return true;
                        }
                    }else{
                        if (!checkIsTouchOtherTank(new Tank(getX(), getY() - getSpeed(), TankDirect.UP))) {
                            return true;
                        }
                    }
                }
            }
            case DOWN -> {
                if(getY() + getSpeed() <= HspTankGame01.HEIGHT - 100){
                    if(getDirect() == TankDirect.LEFT || getDirect() == TankDirect.RIGHT) {
                        if (!checkIsTouchOtherTank(new Tank(getX() + 10, getY() - 10 + getSpeed(), TankDirect.DOWN))) {
                            return true;
                        }
                    }else{
                        if (!checkIsTouchOtherTank(new Tank(getX(), getY() + getSpeed(), TankDirect.DOWN))) {
                            return true;
                        }
                    }
                }
            }
            case LEFT -> {
                if(getX() - getSpeed() >= 0){
                    if(getDirect() == TankDirect.UP || getDirect() == TankDirect.DOWN) {
                        if (!checkIsTouchOtherTank(new Tank(getX() - 10 - getSpeed(), getY() + 10, TankDirect.LEFT))) {
                            return true;
                        }
                    }else{
                        if (!checkIsTouchOtherTank(new Tank(getX() - getSpeed(), getY(), TankDirect.LEFT))) {
                            return true;
                        }
                    }
                }
            }
            case RIGHT -> {
                if(getX() + getSpeed() <= HspTankGame01.WIDTH - 60){
                    if(getDirect() == TankDirect.UP || getDirect() == TankDirect.DOWN) {
                        if (!checkIsTouchOtherTank(new Tank(getX() - 10 + getSpeed(), getY() + 10, TankDirect.RIGHT))) {
                            return true;
                        }
                    }else{
                        if (!checkIsTouchOtherTank(new Tank(getX() + getSpeed(), getY(), TankDirect.RIGHT))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void run() {
        new Thread(new EnemyShot( this)).start();
        while(getIsLive()){

            switch (getDirect()){
                case UP -> {
                    for (int i = 0; i < MOVE_TIME; i++) {
                        moveUp();
                        try {
                            Thread.sleep(MOVE_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case DOWN -> {
                    for (int i = 0; i < MOVE_TIME; i++) {
                        moveDown();
                        try {
                            Thread.sleep(MOVE_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case LEFT -> {
                    for (int i = 0; i < MOVE_TIME; i++) {
                        moveLeft();
                        try {
                            Thread.sleep(MOVE_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                case RIGHT -> {
                    for (int i = 0; i < MOVE_TIME; i++) {
                        moveRight();
                        try {
                            Thread.sleep(MOVE_SLEEP_TIME);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Vector<TankDirect> directs = new Vector<>();

            for(int i = 0;i < 4 ;i++) {
                if (canMove(TankDirect.values()[i])){
                    directs.add(TankDirect.values()[i]);
                }
            }
            if(!directs.isEmpty())
                setDirect(TankDirect.randomDirect(directs));
        }
    }
}

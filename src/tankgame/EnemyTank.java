package tankgame;

/**
 * 敌方坦克
 * 实现移动的线程
 */
public class EnemyTank extends Tank implements Runnable{
    private final static String Type = "ENEMY_TANK";
    private final int SLEEP_TIME = 500 + (int)(Math.random() * 100 - 50);
    private final int MOVE_TIME = 20 + (int)(Math.random() * 4 - 2);
    private final int MOVE_SLEEP_TIME = 60 + (int)(Math.random() * 12 - 6);


    public EnemyTank(int x, int y, TankDirect direct) {
        super(x, y, direct);
    }
    public String getType() {
        return Type;
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
            setDirect(TankDirect.randomDirect());
        }
    }
}

package tankgame;

/**
 * 敌方坦克开火的时间间隔控制线程
 * 将开火和移动分开，避免时间延时碰撞
 */
public class EnemyShot implements Runnable{
    EnemyTank enemyTank;
    private final int SHOT_SLEEP_TIME = 500 + (int)(Math.random() * 100 - 50);
    public EnemyShot(EnemyTank enemyTank) {
        this.enemyTank = enemyTank;
    }

    @Override
    public void run() {
        while(enemyTank.getIsLive()){
            if(enemyTank.shots.isEmpty()){
                Shot shot = null;
                switch (enemyTank.getDirect()) {
                    case UP -> shot = new Shot(enemyTank.getX() + 20 - Shot.getLENGTH() / 2, enemyTank.getY(), TankDirect.UP);
                    case DOWN -> shot = new Shot(enemyTank.getX() + 20 - Shot.getLENGTH() / 2, enemyTank.getY() + 60, TankDirect.DOWN);
                    case LEFT -> shot = new Shot(enemyTank.getX(), enemyTank.getY() + 20 - Shot.getLENGTH() / 2, TankDirect.LEFT);
                    case RIGHT -> shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20 - Shot.getLENGTH() / 2, TankDirect.RIGHT);
                }
                enemyTank.shots.add(shot);
                new Thread(shot).start();
            }
            try {
                Thread.sleep(SHOT_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

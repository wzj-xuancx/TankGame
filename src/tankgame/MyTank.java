package tankgame;

import java.util.Vector;

public class MyTank extends Tank{
    public static final int MAX_SHOT = 5;
    private static final String Type = "MY_TANK";
    public MyTank(int x, int y, TankDirect direct) {
        super(x, y, direct);
    }
    // 开火
    public void shotEnemyTank(){
        Shot shot = null;
        switch (getDirect()) {
            case UP -> shot = new Shot(getX() + 20 - Shot.getLENGTH() / 2, getY(), TankDirect.UP);
            case DOWN -> shot = new Shot(getX() + 20 - Shot.getLENGTH() / 2, getY() + 60, TankDirect.DOWN);
            case LEFT -> shot = new Shot(getX(), getY() + 20 - Shot.getLENGTH() / 2, TankDirect.LEFT);
            case RIGHT -> shot = new Shot(getX() + 60, getY() + 20 - Shot.getLENGTH() / 2, TankDirect.RIGHT);
        }
        new Thread(shot).start();
        shots.add(shot);
    }
    public String getType() {
        return Type;
    }
}

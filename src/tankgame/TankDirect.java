package tankgame;

import java.util.Random;

/**
 * 坦克方向枚举类
 */
public enum TankDirect {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    final int value;
    private TankDirect(int value) {
        this.value = value;
    }

    /**
     * 为了方便随机生成坦克方向
     * @return 返回的随机的坦克方向
     */
    public static TankDirect randomDirect() {
        Random random = new Random();
        return  TankDirect.values()[random.nextInt(TankDirect.values().length)];
    }
}

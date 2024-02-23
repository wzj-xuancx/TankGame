package tankgame;

import java.util.Random;
import java.util.Vector;

/**
 * 坦克方向枚举类
 */
public enum TankDirect {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    final int value;
    TankDirect(int value) {
        this.value = value;
    }

    /**
     * 为了方便随机生成坦克方向
     * @return 返回的随机的坦克方向
     */
    public static TankDirect randomDirect() {
        Random random = new Random();
        return TankDirect.values()[random.nextInt(TankDirect.values().length)];
    }

    /**
     * 为了方便随机生成坦克方向，在提供的方向中选择
     * @param directs 提供的方向
     * @return 返回的随机的坦克方向
     */
    public static TankDirect randomDirect(Vector<TankDirect> directs) {
        Random random = new Random();
        return directs.get(random.nextInt(directs.size()));
    }
}

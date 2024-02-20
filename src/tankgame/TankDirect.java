package tankgame;

public enum TankDirect {
    UP(0), DOWN(1), LEFT(2), RIGHT(3);
    final int value;
    private TankDirect(int value) {
        this.value = value;
    }

    public static TankDirect randomDirect() {
        int random = (int) (Math.random() * 4);
        return switch (random) {
            case 0 -> UP;
            case 1 -> DOWN;
            case 2 -> LEFT;
            case 3 -> RIGHT;
            default -> throw new IllegalStateException("Unexpected value: " + random);
        };
    }
}

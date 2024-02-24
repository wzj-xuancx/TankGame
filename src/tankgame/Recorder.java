package tankgame;

import java.io.*;
import java.util.Vector;

@SuppressWarnings("all")
public class Recorder{
    //记录我方坦克击毁敌方坦克的数量
    private static int killEnemyCount = 0;
    //引用敌方坦克集合
    private static Vector<EnemyTank> enemyTanks;
    //定义IO对象
    private transient static final String recordFile = "src/myRecord.txt";
    //记录我方坦克
    private static MyTank mytank;
    //如果恢复失败,设置初始化
    private static boolean isRecover = true;

    public static void keepRecord(){
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(recordFile));
        ) {
            oos.writeObject(Recorder.killEnemyCount);
            oos.writeObject(Recorder.enemyTanks);
            oos.writeObject(Recorder.mytank);
        }catch (IOException e){
            System.out.println("Can't record the game!");
        }
    }
    public static void getRecord(){
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(recordFile));
        ) {
            Recorder.killEnemyCount = (int) ois.readObject();
            Recorder.enemyTanks = (Vector<EnemyTank>) ois.readObject();
            Recorder.mytank = (MyTank) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            isRecover = false;
        }
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    public static Vector<EnemyTank> getEnemyTanks(){return enemyTanks;}
    public static void setMytank(MyTank mytank) {
        Recorder.mytank = mytank;
    }
    public static MyTank getMytank() {
        return mytank;
    }
    public static int getKillEnemyCount() {
        return killEnemyCount;
    }
    public static void setKillEnemyCount(int killEnemyCount) {
        Recorder.killEnemyCount = killEnemyCount;
    }
    public static void addKillEnemyCount() {
        killEnemyCount++;
    }
    public static boolean getIsRecover() {
        return isRecover;
    }

}

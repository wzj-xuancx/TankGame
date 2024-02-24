package tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * 主界面的设置
 */
public class HspTankGame01 extends JFrame {
    public final static int HEIGHT = 800;
    public final static int WIDTH = 1200;
    public static void main(String[] args) {
        new HspTankGame01("坦克大战");
    }
//    public void mainFrameInit(){
//        JPanel startPanel = new JPanel(new BorderLayout());
//        this.add(startPanel);
//        startPanel.setLayout(null);
//
//        JLabel startLabel = new JLabel("坦克大战");
//        startLabel.setFont(new Font("楷体", Font.BOLD, 100));
//        startLabel.setBounds(WIDTH / 2 - 210, HEIGHT / 2 - 300, 450, 100);
//        startPanel.add(startLabel,BorderLayout.CENTER);
//
//        JButton startButton = new JButton("开始游戏");
//        startButton.setBounds(WIDTH / 2 - 100, HEIGHT / 2 - 125, 200 , 80);
//        startButton.setFont(new Font("宋体", Font.BOLD, 40));
//        startPanel.add(startButton,BorderLayout.CENTER);
//
//        startButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    //点击开始游戏按钮后，移除开始界面，显示游戏界面
//                    remove(startPanel);
//                    startGame();
//                }
//            }
//        );
//
//    }
    public HspTankGame01(String title){
        super(title);
        //播放背景音乐
        AePlayWave aePlayWave = new AePlayWave("src\\musics\\preGameBackground.wav");
        new Thread(aePlayWave).start();

        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainFrameInit();
        startGame();
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }

    public void startGame(){
        MyPanel mp;
        Recorder.getRecord();
        System.out.println("Welcome to Tank War!");
        System.out.println("1. New Game");
        System.out.println("2. Continue");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        if(!Recorder.getIsRecover() || (choice == 2 && !Recorder.getMytank().getIsLive()) || choice == 1){
            if(choice == 2)
                System.out.println("The last game is over!");
            //做一个进度条效果
            System.out.println("Loading the new game...");
            mp = new MyPanel("1");
        }else{
            System.out.println("Loading the last game...");
            mp = new MyPanel("2");
        }
        this.add(mp);
        this.addKeyListener(mp);
        new Thread(mp).start();
    }
}

package tankgame;

import javax.sound.sampled.*;
import java.io.File;

public class AePlayWave implements Runnable {
    private final String filename;
    private boolean isLive = true;
    public AePlayWave(String filename){
        this.filename = filename;
    }
    public boolean getIsLive(){
        return isLive;
    }

    @Override
    public void run() {
        isLive = true;
        File soundFile = new File(filename);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        }

        auline.start();
        int nBytesRead = 0;
        //缓冲
        byte[] abData = new byte[512];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ;
        } finally {
            auline.drain();
            auline.close();
            isLive = false;
        }
    }
}

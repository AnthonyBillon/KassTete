package cassetete.models;

import javafx.application.Platform;

import java.io.Serializable;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class MTimer extends Observable implements Serializable {
    private int sec;
    private Timer t;
    public void start(){
        t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        sec++;
                        setChanged();
                        notifyObservers();
                    }
                });

            }
        }, 1000, 1000);

    }

    public void stop(){
        t.cancel();
    }

    public int getTimerValue(){
        return sec;
    }

    public String getFormatedTime(){
        int s = sec%60;
        int m = sec/60;

        String sstr="";
        String mstr="";

        if(s<10) sstr = "0"+s;
        else  sstr = ""+s;

        if(m<10) mstr = "0"+m;
        else mstr = ""+m;

        return mstr+":"+sstr;
    }
}

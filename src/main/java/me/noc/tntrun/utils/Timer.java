package me.noc.tntrun.utils;

import cn.nukkit.scheduler.NukkitRunnable;
import me.noc.tntrun.TnTRun;

public class Timer {

    private TimerListener timerListener;
    private boolean paused;
    private NukkitRunnable runnable;
    private int time;

    public Timer(TimerListener timerListener, int starttime) {
        this.timerListener = timerListener;
        time = starttime;
        paused = true;

        runnable = new NukkitRunnable() {
            @Override
            public void run() {
                if (!isPaused()) {
                    if (time == 0) {
                        timerListener.onStop();
                        cancel();
                        return;
                    }
                    timerListener.onTick(time);
                    time--;
                }
            }
        };
        runnable.runTaskTimer(TnTRun.getPlugin(), 0, 20);
    }

    public int getTime() {
        return time;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        if (paused) {
            timerListener.onPause(time);
        } else {
            timerListener.onResume(time);
        }
        this.paused = paused;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

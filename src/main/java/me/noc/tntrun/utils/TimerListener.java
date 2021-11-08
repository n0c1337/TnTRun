package me.noc.tntrun.utils;

public interface TimerListener {
    void onTick(int time);
    void onPause(int time);
    void onResume(int time);
    void onStop();
}

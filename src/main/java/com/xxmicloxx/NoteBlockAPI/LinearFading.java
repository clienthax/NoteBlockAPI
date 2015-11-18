package com.xxmicloxx.NoteBlockAPI;

public class LinearFading implements FadeType {

    @Override public double calculateVolume(int time, int duration, double fromVolume, double toVolume) {
        return (toVolume - fromVolume) * time / duration + fromVolume;
    }

}

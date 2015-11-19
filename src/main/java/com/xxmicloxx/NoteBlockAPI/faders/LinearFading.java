package com.xxmicloxx.NoteBlockAPI.faders;

import com.xxmicloxx.NoteBlockAPI.interfaces.FadeType;

public class LinearFading implements FadeType {

    @Override public double calculateVolume(int time, int duration, double fromVolume, double toVolume) {
        return (toVolume - fromVolume) * time / duration + fromVolume;
    }

}

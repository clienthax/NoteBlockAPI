package com.xxmicloxx.NoteBlockAPI;

public interface FadeType {

    /**
     * Calculates the volume at a specified position in time.
     * @param time Time passed
     * @param duration Total duration of the fading
     * @param fromVolume The volume at time = 0
     * @param toVolume Targeted volume at time = duration
     * @return The volume
     */
    double calculateVolume(int time, int duration, double fromVolume, double toVolume);

}
package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.effect.sound.SoundTypes;

public class Instrument {

    public static SoundType getInstrument(byte instrument) {
        switch (instrument) {
            case 0:
                return SoundTypes.NOTE_PIANO;
            case 1:
                return SoundTypes.NOTE_BASS_GUITAR;
            case 2:
                return SoundTypes.NOTE_BASS_DRUM;
            case 3:
                return SoundTypes.NOTE_SNARE_DRUM;
            case 4:
                return SoundTypes.NOTE_STICKS;
            default:
                System.out.println("unknown instrument "+instrument);
                return SoundTypes.NOTE_PIANO;
        }
    }

}

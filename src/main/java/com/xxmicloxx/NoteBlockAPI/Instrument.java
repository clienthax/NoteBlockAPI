package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.effect.sound.SoundTypes;

public enum  Instrument {

    PIANO(SoundTypes.NOTE_PIANO),
    BASS_GUITAR(SoundTypes.NOTE_BASS_GUITAR),
    BASEE_DRUM(SoundTypes.NOTE_BASS_DRUM),
    SNARE_DRUM(SoundTypes.NOTE_SNARE_DRUM),
    STICKS(SoundTypes.NOTE_STICKS);

    private final SoundType sound;

    Instrument(SoundType sound) {
        this.sound = sound;
    }

    public SoundType getSound() {
        return this.sound;
    }

    public static SoundType getInstrument(byte instrument) {
        return Instrument.values()[instrument].getSound();
    }

}

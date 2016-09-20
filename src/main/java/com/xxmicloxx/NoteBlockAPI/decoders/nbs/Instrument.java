package com.xxmicloxx.NoteBlockAPI.decoders.nbs;

import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.effect.sound.SoundTypes;

public enum  Instrument {

    PIANO(SoundTypes.BLOCK_NOTE_HARP),
    BASS_GUITAR(SoundTypes.BLOCK_NOTE_BASS),
    BASEE_DRUM(SoundTypes.BLOCK_NOTE_BASEDRUM),
    SNARE_DRUM(SoundTypes.BLOCK_NOTE_SNARE),
    STICKS(SoundTypes.BLOCK_NOTE_HAT),
    PLING(SoundTypes.BLOCK_NOTE_PLING);

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

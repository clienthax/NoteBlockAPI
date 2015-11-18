package com.xxmicloxx.NoteBlockAPI;

public class Note {

    private Instrument instrument;
    private byte key;

    public Note(Instrument instrument, byte key) {
        this.instrument = instrument;
        this.key = key;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public byte getKey() {
        return key;
    }

    public void setKey(byte key) {
        this.key = key;
    }
}

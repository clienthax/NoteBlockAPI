package com.xxmicloxx.NoteBlockAPI.decoders.nbs;

import static org.spongepowered.api.data.type.NotePitches.*;

import org.spongepowered.api.data.type.NotePitch;

public final class Note {
	public static final NotePitch[] PITCHES = new NotePitch[] {
			F_SHARP0, G0, G_SHARP0, A1, A_SHARP1, B1, C1, C_SHARP1, D1, D_SHARP1, E1, F1, F_SHARP1,
			G1, G_SHARP1, A2, A_SHARP2, B2, C2, C_SHARP2, D2, D_SHARP2, E2, F2, F_SHARP2 };
	
    public final Instrument instrument;
    public final byte nbsKey, mcKey;
    public final NotePitch notePitch;
    public final double pitch;

    public Note(Instrument instrument, byte nbsKey) {
        this.instrument = instrument;
        this.nbsKey = nbsKey;
        this.mcKey = (byte) (nbsKey -33);
        this.notePitch = getNotePitch(this.mcKey);
        this.pitch = getPitch(this.mcKey);
    }
    
    public static double getPitch(byte mcKey) {
    	return Math.pow(2.0, (double) (mcKey - 12) / 12D);
    }
    
    public static NotePitch getNotePitch(byte mcKey) {
    	if (mcKey < 0 || mcKey >= PITCHES.length)
    		return F_SHARP0;
    	return PITCHES[mcKey];
    }
}

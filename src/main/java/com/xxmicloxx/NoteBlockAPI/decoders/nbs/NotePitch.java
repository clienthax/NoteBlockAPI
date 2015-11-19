package com.xxmicloxx.NoteBlockAPI.decoders.nbs;

import org.spongepowered.api.data.type.NotePitches;

public enum NotePitch {

    NOTE_0(0, 0.5F, NotePitches.F_SHARP0),
    NOTE_1(1, 0.53F, NotePitches.G0),
    NOTE_2(2, 0.56F, NotePitches.G_SHARP0),
    NOTE_3(3, 0.6F, NotePitches.A1),
    NOTE_4(4, 0.63F, NotePitches.A_SHARP1),
    NOTE_5(5, 0.67F, NotePitches.B1),
    NOTE_6(6, 0.7F, NotePitches.C1),
    NOTE_7(7, 0.76F, NotePitches.C_SHARP1),
    NOTE_8(8, 0.8F, NotePitches.D1),
    NOTE_9(9, 0.84F, NotePitches.D_SHARP1),
    NOTE_10(10, 0.9F, NotePitches.E1),
    NOTE_11(11, 0.94F, NotePitches.F1),
    NOTE_12(12, 1.0F, NotePitches.F_SHARP1),
    NOTE_13(13, 1.06F, NotePitches.G1),
    NOTE_14(14, 1.12F, NotePitches.G_SHARP1),
    NOTE_15(15, 1.18F, NotePitches.A2),
    NOTE_16(16, 1.26F, NotePitches.A_SHARP2),
    NOTE_17(17, 1.34F, NotePitches.B2),
    NOTE_18(18, 1.42F, NotePitches.C2),
    NOTE_19(19, 1.5F, NotePitches.C_SHARP2),
    NOTE_20(20, 1.6F, NotePitches.D2),
    NOTE_21(21, 1.68F, NotePitches.D_SHARP2),
    NOTE_22(22, 1.78F, NotePitches.E2),
    NOTE_23(23, 1.88F, NotePitches.F2),
    NOTE_24(24, 2.0F, NotePitches.F_SHARP2);

    public org.spongepowered.api.data.type.NotePitch spongeNotePitch;
    public int note;
    public float pitch;

    NotePitch(int note, float pitch, org.spongepowered.api.data.type.NotePitch spongeNotePitch) {
        this.note = note;
        this.pitch = pitch;
        this.spongeNotePitch = spongeNotePitch;
    }

    public static org.spongepowered.api.data.type.NotePitch getSpongeNotePitch(int note) {
        if(note >= 0 && note < values().length) {
            return values()[note].spongeNotePitch;
        }
        return NOTE_0.spongeNotePitch;
    }

    public static float getPitch(int note) {
        if(note >= 0 && note < values().length) {
            return values()[note].pitch;
        }
        return 0.0f;
    }
}
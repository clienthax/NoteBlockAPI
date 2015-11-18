package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.event.impl.AbstractEvent;

public class SongEndEvent extends AbstractEvent {

    private SongPlayer song;

    public SongEndEvent(SongPlayer song) {
        this.song = song;
    }

    public SongPlayer getSongPlayer() {
        return song;
    }
}

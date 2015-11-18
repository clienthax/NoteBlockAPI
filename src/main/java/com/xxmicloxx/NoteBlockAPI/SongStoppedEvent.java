package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.event.impl.AbstractEvent;

public class SongStoppedEvent extends AbstractEvent {

    private SongPlayer song;

    public SongStoppedEvent(SongPlayer song) {
        this.song = song;
    }

    public SongPlayer getSongPlayer() {
        return song;
    }

}

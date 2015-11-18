package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.SongPlayer;
import org.spongepowered.api.event.impl.AbstractEvent;

public abstract class SongPlayerEvent extends AbstractEvent {

    private final SongPlayer songPlayer;

    public SongPlayerEvent(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }

    public SongPlayer getSongPlayer() {
        return songPlayer;
    }

}

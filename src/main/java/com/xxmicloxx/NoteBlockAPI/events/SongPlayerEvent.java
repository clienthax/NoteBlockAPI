package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;
import com.xxmicloxx.NoteBlockAPI.players.SongPlayer;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.impl.AbstractEvent;

public abstract class SongPlayerEvent extends AbstractEvent {

    private final SongPlayer songPlayer;

    public SongPlayerEvent(SongPlayer songPlayer) {
        this.songPlayer = songPlayer;
    }

    public SongPlayer getSongPlayer() {
        return songPlayer;
    }

    public Cause getCause() {
        return Cause.of(NoteBlockPlayerMain.plugin);
    }

}

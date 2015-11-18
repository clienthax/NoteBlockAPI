package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.SongPlayer;
import org.spongepowered.api.event.impl.AbstractEvent;

public class SongEndEvent extends SongPlayerEvent {

    public SongEndEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

}

package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.players.SongPlayer;

public class SongStoppedEvent extends SongPlayerEvent {

    public SongStoppedEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }
}

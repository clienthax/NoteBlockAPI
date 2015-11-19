package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.players.SongPlayer;

public class SongEndEvent extends SongPlayerEvent {

    public SongEndEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

}

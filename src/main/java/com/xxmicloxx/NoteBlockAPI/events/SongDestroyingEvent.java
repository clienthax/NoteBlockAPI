package com.xxmicloxx.NoteBlockAPI.events;

import com.xxmicloxx.NoteBlockAPI.players.SongPlayer;
import org.spongepowered.api.event.Cancellable;

public class SongDestroyingEvent extends SongPlayerEvent implements Cancellable {

    private boolean cancelled = false;

    public SongDestroyingEvent(SongPlayer songPlayer) {
        super(songPlayer);
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

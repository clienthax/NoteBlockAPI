package com.xxmicloxx.NoteBlockAPI.players;

import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Song;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Layer;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Note;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public class PositionSongPlayer extends SongPlayer {

    private Location<World> targetLocation;

    public PositionSongPlayer(Song song) {
        super(song);
    }

    public Location<World> getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Location<World> targetLocation) {
        this.targetLocation = targetLocation;
    }

    @Override
    public void playTick(Player p, int tick) {
        if (!p.getWorld().getUniqueId().equals(targetLocation.getExtent().getUniqueId())) {
            // not in same world
            return;
        }
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            
            float volume = (l.getVolume() * (int) this.volume * (int) playerVolume) / 1000000f;
            p.playSound(note.instrument.getSound(), targetLocation.getPosition(), volume, note.pitch);
        }
    }

    @Override
    public void playAreaTick(int tick) {
        NoteBlockPlayerMain.plugin.getGame().getServer().getOnlinePlayers().stream()
                .filter(player -> player.getLocation().getExtent().equals(targetLocation.getExtent()))
                .filter(player -> player.getLocation().getPosition().distance(targetLocation.getPosition()) < 16).forEach(player -> playTick(player,
                tick));
    }
}

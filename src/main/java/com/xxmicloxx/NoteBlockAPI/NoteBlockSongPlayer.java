package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.effect.particle.NoteParticle;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import uk.co.haxyshideout.musicbox.MusicBox;

/**
 * Created with IntelliJ IDEA.
 * User: ml
 * Date: 07.12.13
 * Time: 12:56
 */
public class NoteBlockSongPlayer extends SongPlayer {

    private Location<World> noteBlockLocation;

    public NoteBlockSongPlayer(Song song) {
        super(song);
    }

    public Location<World> getNoteBlockLocation() {
        return noteBlockLocation;
    }

    public void setNoteBlockLocation(Location<World> noteBlockLocation) {
        this.noteBlockLocation = noteBlockLocation;
    }

    @Override
    public void playTick(Player p, int tick) {
        //noinspection ConstantConditions
        if(noteBlockLocation.getBlock().getType() != BlockTypes.JUKEBOX && noteBlockLocation.getBlock().getType() != BlockTypes.NOTEBLOCK) {
            return;
        }
        if (!p.getWorld().getUniqueId().equals(noteBlockLocation.getExtent().getUniqueId())) {
            // not in same world
            return;
        }
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerHashMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            ParticleEffect particleEffect = NoteBlockPlayerMain.plugin.game.getRegistry().createBuilder(NoteParticle.Builder.class).note(
                    NotePitch.getSpongeNotePitch(note.getKey() - 33)).type(ParticleTypes.NOTE).build();
            p.spawnParticles(particleEffect, noteBlockLocation.getPosition().add(random.nextFloat(), 1, random.nextFloat()));
            p.playSound(Instrument.getInstrument(note.getInstrument()),
                    noteBlockLocation.getPosition(),
                    (l.getVolume() * (int) volume * (int) playerVolume) / 1000000f,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }

    @Override
    public void playAreaTick(int tick) {
        MusicBox.getInstance().game.getServer().getOnlinePlayers().stream()
                .filter(player -> player.getLocation().getExtent().equals(noteBlockLocation.getExtent()))
                .filter(player -> player.getLocation().getPosition().distance(noteBlockLocation.getPosition()) < 16).forEach(player -> playTick
                (player, tick));
    }
}

package com.xxmicloxx.NoteBlockAPI.players;

import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Song;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Instrument;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Layer;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.Note;
import com.xxmicloxx.NoteBlockAPI.decoders.nbs.NotePitch;
import org.spongepowered.api.entity.living.player.Player;

public class RadioSongPlayer extends SongPlayer {

    public RadioSongPlayer(Song song) {
        super(song);
    }

    @Override
    public void playTick(Player p, int tick) {
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            Instrument instrument = note.getInstrument();
            float volume = (l.getVolume() * (int) this.volume * (int) playerVolume) / 1000000f;
            p.playSound(instrument.getSound(),
                    p.getLocation().getPosition(),
                    volume,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }

    @Override
    public void playAreaTick(int tick) {
        NoteBlockPlayerMain.plugin.game.getServer().getOnlinePlayers().stream().forEach(player -> playTick(player, tick));
    }

}

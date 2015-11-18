package com.xxmicloxx.NoteBlockAPI;

import org.spongepowered.api.entity.living.player.Player;
import uk.co.haxyshideout.musicbox.MusicBox;

public class RadioSongPlayer extends SongPlayer {

    public RadioSongPlayer(Song song) {
        super(song);
    }

    @Override
    public void playTick(Player p, int tick) {
        byte playerVolume = NoteBlockPlayerMain.getPlayerVolume(p);

        for (Layer l : song.getLayerHashMap().values()) {
            Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            p.playSound(Instrument.getInstrument(note.getInstrument()),
                    p.getLocation().getPosition(),
                    (l.getVolume() * (int) volume * (int) playerVolume) / 1000000f,
                    NotePitch.getPitch(note.getKey() - 33));
        }
    }

    @Override
    public void playAreaTick(int tick) {
        MusicBox.getInstance().game.getServer().getOnlinePlayers().stream().forEach(player -> playTick(player, tick));
    }

}

package com.xxmicloxx.NoteBlockAPI;

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

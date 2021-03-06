package com.xxmicloxx.NoteBlockAPI;

import com.xxmicloxx.NoteBlockAPI.players.SongPlayer;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Plugin(name = "NoteBlockAPI", id = "noteblockapi", version = "2.2")
public class NoteBlockPlayerMain {

    public static NoteBlockPlayerMain plugin;
    public Map<Player, List<SongPlayer>> playingSongs = new WeakHashMap<>();
    public Map<Player, Byte> playerVolume = new WeakHashMap<>();

    public Game getGame() {
        return Sponge.getGame();
    }

    public static boolean isReceivingSong(Player p) {
        List<SongPlayer> songs = plugin.playingSongs.get(p);
        return songs != null && !songs.isEmpty();
    }

    public static void stopPlaying(Player p) {
        List<SongPlayer> songs = plugin.playingSongs.get(p);
        if(songs == null) {
            return;
        }
        for (SongPlayer s : songs) {
            s.removePlayer(p);
        }
    }

    public static void setPlayerVolume(Player p, byte volume) {
        plugin.playerVolume.put(p, volume);
    }

    public static byte getPlayerVolume(Player p) {
        Byte b = plugin.playerVolume.get(p);
        return b == null ? 100 : b;
    }

    @Listener
    public void onEnable(GameInitializationEvent event) {
        plugin = this;
    }

}

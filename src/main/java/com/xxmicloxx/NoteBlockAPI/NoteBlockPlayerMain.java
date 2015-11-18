package com.xxmicloxx.NoteBlockAPI;

import com.google.inject.Inject;
import org.spongepowered.api.Game;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.scheduler.Task;

import java.util.ArrayList;
import java.util.HashMap;

@Plugin(name = "NoteBlockAPI", id = "NoteBlockAPI", version = "1.0")
public class NoteBlockPlayerMain {

    public static NoteBlockPlayerMain plugin;
    public HashMap<String, ArrayList<SongPlayer>> playingSongs = new HashMap<>();
    public HashMap<String, Byte> playerVolume = new HashMap<>();

    @Inject
    public Game game;

    public static boolean isReceivingSong(Player p) {
        return ((plugin.playingSongs.get(p.getName()) != null) && (!plugin.playingSongs.get(p.getName()).isEmpty()));
    }

    public static void stopPlaying(Player p) {
        if (plugin.playingSongs.get(p.getName()) == null) {
            return;
        }
        for (SongPlayer s : plugin.playingSongs.get(p.getName())) {
            s.removePlayer(p);
        }
    }

    public static void setPlayerVolume(Player p, byte volume) {
        plugin.playerVolume.put(p.getName(), volume);
    }

    public static byte getPlayerVolume(Player p) {
        Byte b = plugin.playerVolume.get(p.getName());
        if (b == null) {
            b = 100;
            plugin.playerVolume.put(p.getName(), b);
        }
        return b;
    }

    @Listener
    public void onEnable(GameInitializationEvent event) {
        plugin = this;
    }

    @Listener
    public void onDisable(GameStoppingServerEvent event) {
        game.getScheduler().getScheduledTasks(this).forEach(Task::cancel);
    }
}

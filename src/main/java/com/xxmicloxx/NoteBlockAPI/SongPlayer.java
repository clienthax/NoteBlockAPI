package com.xxmicloxx.NoteBlockAPI;

import com.xxmicloxx.NoteBlockAPI.events.SongDestroyingEvent;
import com.xxmicloxx.NoteBlockAPI.events.SongEndEvent;
import com.xxmicloxx.NoteBlockAPI.events.SongStoppedEvent;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.scheduler.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

public abstract class SongPlayer {

    protected final Song song;
    protected boolean playing = false;
    protected short tick = -1;
    protected Set<Player> players = Collections.newSetFromMap(new WeakHashMap<Player, Boolean>());
    protected boolean autoDestroy = false;
    protected boolean destroyed = false;
    protected byte fadeTarget = 100;
    protected byte volume = 100;
    protected byte fadeStart = volume;
    protected int fadeDuration = 60;
    protected int fadeDone = 0;
    protected FadeType fadeType = new LinearFading();
    private Task timerTask;
    protected boolean areaMusic = false;
    protected Random random = new Random();

    public SongPlayer(Song song) {
        this.song = song;
    }

    public FadeType getFadeType() {
        return fadeType;
    }

    public void setFadeType(FadeType fadeType) {
        this.fadeType = fadeType;
    }

    public byte getFadeTarget() {
        return fadeTarget;
    }

    public void setFadeTarget(byte fadeTarget) {
        this.fadeTarget = fadeTarget;
    }

    public byte getFadeStart() {
        return fadeStart;
    }

    public void setFadeStart(byte fadeStart) {
        this.fadeStart = fadeStart;
    }

    public int getFadeDuration() {
        return fadeDuration;
    }

    public void setFadeDuration(int fadeDuration) {
        this.fadeDuration = fadeDuration;
    }

    public int isFadeDone() {
        return fadeDone;
    }

    public void setFadeDone(int fadeDone) {
        this.fadeDone = fadeDone;
    }

    public void setAreaMusic(boolean areaMusic) {
        this.areaMusic = areaMusic;
    }

    public boolean isAreaMusic() {
        return this.areaMusic;
    }

    protected void calculateFade() {
        if (fadeDone == fadeDuration) {
            return; // no fade today
        }
        double targetVolume = fadeType.calculateVolume(fadeDone, fadeDuration, fadeStart, fadeTarget);
        setVolume((byte) targetVolume);
        fadeDone++;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void addPlayer(Player p) {
        if (players.add(p)) {
            List<SongPlayer> songs = NoteBlockPlayerMain.plugin.playingSongs.get(p);
            if(songs == null) {
                songs = new ArrayList<>();
                NoteBlockPlayerMain.plugin.playingSongs.put(p, songs);
            }
            songs.add(this);
        }
    }

    public boolean getAutoDestroy() {
        return autoDestroy;
    }

    public void setAutoDestroy(boolean autoDestroy) {
        this.autoDestroy = autoDestroy;
    }

    public abstract void playTick(Player p, int tick);

    public abstract void playAreaTick(int tick);

    public void destroy() {
        SongDestroyingEvent event = new SongDestroyingEvent(this);
        NoteBlockPlayerMain.plugin.game.getEventManager().post(event);
        if(event.isCancelled()) {
            return;
        }
        timerTask.cancel();
        destroyed = true;
        playing = false;
        setTick((short) -1);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        if(this.playing == playing) {
            return;
        }
        this.playing = playing;
        if(playing) {
            timerTask = NoteBlockPlayerMain.plugin.game.getScheduler().createTaskBuilder().intervalTicks(((Float)song.getDelay()).intValue())
                    .execute(new Timer()).submit(NoteBlockPlayerMain.plugin);
        } else {
            NoteBlockPlayerMain.plugin.game.getEventManager().post(new SongStoppedEvent(this));
        }
    }

    public short getTick() {
        return tick;
    }

    public void setTick(short tick) {
        this.tick = tick;
    }

    public void removePlayer(Player p) {
        if(!players.remove(p)) {
            return;
        }
        List<SongPlayer> songs = NoteBlockPlayerMain.plugin.playingSongs.get(p);
        songs.remove(this);
        if(songs.isEmpty()) {
            NoteBlockPlayerMain.plugin.playingSongs.remove(p);
        }
        if(players.isEmpty() && autoDestroy) {
            NoteBlockPlayerMain.plugin.game.getEventManager().post(new SongEndEvent(this));
            destroy();
        }
    }

    public byte getVolume() {
        return volume;
    }

    public void setVolume(byte volume) {
        this.volume = volume;
    }

    public Song getSong() {
        return song;
    }

    private class Timer implements Runnable {//TODO area ticks

        @Override public void run() {
            if(destroyed) {
                return;
            }
            if(playing) {
                tick++;
                if(tick > song.getLength()) {
                    playing = false;
                    tick = -1;
                    NoteBlockPlayerMain.plugin.game.getEventManager().post(new SongEndEvent(SongPlayer.this));
                    if(autoDestroy) {
                        destroy();
                        return;
                    }
                }
                if(!areaMusic) {
                    for(Player player : players) {
                        playTick(player, tick);
                    }
                } else {
                    playAreaTick(tick);
                }
            }
        }
    }
}

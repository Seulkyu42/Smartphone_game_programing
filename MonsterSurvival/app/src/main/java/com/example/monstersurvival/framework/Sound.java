package com.example.monstersurvival.framework;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.monstersurvival.game.scenes.MainGame;

import java.util.HashMap;

public class Sound {
    protected static MediaPlayer mediaPlayer;
    protected static SoundPool soundPool;

    public static void playMusic(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(GameView.view.getContext(), resId);
        mediaPlayer.setLooping(true);

        if(MainGame.getInstance().soundOn == true) {
            mediaPlayer.start();
        }
    }
    public static void stopMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer = null;
    }
    public static void pauseMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
    }
    public static void resumeMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.start();
    }

    private static HashMap<Integer, Integer> soundIdMap = new HashMap<>();
    public static void playEffect(Context context, int resId) {
        SoundPool pool = getSoundPool();
        int soundId;
        if (soundIdMap.containsKey(resId)) {
            soundId = soundIdMap.get(resId);
        } else {
            soundId = pool.load(context, resId, 1);
            soundIdMap.put(resId, soundId);
        }
        // int streamId =

        if(MainGame.getInstance().soundOn2 == true) {
            pool.play(soundId, 1f, 1f, 1, 0, 1f);
        }
    }

    private static SoundPool getSoundPool() {
        if (soundPool != null) return soundPool;

        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(3)
                .build();
        return soundPool;
    }
}

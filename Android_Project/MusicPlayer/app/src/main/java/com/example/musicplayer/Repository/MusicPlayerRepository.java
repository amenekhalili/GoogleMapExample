package com.example.musicplayer.Repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.musicplayer.Model.Music;
import com.example.musicplayer.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicPlayerRepository {

    private static MusicPlayerRepository sInstance;
    private Context mContext;
    public MediaPlayer mMediaPlayer;
    private List<Music> mMusicList = new ArrayList<>();


    public static MusicPlayerRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new MusicPlayerRepository(context);
        return sInstance;
    }


    private MusicPlayerRepository(Context context) {
        mContext = context.getApplicationContext();

        CreateMusic();

    }

    public List<Music> getMusic() {
        return mMusicList;
    }

    public void CreateMusic() {

        ContentResolver contentResolver = mContext.getContentResolver();

        Uri  uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0" ;
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC" ;
        Cursor cursor = contentResolver.query(uri ,
                null ,
                selection ,
                null ,
                sortOrder);

        if(cursor != null && cursor.getCount() > 0){

            while(cursor.moveToNext()){
                String data  = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                String title = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String album = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                String artist = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));

                mMusicList.add(new Music(data , title , album ,artist));


            }

        }
 cursor.close();


    }



    public void playMusic(Music music) throws IOException {
        if(music == null)
            return;
        Uri  uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        mMediaPlayer = MediaPlayer.create(mContext ,uri );

        long id = mMediaPlayer.getAudioSessionId();
        Uri contentUri = ContentUris.withAppendedId(
                uri,id);

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA).build());

        mMediaPlayer.setDataSource(mContext,contentUri);

        mMediaPlayer.start();


    }




}

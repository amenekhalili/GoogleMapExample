package com.example.beatbox.Repository;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.beatbox.Model.Sound;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBoxRepository {

    public static final String TAG = "beatBox";
    private static String ASSET_FOLDER = "Pixel Sounds";
    private static BeatBoxRepository sInstance;
    private Context mContext;

    public static BeatBoxRepository getInstance(Context context) {
        if(sInstance == null)
            sInstance = new BeatBoxRepository(context);

        return sInstance;
    }

    private BeatBoxRepository(Context context) {
        mContext = context.getApplicationContext();
    }


    public List<Sound> getSounds(){

        List<Sound> sounds = new ArrayList<>();
        AssetManager assetManager = mContext.getAssets();
        try {
            String[] fileNames =   assetManager.list(ASSET_FOLDER);
            for (String fileName : fileNames) {
                Log.i(TAG , fileName);

                String assetPath = ASSET_FOLDER + File.separator + fileName;
                Sound sound = new Sound(assetPath);

                sounds.add(sound);


            }
        } catch (IOException e) {
            Log.e(TAG,e.getMessage(),e);
        }

        return sounds;

    }
}

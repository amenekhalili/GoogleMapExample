package com.example.musicplayer.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.musicplayer.Controller.Fragment.MusicPlayerFragment;

public class MusicPlayerActivity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return MusicPlayerFragment.newInstance();
    }
}
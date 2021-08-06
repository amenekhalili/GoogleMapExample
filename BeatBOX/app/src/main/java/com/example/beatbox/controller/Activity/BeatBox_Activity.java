package com.example.beatbox.controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.beatbox.controller.Fragment.BeatBoxFragment;

public class BeatBox_Activity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return BeatBoxFragment.newInstance();
    }

}
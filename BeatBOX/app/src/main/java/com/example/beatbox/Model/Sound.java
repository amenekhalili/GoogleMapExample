package com.example.beatbox.Model;

import java.io.File;

public class Sound {
    private String mName;
    private String assetPath;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getAssetPath() {
        return assetPath;
    }

    public void setAssetPath(String assetPath) {
        this.assetPath = assetPath;
    }

    public Sound( String assetPath) {
        this.assetPath = assetPath;

        String[] sections = assetPath.split(File.separator);
        String fileNameWithExtension = sections[sections.length - 1];
        int lastDotIndex = fileNameWithExtension.lastIndexOf(".");
        mName = fileNameWithExtension.substring(0 , lastDotIndex);
    }

    public Sound() {
    }
}

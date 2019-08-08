package com.yakymovych.simon.yboxsdk.custom;

public class MappingAlgorithms {
    static {
        System.loadLibrary("MappingAlgorithms");
    }


    private native byte[] prepareBytes(float x,float y,char buttons);
}
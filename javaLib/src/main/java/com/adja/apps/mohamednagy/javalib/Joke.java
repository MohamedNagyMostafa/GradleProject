package com.adja.apps.mohamednagy.javalib;

public class Joke {
    private static final int JOKE_COUNT = 100;

    private String[] mJokes;

    public Joke(){
        init();
    }

    private void init(){
        mJokes = new String[JOKE_COUNT];
        for(int i = 0; i < mJokes.length; i++){
            mJokes[i] = "Funny joke("+String.valueOf(i)+")";
        }
    }

    public String getJoke(int index){
        return mJokes[index];
    }

}

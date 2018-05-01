package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.endpoint.EndPointAsyncTask;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Mohamed Nagy on 4/30/2018 .
 * Project FinalProject
 * Time    4:29 PM
 */
@RunWith(AndroidJUnit4.class)
public class JokeVerification {

    @Test
    public void retrievingJokes_AsyncTaskExecute(){
        new EndPointAsyncTask(new EndPointAsyncTask.EndPointListener<String>() {
            @Override
            public void onFinishedListener(String data) {
                Assert.assertNotEquals(data, "");
            }
        }).execute(5);
    }



}

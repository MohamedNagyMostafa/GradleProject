package com.udacity.gradle.builditbigger.endpoint;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by Mohamed Nagy on 4/30/2018 .
 * Project FinalProject
 * Time    1:52 PM
 */

public class EndPointAsyncTask extends AsyncTask<Integer,Void,String> {
    private static MyApi mApiService = null;
    private EndPointListener<String> mEndPointListener;

    public EndPointAsyncTask(EndPointListener<String> endPointListener){
        mEndPointListener = endPointListener;
    }

    @Override
    protected String  doInBackground(Integer... integers) {
        if(mApiService == null){
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            mApiService = builder.build();
        }

        int jokeIndex = integers[0];

        try {
            return mApiService.getJoke(jokeIndex).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String data) {
        Log.e("data",data);
        mEndPointListener.onFinishedListener(data);
    }

    public interface EndPointListener<T>{
        void onFinishedListener(T data);
    }

}

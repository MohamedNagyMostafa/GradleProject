package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.adja.apps.mohamednagy.jokescreenlib.JokeActivity;
import com.udacity.gradle.builditbigger.databinding.ActivityMainBinding;
import com.udacity.gradle.builditbigger.endpoint.EndPointAsyncTask;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private static final int JOKES_COUNT = 100;

    private Random mRandomJokeIndex;
    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityMainBinding = DataBindingUtil.bind(findViewById(R.id.parent_activity));
        mActivityMainBinding.progressBar.setIndeterminate(true);
        mRandomJokeIndex = new Random();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        int jokeIndex = mRandomJokeIndex.nextInt(JOKES_COUNT);

        {
            mActivityMainBinding.progressBar.setVisibility(View.VISIBLE);
            setClickButtonEnabled(false);
        }

        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask(new EndPointAsyncTask.EndPointListener<String>() {
            @Override
            public void onFinishedListener(String data) {
                Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra(JokeActivity.Extras.JOKE_EXT, data);

                {
                    mActivityMainBinding.progressBar.setVisibility(View.INVISIBLE);
                    setClickButtonEnabled(true);
                }
                startActivity(intent);

            }
        });

        endPointAsyncTask.execute(jokeIndex);
    }

    private void setClickButtonEnabled(boolean state){
        Button tellJokeButton = findViewById(R.id.fragment).findViewById(R.id.tell_joke_button);
        tellJokeButton.setEnabled(state);
    }

    private boolean getClickButtonEnabledState(){
        Button tellJokeButton = findViewById(R.id.fragment).findViewById(R.id.tell_joke_button);
        return tellJokeButton.isEnabled();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("save", String.valueOf(getClickButtonEnabledState()) + " " + String.valueOf(mActivityMainBinding.progressBar.getVisibility()));

        outState.putInt(Extras.PROGRESS_BAR_STATE, mActivityMainBinding.progressBar.getVisibility());
        outState.putBoolean(Extras.TELL_JOKE_STATE, getClickButtonEnabledState());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null) {
            Integer previousVisibilityState = savedInstanceState.getInt(Extras.PROGRESS_BAR_STATE);
            Boolean previousTellJokeButtonState = savedInstanceState.getBoolean(Extras.TELL_JOKE_STATE);
            Log.e("state", String.valueOf(previousTellJokeButtonState) + " " + String.valueOf(previousVisibilityState));
            mActivityMainBinding.progressBar.setVisibility(previousVisibilityState);
            setClickButtonEnabled(previousTellJokeButtonState);
        }

    }

    private static class Extras{
        private static final String PROGRESS_BAR_STATE = "pro_st_ex";
        private static final String TELL_JOKE_STATE = "btn_st_ex";
    }
}

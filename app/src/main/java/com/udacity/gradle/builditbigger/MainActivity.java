package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.adja.apps.mohamednagy.jokescreenlib.JokeActivity;
import com.udacity.gradle.builditbigger.endpoint.EndPointAsyncTask;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private static final int JOKES_COUNT = 100;

    private Random mRandomJokeIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask(new EndPointAsyncTask.EndPointListener<String>() {
            @Override
            public void onFinishedListener(String data) {
                Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                intent.putExtra(JokeActivity.Extras.JOKE_EXT, data);
                startActivity(intent);
            }
        });

        endPointAsyncTask.execute(jokeIndex);
    }


}

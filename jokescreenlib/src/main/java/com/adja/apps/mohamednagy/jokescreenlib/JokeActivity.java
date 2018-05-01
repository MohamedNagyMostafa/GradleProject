package com.adja.apps.mohamednagy.jokescreenlib;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.adja.apps.mohamednagy.jokescreenlib.databinding.ActivityJokeBinding;

public class JokeActivity extends AppCompatActivity {

    ActivityJokeBinding activityJokeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        activityJokeBinding = DataBindingUtil.setContentView(this, R.layout.activity_joke);

        // accept incoming joke.
        Bundle bundle = getIntent().getExtras();
        String jokeContent = null;

        if(bundle != null)
            jokeContent = bundle.getString(Extras.JOKE_EXT);
        else if(savedInstanceState != null)
            jokeContent = savedInstanceState.getString(Extras.JOKE_EXT);

        assert jokeContent != null;
        activityJokeBinding.jokeTextView.setText(jokeContent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String jokeContent = activityJokeBinding.jokeTextView.getText().toString();

        outState.putString(Extras.JOKE_EXT, jokeContent);
    }

    public static class Extras{
        public static final String JOKE_EXT = "joke_ex";
    }
}

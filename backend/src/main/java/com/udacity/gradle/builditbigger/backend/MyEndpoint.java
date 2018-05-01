package com.udacity.gradle.builditbigger.backend;

import com.adja.apps.mohamednagy.javalib.Joke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke(@Named("jokeId") int jokeId) {
        MyBean response = new MyBean();
        Joke joke = new Joke();
        response.setData(joke.getJoke(jokeId));
        return response;
    }

}

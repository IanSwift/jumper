package io.pivotal.demo;

import retrofit.http.GET;

public interface BreweryGetInterface {
    @GET("/breweries?key=2a9a3e2f024586e50035f6758e6e44d7&format=json&established=2012&order=random&randomCount=1")
    BreweryResponse getRandomBreweryFrom2012();
}

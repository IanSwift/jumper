package io.pivotal.demo;

import retrofit.http.GET;

public interface BreweryGetInterface {
    @GET("/breweries?key=<MYAPIKEY>&format=json&established=2012&order=random&randomCount=1")
    BreweryResponse getRandomBreweryFrom2012();
}

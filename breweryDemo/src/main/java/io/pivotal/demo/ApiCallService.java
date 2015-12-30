package io.pivotal.demo;

import android.app.IntentService;
import android.content.Intent;

import javax.inject.Inject;

import io.pivotal.jumper.ModelFactory;
import retrofit.RestAdapter;

public class ApiCallService extends IntentService{

    @Inject
    RestAdapter restAdapter;

    @Inject
    ModelFactory modelFactory;

    private static final String name = "API_CALL_SERVICE";

    public ApiCallService() {
        super(name);
        GraphProvider.getInstance().getGraph().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        BreweryResponse apiResponse = getApiResponse();
        modelFactory.newInstance(this, BreweryInterface.class, apiResponse, "BREWERY");
    }

    public BreweryResponse getApiResponse() {
        BreweryGetInterface breweryGetInterface = restAdapter.create(BreweryGetInterface.class);
        BreweryResponse randomBreweryFrom2012 = breweryGetInterface.getRandomBreweryFrom2012();
        return randomBreweryFrom2012;
    }
}

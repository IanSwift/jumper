package io.pivotal.demo;

import dagger.Module;
import dagger.Provides;
import io.pivotal.jumper.ModelFactory;
import retrofit.RestAdapter;

@Module
        (
                injects = ApiCallService.class
        )
public class MyModule {
    @Provides
    RestAdapter providesRestAdapter() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("http://api.brewerydb.com/v2");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder.build();
    }

    @Provides
    ModelFactory providesModelFactory() {
        return new ModelFactory();
    }

}

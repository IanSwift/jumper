package io.pivotal.demo;

import org.mockito.Mock;

import dagger.Module;
import dagger.Provides;
import io.pivotal.jumper.ModelFactory;
import retrofit.RestAdapter;

import static org.mockito.MockitoAnnotations.initMocks;

@Module (
        injects = ApiCallServiceTest.class,
        overrides = true
)
public class MyTestModule {
    @Mock
    RestAdapter mockRestAdapter;

    @Mock
    ModelFactory mockModelFactory;

    public MyTestModule() {
        initMocks(this);
    }

    @Provides
    RestAdapter providesRestAdapter() {
        return mockRestAdapter;
    }

    @Provides
    ModelFactory providesModelFactory() {
        return mockModelFactory;
    }
}

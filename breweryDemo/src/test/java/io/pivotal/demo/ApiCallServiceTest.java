package io.pivotal.demo;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import io.pivotal.jumper.ModelFactory;
import retrofit.RestAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ApiCallServiceTest {

    @Inject
    RestAdapter mockRestAdapter;

    @Inject
    ModelFactory mockModelFactory;

    private ApiCallService subject;

    @Before
    public void setup() {
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(MyModule.class, MyTestModule.class);
        graphProvider.getGraph().inject(this);
        subject = new ApiCallService();
    }

    @Test
    public void getApiResponse_sendsOutApiCall() {

        BreweryGetInterface mockInterface = mock(BreweryGetInterface.class);
        stub(mockRestAdapter.create(BreweryGetInterface.class)).toReturn(mockInterface);
        BreweryResponse response = new BreweryResponse();
        stub(mockInterface.getRandomBreweryFrom2012()).toReturn(response);

        BreweryResponse apiResponse = subject.getApiResponse();
        verify(mockRestAdapter).create(BreweryGetInterface.class);
        verify(mockInterface).getRandomBreweryFrom2012();

        assertThat(apiResponse).isEqualTo(response);
    }

    @Test
    public void onHandleIntent_updatesModel() {
        ApiCallService subject = spy(this.subject);
        BreweryResponse breweryResponse = mock(BreweryResponse.class);

        doReturn(breweryResponse).when(subject).getApiResponse();

        subject.onHandleIntent(new Intent());

        verify(mockModelFactory).newInstance(eq(subject), eq(BreweryInterface.class), eq(breweryResponse), eq("BREWERY"));
    }
}
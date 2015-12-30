package io.pivotal.demo;

import java.util.ArrayList;

public interface BreweryInterface {

    void setData(ArrayList<BreweryResponse.Brewery> data);

    void setName(String name);

    void setDescription(String description);

    void setWebsite(String website);
}

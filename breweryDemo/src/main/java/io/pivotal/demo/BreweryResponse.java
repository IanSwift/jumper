package io.pivotal.demo;

import java.io.Serializable;
import java.util.ArrayList;

import io.pivotal.jumper.Model;

public class BreweryResponse extends Model implements BreweryInterface, Serializable {

    @Override
    public void setData(ArrayList<Brewery> data) {
        this.data = data;
    }

    private ArrayList<Brewery> data;

    public Brewery getBrewery() {
        return data.get(0);
    }

    @Override
    public void setName(String name) {
        data.get(0).getName();
    }

    @Override
    public void setDescription(String description) {
        data.get(0).setDescription(description);
    }

    @Override
    public void setWebsite(String website) {
        data.get(0).setWebsite(website);
    }

    public String getName() {
        return data.get(0).getName();
    }

    public String getDescription() {
        return data.get(0).getDescription();
    }

    public String getWebsite() {
        return data.get(0).getWebsite();
    }

    public static class Brewery implements Serializable{
        private String name;
        private String description;
        private String website;

        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getWebsite() {
            return website;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreweryResponse that = (BreweryResponse) o;

        return !(data != null ? !data.equals(that.data) : that.data != null);

    }

    @Override
    public Model copyOf(Model original) {
        BreweryResponse copy = new BreweryResponse();
        BreweryResponse o = (BreweryResponse) original;

        copy.data = new ArrayList<>();
        copy.data.add(new Brewery());

        copy.setName(o.getName());
        copy.setDescription(o.getDescription());
        copy.setWebsite(o.getWebsite());
        return copy;
    }
}

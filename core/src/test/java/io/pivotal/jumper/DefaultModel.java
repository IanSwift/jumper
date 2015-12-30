package io.pivotal.jumper;

public class DefaultModel extends Model implements DummyInterface {
    public int dummyData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DefaultModel that = (DefaultModel) o;

        return dummyData == that.dummyData;
    }

    @Override
    public Model copyOf(Model original) {
        return null;
    }
}

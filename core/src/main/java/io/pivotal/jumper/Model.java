package io.pivotal.jumper;

public abstract class Model implements java.io.Serializable {

    /*Please use android studios generate equals for the best effect*/
    public abstract boolean equals(Object o);

    public abstract Model copyOf(Model original);
}

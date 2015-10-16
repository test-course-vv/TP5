package vv.tdd.factory;


public interface Factory<O, T> {

    //transform T in O
    public O transform(T object);

    public O create();
}

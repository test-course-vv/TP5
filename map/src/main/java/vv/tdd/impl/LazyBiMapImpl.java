package vv.tdd.impl;


import vv.tdd.factory.Factory;


public class LazyBiMapImpl<K,V> extends BiMapImpl<K,V>{

    public LazyBiMapImpl(Factory<K, V> keyFactory, Factory<V, K> valueFactory) {}

}

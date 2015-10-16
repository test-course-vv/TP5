package vv.tdd;

import java.util.Map;

/**
 * Created by ebousse on 21/10/14.
 */
public interface BiMap<K,V> extends Map<K,V>{

    public K getByValue(Object value);

    public K removeValue(Object value);
}

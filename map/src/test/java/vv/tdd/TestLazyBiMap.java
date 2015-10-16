package vv.tdd;

import org.junit.Test;
import vv.tdd.factory.IntegerToStringFactory;
import vv.tdd.factory.StringToIntegerFactory;
import vv.tdd.impl.LazyBiMapImpl;

import java.util.Map;

import static org.junit.Assert.assertEquals;


/**
 * Created by Simon on 14/10/14.
 */
public class TestLazyBiMap extends TestBiMap {


    @Test
    public void testGet() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(1,map.get("1"));
        map.put("1", 11);
        assertEquals(11,map.get("1"));

        assertEquals(4,map.get("4"));
    }


    @Test
    public void testGetByValue() {
        BiMap biMap = (BiMap) map;
        biMap.put("1", 1);
        biMap.put("2", 12);
        biMap.put("3", 2);

        assertEquals("1",biMap.getByValue(1));
        biMap.put("1", 11);
        assertEquals("1",biMap.getByValue(11));

        assertEquals("4",biMap.getByValue(4));

        biMap.put("5", 5);
        assertEquals("5",biMap.getByValue(5));
    }

    protected Map<String, Integer> getNewMap() {
        return new LazyBiMapImpl<String, Integer>(new IntegerToStringFactory(), new StringToIntegerFactory());
    }

}

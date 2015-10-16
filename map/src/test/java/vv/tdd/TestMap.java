package vv.tdd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vv.tdd.impl.SimpleMapImpl;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Simon on 30/09/14.
 */
public class TestMap<K, V> {

    protected Map<String, Integer> map;

    @Before
    public void setUp() {
        map =  getNewMap();
    }

    @After
    public void tearDown() {
        map = null;
    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, map.isEmpty());

        map.put("q",1);
        assertEquals(false, map.isEmpty());

    }

    @Test
    public void testSize() {
        assertEquals(0,map.size());

        map.put("1",1);
        assertEquals(1,map.size());

        map.put("2",2);
        assertEquals(2,map.size());

        map.put("2",2);
        assertEquals(2,map.size());

        map.remove("1");
        assertEquals(1,map.size());
    }

    @Test
    public void testClear() {
        map.put("1",1);
        map.put("2",2);

        assertEquals(false,map.isEmpty());

        map.clear();
        assertEquals(true,map.isEmpty());
        assertFalse(map.containsKey("1"));
        assertFalse(map.containsKey("2"));
    }

    @Test
    public void testRemove() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(3, map.size());
        map.remove("3");
        assertFalse(map.containsKey("3"));
        assertEquals(2, map.size());
        assertEquals(1, map.get("1"),0);
        assertEquals(12, map.get("2"),0);

        assertEquals(1, map.remove("1"),0);
        assertFalse(map.containsKey("1"));
        assertEquals(1, map.size());
        assertEquals(12, map.get("2"),0);
    }


    @Test
    public void testGet() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        assertEquals(1,map.get("1"),0);
        map.put("1", 11);
        assertEquals(11,map.get("1"),0);

        assertNull(map.get("4"));

        map.put("4", 4);
        assertEquals(4,map.get("4"), 0);
    }

    @Test
    public void testPut() {
        Object previous1 = map.put("1", 1);
        Object previous2 = map.put("2", 12);
        assertEquals(1, map.get("1"),0);
        assertEquals(12, map.get("2"), 0);
        assertEquals(null, previous1);
        assertEquals(null, previous2);

        Object previous1_2 = map.put("1", 14);
        Object previous2_2 = map.put("2", 122);
        map.put("Fred", 11111);
        assertEquals(14, map.get("1"), 0);
        assertEquals(122, map.get("2"), 0);
        assertEquals(1,previous1_2);
        assertEquals(12,previous2_2);
    }

    @Test
    public void testPutAll() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 =  getNewMap();
        map2.put("5",18);
        map2.putAll(map);


        assertFalse(map2.containsKey("4"));
        assertEquals(1, map2.get("1"), 0);
        assertEquals(2, map2.get("3"), 0);
        assertEquals(12, map2.get("2"), 0);
        assertEquals(12, map2.get("5"), 18);
    }

    /**
     * checks if the SimpleMap equals method works.
     */
    @Test
    public void testEquals() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 = getNewMap();
        map2.put("1", 1);
        map2.put("2", 12);
        map2.put("3", 2);
        map2.put("4", 34);

        assertNotEquals(map, map2);

        map2.remove("4");
        assertEquals(map, map2);
    }

    /**
     * checks if the SimpleMap equals method works.
     */
    @Test
    public void testHasCode() {
        map.put("1", 1);
        map.put("2", 12);
        map.put("3", 2);

        Map<String, Integer> map2 =  getNewMap();
        map2.put("1", 1);
        map2.put("2", 12);
        map2.put("3", 2);
        map2.put("4", 34);

        assertNotEquals(map, map2);
        assertNotEquals(map.hashCode(), map2.hashCode());

        map2.remove("4");
        assertEquals(map, map2);
        assertEquals(map.hashCode(), map2.hashCode());
    }

    @Test
    public void testContains() {
        map.put("1", 1);
        map.put("2", 12);

        assertTrue(map.containsKey("1"));
        assertTrue(map.containsKey("2"));
        assertFalse(map.containsKey("3"));

        map.put("3", 2);

        assertTrue(map.containsKey("1"));
        assertTrue(map.containsKey("2"));
        assertTrue(map.containsKey("3"));

        map.remove("2");

        assertTrue(map.containsKey("1"));
        assertFalse(map.containsKey("2"));
        assertTrue(map.containsKey("3"));
    }

    @Test
    public void testContainsValues() {
        map.put("1", 1);
        map.put("2", 12);

        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(12));
        assertFalse(map.containsValue(2));

        map.put("3", 2);

        assertTrue(map.containsValue(1));
        assertTrue(map.containsValue(12));
        assertTrue(map.containsValue(2));

        map.remove("2");

        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(12));
        assertTrue(map.containsValue(2));
    }

    @Test
    public void testKeySet() {
        Set<String> set = new HashSet<String>();

        assertEquals(map.keySet(), set);

        map.put("1", 1);
        map.put("2", 12);
        set.add("1");
        set.add("2");
        assertEquals(set,map.keySet());

        map.put("3", 2);
        set.add("3");
        assertEquals(set,map.keySet());

        map.remove("2");
        assertNotEquals(set,map.keySet());

        set.remove("2");
        assertEquals(set,map.keySet());
    }

    @Test
    public void testValues() {
        List<Integer> set = new ArrayList<Integer>();

        assertEquals(set,map.values());

        map.put("1", 1);
        map.put("2", 12);
        set.add(1);
        set.add(12);
        assertTrue(set.containsAll(map.values()) && map.values().containsAll(set));
        map.put("3", 2);
        set.add(2);
        assertTrue(set.containsAll(map.values()) && map.values().containsAll(set));
        map.remove("2");
        assertFalse(set.containsAll(map.values()) && map.values().containsAll(set));
        set.remove(1);
        assertTrue(set.containsAll(map.values()) && map.values().containsAll(set));
    }

    @Test(expected=NotImplementedException.class)
    public void testEntrySet() {
        map.entrySet();
    }


    protected Map<String, Integer> getNewMap() {
        return new SimpleMapImpl<String, Integer>();
    }
}
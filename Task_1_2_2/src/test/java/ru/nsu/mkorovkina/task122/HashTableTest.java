package ru.nsu.mkorovkina.task122;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

class HashTableTest {

    @Test
    void put() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        assertTrue(hashTable.put("one", 1));
        assertTrue(hashTable.put("two", 2));
        assertEquals("{one=1, two=2}", hashTable.toString());
        assertFalse(hashTable.put("two", 2));
    }

    @Test
    void remove() {
        HashTable<String, Integer> hashTable = new HashTable<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        assertFalse(hashTable.remove("three"));
        assertTrue(hashTable.remove("two"));
        assertEquals("{one=1}", hashTable.toString());
        assertTrue(hashTable.remove("one"));
        assertEquals("{}", hashTable.toString());
    }

    @Test
    void get() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("one", "apple");
        hashTable.put("two", "banana");
        assertEquals("apple", hashTable.get("one"));
        assertEquals("banana", hashTable.get("two"));
        assertNull(hashTable.get("three"));
    }

    @Test
    void replace() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("one", "apple");
        hashTable.put("two", "banana");
        assertTrue(hashTable.replace("one", "fruit"));
        assertEquals("{one=fruit, two=banana}", hashTable.toString());
        assertFalse(hashTable.replace("three", "pear"));
    }

    @Test
    void contains() {
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        assertTrue(hashTable.contains(1, 1));
        assertTrue(hashTable.contains(2, 2));
        assertFalse(hashTable.contains(2, 3));
        assertFalse(hashTable.contains(3, 3));
    }

    @Test
    void size() {
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 1; i < 18; i++) {
            assertTrue(hashTable.put(i, i));
        }
        assertEquals(17, hashTable.size());
        for (int i = 1; i < 18; i++) {
            assertEquals(i, hashTable.get(i));
        }
    }

    @Test
    void testEquals() {
        HashTable<Integer, Integer> hashTable1 = new HashTable<>();
        hashTable1.put(1, 1);
        hashTable1.put(2, 2);
        hashTable1.put(3, 3);
        HashTable<Integer, Integer> hashTable2 = new HashTable<>();
        hashTable2.put(1, 1);
        hashTable2.put(2, 2);
        hashTable2.put(3, 3);
        assertEquals(hashTable1, hashTable2);
        hashTable2.replace(3, 4);
        assertNotEquals(hashTable1, hashTable2);
        hashTable2.remove(3);
        hashTable2.put(4, 4);
        assertNotEquals(hashTable1, hashTable2);
        hashTable1.remove(3);
        assertNotEquals(hashTable1, hashTable2);
    }

    @Test
    void testHashCode() {
        HashTable<Integer, Integer> hashTable1 = new HashTable<>();
        hashTable1.put(1, 1);
        hashTable1.put(2, 2);
        hashTable1.put(3, 3);
        HashTable<Integer, Integer> hashTable2 = new HashTable<>();
        hashTable2.put(1, 1);
        hashTable2.put(2, 2);
        hashTable2.put(3, 3);
        assertEquals(hashTable1.hashCode(), hashTable2.hashCode());
    }

    @Test
    void testToString() {
        HashTable<String, String> hashTable = new HashTable<>();
        hashTable.put("one", "apple");
        hashTable.put("two", "banana");
        hashTable.put("three", "pear");
        String str = hashTable.toString();
        assertTrue(str.contains("{"));
        assertTrue(str.contains("one=apple"));
        assertTrue(str.contains("two=banana"));
        assertTrue(str.contains("three=pear"));
        assertTrue(str.contains("}"));
    }

    @Test
    void regularIterator() {
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 1; i < 16; i++) {
            hashTable.put(i, i);
        }
        HashTable<Integer, Integer> result = new HashTable<>();
        Iterator<Map.Entry<Integer, Integer>> it = hashTable.iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> elem = it.next();
            result.put(elem.getKey(), elem.getValue());
        }
        assertEquals(hashTable, result);
    }

    @Test
    void iteratorWithException() {
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        Iterator<Map.Entry<Integer, Integer>> it = hashTable.iterator();
        assertThrows(NoSuchElementException.class, it::next);
        hashTable.put(1, 1);
        hashTable.put(2, 2);
        hashTable.put(3, 3);
        it = hashTable.iterator();
        it.next();
        hashTable.put(4, 4);
        assertThrows(ConcurrentModificationException.class, it::next);
        it = hashTable.iterator();
        it.next();
        it.remove();
        assertThrows(IllegalStateException.class, it::remove);
        it.next();
        hashTable.put(5, 5);
        assertThrows(ConcurrentModificationException.class, it::remove);
    }
}
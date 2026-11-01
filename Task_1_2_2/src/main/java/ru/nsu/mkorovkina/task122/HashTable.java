package ru.nsu.mkorovkina.task122;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Parameterized container HashTable.
 * Allows you to add, search for, and remove objects of type V using a key of type K.
 *
 * @param <K> key type
 * @param <V> value type
 */
public class HashTable<K, V> implements Iterable<Map.Entry<K, V>> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private static final class Node<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node<K, V> nextNode;

        Node(K key, V value, int hash, Node<K, V> nextNode) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.nextNode = nextNode;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = value;
            value = v;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Map.Entry<?, ?> other)) {
                return false;
            }
            return Objects.equals(key, other.getKey()) && Objects.equals(value, other.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private int modCount;

    /**
     * Creates an empty hash table.
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        this.buckets = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
        this.size = 0;
        this.modCount = 0;
    }

    /**
     * Adds a key-value pair.
     *
     * @param key the key
     * @param value the value
     * @return true if the pair was successfully added, and false if the key already exists
     */
    public boolean put(K key, V value) {
        if (size >= (int) (0.75 * buckets.length)) {
            resize();
        }
        int h = hash(key);
        int index = h % (buckets.length);
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.nextNode) {
            if (h == curNode.hash && Objects.equals(key, curNode.key)) {
                return false;
            }
        }
        buckets[index] = new Node<>(key, value, h, buckets[index]);
        size++;
        modCount++;
        return true;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int capacity = buckets.length * 2;
        Node<K, V>[] newBuckets = (Node<K, V>[]) new Node[capacity];
        for (Node<K, V> bucket : buckets) {
            Node<K, V> curNode = bucket;
            while (curNode != null) {
                Node<K, V> nextNode = curNode.nextNode;
                int index = curNode.hash % (newBuckets.length);
                curNode.nextNode = newBuckets[index];
                newBuckets[index] = curNode;
                curNode = nextNode;
            }
        }
        buckets = newBuckets;
    }

    /**
     * Removes a key-value pair.
     *
     * @param key the key
     * @return true if the pair was successfully removed, and false if the key doesn't exist
     */
    public boolean remove(K key) {
        int h = hash(key);
        int index = h % (buckets.length);
        Node<K, V> prevNode = null;
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.nextNode) {
            if (h == curNode.hash && Objects.equals(key, curNode.key)) {
                if (prevNode == null) {
                    buckets[index] = curNode.nextNode;
                } else {
                    prevNode.nextNode = curNode.nextNode;
                }
                size--;
                modCount++;
                return true;
            }
            prevNode = curNode;
        }
        return false;
    }

    /**
     * Gets the value by key.
     *
     * @param key the key
     * @return null if the key doesn't exist, otherwise the value
     */
    public V get(K key) {
        int h = hash(key);
        int index = h % (buckets.length);
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.nextNode) {
            if (h == curNode.hash && Objects.equals(key, curNode.key)) {
                return curNode.value;
            }
        }
        return null;
    }

    /**
     * Updates the value by key.
     *
     * @param key the key
     * @param value new value
     * @return true if the value was successfully replaced, and false if the key doesn't exist
     */
    public boolean replace(K key, V value) {
        int h = hash(key);
        int index = h % (buckets.length);
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.nextNode) {
            if (h == curNode.hash && Objects.equals(key, curNode.key)) {
                curNode.value = value;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for the presence of a key-value pair.
     *
     * @param key the key
     * @param value the value
     * @return true if the pair exists, false otherwise
     */
    public boolean contains(K key, V value) {
        int h = hash(key);
        int index = h % (buckets.length);
        for (Node<K, V> curNode = buckets[index]; curNode != null; curNode = curNode.nextNode) {
            if (h == curNode.hash && Objects.equals(key, curNode.key)
                    && Objects.equals(value, curNode.value)) {
                return true;
            }
        }
        return false;
    }

    private int hash(K key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            int tmp = key.hashCode();
            h = tmp ^ (tmp >>> 16);
        }
        return h;
    }

    /**
     * Gets the size of the hash table.
     *
     * @return number of elements in a hash table
     */
    public int size() {
        return size;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HashTable<K, V> other = (HashTable<K, V>) o;
        if (size != other.size()) {
            return false;
        }
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.nextNode) {
                if (!other.contains(curNode.key, curNode.value)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.nextNode) {
                h += curNode.hashCode();
            }
        }
        return h;
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder builder = new StringBuilder("{");
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> curNode = bucket; curNode != null; curNode = curNode.nextNode) {
                builder.append(curNode);
                if (count != size) {
                    builder.append(", ");
                }
                count++;
            }
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Returns a fail-fast iterator for traversing key-value pairs.
     *
     * @return hash table iterator
     */
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    private final class HashTableIterator implements Iterator<Map.Entry<K, V>> {
        private Node<K, V> nextNode;
        private Node<K, V> curNode;
        private int bucketIndex;
        private int iteratorModCount;

        HashTableIterator() {
            this.bucketIndex = -1;
            this.iteratorModCount = modCount;
            this.nextNode = null;
            this.curNode = null;
            findNextNode();
        }

        private void findNextNode() {
            if (nextNode != null && nextNode.nextNode != null) {
                nextNode = nextNode.nextNode;
            } else {
                bucketIndex++;
                while (bucketIndex < buckets.length) {
                    if (buckets[bucketIndex] != null) {
                        nextNode = buckets[bucketIndex];
                        return;
                    }
                    bucketIndex++;
                }
                nextNode = null;
            }
        }

        @Override
        public Map.Entry<K, V> next() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            Map.Entry<K, V> result = nextNode;
            curNode = nextNode;
            findNextNode();
            return result;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public void remove() {
            if (curNode == null) {
                throw new IllegalStateException();
            }
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            HashTable.this.remove(curNode.key);
            iteratorModCount = modCount;
            curNode = null;
        }
    }
}

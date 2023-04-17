/**
 * @author chatGpt
 * @documention:chatGpt实现的基础版本的hashmap，要求支持泛型，允许存入重复元素，支持并发操作
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class GptHashMap<K, V> {
    // 初始桶的数量
    private static final int DEFAULT_CAPACITY = 16;
    // 桶数组
    private List<Entry<K, V>>[] table;
    // 锁
    private ReentrantReadWriteLock[] locks;

    public GptHashMap() {
        table = new List[DEFAULT_CAPACITY];
        locks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            table[i] = new ArrayList<>();
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    /**
     * 插入元素
     *
     * @param key   键
     * @param value 值
     */
    public void put(K key, V value) {
        int hash = hash(key);
        locks[hash].writeLock().lock();
        try {
            List<Entry<K, V>> list = table[hash];
            for (Entry<K, V> entry : list) {
                if (entry.getKey().equals(key)) {
                    entry.setValue(value);
                    return;
                }
            }
            list.add(new Entry<>(key, value));
        } finally {
            locks[hash].writeLock().unlock();
        }
    }

    /**
     * 获取元素
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        int hash = hash(key);
        locks[hash].readLock().lock();
        try {
            List<Entry<K, V>> list = table[hash];
            for (Entry<K, V> entry : list) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null;
        } finally {
            locks[hash].readLock().unlock();
        }
    }

    /**
     * 删除元素
     *
     * @param key 键
     * @return 值
     */
    public V remove(K key) {
        int hash = hash(key);
        locks[hash].writeLock().lock();
        try {
            List<Entry<K, V>> list = table[hash];
            for (int i = 0; i < list.size(); i++) {
                Entry<K, V> entry = list.get(i);
                if (entry.getKey().equals(key)) {
                    list.remove(i);
                    return entry.getValue();
                }
            }
            return null;
        } finally {
            locks[hash].writeLock().unlock();
        }
    }
    /**
     * 计算哈希值
     *
     * @param key 键
     * @return 哈希值
     */
    private int hash(K key) {
        return key.hashCode() % DEFAULT_CAPACITY;
    }

    /**
     * Entry内部类，用于保存键值对
     */
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        GptHashMap<Integer, String> map = new GptHashMap<>();
        map.put(1,"Amy");
        map.put(2,"Bob");
        map.put(17,"Amy1");
        map.get(1);
        map.get(2);
        map.get(3);
        map.remove(1);
        map.get(1);
        map.get(2);
    }
}
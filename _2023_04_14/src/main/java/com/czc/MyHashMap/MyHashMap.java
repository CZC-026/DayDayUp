package com.czc.MyHashMap;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Pers
 * @apiNote
 */

public class MyHashMap<K, V> {
    //初始bucket大小
    private static final int DEFAULT_CAPACITY = 16;
    //初始bucket
    private SingeList<Entry<K, V>>[] table;
    //锁
    private ReentrantReadWriteLock[] locks;

    public MyHashMap() {
        table = new SingeList[DEFAULT_CAPACITY];
        locks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {


        }
    }

}




class Entry<K, V> {
    private K key;
    private V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}               

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Pers
 * @Documention:
 *
 */
public class MyHashMap<K, V> {

    //初始bucket的数量
    private static final int DEFAULT_CAPACITY = 16;
    //bucket数组
    private List<Entry<K,V>>[] table;
    //分段锁,锁的粒度小
    //采用读写锁，并发能力强于互斥锁ReentrantLock
    private ReentrantReadWriteLock[] locks;

    public MyHashMap(){
        table = new List[DEFAULT_CAPACITY];
        locks = new ReentrantReadWriteLock[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            table[i] = new ArrayList<>();
            locks[i] = new ReentrantReadWriteLock();
        }
    }
    private int hash(K key) {
        return key.hashCode() % DEFAULT_CAPACITY;
    }
    public void put(K key , V value){
        int hash = hash(key);
    }





    public static class Entry<K,V>{
        private K key;
        private V value;
        public Entry(K key , V value){
            this.key = key;
            this.value = value;
        }
    }
}




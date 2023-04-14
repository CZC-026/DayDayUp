import java.util.HashMap;

/**
 * @author pers
 * 基础版本的LRU算法的实现，参照leetcode
 * @apiNote 双向链表+hash表
 */
public class LRUCache {
    private DoubleList cache;
    private HashMap<Integer,Node> map;
    int capacity;

    public LRUCache(int cap) {
        this.cache = new DoubleList();
        this.map = new HashMap<Integer,Node>();
        this.capacity = cap;
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        int res = map.get(key).value;
        put(key,res);
        return res;
    }

    private void put(int key, int value) {
        //是否包含该key
        if(map.containsKey(key)){
            //删除原来的，把修改后的节点放在队首
            Node tmp =
        }else {
            if(cache.size < capacity){
                //直接添加在队首
            }else {
                //淘汰掉队尾的，插入到队尾
            }
        }
    }

}


class DoubleList {
    Node tail = new Node(0, 0);
    Node head = new Node(0, 0);
    int size;

    public DoubleList() {
        size = 0;//初始化缓存
        head.next = tail;
        tail.prev = head;
    }

    //在头部插入节点
    void addFirst(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    //删除节点
    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    //在尾部删除节点
    Node removeLast() {
        Node tmp = tail.prev;
        remove(tmp);
        return tmp;
    }

}

class Node {
    int key;
    int value;
    Node prev;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}



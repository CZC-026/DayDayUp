package com.example._2023_04_17;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author pers
 * 基础版本的LRU算法的实现，参照leetcode
 * @apiNote 双向链表+hash表
 */
@Component
public class LRUCache {
    private DoubleList cache;
    private HashMap<Integer, Node> map;
    int capacity;

    public LRUCache() {
        this.cache = new DoubleList();
        this.map = new HashMap<Integer, Node>();
        this.capacity = 10;
    }
    public int getSize(){
        return cache.size;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int res = map.get(key).value;
        put(key, res);
        return res;
    }

    void put(int key, int value) {
        //是否包含该key
        Node target = new Node(key, value);
        if (map.containsKey(key)) {
            //删除原来的，把修改后的节点放在队首
            Node tmp = map.get(key);
            cache.remove(tmp);
            cache.addFirst(target);
            map.put(key , target);
        } else {
            if (cache.size >= capacity) {
                Node last = cache.removeLast();
                map.remove(last.key);
            } else {
                //淘汰掉队尾的，插入到队尾
                cache.addFirst(target);
                map.put(key, target);
            }
        }
    }

    void delete(int key) throws Exception {
        //删除对应值为key节点
        if(cache.size <= 0){
            throw new Exception("空链表不能删除");
        }
//        1.找到节点
        Node target = map.get(key);
//        2.链表中删除
        target.prev.next = target.next;
        target.next.prev = target.prev;
//        3.hash关系删除
        map.remove(key);
    }

    String getAllNodes(){
        Node cur = cache.head;
        String res = new String();
        while(cur.next != cache.tail){
            cur = cur.next;
            res = res + "(" + cur.key + "," + cur.value + ")" + "->";
        }
        res = res + "null";
        System.out.println(res);
        return res;
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



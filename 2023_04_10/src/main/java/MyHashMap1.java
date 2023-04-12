/**
 * @author Pers
 * @Finalrequirements:1.实现一个支持泛型的hashmap；2.支持并发操作；3.允许插入重复的键值
 * @版本一
 */
public class MyHashMap1 {
    //链表+数组实现
    Node[] nodeArray;//初始不考虑扩容

    public MyHashMap1() {
        nodeArray = new Node[64];
    }

    //插入元素
    public void put(int key, int value) {
        int index = key % 64;
        if (nodeArray[index] == null) {
            //新插入的元素
            nodeArray[index] = new Node(key, value);
        } else {
            append(nodeArray[index], key, value);
        }
    }

    //在head链表里添加元素
    void append(Node head, int key, int value) {
        while (head.next != null) {
            if (head.key == key) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        head.next = new Node(key, value);
    }

    //删除元素
    public void remove(int key) throws Exception {
        int index = key % 64;
        Node head = nodeArray[index];
        if (head == null) {
            throw new Exception("不存在对应键值的元素");
        }
        Node prev = head;
        while (head != null) {
            if (head.key == key) {
                if (head == prev) {
                    nodeArray[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            } else {
                prev = head;
                head = head.next;
            }
        }
    }

    //查找元素,fix
    public int get(int key) {
        int index = key % 64;
        Node head = nodeArray[index];
        if (head == null) {
            return -1;
        }
        while (head != null) {
            if (head.key == key) {
                return head.value;
            } else {
                head = head.next;
            }
        }
        return -1;
    }

    //Test
    public static void main(String[] args) throws Exception {
        MyHashMap1 hashmap = new MyHashMap1();
        hashmap.put(0, 000);
        hashmap.put(1, 111);
        hashmap.put(2, 222);
        hashmap.put(64, 000);
        hashmap.put(65, 111);
        hashmap.put(66, 222);
        hashmap.get(0);
        hashmap.get(1);
        hashmap.get(2);
        hashmap.get(64);
        hashmap.get(65);
        hashmap.get(66);

        hashmap.remove(0);
        hashmap.remove(1);
        hashmap.remove(2);
        hashmap.remove(64);
        hashmap.remove(65);
        hashmap.remove(66);

        hashmap.get(0);
        hashmap.get(1);
        hashmap.get(2);
        hashmap.get(64);
        hashmap.get(65);
        hashmap.get(66);


        System.out.println(hashmap);
    }
}

class Node {
    int key, value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


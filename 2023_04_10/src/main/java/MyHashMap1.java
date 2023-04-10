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
    public void put(Node node) {
        int insPos = node.key % 64;
        if (nodeArray[insPos] == null) {
            //新插入的元素
            Node head = new Node(node.key, node.value);
            head.next = null;
            nodeArray[insPos] = head;
        } else {
            if (nodeArray[insPos].key != node.key) {
                //分配到一个bucket，但是要看是否出现了hash冲突，还是说就
                Node cur = nodeArray[insPos];
                while (cur.next != null) {
                    cur = cur.next;
                }
                cur.next = node;
                node.next = null;
            }else{

            }
        }
    }

    //删除元素
    public Node remove(int key) throws Exception {
        int delPos = key % 10001;
        Node head = nodeArray[delPos];
        if (head == null) {
            throw new Exception("不存在对应键值的元素");
        }
        if (head.key == key) {
            //头部为重复的
            nodeArray[delPos] = head.next;
            head.next = null;
            return head;
        }
        while (head.next != null && head.next.key != key) {
            head = head.next;
        }
        if (head.next == null) {
            throw new Exception("不存在对应键值的元素");
        }
        Node tmp = head.next;
        head.next = head.next.next;
        return tmp;
    }

    //查找元素
    public Node get(int key) throws Exception {
        int queryPos = key % 64;
        Node head = nodeArray[queryPos];
        if (head == null) {
            throw new Exception("不存在对应键值的元素");
        }
        while (head != null) {
            if (head.key == key) {
                return head;
            } else {
                head = head.next;
            }
        }
        return null;
    }

    //Test
    public static void main(String[] args) {
        MyHashMap1 hashmap = new MyHashMap1();
        hashmap.put(new Node(0, 111));
        hashmap.put(new Node(1, 111));
        hashmap.put(new Node(65, 111));
        System.out.println(hashmap);
    }


}

class Node {
    int key;
    int value;
    Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}


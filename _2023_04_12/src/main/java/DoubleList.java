import java.util.NoSuchElementException;

/**
 * @author Pers
 * @documention 手写双向链表
 */
public class DoubleList {
    Node head, tail;
    int size;
    int maxLen;

    DoubleList(int maxLen) {
        this.maxLen = maxLen;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    int size() {
        return this.size;
    }


    boolean isEmpty() {
        return size == 0;
    }

    void addFirst(Node node) {
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
        size++;
    }

    void addLast(Node node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        size++;
    }

    Node getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        return head.next;
    }

    Node getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        return tail.prev;
    }

    Node removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        Node tmp = head.next;
        head.next = tmp.next;
        tmp.next.prev = head;
        tmp.next = null;
        tmp.prev = null;
        size--;
        return tmp;
    }

    Node removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        Node tmp = tail.prev;
        tail.prev = tmp.prev;
        tmp.prev.next = tail;
        tmp.prev = null;
        tmp.next = null;
        size--;
        return tmp;
    }

    public static void main(String[] args) {
        // 创建双向链表对象
        DoubleList list = new DoubleList(10);

        // 添加元素
        list.addFirst(new Node(1,1));
        list.addLast(new Node(2, 2));
        list.addLast(new Node(3,3));

        // 删除元素
        list.removeFirst();
        list.removeLast();

        // 获取元素
        Node first = list.getFirst();
        Node last = list.getLast();

        // 获取链表长度
        int size = list.size();

        // 判断链表是否为空
        boolean empty = list.isEmpty();
    }


}

class Node {
    int key, value;
    Node prev, next;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}



import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // 定义节点类
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // 获取链表长度
    public int size() {
        return size;
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加节点
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data, null, head);
        if (head == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }

    // 在链表尾添加节点
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data, tail, null);
        if (tail == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    // 删除链表头节点
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        T data = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return data;
    }

    // 删除链表尾节点
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return data;
    }

    // 获取链表头节点
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        return head.data;
    }

    // 获取链表尾节点
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Linked list is empty");
        }
        return tail.data;
    }

    public static void main(String[] args) {
        // 创建双向链表对象
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

    // 添加元素
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");

    // 删除元素
        list.removeFirst();
        list.removeLast();

    // 获取元素
        String first = list.getFirst();
        String last = list.getLast();

    // 获取链表长度
        int size = list.size();

    // 判断链表是否为空
        boolean empty = list.isEmpty();
    }
}

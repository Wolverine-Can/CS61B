public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        private T item;
        private Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
        public T getrecuisive(int index) {
            if (index == 0) {
                return item;
            }
            return next.getrecuisive(index - 1);
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    public LinkedListDeque(T x) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public void addFirst(T item) {
        Node itemnode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = itemnode;
        sentinel.next = itemnode;
        size += 1;
    }
    public void addLast(T item) {
        Node itemnode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = itemnode;
        sentinel.prev = itemnode;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return this.size;
    }
    public void printDeque() {
        Node printnode = sentinel.next;
        while (printnode.next != sentinel) {
            System.out.print(printnode.item + " ");
            printnode = printnode.next;
        }
        System.out.println(printnode.item);
    }
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return item;
    }
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return item;
    }
    public T get(int index) {
        Node getitem = sentinel;
        for (int i = 0; i <= index; i++) {
            getitem = getitem.next;
        }
        if (sentinel.next == sentinel) {
            return null;
        }
        return getitem.item;
    }
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        Node othernode = other.sentinel.next;
        while (othernode != other.sentinel) {
            this.addLast(othernode.item);
            othernode = othernode.next;
        }
    }
    public T getRecursive(int index) {
        if (size() == 0 || size() < index) {
            return null;
        }
        return (sentinel.next.getrecuisive(index));
    }
}

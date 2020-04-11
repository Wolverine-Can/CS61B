public class test {
    public static void main(String[] args){
        LinkedListDeque x = new LinkedListDeque(2);
        x.addFirst("aa");
        x.addLast("bb");
        System.out.println(x.sentinel.prev.item);
        System.out.println(x.isEmpty());
        System.out.println(x.size);
        x.printDeque();
        System.out.println(x.getRecursive(0));
        LinkedListDeque y = new LinkedListDeque(x);
        y.printDeque();
        System.out.println(x.sentinel.next.next.item==y.sentinel.next.next.item);
    }
}

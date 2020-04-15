public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        if (items.length - nextFirst > size) {
            System.arraycopy(items, nextFirst + 1, newItems, 0, size);
        } else {
            if (nextFirst + 1 == items.length) {
                System.arraycopy(items, 0, newItems, 0, size);
            } else {
                System.arraycopy(items, nextFirst + 1, newItems, 0, items.length - nextFirst - 1);
                System.arraycopy(items, 0, newItems, items.length - nextFirst - 1, nextLast);
            }
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        if (nextFirst == 0) {
            items[nextFirst] = item;
            nextFirst = items.length - 1;
        } else {
            items[nextFirst] = item;
            nextFirst -= 1;
        }
        size += 1;
    }
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        if (nextLast == (items.length - 1)) {
            items[nextLast] = item;
            nextLast = 0;
        } else {
            items[nextLast] = item;
            nextLast += 1;
        }
        size += 1;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        resize(size);
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
    }
    @Override
    public T removeFirst() {
        T returnValue;
        if (size == 0) {
            return null;
        } else if (nextFirst == items.length - 1) {
            returnValue = items[0];
            items[0] = null;
            nextFirst = 0;
            size -= 1;
            if (items.length > 4 * size && size >= 8) {
                resize(2 * size);
            }
            return returnValue;
        } else {
            returnValue = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
            size -= 1;
            if (items.length > 4 * size && size >= 8) {
                resize(2 * size);
            }
            return returnValue;
        }
    }
    @Override
    public T removeLast() {
        T returnValue;
        if (size == 0) {
            return null;
        } else if (nextLast == 0) {
            returnValue = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
            size -= 1;
            if (items.length > 4 * size && size >= 8) {
                resize(2 * size);
            }
            return returnValue;
        } else {
            returnValue = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast -= 1;
            size -= 1;
            if (items.length > 4 * size && size >= 8) {
                resize(2 * size);
            }
            return returnValue;
        }
    }
    @Override
    public T get(int index) {
        if (size == 0 || (index + 1 > size)) {
            return null;
        }
        resize(size * 2);
        return items[index];
    }
}

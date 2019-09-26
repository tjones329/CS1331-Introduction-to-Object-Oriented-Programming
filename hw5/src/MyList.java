

public class MyList<E> implements List<E> {
    private E[] elements;
    private int size;

    public MyList() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public MyList(int capacity) {
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    @Override
    public void add(E e) {
        if(size == elements.length) {
            E[] newElements = (E[]) new Object[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
        elements[size++] = e;
    }

    @Override
    public E get(int index) {
        return elements[index];
    }

    @Override
    public void replace(E e, E replaceWith) {
        for(int i = 0; i < elements.length; i++) {
            if (elements[i] == e) {
                elements[i] = replaceWith;
            }
        }
    }

    @Override
    public int remove(E e) {
        int count = 0;
        int j = 0;
        E[] newElements = (E[]) new Object[elements.length];
        for(int i = 0; i < elements.length; i++) {
            if (elements[i] != e) {
                newElements[j] = elements[i];
                count ++;
                j++;
            } else {
                size--;
            }
        }
        elements = newElements;
        return count;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int contains(E e) {
        int count = 0;
        for(int i = 0; i < elements.length; i++) {
            if (elements[i] == e) {
                count ++;
            }
        }
        return count;
    }

    @Override
    public void clear() {
        elements = (E[]) new Object[elements.length];
        size = 0;
    }

    @Override
    public int size() {
        int count = 0;
        for(int i = 0; i < elements.length; i++) {
            if (elements[i] != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public E[] toArray(E[] e) {
        return null;
    }
}

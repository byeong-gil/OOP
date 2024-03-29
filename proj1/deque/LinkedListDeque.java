package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(Node p, T i, Node t) {
            item = i;
            next = t;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel =new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x){
        sentinel =new Node(null, null, null);
        sentinel.next = new Node(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

// Adds an item of type T to the front of the deque. You can assume that item is never null.
    @Override
    public void addFirst(T item){
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

//Adds an item of type T to the back of the deque. You can assume that item is never null.
    @Override
    public void addLast(T item){
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

//Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty(){
        if (size == 0) //sentinel.equals(sentinel.next) == true && sentinel.equals(sentinel.prev) == true)
            return true;
        return false;
    }

//Returns the number of items in the deque.
    @Override
    public int size(){
        return size;
    }

//Prints the items in the deque from first to last, separated by a space.
// Once all the items have been printed, print out a new line.
    @Override
    public void printDeque(){
        Node temp = sentinel.next;
        while(temp.next != sentinel.next) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

//Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (isEmpty())
            return null;
        T item = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size --;
        return item;
    }

//Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (isEmpty())
            return null;
        T item = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size --;
        return item;
    }

//Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
// If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (size < index)
            return null;
        Node temp = sentinel;
        for(int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    //public T getRecursive(int index): Same as get, but uses recursion.
    public T getrecursive(int index) {
        if (size < index)
            return null;
        if (index == 1) {
            return null;
        } else {
            sentinel.next = sentinel.next.next;
            return getrecursive(index-1);
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int pos;
        public LinkedListDequeIterator() {
            pos = 1;
        }

        @Override
        public boolean hasNext() {
            return pos <= size;
        }

        @Override
        public T next() {
            return get(pos++);
        }
    }
//The Deque objects we’ll make are iterable (i.e. Iterable<T>) so we must provide this method to return an iterator.
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

//Returns whether or not the parameter o is equal to the Deque.
// o is considered equal if it is a Deque and if it contains the same contents
// (as goverened by the generic T’s equals method) in the same order.
    @Override
    public boolean equals(Object o){
        Deque temp = null;
        if(o instanceof  LinkedListDeque) {
            temp = (LinkedListDeque) o;
        } else if (o instanceof ArrayDeque){
            temp = (ArrayDeque) o;
        } else {
            return false;
        }
        if(size != temp.size())
            return false;
        if(!ItemEqual(temp)) {
            return false;
        }
        return true;
    }

    public boolean ItemEqual(Deque a) {
        for(int i = 1; i <= a.size(); i++) {
            if(a.get(i) != get(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedListDeque<String> Deque = new LinkedListDeque<String>();
        Deque.addFirst("2");
        System.out.println(Deque.size());
        Deque.addFirst("1");
        Deque.addLast("3");
        System.out.println(Deque.size());
        Deque.printDeque();
    }
}


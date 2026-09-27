import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class QueueUsingTwoStacks {

    static class MyQueue<T> {
        private Stack<T> stackIn = new Stack<>();
        private Stack<T> stackOut = new Stack<>();

        public void enqueue(T value) {
            stackIn.push(value);
        }

        private void shiftStacks() {
            if (stackOut.isEmpty()) {
                while (!stackIn.isEmpty()) {
                    stackOut.push(stackIn.pop());
                }
            }
        }

        public T dequeue() {
            shiftStacks();
            return stackOut.pop();
        }

        public T peek() {
            shiftStacks();
            return stackOut.peek();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong truy van: ");

        if (scanner.hasNextInt()) {
            int q = scanner.nextInt();
            MyQueue<Integer> queue = new MyQueue<>();

            System.out.println("Goi y truy van:\n 1 x : Them x vao hang doi\n 2   : Xoa phan tu dau tien\n 3   : In ra phan tu dau tien");

            for (int i = 0; i < q; i++) {
                System.out.print("Nhap truy van thu " + (i + 1) + ": ");
                int type = scanner.nextInt();
                if (type == 1) {
                    int x = scanner.nextInt();
                    queue.enqueue(x);
                } else if (type == 2) {
                    queue.dequeue();
                } else if (type == 3) {
                    System.out.println("-> Phan tu dau: " + queue.peek());
                }
            }
        }

        scanner.close();
    }
}

// code stack
class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() { first = null; n = 0; }
    public boolean isEmpty() { return first == null; }
    public int size() { return n; }

    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }

    public Iterator<Item> iterator() { return new LinkedIterator(first); }
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;
        public LinkedIterator(Node<Item> first) { current = first; }
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
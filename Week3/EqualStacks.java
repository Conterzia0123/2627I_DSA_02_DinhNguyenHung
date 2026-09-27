import java.util.*;
import java.util.NoSuchElementException;

class Result {
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        Stack<Integer> st3 = new Stack<>();

        int sum1 = 0, sum2 = 0, sum3 = 0;

        for (int i = h1.size() - 1; i >= 0; i--) {
            st1.push(h1.get(i));
            sum1 += h1.get(i);
        }
        for (int i = h2.size() - 1; i >= 0; i--) {
            st2.push(h2.get(i));
            sum2 += h2.get(i);
        }
        for (int i = h3.size() - 1; i >= 0; i--) {
            st3.push(h3.get(i));
            sum3 += h3.get(i);
        }

        while (!(sum1 == sum2 && sum2 == sum3)) {
            if (sum1 >= sum2 && sum1 >= sum3) {
                sum1 -= st1.pop();
            } else if (sum2 >= sum1 && sum2 >= sum3) {
                sum2 -= st2.pop();
            } else {
                sum3 -= st3.pop();
            }
        }

        return sum1;
    }
}

public class EqualStacks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so luong phan tu cua 3 stack (n1 n2 n3 cach nhau boi dau cach): ");
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();

        List<Integer> h1 = new ArrayList<>();
        System.out.print("Nhap cac phan tu cua stack 1: ");
        for (int i = 0; i < n1; i++) {
            h1.add(scanner.nextInt());
        }

        List<Integer> h2 = new ArrayList<>();
        System.out.print("Nhap cac phan tu cua stack 2: ");
        for (int i = 0; i < n2; i++) {
            h2.add(scanner.nextInt());
        }

        List<Integer> h3 = new ArrayList<>();
        System.out.print("Nhap cac phan tu cua stack 3: ");
        for (int i = 0; i < n3; i++) {
            h3.add(scanner.nextInt());
        }

        int result = Result.equalStacks(h1, h2, h3);
        System.out.println("Chieu cao toi da khi 3 stack can bang la: " + result);

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
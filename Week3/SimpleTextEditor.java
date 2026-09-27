import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class SimpleTextEditor {

    static class UndoOp {
        int type;
        int len;
        String str;

        UndoOp(int type, int len) {
            this.type = type;
            this.len = len;
        }

        UndoOp(int type, String str) {
            this.type = type;
            this.str = str;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong thao tac: ");

        if (!scanner.hasNextLine()) return;

        int q = Integer.parseInt(scanner.nextLine().trim());
        StringBuilder sb = new StringBuilder();
        Stack<UndoOp> stack = new Stack<>();

        System.out.println("Goi y thao tac:");
        System.out.println(" 1 W : Them chuoi W vao cuoi");
        System.out.println(" 2 k : Xoa k ki tu cuoi cung");
        System.out.println(" 3 k : In ra ki tu thu k (1-indexed)");
        System.out.println(" 4   : Hoan tac (Undo)");

        for (int i = 0; i < q; i++) {
            System.out.print("Nhap thao tac thu " + (i + 1) + ": ");
            String req = scanner.nextLine().trim();
            if (req.isEmpty()) continue;

            String[] parts = req.split(" ");
            int type = Integer.parseInt(parts[0]);

            if (type == 1) {
                //1: Append
                String arg = parts[1];
                sb.append(arg);
                stack.push(new UndoOp(1, arg.length()));
            } else if (type == 2) {
                // 2: Delete
                int k = Integer.parseInt(parts[1]);
                String deletedStr = sb.substring(sb.length() - k);
                sb.delete(sb.length() - k, sb.length());
                stack.push(new UndoOp(2, deletedStr));
            } else if (type == 3) {
                // 3: Print
                int k = Integer.parseInt(parts[1]);
                System.out.println("-> Ki tu thu " + k + " la: " + sb.charAt(k - 1));
            } else if (type == 4) {
                //4: Undo
                if (!stack.isEmpty()) {
                    UndoOp op = stack.pop();
                    if (op.type == 1) {
                        sb.delete(sb.length() - op.len, sb.length());
                    } else if (op.type == 2) {
                        sb.append(op.str);
                    }
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
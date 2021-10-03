public class Node {
    private Creature data;
    private Node next;

    public Node(Creature d) {
        this(d, null);
    }

    public Node(Creature d, Node n) {
        setData(d);
        setNext(n);
    }

    public Creature getData() {
        return data;
    }

    public Creature setData(Creature d) {
        Creature temp = data;
        data = d;
        return temp;
    }

    public Node getNext() {
        return next;
    }

    public Node setNext(Node n) {
        Node temp = next;
        next = n;
        return temp;
    }
}
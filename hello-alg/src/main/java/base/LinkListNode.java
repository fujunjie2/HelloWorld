package base;

public class LinkListNode<T> {

    public T value;

    public LinkListNode<T> next;

    public LinkListNode() {

    }
    public LinkListNode(T value) {
        this.value = value;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkListNode<Integer> head = (LinkListNode<Integer>) this;

        sb.append(head.value);
        while (head.next != null) {
            head = head.next;
            sb.append(head.value);
        }

        return sb.toString();
    }
}

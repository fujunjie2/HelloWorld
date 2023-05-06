package plus;

import base.LinkListNode;

public class TwoPlus {


    public static LinkListNode<Integer> solution(LinkListNode<Integer> L1, LinkListNode<Integer> L2) {

        LinkListNode<Integer> head = null, tail = null;
        int carry = 0;
        while (L1 != null || L2 != null) {
            int v1 = L1 == null ? 0 : L1.value;
            int v2 = L2 == null ? 0 : L2.value;

            int sum = v1 + v2 + carry;

            if (head == null) {
                head = tail = new LinkListNode<>(sum % 10);
            } else {
                tail.next = new LinkListNode<>(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (L1 != null) {
                L1 = L1.next;
            }
            if (L2 != null) {
                L2 = L2.next;
            }
        }
        if (carry > 0) {
            tail.next = new LinkListNode<>(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        LinkListNode<Integer> L1 = new LinkListNode<>(9);

        LinkListNode<Integer> h2 = L1.next = new LinkListNode<>(2);
        LinkListNode<Integer> h3 = h2.next = new LinkListNode<>(2);


        LinkListNode<Integer> L2 = new LinkListNode<>(2);
        LinkListNode<Integer> g1 = L2.next = new LinkListNode<>(2);
        LinkListNode<Integer> g2 = g1.next = new LinkListNode<>(2);

        LinkListNode<Integer> rest = solution(L1, L2);

        System.out.println(rest);

    }
}

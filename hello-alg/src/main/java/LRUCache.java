import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class LRUCache {

    class DLinkNode {
        int key;
        int value;

        DLinkNode prev;
        DLinkNode next;

        public DLinkNode() {}

        public DLinkNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkNode> cache = new HashMap<>();

    private int size;
    private int capacity;

    private DLinkNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        head = new DLinkNode();
        tail = new DLinkNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkNode node = cache.get(key);
        if (node == null) {
            return -1;
        }

        return node.value;
    }

    public void put(int key, int value) {
        DLinkNode node = cache.get(key);

        // 为空，则需要创建一个节点，放进 cache 前 检查一下 size + 1 是否 <= capacity。
        // 如果否，则需要移除 双向链表的尾部节点，
        if (node == null) {
             DLinkNode newNode = new DLinkNode(key, value);

             cache.put(key, newNode);
             size++;

             if (size > capacity) {
                 DLinkNode tailNode = removeTail();
                 cache.remove(tailNode.key);
                 size--;
             }
        } else {
            // 不为空，则更新这个 节点的value值，并将这个节点在双向 链表中移动到头结点

            node.value = value;

            moveToHead(node);
        }
    }

    public void addToHead(DLinkNode node) {
         node.next = head.next;
         node.prev = head;
         head.next.prev = node;
         head.next = node;
    }

    public void removeNode(DLinkNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void moveToHead(DLinkNode node) {
        removeNode(node);
        addToHead(node);
    }

    public DLinkNode removeTail() {
        DLinkNode theTail = tail.prev;
        removeNode(theTail);
        return theTail;
    }



}

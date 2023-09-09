public class Basket {
    public Node head;

    private class Node {
        private Entity value;
        private Node next;
    }


    public Boolean add(Entity entity, Basket[] basketArray) {
        if (basketArray[calculateIndex(entity.getKey(), basketArray)] != null) {
            return false;
        }
        Node node = new Node();
        node.value = entity;
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        return true;
    }
    private Integer calculateIndex(Object key, Basket[] basketArray) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() % basketArray.length;
    }
    private Basket get(Object key, int index, Basket[] basketArray) {
        if (basketArray[index] != null) {
            return basketArray[index];
        }
        return null;
    }
}

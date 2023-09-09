public class Tree<Key extends Comparable<Key>, Value> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    public Node getRoot() {
        return root;
    }

    /**
     * Вспомогательный класс для представления узла дерева
     */
    class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private boolean color;
        private int subtreeSize;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.subtreeSize = size;
        }
        @Override
        public String toString() {
            return "(" + key + ", " + value + ")";
        }
        public Node getKey() {
            return root;
        }

        public Node getValue() {
            return root;
        }
    }
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node node, Key key, Value val) {
        if (node == null) return new Node(key, val, RED, 1);

        int compare = key.compareTo(node.key);
        if      (compare < 0) node.left  = put(node.left,  key, val);
        else if (compare > 0) node.right = put(node.right, key, val);
        else              node.value = val;


        if (isRed(node.right) && !isRed(node.left))      node = rotateLeft(node);
        if (isRed(node.left)  &&  isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left)  &&  isRed(node.right))     turnColors(node);

        node.subtreeSize = size(node.left) + 1 + size(node.right);
        return node;
    }

    private boolean isRed(Node nodeIsRed) {
        if (nodeIsRed == null) return false;
        return nodeIsRed.color == RED;
    }

    /**
     * левый малый поворот
     * @param nodeRotateLeft
     * @return
     */
    private Node rotateLeft(Node nodeRotateLeft) {
        Node nodeIsRed = nodeRotateLeft.right;
        nodeRotateLeft.right = nodeIsRed.left;
        nodeIsRed.left = nodeRotateLeft;
        nodeIsRed.color = nodeRotateLeft.color;
        nodeRotateLeft.color = RED;
        nodeIsRed.subtreeSize = nodeRotateLeft.subtreeSize;
        nodeRotateLeft.subtreeSize = size(nodeRotateLeft.left) + 1 + size(nodeRotateLeft.right);
        return nodeIsRed;
    }

    /**
     * правый малый поворот
     * @param nodeRotateRight
     * @return
     */
    private Node rotateRight(Node nodeRotateRight) {
        Node nodeIsRed = nodeRotateRight.left;
        nodeRotateRight.left = nodeIsRed.right;
        nodeIsRed.right = nodeRotateRight;
        nodeIsRed.color = nodeRotateRight.color;
        nodeRotateRight.color = RED;
        nodeIsRed.subtreeSize = nodeRotateRight.subtreeSize;
        nodeRotateRight.subtreeSize = size(nodeRotateRight.left) + 1 + size(nodeRotateRight.right);
        return nodeIsRed;
    }

    /**
     * смена цвета
     * @param nodeTurnColors
     */
    private void turnColors(Node nodeTurnColors) {
        nodeTurnColors.color = RED;
        nodeTurnColors.left.color = BLACK;
        nodeTurnColors.right.color = BLACK;
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.subtreeSize;
    }

}

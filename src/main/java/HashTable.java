public class HashTable<K, V> {
    public final Integer FISK_BASKET_SIZE = 16;
    public Basket[] basketArray;

    public HashTable(Integer size) {
        this.basketArray = new Basket[size];
    }

    public HashTable() {
        this.basketArray = new Basket[16];
    }

    public int calculateIndex(K key) {
        int index = 0;
        if (key != null) {
            index = key.hashCode() % this.basketArray.length;
            if (index < 0) {
                index += this.basketArray.length;
            }
        }
        return index;
    }
}
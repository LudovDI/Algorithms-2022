package lesson4;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        SortedMap<Character, Node> children = new TreeMap<>();
    }

    private final Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() {
        return new TrieIterator();
    }

    public class TrieIterator implements Iterator<String> {

        private final List<String> resultList;
        private String lastLine;

        private TrieIterator() {
            resultList = new ArrayList<>();
            if (root != null) {
                traversal("", root);
            }
        }

        private void traversal(String path, Node currentNode) {
            if (currentNode.children != null) {
                for (Character symbol: currentNode.children.keySet()) {
                    if (symbol == (char) 0)
                        resultList.add(path);
                    else
                        traversal(path + symbol, currentNode.children.get(symbol));
                }
            }
        }

        /**
         * Проверка наличия следующего элемента
         * Трудоемкость T = O(1) - считываем первый элемент списка
         * Ресурсоемкость R = О(1) - размер используемой памяти не зависит от размера списка
         */
        @Override
        public boolean hasNext() {
            return !resultList.isEmpty();
        }

        /**
         * Получение следующего элемента
         * Трудоемкость Т = O(1) - считываем первый элемент списка
         * Ресурсоемкость R = О(1) - размер используемой памяти не зависит от размера списка
         */
        @Override
        public String next() {
            if (resultList.isEmpty()) throw new NoSuchElementException();
            lastLine = resultList.get(0);
            resultList.remove(0);
            return lastLine;
        }

        /**
         * Удаление элемента
         * Трудоемкость Т = O(maxLength) - чтобы удалить элемент, нужно найти его в дереве
         * Ресурсоемкость R = O(1)
         */
        @Override
        public void remove() {
            if (lastLine == null) throw new IllegalStateException();
            Trie.this.remove(lastLine);
            lastLine = null;
        }
    }
}
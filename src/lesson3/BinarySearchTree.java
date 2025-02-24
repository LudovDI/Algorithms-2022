package lesson3;

import java.util.*;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// attention: Comparable is supported but Comparator is not
public class BinarySearchTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;
        Node<T> left = null;
        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    /**
     * Добавление элемента в дерево
     * <p>
     * Если элемента нет в множестве, функция добавляет его в дерево и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * <p>
     * Спецификация: {@link Set#add(Object)} (Ctrl+Click по add)
     * <p>
     * Пример
     */
    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    /**
     * Удаление элемента из дерева
     * <p>
     * Если элемент есть в множестве, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     * <p>
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     * <p>
     * Средняя (5 баллов)
     */
    @Override
    public boolean remove(Object o) {
        /*
        Т = O(log(n)) - чтение множества узлов дерева
        R = О(1) - размер используемой памяти от размера дерева не зависит
         */
        if (!contains(o)) return false;
        T t = (T) o;
        Node<T> parent = null;
        Node<T> parentChild;
        Node<T> child;
        Node<T> removedNode = root;
        while (t.compareTo(removedNode.value) != 0) {
            parent = removedNode;
            if (t.compareTo(removedNode.value) < 0) {
                removedNode = parent.left;
            } else {
                removedNode = parent.right;
            }
        }
        if (removedNode == root) {
            if (removedNode.right == null && removedNode.left == null) {
                root = null;
            } else if (removedNode.right != null ^ removedNode.left != null) {
                if (removedNode.right != null) {
                    root = removedNode.right;
                } else {
                    root = removedNode.left;
                }
            } else {
                parentChild = removedNode;
                child = removedNode.right;
                while (child.left != null) {
                    parentChild = child;
                    child = child.left;
                }
                if (parentChild != removedNode) {
                    if (child.right != null) {
                        parentChild.left = child.right;
                    } else {
                        parentChild.left = null;
                    }
                }
                if (child != root.left)
                    child.left = root.left;
                if (child != root.right)
                    child.right = root.right;
                root = child;
            }
        }
        if (removedNode.right == null && removedNode.left == null && parent != null) {
            if (t.compareTo(parent.value) < 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if ((removedNode.right != null ^ removedNode.left != null) && parent != null) {
            if (removedNode.right != null) {
                if (t.compareTo(parent.value) < 0) {
                    parent.left = removedNode.right;
                } else {
                    parent.right = removedNode.right;
                }
            } else {
                if (t.compareTo(parent.value) < 0) {
                    parent.left = removedNode.left;
                } else {
                    parent.right = removedNode.left;
                }
            }
        } else if (parent != null) {
            parentChild = removedNode;
            child = removedNode.right;
            while (child.left != null) {
                parentChild = child;
                child = child.left;
            }
            if (child.right != null) {
                if (parentChild == removedNode) {
                    parentChild.right = child.right;
                } else {
                    parentChild.left = child.right;
                }
            } else {
                if (parentChild == removedNode) {
                    parentChild.right = null;
                } else {
                    parentChild.left = null;
                }
            }
            child.left = removedNode.left;
            child.right = removedNode.right;
            if (t.compareTo(parent.value) < 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
        size--;
        return true;
    }

    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinarySearchTreeIterator();
    }

    public class BinarySearchTreeIterator implements Iterator<T> {

        private final Queue<Node<T>> queue;

        private BinarySearchTreeIterator() {
            queue = new ArrayDeque<>();
            if (root != null) {
                traversal(root);
            }
        }

        private void traversal(Node<T> nodeStart) {
            if (nodeStart.left != null) {
                traversal(nodeStart.left);
            }
            queue.add(nodeStart);
            if (nodeStart.right != null) {
                traversal(nodeStart.right);
            }
        }

        /**
         * Проверка наличия следующего элемента
         * <p>
         * Функция возвращает true, если итерация по множеству ещё не окончена (то есть, если вызов next() вернёт
         * следующий элемент множества, а не бросит исключение); иначе возвращает false.
         * <p>
         * Спецификация: {@link Iterator#hasNext()} (Ctrl+Click по hasNext)
         * <p>
         * Средняя
         */
        @Override
        public boolean hasNext() {
            /*
            Т = O(1) - считываем первый элемент очереди
            R = О(1) - размер используемой памяти не зависит от размера очереди
             */
            return queue.peek() != null;
        }

        /**
         * Получение следующего элемента
         * <p>
         * Функция возвращает следующий элемент множества.
         * Так как BinarySearchTree реализует интерфейс SortedSet, последовательные
         * вызовы next() должны возвращать элементы в порядке возрастания.
         * <p>
         * Бросает NoSuchElementException, если все элементы уже были возвращены.
         * <p>
         * Спецификация: {@link Iterator#next()} (Ctrl+Click по next)
         * <p>
         * Средняя
         */
        @Override
        public T next() {
            /*
            Т = O(1) - считываем первый элемент очереди
            R = О(1) - размер используемой памяти не зависит от размера очереди
             */
            if (queue.peek() == null) throw new NoSuchElementException();
            else return queue.poll().value;
        }

        /**
         * Удаление предыдущего элемента
         * <p>
         * Функция удаляет из множества элемент, возвращённый крайним вызовом функции next().
         * <p>
         * Бросает IllegalStateException, если функция была вызвана до первого вызова next() или же была вызвана
         * более одного раза после любого вызова next().
         * <p>
         * Спецификация: {@link Iterator#remove()} (Ctrl+Click по remove)
         * <p>
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    /**
     * Подмножество всех элементов в диапазоне [fromElement, toElement)
     * <p>
     * Функция возвращает множество, содержащее в себе все элементы дерева, которые
     * больше или равны fromElement и строго меньше toElement.
     * При равенстве fromElement и toElement возвращается пустое множество.
     * Изменения в дереве должны отображаться в полученном подмножестве, и наоборот.
     * <p>
     * При попытке добавить в подмножество элемент за пределами указанного диапазона
     * должен быть брошен IllegalArgumentException.
     * <p>
     * Спецификация: {@link SortedSet#subSet(Object, Object)} (Ctrl+Click по subSet)
     * (настоятельно рекомендуется прочитать и понять спецификацию перед выполнением задачи)
     * <p>
     * Очень сложная (в том случае, если спецификация реализуется в полном объёме)
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Подмножество всех элементов строго меньше заданного
     * <p>
     * Функция возвращает множество, содержащее в себе все элементы дерева строго меньше toElement.
     * Изменения в дереве должны отображаться в полученном подмножестве, и наоборот.
     * <p>
     * При попытке добавить в подмножество элемент за пределами указанного диапазона
     * должен быть брошен IllegalArgumentException.
     * <p>
     * Спецификация: {@link SortedSet#headSet(Object)} (Ctrl+Click по headSet)
     * (настоятельно рекомендуется прочитать и понять спецификацию перед выполнением задачи)
     * <p>
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Подмножество всех элементов нестрого больше заданного
     * <p>
     * Функция возвращает множество, содержащее в себе все элементы дерева нестрого больше toElement.
     * Изменения в дереве должны отображаться в полученном подмножестве, и наоборот.
     * <p>
     * При попытке добавить в подмножество элемент за пределами указанного диапазона
     * должен быть брошен IllegalArgumentException.
     * <p>
     * Спецификация: {@link SortedSet#tailSet(Object)} (Ctrl+Click по tailSet)
     * (настоятельно рекомендуется прочитать и понять спецификацию перед выполнением задачи)
     * <p>
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

}
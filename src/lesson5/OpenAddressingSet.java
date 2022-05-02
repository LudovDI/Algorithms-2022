package lesson5;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class OpenAddressingSet<T> extends AbstractSet<T> {

    private final int bits;

    private final int capacity;

    private final Object[] storage;

    private int size = 0;

    enum Deleted {
        DELETED
    }

    private int startingIndex(Object element) {
        return element.hashCode() & (0x7FFFFFFF >> (31 - bits));
    }

    public OpenAddressingSet(int bits) {
        if (bits < 2 || bits > 31) {
            throw new IllegalArgumentException();
        }
        this.bits = bits;
        capacity = 1 << bits;
        storage = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Проверка, входит ли данный элемент в таблицу
     */
    @Override
    public boolean contains(Object o) {
        int index = startingIndex(o);
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                return true;
            }
            index = (index + 1) % capacity;
            current = storage[index];
        }
        return false;
    }

    /**
     * Добавление элемента в таблицу.
     *
     * Не делает ничего и возвращает false, если такой же элемент уже есть в таблице.
     * В противном случае вставляет элемент в таблицу и возвращает true.
     *
     * Бросает исключение (IllegalStateException) в случае переполнения таблицы.
     * Обычно Set не предполагает ограничения на размер и подобных контрактов,
     * но в данном случае это было введено для упрощения кода.
     */
    @Override
    public boolean add(T t) {
        int startingIndex = startingIndex(t);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null && current != Deleted.DELETED) {
            if (current.equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                throw new IllegalStateException("Table is full");
            }
            current = storage[index];
        }
        storage[index] = t;
        size++;
        return true;
    }

    /**
     * Удаление элемента из таблицы
     *
     * Если элемент есть в таблица, функция удаляет его из дерева и возвращает true.
     * В ином случае функция оставляет множество нетронутым и возвращает false.
     * Высота дерева не должна увеличиться в результате удаления.
     *
     * Спецификация: {@link Set#remove(Object)} (Ctrl+Click по remove)
     *
     * Средняя
     *
     * Трудоемкость T = O(1 / (1 - A)) - трудоемкость зависит от коэффициента заполнения
     * Ресурсоемкость R = O(1) - размер используемой памяти не зависит от размера массива
     */
    @Override
    public boolean remove(Object o) {
        int startingIndex = startingIndex(o);
        int index = startingIndex;
        Object current = storage[index];
        while (current != null) {
            if (current.equals(o)) {
                storage[index] = Deleted.DELETED;
                size--;
                return true;
            }
            index = (index + 1) % capacity;
            if (index == startingIndex) {
                break;
            }
            current = storage[index];
        }
        return false;
    }

    /**
     * Создание итератора для обхода таблицы
     *
     * Не забываем, что итератор должен поддерживать функции next(), hasNext(),
     * и опционально функцию remove()
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Средняя (сложная, если поддержан и remove тоже)
     */
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new OpenAddressingSetIterator();
    }

    public class OpenAddressingSetIterator implements Iterator<T> {

        private int indexOfSet;
        private Integer currentIndex;

        private OpenAddressingSetIterator() {
            indexOfSet = nextIndexOfSet(0);
            currentIndex = null;
        }

        private int nextIndexOfSet(int index) {
            while (index < capacity && (storage[index] == null || storage[index] == Deleted.DELETED)) {
                index++;
            }
            return index;
        }

        /**
         * Проверка наличия следующего элемента
         * Трудоемкость T = O(1) - считываем индекс элемента и размерность массива
         * Ресурсоемкость R = О(1) - размер используемой памяти не зависит от размера массива
         */
        @Override
        public boolean hasNext() {
            return indexOfSet < capacity;
        }

        /**
         * Получение следующего элемента
         * Трудоемкость Т = O(1 / A) - трудоемкость зависит от коэффициента заполнения
         * Ресурсоемкость R = О(1) - размер используемой памяти не зависит от размера массива
         */
        @Override
        public T next() {
            if (indexOfSet >= capacity) throw new NoSuchElementException();
            else {
                Object element = storage[indexOfSet];
                currentIndex = indexOfSet;
                indexOfSet = nextIndexOfSet(indexOfSet + 1);
                return (T) element;
            }
        }

        /**
         * Удаление элемента
         * Трудоемкость Т = O(1) - обращение к элементу массива по его индексу
         * Ресурсоемкость R = O(1) - размер используемой памяти не зависит от размера массива
         */
        @Override
        public void remove() {
            if (currentIndex == null) throw new IllegalStateException();
            else {
                storage[currentIndex] = Deleted.DELETED;
                currentIndex = null;
                size--;
            }
        }
    }
}

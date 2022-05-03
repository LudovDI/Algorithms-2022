package lesson7;

import kotlin.NotImplementedError;
import kotlin.collections.EmptyList;

import java.util.*;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     *
     * Трудоемкость T = O((first.length()+1)*(second.length()+1)) - вложенный цикл из 2 уровней,
     *      один цикл с first.length()+1 операцией, второй цикл с second.length()+1 операцией и
     *      цикл while с max(second.length, first.length) операцией
     * Ресурсоемкость R = O((first.length()+1)*(second.length()+1)) -
     *      двумерный массив размером (first.length()+1)х(second.length()+1),
     *      строка длиной min(first.length(), second.length())
     */
    public static String longestCommonSubSequence(String first, String second) {
        int m = first.length() + 1;
        int n = second.length() + 1;
        int[][] lcs = new int[n][m];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (first.charAt(j - 1) == second.charAt(i - 1))
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                else lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int i = second.length();
        int j = first.length();
        while (i != 0 && j != 0) {
            if (lcs[i - 1][j - 1] == lcs[i][j] - 1 && first.charAt(j - 1) == second.charAt(i - 1)) {
                stack.push(first.charAt(j - 1));
                i--;
                j--;
            } else if (lcs[i - 1][j] == lcs[i][j]) {
                i--;
            } else {
                j--;
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }
    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     *
     * Трудоемкость T = O(n^2) - вложенный цикл с n^2 операцией
     * Ресурсоемкость R = O(n) - два массива размером n, лист размером n и строка длиной n (в худшем случае)
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        if (list.isEmpty()) return result;
        int[] arrayOfLength = new int[list.size()];
        int[] arrayOfIndex = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayOfLength[i] = 1;
            arrayOfIndex[i] = -1;
            int maxLength = 0;
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i)) {
                    if (1 + arrayOfLength[j] > arrayOfLength[j] && 1 + arrayOfLength[j] > maxLength) {
                        arrayOfLength[i] = 1 + arrayOfLength[j];
                        maxLength = 1 + arrayOfLength[j];
                        arrayOfIndex[i] = j;
                    }
                }
            }
        }
        int answer = arrayOfLength[0];
        int position = 0;
        for (int i = 0; i < list.size(); i++) {
            if (arrayOfLength[i] > answer) {
                answer = arrayOfLength[i];
                position = i;
            }
        }
        while (position != -1) {
            for (int i = position; i > 0; i--) {
                if (arrayOfIndex[i - 1] == arrayOfIndex[i] && list.get(i) > list.get(i - 1)) {
                    position--;
                } else break;
            }
            result.add(list.get(position));
            position = arrayOfIndex[position];
        }
        Collections.reverse(result);
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }
}

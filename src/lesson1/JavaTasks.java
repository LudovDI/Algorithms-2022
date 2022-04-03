package lesson1;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        /*
        O(nlog(n)) - чтение из файла, запись в файл и заполнение массива это O(n), а быстрая сортировка O(nlog(n))
        R(n) - массив, список и Map
         */
        Map<Integer, String> myMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
            String line = br.readLine();
            while (line != null) {
                int time = 0;
                String[] lineParts = line.split(" ");
                if (lineParts.length == 2) {
                    String[] data = lineParts[0].split(":");
                    if (Integer.parseInt(data[2]) < 60 && Integer.parseInt(data[2]) >= 0 &&
                            Integer.parseInt(data[1]) < 60 && Integer.parseInt(data[1]) >= 0 &&
                            Integer.parseInt(data[0]) <= 12 && Integer.parseInt(data[0]) >= 0) {
                        if (Integer.parseInt(data[0]) == 12) {
                            time += Integer.parseInt(data[2]) + Integer.parseInt(data[1]) * 60;
                        } else time += Integer.parseInt(data[2]) + Integer.parseInt(data[1]) * 60 +
                                Integer.parseInt(data[0]) * 3600;
                        switch (lineParts[1]) {
                            case "AM" -> {
                            }
                            case "PM" -> time += 12 * 3600;
                            default -> throw new IOException("Wrong format");
                        }
                    } else throw new IOException("Wrong format");
                } else throw new IOException("Wrong format");
                myMap.put(time, line);
                list.add(time);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        Sorts.quickSort(array);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
            for (int j : array) {
                bw.write(myMap.get(j));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) {
        /*
        O(nlog(n)) - чтение из файла, запись в файл и заполнение массива это O(n), а быстрая сортировка O(nlog(n))
        R(n) - массив, список и Map
        */
        Map<Integer, String> myMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int number;
        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
            String line = br.readLine();
            while (line != null) {
                double element = Double.parseDouble(line);
                number = (int) (element * 10);
                list.add(number);
                myMap.put(number, line);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        Sorts.quickSort(array);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
            for (int j : array) {
                bw.write(myMap.get(j));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        /*
        O(n) - чтение из файла, запись в файл и заполнение массива это O(n)
        R(n) - список и Map
         */
        Map<Integer, Integer> myMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int numberOfSequence = 0;
        int maxSequence = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
            String line = br.readLine();
            while (line != null) {
                int number = Integer.parseInt(line);
                if (myMap.containsKey(number)) {
                    myMap.replace(number, myMap.get(number) + 1);
                } else myMap.put(number, 1);
                if (maxSequence < myMap.get(number) || maxSequence == 0) {
                    maxSequence = myMap.get(number);
                    numberOfSequence = number;
                } else if (maxSequence == myMap.get(number) && numberOfSequence > number) {
                    numberOfSequence = number;
                }
                list.add(number);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputName))) {
            for (int j : list) {
                if (j != numberOfSequence) {
                    bw.write(Integer.toString(j));
                    bw.newLine();
                }
            }
            for (int i = 0; i < maxSequence; i++) {
                bw.write(Integer.toString(numberOfSequence));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}

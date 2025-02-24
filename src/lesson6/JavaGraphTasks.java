package lesson6;

import kotlin.NotImplementedError;
import lesson6.impl.GraphBuilder;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     *
     * Трудоемкость T = O(E^2) - метод eulerCycle вызывается Е раз, трудоемкость внутри цикла for O(Е),
     *                           где E - количество дуг в графе.
     * Ресурсоемкость R = O(Е) - 2 списка размером Е.
     */
    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        List<Graph.Edge> result = new LinkedList<>();
        Graph.Vertex randomVertex = new GraphBuilder.VertexImpl("");
        if (graph.getVertices().size() <= 1) return result;
        for (Graph.Vertex vertex : graph.getVertices()) {
            if (graph.getNeighbors(vertex).size() % 2 != 0) return result;
            randomVertex = vertex;
        }
        List<Graph.Edge> path = new LinkedList<>();
        eulerCycle(graph, randomVertex, path, result);
        return result.size() == graph.getEdges().size() ? result : Collections.emptyList();
    }
    static void eulerCycle(Graph graph, Graph.Vertex v, List<Graph.Edge> path, List<Graph.Edge> result) {
        for (Graph.Vertex vertex : graph.getNeighbors(v)) {
            if (!path.contains(graph.getConnection(v, vertex))) {
                path.add(graph.getConnection(v, vertex));
                eulerCycle(graph, vertex, path, result);
                result.add(graph.getConnection(v, vertex));
            }
        }
    }
    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан связный граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     *
     * Трудоемкость T = O(V^2*E) - цикл while с V операцией, вложенный цикл for с Е операцией,
     *      трудоемкость внутри цикла for O(V) (где V - количество вершин, E - количество дуг в графе)
     * Ресурсоемкость R = O(V) - очередь размером V, список размером V, Graph размером V.
     */
    public static Graph minimumSpanningTree(Graph graph) {
        Queue<Graph.Vertex> queueVertex = new ArrayDeque<>(graph.getVertices());
        List<String> treeVertices = new LinkedList<>();
        GraphBuilder graphBuilder = new GraphBuilder();
        while (!queueVertex.isEmpty()) {
            Graph.Vertex vertex = queueVertex.poll();
            for (Graph.Vertex neighbor : graph.getNeighbors(vertex)) {
                if (!treeVertices.contains(vertex.getName()) || !treeVertices.contains(neighbor.getName())) {
                    if (!treeVertices.contains(vertex.getName())) {
                        treeVertices.add(vertex.getName());
                        graphBuilder.addVertex(vertex.getName());
                    }
                    if (!treeVertices.contains(neighbor.getName())) {
                        treeVertices.add(neighbor.getName());
                        graphBuilder.addVertex(neighbor.getName());
                    }
                    graphBuilder.addConnection(vertex, neighbor, 1);

                }
            }
        }
        return graphBuilder.build();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }


    /**
     * Балда
     * Сложная
     *
     * Задача хоть и не использует граф напрямую, но решение базируется на тех же алгоритмах -
     * поэтому задача присутствует в этом разделе
     *
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     *
     * И Т Ы Н
     * К Р А Н
     * А К В А
     *
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     *
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     *
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     *
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        throw new NotImplementedError();
    }
}

import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Node {

    public int node;
    public int weight;

    public Node() {
    }

    public Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }

}

public class Dijkstra {

    public void calculateShortestDistance(int[][] graph, int src, int[] vertices) {
        int numVertices = vertices.length;
        boolean[] visited = new boolean[numVertices];
        int[] weights = new int[numVertices];
        int[][] edges = new int[numVertices][numVertices];
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                if (node1.weight < node2.weight)
                    return -1;

                if (node1.weight > node2.weight)
                    return 1;

                return 0;
            }
        });

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                edges[i][j] = Integer.MAX_VALUE;
            }
            weights[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        System.out.println("Edges \t Initial edge weights");

        for (int i = 0; i < graph.length; i++) {
            edges[graph[i][0]][graph[i][1]] = graph[i][2];
            edges[graph[i][1]][graph[i][0]] = graph[i][2];
            System.out.println(graph[i][0] + " --> " + graph[i][1] + " \t " + graph[i][2]);
        }

        weights[src] = 0;
        queue.add(new Node(src, weights[src]));

        while (true) {
            if (queue.size() == 0)
                break;

            Node curr = queue.poll();
            for (int j = 0; j < numVertices; j++) {

                if (visited[j])
                    continue;

                if (edges[curr.node][j] == Integer.MAX_VALUE) {
                    continue;
                }

                int newVal = weights[curr.node] + edges[curr.node][j];
                if (newVal < weights[j] && !visited[j]) {
                    weights[j] = newVal;
                    map.put(j, curr.node);

                    queue.add(new Node(j, weights[j]));
                }

            }
            visited[curr.node] = true;
        }

        System.out.println("\n-------------------------------------------------------");
        System.out.println();

        System.out.println("After applying Dijkstra's algorithm ");
        printVertices(vertices);
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < numVertices; i++)
            System.out.println(i + " \t\t " + weights[i]);

        System.out.println("\n-------------------------------------------------------");
        System.out.println();
        System.out.println("Edges for the shortest path: ");

        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> item : list) {
            temp.put(item.getKey(), item.getValue());
        }

        System.out.println("Source \t\t Destination \t Destination node weight");
        for (int key : temp.keySet()) {
            System.out.println(temp.get(key) + "\t ---> \t\t" + key + " \t\t " + weights[key]);
        }

    }

    public void printVertices(int[] vertices) {

        System.out.println("Vertices: ");
        for (int i : vertices) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {

        int[] vertices = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int[][] graph = { { 0, 1, 1 }, { 0, 2, 4 }, { 1, 2, 2 }, { 1, 3, 9 }, { 1, 6, 4 }, { 2, 3, 1 }, { 2, 4, 3 },
                { 3, 4, 1 }, { 3, 6, 1 },
                { 3, 5, 3 }, { 4, 5, 1 }, { 4, 8, 5 }, { 6, 8, 3 }, { 8, 5, 2 }, { 8, 7, 14 }, { 1, 7, 2 }, { 6, 9, 2 },
                { 6, 5, 6 }, { 5, 9, 6 }, { 7, 9, 5 } };

        Dijkstra d = new Dijkstra();
        d.printVertices(vertices);
        d.calculateShortestDistance(graph, 0, vertices);
    }

}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Graph {

    private int S;
    private int start;
    private int dist[];
//    private  int parent[];
    private List<List<Integer>> parent;
    private List<Integer> visited;
    private List<List<Node>> adjacentList;
    private PriorityQueue<Node> pq;

    public Graph(int S){

        this.S = S;
        dist = new int[S];
//        parent = new int[S];
        parent = new ArrayList<List<Integer>>(S);
        for(int i = 0;i < S; i++){
            parent.add(new ArrayList<Integer>(){{add(-1);add(-1);}});
        }
        visited = new ArrayList<>(S);
        pq = new PriorityQueue<Node>(S, new Node());

    }

    static class Node implements Comparator<Node> {

        int label;
        int cost;

        public Node() {
        }

        public Node(int label, int cost) {
            this.label = label;
            this.cost = cost;
        }


        @Override
        public int compare(Node node1, Node node2) {
            if(node1.cost < node2.cost)
                return -1;
            else if(node1.cost > node2.cost)
                return 1;
            else
                return 0;
        }
    }

    public void dijkstra(List<List<Node>> adjacentList, int start){

        this.adjacentList = adjacentList;
        this.start = start;

        for (int i = 0; i < S; i++)
            dist[i] = Integer.MAX_VALUE;

        pq.add(new Node(start, 0));

        dist[start] = 0;
        parent.set(0,new ArrayList<Integer>(){{
            add(-1);
            add(-1);
        }});

        while(visited.size() != S){

            if(pq.isEmpty())
                return;

            int min = pq.remove().label;

            if(visited.contains(min))
                continue;

            visited.add(min);

            neighbours(min);
        }

    }

    public void neighbours(int present){

        int newDistance = -1;

        for(int i=0; i < adjacentList.get(present).size();i++){
            Node next = adjacentList.get(present).get(i);

            if(!visited.contains(next.label)){
                newDistance = dist[present] + next.cost;

                if(newDistance < dist[next.label]){
                    dist[next.label] = newDistance;
                    parent.set(next.label,new ArrayList<Integer>(){{
                        add(present);
                        add(next.cost);
                    }});
//                    parent[next.label] = present;
                }
                pq.add(new Node(next.label, dist[next.label]));
            }
        }
    }

    public void printVertices(){
        for(int i = 0; i < S; i++)
            System.out.print(i+ " ");
        System.out.println();
    }

    public void printEdges(List<List<Node>> adjacentList){
        System.out.println("Edges in the graph");
        for(int i = 0; i < adjacentList.size(); i++){
            for(int j = 0; j < adjacentList.get(i).size(); j++){
                System.out.println(i + " -> " + adjacentList.get(i).get(j).label + " : weight = " + adjacentList.get(i).get(j).cost);
            }
        }
    }

    public void printTreeEdges(){
        System.out.println("Edges in the tree after applying dijkstra's algorithm:");

        for (int i = 0; i < S; i++){
            if(parent.get(i).get(0)<0) {
                continue;
            }
            if(i != start){
//                System.out.println(parent[i] + " -> " + i + ": path from "+ start + " to " + i + " is " + dist[i]);
                System.out.println(parent.get(i).get(0) + " -> " + i + ": weight = " + parent.get(i).get(1) + ": distance from "+ start + " to " + i + " is " + dist[i]);

            }

        }
    }

    public void writeTree(String fileName, Boolean append){
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(new File(fileName), append));
            br.write("digraph T {\n");
            for (int i = 0; i < S; i++) {
                if(i != start && parent.get(i).get(0)<0) {
                    br.write(i+";\n");
                    continue;
                }
                if (i != start) {
                    br.write(parent.get(i).get(0) + " -> " + i +  " [label=" + parent.get(i).get(1) + "];\n");
                }
            }

            br.write("}\n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeGraph(String fileName){
        BufferedWriter br = null;
        try {
            br = new BufferedWriter(new FileWriter(new File(fileName)));
            br.write("digraph G {\n");

            for(int i = 0; i < adjacentList.size(); i++) {
                for (int j = 0; j < adjacentList.get(i).size(); j++) {
                    br.write(i + " -> " + adjacentList.get(i).get(j).label + " [label=" + adjacentList.get(i).get(j).cost + "];\n");
                }
            }

            br.write("}\n");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){

        int S = 11;
        int start = 0;

        List<List<Node> > adj = new ArrayList<List<Node> >();

        for (int i = 0; i < S; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        adj.get(0).add(new Node(1, 2));
        adj.get(0).add(new Node(3, 2));
        adj.get(0).add(new Node(4, 1));
        adj.get(0).add(new Node(6, 3));

        adj.get(1).add(new Node(2, 1));
        adj.get(1).add(new Node(4, 2));

        adj.get(2).add(new Node(5, 1));

        adj.get(3).add(new Node(4, 2));
        adj.get(3).add(new Node(6, 2));
        adj.get(3).add(new Node(7, 2));

        adj.get(4).add(new Node(5, 3));
        adj.get(4).add(new Node(7, 1));
        adj.get(4).add(new Node(8, 1));

        adj.get(5).add(new Node(8, 1));

        adj.get(6).add(new Node(7, 1));
        adj.get(6).add(new Node(9, 2));
        adj.get(6).add(new Node(10, 3));

        adj.get(7).add(new Node(8, 2));
        adj.get(7).add(new Node(10, 4));

        adj.get(8).add(new Node(9, 1));
        adj.get(8).add(new Node(10, 1));

        adj.get(9).add(new Node(10, 1));

        Graph graph = new Graph(S);
        System.out.println("Vertices in the graph:");
        graph.printVertices();
        graph.printEdges(adj);
        graph.dijkstra(adj, start);

        System.out.println("--------------------------------------------");
        System.out.println("Start vertex: " + start);
        System.out.println("Vertices in the tree:");
        graph.printVertices();
        graph.printTreeEdges();

        String graphFile = "graph.dot";
        String treeFile = "tree.dot";

        graph.writeGraph(graphFile);
        graph.writeTree(treeFile, false);

    }
}

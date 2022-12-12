import java.util.*;

public class Topological {


    private Map<String, Boolean> visited;

    private Map<String, Boolean>  done;

    private Map<String, Integer>  inDegree;

    private Map<String,ArrayList<String>> adj;


    Topological()
    {
        adj = new HashMap<>();
        visited = new HashMap<>();
        done = new HashMap<>();
        inDegree = new HashMap<>();
    }


    void addEdge(String label1, String label2) {
        if(!adj.containsKey(label1))
            adj.put(label1, new ArrayList<String>());
        adj.get(label1).add(label2);

        // Initialize variables when adding edges
        if(!visited.containsKey(label1)) {
            visited.put(label1, Boolean.FALSE);
            done.put(label1, Boolean.FALSE);
            inDegree.put(label1, 0);
        }
        if(!visited.containsKey(label2)) {
            visited.put(label2, Boolean.FALSE);
            done.put(label2, Boolean.FALSE);
            inDegree.put(label2, 0);
        }

    }

    boolean dfsTopologicalUtil(String label, Map<String, Boolean> visited,Map<String, Boolean>  done, Stack<String> sort){

        boolean cycle = false;
        visited.put(label, Boolean.TRUE);

        if(adj.containsKey(label)) {
            ArrayList<String> adjacentNodes = adj.get(label);

            for (int i = 0; i < adjacentNodes.size(); i++) {
                if (!done.get(adjacentNodes.get(i)) && visited.get(adjacentNodes.get(i))) {
                    return true;
                }
                if (!done.get(adjacentNodes.get(i))) {
                    cycle = dfsTopologicalUtil(adjacentNodes.get(i), visited, done, sort) || cycle;
                }
            }
        }
        done.put(label, Boolean.TRUE);
        sort.push(label);
        return cycle;
    }

    void dfsInitialize(){
        for (String key : visited.keySet()) {
            visited.put(key, Boolean.FALSE);
            done.put(key, Boolean.FALSE);
        }
    }

    void bfsInitialize(){

        for (String key : inDegree.keySet()) {
            inDegree.put(key, 0);
        }
        for (String key : inDegree.keySet()) {
            if(adj.containsKey(key)) {
                ArrayList<String> adjacentNodes = adj.get(key);
                for (String temp : adjacentNodes)
                    inDegree.put(temp, inDegree.get(temp) + 1);
            }
        }
    }

    void dfsTopologicalSort(){

        Stack<String> sort = new Stack<>();

        dfsInitialize();

        System.out.println("DFS Topological Sort:");

        for (String key : visited.keySet()) {
            if(!visited.get(key))
                if(dfsTopologicalUtil(key, visited, done, sort)){
                    System.out.println("There exists a cycle in the graph");
                    return;
                }
        }

        while (!sort.empty()){
            System.out.print(sort.pop()+" ");
        }
        System.out.println();
    }


    void bfsTopologicalSort() {

        bfsInitialize();
        Queue<String> q = new LinkedList<String>();


        System.out.println("BFS Topological Sort:");

        int V = 0;

        for (String key : inDegree.keySet()){
            // Total no. of vertices
            V++;
            if (inDegree.get(key) == 0)
                q.add(key);
        }

        ArrayList<String> tpSort = new ArrayList<>(V);

        int count = 0;

        while (!q.isEmpty()){
            // Add to first element of queue to Topological order
            String temp = q.poll();
            tpSort.add(temp);

            if(adj.containsKey(temp)) {
                for (String adjacentNode : adj.get(temp)) {
                    inDegree.put(adjacentNode, inDegree.get(adjacentNode) - 1);
                    if (inDegree.get(adjacentNode) == 0) {
                        q.add(adjacentNode);
                    }
                }
            }
            count++;
        }

        if(count != V){
            System.out.println("There exists a cycle in the graph");
            return;
        }

        // Print topological order
        for (String i : tpSort) {
            System.out.print(i + " ");
        }
        System.out.println();
    }


    public static void main(String args[])
    {
        System.out.println("Graph 1");
        Topological g1 = new Topological();

        g1.addEdge("1","2");
        g1.addEdge("1","6");
        g1.addEdge("1","5");

        g1.addEdge("2","3");
        g1.addEdge("2","5");
        g1.addEdge("2","7");

        g1.addEdge("3","4");

        g1.addEdge("4","5");

        g1.addEdge("5","7");
        g1.addEdge("5","8");

        g1.addEdge("6","5");
        g1.addEdge("6","8");

        g1.addEdge("7","4");
        g1.addEdge("7","8");


        g1.dfsTopologicalSort();
        g1.bfsTopologicalSort();

        System.out.println("-------------------------");


        System.out.println("Graph 2");
        Topological g2 = new Topological();


        g2.addEdge("m","q");
        g2.addEdge("m","r");
        g2.addEdge("m","x");

        g2.addEdge("n","q");
        g2.addEdge("n","u");
        g2.addEdge("n","o");

        g2.addEdge("o","r");
        g2.addEdge("o","s");
        g2.addEdge("o","v");

        g2.addEdge("p","o");
        g2.addEdge("p","s");
        g2.addEdge("p","z");

        g2.addEdge("q","t");

        g2.addEdge("r","u");
        g2.addEdge("r","y");

        g2.addEdge("s","r");

        g2.addEdge("u","t");

        g2.addEdge("v","w");
        g2.addEdge("v","x");

        g2.addEdge("w","z");

        g2.addEdge("y","v");

        g2.dfsTopologicalSort();
        g2.bfsTopologicalSort();


    }

}
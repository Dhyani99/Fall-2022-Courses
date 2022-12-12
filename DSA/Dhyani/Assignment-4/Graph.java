import java.util.*;

public class Graph {
    
    private int vertices;
    private int num;
    boolean flag = false;
    ArrayList<LinkedList<Character>> adjList = new ArrayList<>();
    Map<Character, Integer> inDegreeMap = new HashMap<>();
    Queue<Character> queue = new LinkedList<>();
    Map<Integer, Character> bfsOrder = new HashMap<>();
    ArrayList<Character> dfsList = new ArrayList<>();
    ArrayList<Character> dfsResult = new ArrayList<>();

    public Graph(char[] arr){
        this.vertices = arr.length;
        this.num = arr.length;
        for(int i=0; i<arr.length; i++){
            LinkedList<Character> vertex = new LinkedList<Character>();
            vertex.add(arr[i]);
            dfsList.add(arr[i]);
            inDegreeMap.put(arr[i], 0);
            adjList.add(vertex);
        }        
        
    }
    
    public void insertEdge(Character src, Character dest){
        for(LinkedList<Character> eachList: adjList){
            if(eachList.get(0).equals(src)){
                eachList.add(dest);
                inDegreeMap.put(dest, inDegreeMap.getOrDefault(dest,0)+1);
                break;
            }
        }       
    }

    public void helper(int v, boolean[] visited, int[] order, boolean[] done){
        visited[v] = true;

        Iterator<Character> itr= adjList.get(v).listIterator(1);

        while(itr.hasNext()){
            Character i=itr.next();
            if(visited[dfsList.indexOf(i)] && !done[dfsList.indexOf(i)]){
                flag = true;
                break;
            }

            if(!visited[dfsList.indexOf(i)]){
                helper(dfsList.indexOf(i), visited, order, done);
            }
        }

        order[v] = num;
        num--;
        done[v] = true;
        dfsResult.add(dfsList.get(v));
        

    }
    
    
    public void DFSTopoSort(){
        for(LinkedList<Character> eachList: adjList){
            Collections.sort(eachList.subList(1, eachList.size()));
        }
        
        boolean[] visited = new boolean[vertices];
        boolean[] done = new boolean[vertices];
        int[] order = new int[vertices];
        for(int i=0; i<vertices; i++){
            visited[i] = false;
            done[i] = false;
            order[i] = -1;
        }    
        System.out.println("DFS Topological Sorting order:");

        for(int i=0; i<vertices; i++){
            if(flag){
                System.out.println("Cycle");
                break;
            }
            if(!visited[i] && !done[i] && !flag){
                helper(i, visited, order, done);
            }
        }


        if(!flag){
            
            for(int i=vertices-1; i>=0; i--){
                
                System.out.print(dfsResult.get(i)+" ");

            }

        }      

    }

    public void BFSTopoSort(char[] vertices){
        int order = 1;
        int count = 0;
        for(Map.Entry<Character, Integer> m: inDegreeMap.entrySet()){
            if(m.getValue()==0){
                queue.add(m.getKey());
            }
        }
        System.out.println("BFS Topological Sorting order:");
        
        while(!queue.isEmpty()){
            Character c = queue.peek();
            bfsOrder.put(order, c);
            for(LinkedList<Character> eachList: adjList){
                if(eachList.get(0)==(c)) {
                    for(Character eachDest: eachList.subList(1, eachList.size())){
                        int degree = inDegreeMap.get(eachDest);
                        inDegreeMap.put(eachDest, degree-1);
                    }
                }
            }
            Character qChar = queue.poll();          
            order++;
            inDegreeMap.remove(qChar);
            for(Map.Entry<Character, Integer> m: inDegreeMap.entrySet()){
                if(m.getValue()==0 && !queue.contains(m.getKey())){
                    queue.add(m.getKey());
                }
            }
            count++;

        }
        
        if(count != vertices.length){
            System.out.println("Cycle");
        }else{
            for(Map.Entry<Integer, Character> m : bfsOrder.entrySet()){
                System.out.print(m.getValue()+" ");
            }
        }

    }
    
    public static void main(String args[]){
        
        System.out.println("Graph g1: ");

        char[] vertices1 = {'m', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Graph graph = new Graph(vertices1);
        graph.insertEdge('s', 'r');
        graph.insertEdge('r', 'u');
        graph.insertEdge('r', 'y');
        graph.insertEdge('u', 't');
        graph.insertEdge('y', 'v');
        graph.insertEdge('v', 'w');
        graph.insertEdge('w', 'z');
        graph.insertEdge('v', 'x');
        graph.insertEdge('m', 'x');
        graph.insertEdge('m', 'q');
        graph.insertEdge('m', 'r');
        graph.insertEdge('q', 't');
        graph.insertEdge('n', 'q');
        graph.insertEdge('n', 'u');
        graph.insertEdge('n', 'o');
        graph.insertEdge('o', 'r');
        graph.insertEdge('o', 'v');
        graph.insertEdge('o', 's');
        graph.insertEdge('p', 'o');
        graph.insertEdge('p', 's');
        graph.insertEdge('p', 'z');
        graph.DFSTopoSort();
        System.out.println();
        graph.BFSTopoSort(vertices1);
        System.out.println();
        System.out.println();
        System.out.println("Graph g2: ");
        char[] vertices ={'1','2','3','4','5','6','7','8'};
        graph = new Graph(vertices);
        graph.insertEdge('1', '6');
        graph.insertEdge('1', '2');
        graph.insertEdge('1', '5');
        graph.insertEdge('2', '3');
        graph.insertEdge('2', '5');
        graph.insertEdge('2', '7');
        graph.insertEdge('3', '4');
        graph.insertEdge('4', '5');
        graph.insertEdge('5', '7');
        graph.insertEdge('5', '8');
        graph.insertEdge('6', '5');
        graph.insertEdge('6', '8');
        graph.insertEdge('7', '4');
        graph.insertEdge('7', '8');
        graph.DFSTopoSort();
        System.out.println();
        graph.BFSTopoSort(vertices);
        System.out.println();
    }
}


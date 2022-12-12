#How to Execute Assignment-4


Run the following commands in the same directory as the Graph.java in the same order.
```console
javac Graph.java
java Graph
```
## Input

The graph vertex should start from 0 to n-1, where n is the number of vertices in the given graph. Start index can be anything.
## The program creates two dot files containing Initial Graph representation and the Tree representation after applying Dijkstra's Algorithm

Name of the files created are graph.dot and tree.dot

**Info**: Need Graphviz installed to convert dot files to png
```console
dot graph.dot -Tpng -o graph.png
```
Tried on Windows!

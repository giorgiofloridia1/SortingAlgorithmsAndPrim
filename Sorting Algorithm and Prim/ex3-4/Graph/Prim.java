package Graph;

import java.util.*;
import java.io.*;

import PriorityQueue.PriorityQueue;

public class Prim {
  
  private static final Graph<String, Float> graph = new Graph<String, Float>(false, true);
  private static int nNode = 0;


  public static <V, L extends Number> Collection<? extends AbstractEdge<V, L>> minimumSpanningForest(Graph<V, L> graph) {
    Comparator<Edge<V, L>> edgeComparator = (o1, o2) -> Double.compare(o1.getLabel().doubleValue(), o2.getLabel().doubleValue());
    Set<V> visited = new HashSet<>();
    Collection<Edge<V, L>> forest = new ArrayList<>();
    PriorityQueue<Edge<V, L>> queue = new PriorityQueue<>(edgeComparator);

    for (V node : graph.getNodes()) {
      if (!visited.contains(node)) {
        visited.add(node);
        for (V next : graph.getNeighbours(node)) {
          queue.push(new Edge<>(node, next, graph.getLabel(node, next)));
        }
        while (!queue.empty()) {
          Edge<V, L> edge = queue.top();
          queue.pop();
          V currentNode = edge.getEnd();
          if (visited.contains(currentNode)) {
            continue;
          }
          forest.add(edge);
          visited.add(currentNode);
          for (V nextNode : graph.getNeighbours(currentNode)) {
            if (!visited.contains(nextNode)) {
              queue.push(new Edge<>(currentNode, nextNode, graph.getLabel(currentNode, nextNode)));
            }
          }
        }
      }
    }

    nNode = visited.size();
    return forest;
  } 

  private static void printMsf() {
    double weight = 0;
    Collection<? extends AbstractEdge<String, Float>> forest = minimumSpanningForest(graph);
    for (AbstractEdge<String, Float> edge : forest) {
        weight += edge.getLabel().doubleValue();
    }
    System.out.println("Number of nodes in the forest: " + nNode);
    System.out.println("Number of edges in the forest: " + forest.size());
    weight= weight / 1000;
    System.out.printf("Total weight of the forest: %.3f km%n",weight);

  }

  //Reads the data from CSV file and constructs the graph
  private static void readCsv(String path) {
    String line;
    try(BufferedReader br = new BufferedReader(new FileReader(path))){
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        graph.addNode(split[0]);
        graph.addNode(split[1]);
        graph.addEdge(split[0], split[1], Float.valueOf(split[2]));
      }
    }catch(IOException e) {
    }
  }

  public static void main(String[] args) {
    if(args.length == 0){
      System.out.println("ERROR, correct use: java Prim <file>\n");
      System.exit(1);
    }
    readCsv(args[0]);
    printMsf();
  }
}
  
  

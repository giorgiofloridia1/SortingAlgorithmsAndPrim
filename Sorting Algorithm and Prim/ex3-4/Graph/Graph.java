package Graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V, L> implements AbstractGraph<V, L>  {

    private int sizeEdge;
    private int sizeNode;
    private final boolean directed;
    private final boolean labelled;
    private final Map<V, HashSet<Edge<V, L>>> neighbourList;
    final Map<V, Set<V>> adjacencyList;

    // Constructor to initialize the graph
    public Graph(boolean directed, boolean labelled) {
        sizeEdge = 0;
        sizeNode = 0;
        this.directed = directed; 
        this.labelled = labelled; 
        neighbourList = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public boolean isLabelled() {
        return labelled;
    }

    public boolean isEmpty() {
        return numNodes() == 0 && numEdges() == 0;
    }

    @Override
    public boolean addNode(V a){
        if(a == null){
            return false;
        }else if(neighbourList.containsKey(a) == true){
            return false;
        }else{
            neighbourList.put(a, new HashSet<>());
            adjacencyList.put(a, new HashSet<>());
            sizeNode++;
            return true;
        }

    }

    //adds an edge between two elements(a,b), with a label 
    @Override
    public boolean addEdge(V a, V b, L l){
        if(a == null || b == null){
            return false;
        }
        if(!containsNode(a)){
            return false;
        }else if(!containsNode(b)){
            return false;
        }
        if(containsEdge(a, b)){
        }
        if(!labelled && l != null){
            return false;
        }else if(labelled && l == null){
            return false;
        }
        neighbourList.get(a).add(new Edge<>(a, b, l));
        adjacencyList.get(a).add(b);
        if (!directed){ 
            neighbourList.get(b).add(new Edge<>(b, a, l));
            adjacencyList.get(b).add(a);
        }
        sizeEdge++;
        return true;
    }

    //checks if a node is in the graph
    public boolean containsNode(V a){
        return neighbourList.containsKey(a);
    }

    //chechs if an edge is in the graph
    public boolean containsEdge(V a, V b){
        if(!neighbourList.containsKey(a) || !neighbourList.containsKey(b)){
            return false;
        }
        return adjacencyList.get(a).contains(b);
    }

    //removes a node from the graph
    public boolean removeNode(V a){
        if(!containsNode(a) || a == null){
            return false;
        }
        Set<V> adjacentNodes = adjacencyList.get(a);
        for (V adjacentNode : adjacentNodes) {
            Set<Edge<V, L>> edges = neighbourList.get(adjacentNode);
            edges.removeIf(edge -> edge.getEnd().equals(a));
            sizeEdge--; 
            if (!directed) { 
                Set<V> edgesToRemove = adjacencyList.get(adjacentNode); 
                edgesToRemove.remove(a); 
            }
        }
        neighbourList.remove(a);
        adjacencyList.remove(a);
        sizeNode--;
        return true;
    }

    //removes an edge from the graph
    public boolean removeEdge(V a, V b){
        if(!containsEdge(a, b)){
            return false;
        }else if(!adjacencyList.containsKey(a) || !adjacencyList.containsKey(b)){
            return false;
        }else{
            HashSet<Edge<V,L>> edges = neighbourList.get(a); 
            if(edges.removeIf(edge -> edge.getEnd().equals(b))){
                adjacencyList.get(a).remove(b);
                if(!directed){
                    HashSet<Edge<V,L>> reverseEdges = neighbourList.get(b);
                    reverseEdges.removeIf(edge -> edge.getEnd().equals(a));
                    adjacencyList.get(b).remove(a);
                }
                sizeEdge--;
                return true;
            }else return false;
        }      
    }

    public int numNodes(){
        return sizeNode;
    }

    public int numEdges(){
        return sizeEdge;
    }

    //gets the nodes of the graph
    public Collection<V> getNodes(){
        return neighbourList.keySet();
    }
    
    //gets the edges of the graph
    public Collection<? extends AbstractEdge<V,L>> getEdges(){
        if(numEdges() == 0) return Collections.emptySet();
        Collection<Edge<V, L>> edges = new HashSet<>();
        for (Set<Edge<V, L>> edgeSet : neighbourList.values()) {
            edges.addAll(edgeSet);
        }
        return edges;
    }

    //gets the neighbors of a node
    public Collection<V> getNeighbours(V a){
        return adjacencyList.get(a);
    }

    //gets the label of an edge
    public L getLabel(V a, V b){
        if (!labelled) {
            return null;
        }
        if (!this.containsEdge(a, b)) {
        }
        HashSet<Edge<V, L>> edges = neighbourList.get(a);
        for (Edge<V, L> edge : edges) {
            if (edge.getEnd().equals(b)) {
                return edge.getLabel();
            }
        }
        return null;
    }
}    
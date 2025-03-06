package Graph;

public class Edge<V, L> implements AbstractEdge<V, L> {
    private final V start;
    private final V end;
    private final L label;

    //constructor method
    public Edge(V a, V b, L l) {
        start = a;
        end = b;
        label = l;
    }

    @Override
    public V getStart() {
        if (start == null) System.out.println("The starting node of the edge is null.\n");
        return start;
    }

    @Override
    public V getEnd() {
        if (end == null) System.out.println("The destination node of the edge is null.\n");
        return end;
    }

    @Override
    public L getLabel() {
        if (label == null) System.out.println("The label of the edge is null.");
        return label;
    }
}

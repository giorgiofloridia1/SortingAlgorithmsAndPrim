package Graph;

import org.junit.Test;

import java.beans.Transient;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class GraphTest {

    @Test
    public void testGetNode() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestGetNode()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        Collection<Integer> test = graph.getNodes();
        assertTrue(test.contains(5));
        assertTrue(test.contains(6));
        assertTrue(test.contains(7));
    }

    @Test
    public void testLabelled() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestLabelled()");
        assertTrue(graph.isLabelled());
    }

    @Test
    public void testDirected() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(true, false);
        System.out.print("\ntestDirected()");
        assertTrue(graph.isDirected());
    }

    @Test
    public void testNodeAdded() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestNodeAdded()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.containsNode(5));
        assertEquals(1, graph.numNodes());
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testNodesAdded() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestNodesAdded()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(3));
        assertTrue(graph.containsNode(5));
        assertTrue(graph.containsNode(3));
        assertEquals(2, graph.numNodes());
        assertFalse(graph.isEmpty());
    }

    @Test
    public void testRemoveNode() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(true, false);
        System.out.print("\ntestRemoveNode()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertFalse(graph.removeNode(null));
        assertFalse(graph.removeNode(8));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.addEdge(5, 7, null));
        assertTrue(graph.removeNode(5));
        assertFalse(graph.containsNode(5));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertFalse(graph.containsEdge(5, 7));
        assertTrue(graph.containsNode(6));
        assertFalse(graph.containsNode(5));
        assertEquals(2, graph.numNodes());
        assertEquals(0, graph.numEdges());
    }

    @Test    
    public void testRemoveNodeNotDirected() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, false);
        System.out.print("\ntestRemoveNodeNotDirected()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertTrue(graph.addNode(8));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.addEdge(5, 7, null));
        assertTrue(graph.addEdge(5, 8, null));
        assertTrue(graph.removeNode(5));
        assertFalse(graph.containsNode(5));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertEquals(3, graph.numNodes());
        assertEquals(0, graph.numEdges());
        assertTrue(graph.containsNode(6));
        assertFalse(graph.containsNode(5));
    }

    @Test
    public void testRemoveEdgeDirected() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(true, false);
        System.out.print("\ntestRemoveEdgeDirected()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.removeEdge(5, 6));
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertTrue(graph.containsNode(6));
        assertTrue(graph.containsNode(5));
    }

    @Test
    public void testRemoveEdgeNotDirected() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, false);
        System.out.print("\ntestRemoveEdgeNotDirected()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, null));
        assertTrue(graph.removeEdge(5, 6)); 
        assertFalse(graph.containsEdge(5, 6));
        assertFalse(graph.containsEdge(6, 5));
        assertTrue(graph.containsNode(6));
        assertTrue(graph.containsNode(5));
    }
 
    @Test
    public void testGetLabel() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestGetLabel()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addEdge(5, 6, "prova"));
        assertEquals(graph.getLabel(5, 6), "prova");
    }

    @Test
    public void testGetNeighbourList() {
        Graph<Integer, String> graph = null;
        graph = new Graph<Integer, String>(false, true);
        System.out.print("\ntestGetNeighbourList()");
        assertTrue(graph.addNode(5));
        assertTrue(graph.addNode(6));
        assertTrue(graph.addNode(7));
        assertTrue(graph.addNode(8));
        assertTrue(graph.addEdge(5, 6, "prova"));
        assertTrue(graph.addEdge(5, 7, "prova"));
        assertTrue(graph.addEdge(6, 7, "prova"));
        assertTrue(graph.addEdge(7, 8, "prova"));
        assertTrue(graph.addEdge(8, 5, "prova"));
        Collection<Integer> test = graph.getNeighbours(5);
        assertTrue(test.contains(6));
        assertTrue(test.contains(7));
    }
}
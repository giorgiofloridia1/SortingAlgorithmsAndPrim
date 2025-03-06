BIN_PRIORITY_QUEUE :=PriorityQueue
BIN_GRAPH :=Graph
BIN_DIR := bin
DATA_DIR := data

.PHONY: all queue queueL graph graphL cleanup

all: 
	javac -cp "lib/*" -d $(BIN_DIR) $(BIN_PRIORITY_QUEUE)/*.java
	javac -cp "lib/*" -d $(BIN_DIR) $(BIN_GRAPH)/*.java $(BIN_PRIORITY_QUEUE)/*.java
queue:
	java -cp "$(BIN_DIR);lib/*" PriorityQueue.PriorityQueueTestRunner
	
#Option for linux system    
queueL:
	java -cp "$(BIN_DIR):lib/*" PriorityQueue.PriorityQueueTestRunner    
graph:
	java -cp "$(BIN_DIR);lib/*" Graph.GraphTestRunner
#Option for linux system
graphL:
	java -cp "$(BIN_DIR):lib/*" Graph.GraphTestRunner
prim:
	java -cp "$(BIN_DIR);lib/*" Graph.Prim "./italian_dist_graph.csv"
#Option for linux system
primL:
	java -cp "$(BIN_DIR):lib/*" Graph.Prim "./italian_dist_graph.csv"

cleanup:
	rm -f $(BIN_DIR)/PriorityQueue/*.class $(BIN_DIR)/Graph/*.class 
	rm -d $(BIN_DIR)/PriorityQueue $(BIN_DIR)/Graph $(BIN_DIR)
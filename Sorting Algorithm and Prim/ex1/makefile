BIN_SRC := src
BIN_DIR := bin

$(shell mkdir -p $(BIN_DIR))

all: 
	gcc -o $(BIN_DIR)/test_ex1 $(BIN_SRC)/test.c
	gcc -o $(BIN_DIR)/main_ex1 $(BIN_SRC)/main.c  $(BIN_SRC)/mergeSort.c $(BIN_SRC)/quickSort.c
cleanup:
	rm -f $(BIN_DIR)/*.o $(BIN_DIR)/test_ex1 $(BIN_DIR)/main_ex1 
	rm -d $(BIN_DIR)
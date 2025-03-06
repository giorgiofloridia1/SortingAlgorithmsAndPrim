BIN_SRC := src
BIN_DIR := bin
DATA_DIR := data

$(shell mkdir -p $(BIN_DIR))
$(shell mkdir -p $(DATA_DIR))

all: 
	gcc -o $(BIN_DIR)/test_ex2 $(BIN_SRC)/test.c $(BIN_SRC)/edit_distance.c
	gcc -o $(BIN_DIR)/main_ex2 $(BIN_SRC)/main.c $(BIN_SRC)/edit_distance.c

run:
	./bin/main_ex2 $(DATA_DIR)/dictionary.txt $(DATA_DIR)/correctme.txt

cleanup:
	rm -f $(BIN_DIR)/*.o $(BIN_DIR)/_ex2 $(BIN_DIR)/test_ex2 $(BIN_DIR)/main_ex2
# Makefile for GenericsKB Assignment
# Abdullah Ali Mubarak ALMABD030

# Directories
SRC_DIR = src
BIN_DIR = bin

# Data files
DATA_FILES = GenericsKB.txt GenericsKB-additional.txt

# Java compiler
JC = javac
JFLAGS = -d $(BIN_DIR)

# Default target - compile and copy in one step
all: setup compile

# Setup directories
setup:
	mkdir -p $(BIN_DIR)

# Compile all Java files and copy data files as a dependency
compile: $(BIN_DIR) copy-data
	$(JC) $(JFLAGS) $(SRC_DIR)/*.java

# Copy data files from src to bin directory
copy-data:
	@echo "Copying data files to bin directory..."
	@for file in $(DATA_FILES); do \
		if [ -f $(SRC_DIR)/$$file ]; then \
			cp $(SRC_DIR)/$$file $(BIN_DIR)/; \
			echo "  Copied $$file"; \
		else \
			echo "  Warning: $$file not found in $(SRC_DIR) directory"; \
		fi; \
	done

# Generate Javadoc documentation
docs:
	javadoc -d $(DOC_DIR) -sourcepath $(SRC_DIR) $(SRC_DIR)/*.java

# Run the BST application
run-bst: all
	cd $(BIN_DIR) && java GenericsKbBSTApp

# Run the Array application
run-array: all
	cd $(BIN_DIR) && java GenericsKbArrayApp

# Clean up
clean:
	rm -rf $(BIN_DIR)/*

# Makefile for Hash Table Assignment

# Variables
JC = javac
RM = rm -f
JFLAGS = -g
JAVA_FILES = $(wildcard *.java)
CLASS_FILES = $(JAVA_FILES:.java=.class)

# Default target - compile all java files
all: $(CLASS_FILES)

# Rule for compiling .java files to .class files
%.class: %.java
	$(JC) $(JFLAGS) $<

# Clean target - removes all .class files
clean:
	$(RM) *.class

# Run test targets
test-lp:
	java LPAutoTest

test-qp:
	java QPAutoTest

# Phony targets
.PHONY: all clean test-lp test-qp

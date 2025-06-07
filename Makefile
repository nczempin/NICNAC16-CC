CXX ?= g++
CXXFLAGS = -std=c++20 -Wall -Wextra -pedantic
TARGET = nicnac16-cc
SRC = src/main.cpp

all: $(TARGET)

$(TARGET): $(SRC)
	$(CXX) $(CXXFLAGS) -o $@ $(SRC)

clean:
	rm -f $(TARGET)

run: $(TARGET)
	./$(TARGET) $(ARGS)

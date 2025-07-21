# Martian Robots

This is a solution to the Martian Robots challenge implemented in Kotlin.

## Problem Description

The surface of Mars can be modelled by a rectangular grid around which robots are able to move according to instructions provided from Earth. A robot position consists of a grid coordinate (x, y) and an orientation (N, S, E, W). A robot instruction is a string of the letters "L", "R", and "F" which represent, respectively, the instructions:

- Left: the robot turns left 90 degrees and remains on the current grid point.
- Right: the robot turns right 90 degrees and remains on the current grid point.
- Forward: the robot moves forward one grid point in the direction of the current orientation and maintains the same orientation.

The grid is rectangular and bounded. A robot that moves "off" an edge of the grid is lost forever. However, lost robots leave a robot "scent" that prohibits future robots from dropping off the world at the same grid point. The scent is left at the last grid position the robot occupied before disappearing over the edge. An instruction to move "off" the world from a grid point from which a robot has been previously lost is simply ignored by the current robot.

## Design Approach

The solution is designed with a focus on clean, idiomatic Kotlin code and follows object-oriented principles. The main components are:

1. **Domain Models**:
   - `Position`: Represents a position on the grid with x and y coordinates.
   - `Orientation`: Enum representing the four cardinal directions (N, S, E, W) with methods for turning left and right.
   - `Instruction`: Enum representing the possible instructions (L, R, F) with methods for parsing instructions from strings.
   - `Robot`: Represents a robot with a position, orientation, and lost status.
   - `Grid`: Represents the rectangular grid with methods for checking if a position is valid and tracking scents.

2. **Controller**:
   - `RobotController`: Controls the movement of robots on the grid, processing instructions and handling boundary checking and scent tracking.

3. **Application**:
   - `MartianRobots`: Main application class that handles input parsing, processing, and output generation.

## Running the Application

### Prerequisites

- Java 8 or higher
- Kotlin 1.5 or higher
- Gradle 7.0 or higher

### Building the Application

```bash
./gradlew build
```

### Running the Application

```bash
./gradlew run --args="input.txt"
```

Or you can run the application without arguments to read from standard input:

```bash
./gradlew run
```

### Running the Tests

```bash
./gradlew test
```

## Input/Output Format

### Input

The first line of input is the upper-right coordinates of the rectangular world, the lower-left coordinates are assumed to be 0, 0.

The remaining input consists of a sequence of robot positions and instructions (two lines per robot). A position consists of two integers specifying the initial coordinates of the robot and an orientation (N, S, E, W), all separated by whitespace on one line. A robot instruction is a string of the letters "L", "R", and "F" on one line.

Example:
```
5 3
1 1 E
RFRFRFRF
3 2 N
FRRFLLFFRRFLL
0 3 W
LLFFFLFLFL
```

### Output

For each robot position/instruction in the input, the output indicates the final grid position and orientation of the robot. If a robot falls off the edge of the grid the word "LOST" is printed after the position and orientation.

Example:
```
1 1 E
3 3 N LOST
2 3 S
```

## Design Decisions

1. **Immutability**: The domain models are designed to be immutable, using Kotlin's data classes and immutable properties. This makes the code more predictable and easier to reason about.

2. **Functional Style**: The solution uses a functional style where appropriate, such as using `when` expressions for handling different orientations and instructions.

3. **Extensibility**: The design allows for easy extension, such as adding new types of instructions or orientations in the future.

4. **Separation of Concerns**: The code is organized into separate classes with clear responsibilities, making it easier to understand and maintain.

5. **Error Handling**: The solution includes proper error handling for invalid input, such as invalid orientation symbols or instruction symbols.

6. **Testing**: The solution includes comprehensive unit tests to verify the correctness of the implementation.
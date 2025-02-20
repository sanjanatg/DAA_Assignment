# TSP Solver

## Description
This project implements a graphical Travelling Salesperson Problem (TSP) solver using Java and Swing. It supports two algorithms:
- **Nearest Neighbor** heuristic
- **2-opt Improvement** for route optimization

The visualization includes an animated tour display with interactive controls.

## Features
- Randomly generates cities and road networks.
- Implements TSP algorithms for route optimization.
- Interactive UI with animated route display.
- Displays calculated tour distance.

## Usage
1. Run the `Main` class in a Java IDE or using the command line.
2. Click **Nearest Neighbor** to generate an initial tour.
3. Click **2-opt Improvement** to optimize the tour.
4. The visualization updates dynamically.

## Requirements
- Java 8 or higher
- Swing (included in Java SE)

## File Structure
- `City.java` - Represents a city with coordinates and road connections.
- `RoadNetwork.java` - Generates random roads between cities.
- `TSP.java` - Implements Nearest Neighbor and 2-opt algorithms.
- `TSPVisualizer.java` - Handles graphical rendering and animation.
- `Main.java` - Entry point to run the application.


## How to Use
1. Run the program to generate a random set of cities and roads.
2. Click **"Nearest Neighbor"** to find an initial solution.
3. Click **"2-opt Improvement"** to optimize the tour further.
4. Watch the animation as the tour is visualized.
5. The total tour distance is displayed at the bottom.

## Algorithms Used
### 1. Nearest Neighbor (NN)
- Starts from a random city.
- Iteratively visits the nearest unvisited city.
- Forms an initial (but suboptimal) tour.

### 2. 2-opt Optimization
- Iteratively improves the NN solution.
- Swaps city connections to minimize total tour distance.
- Stops when no further improvement is possible.

## Example Output
Upon running the program, a GUI window opens displaying:
- A set of **red dots** (cities) with an ID label.
- **Gray lines** representing road paths between cities.
- **Blue lines** showing the animated TSP tour.
- **Distance label** showing the total tour length.

## Future Enhancements
- Add **more TSP heuristics** (e.g., Genetic Algorithm, Simulated Annealing)
- Improve **road generation logic** for more realistic paths
- Add **user input support** to specify cities manually



![image](https://github.com/user-attachments/assets/1f27654a-a1f3-49da-af2f-f9b1f9128781)

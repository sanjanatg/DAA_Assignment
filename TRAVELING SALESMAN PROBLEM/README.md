# TSP Solver with Road Network Visualization

## Description
This project implements a **Traveling Salesperson Problem (TSP) Solver** with a graphical **road network visualization** using Java and Swing. It generates a set of cities, constructs a network of roads between them, and solves the TSP using the **Nearest Neighbor** and **2-opt Improvement** algorithms. The solution is animated in real-time.

## Features
- **Random city and road network generation**
- **Graphical visualization** of cities and road paths
- **TSP solving algorithms**:
  - Nearest Neighbor
  - 2-opt Improvement
- **Real-time animation** of the computed tour
- **Interactive GUI** with buttons to start algorithms
- **Displays total tour distance**

## Technologies Used
- **Java** (Swing for GUI)
- **Awt Graphics2D** (for rendering)
- **Java Timer** (for animation control)

## Installation
1. Ensure you have **Java (JDK 8+)** installed on your system.
2. Clone this repository:
   ```sh
   git clone https://github.com/your-username/TSP-Solver.git
   ```
3. Navigate to the project directory:
   ```sh
   cd TSP-Solver
   ```
4. Compile and run the program:
   ```sh
   javac Main.java
   java Main
   ```

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

## Author
- **[Your Name]**
- GitHub: [your-github-link](https://github.com/your-username)

## License
This project is open-source and available under the **MIT License**.

![image](https://github.com/user-attachments/assets/1f27654a-a1f3-49da-af2f-f9b1f9128781)

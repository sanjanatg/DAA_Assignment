**___________________MERGE SORT VISUALIZATION________________________

**
# Merge Sort Visualization in Java

This project is a graphical visualization of the **Merge Sort** algorithm implemented in Java. It provides a step-by-step view of how the Merge Sort algorithm divides and merges an array, making it easier to understand the sorting process.

## Features

- **Merge Sort Algorithm Visualization:**
  - The array is recursively divided into smaller subarrays and then merged back together in sorted order.
  - The division and merging steps are visually displayed using different colors.

- **Graphical User Interface (GUI):**
  - The visualization is done using Java's `JPanel` and `Graphics` classes.
  - Each sorting step is drawn on the panel with arrays displayed in rectangles.
  - The colors represent different phases:
    - **Blue** for dividing the array.
    - **Green** for merging the array.
    - **Red** for the final sorted array.

- **User Input:**
  - The user is prompted to input an array as a comma-separated list.
  - If no input or invalid input is provided, the program defaults to the array `{38, 27, 43, 3, 9, 82, 10}`.

- **Animation:**
  - The sorting process is animated with one step displayed every second, allowing the user to follow the algorithm's progress in real time.

## Requirements

- **Java Development Kit (JDK) 8 or higher** is required to run the program.
- **IDE or Text Editor** to open and compile the code.

## How to Run

1. **Download the Project Files:**
   - Clone or download this repository to your local machine.

2. **Compile the Code:**
   - Open the project in your Java IDE (like IntelliJ IDEA, Eclipse, or NetBeans).
   - Alternatively, you can compile the code manually using the following command in the terminal:
     ```
     javac MergeSortVisualization.java
     ```

3. **Run the Program:**
   - Run the program by executing the `main` method of the `MergeSortVisualization` class. 
     If you're using the terminal, use this command:
     ```
     java MergeSortVisualization
     ```

4. **Input the Array:**
   - You will be prompted to enter an array of integers in a comma-separated format, e.g., `38, 27, 43, 3, 9, 82, 10`.
   - If no input is provided or the input is invalid, the program will use the default array: `{38, 27, 43, 3, 9, 82, 10}`.

5. **Visualize the Sorting Process:**
   - A window will pop up showing the Merge Sort visualization.
   - The algorithm steps will be shown in real-time, with dividing and merging steps illustrated by colored rectangles and arrows.

## Code Overview

- **MergeSortVisualization.java:**
  - This class contains the main program logic and the GUI for visualizing the Merge Sort algorithm.
  - It defines methods for:
    - Dividing and merging the array (`divide()` and `merge()` methods).
    - Drawing the array and arrows for each step (`paintComponent()` and `drawArray()` methods).
    - Animating the process using a `Timer` (`animate()` method).

- **User Input:**
  - The program accepts a comma-separated array of integers from the user.
  - If the input is invalid, a default array is used to visualize the Merge Sort process.

## Example

When the program is run, it will prompt you to input an array. For example:

If the input is invalid or left empty, the program will use the default array:

The GUI will then display the sorting process, showing the recursive division and merging of the array, along with arrows indicating the flow of the Merge Sort algorithm.


## Acknowledgements

- **Java** and the **Swing** library were used for creating the graphical user interface and visualization.
- The algorithm is based on the standard **Merge Sort** algorithm.


![WhatsApp Image 2025-02-23 at 12 43 58_50fb0baf](https://github.com/user-attachments/assets/99ae21f5-a17d-4f0c-9008-260f4ef5cb82)

![WhatsApp Image 2025-02-23 at 12 43 58_fb22c179](https://github.com/user-attachments/assets/807b617f-1b45-49f1-baac-c0531961288b)




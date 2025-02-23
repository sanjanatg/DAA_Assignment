Merge Sort Visualization
Overview
This project provides a graphical user interface (GUI) for visualizing the Merge Sort algorithm. The application takes an array of integers from the user, sorts it using the Merge Sort algorithm, and visualizes each step of the sorting process. The GUI displays the dividing and merging steps, with color-coded indicators to distinguish between the phases of the sorting process. The program uses Java Swing for the graphical interface and animations.

Features
Visualizes the Merge Sort algorithm in real-time.
Shows the original array and how it is divided into smaller arrays during the sorting process.
Highlights the merging steps with different colors for easy tracking.
Animates the sorting steps with a pause between each step to help understand the algorithm’s flow.
Allows the user to input a custom array of integers or use a default array.
Displays each step with arrows indicating the merging and dividing operations.
Requirements
Java Development Kit (JDK) version 8 or higher.
A Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans) or a text editor for writing the code.
A basic understanding of the Merge Sort algorithm and Java GUI programming with Swing.
How to Use
Run the Program:

Compile and run the MergeSortVisualization.java file.
When prompted, input an array of integers (comma-separated). For example:
Copy
38, 27, 43, 3, 9, 82, 10
If no input is provided or the input is invalid, the program will use the default array: 38, 27, 43, 3, 9, 82, 10.
Visualizing the Sort:

Once the input is provided, the Merge Sort algorithm begins to sort the array step by step.
The original array, the dividing steps, and the merging steps are displayed.
Each step is color-coded:
Blue: Dividing the array.
Green: Merging the array.
Red: Final sorted array.
The algorithm will animate with a 1-second pause between steps.
Interacting with the Interface:

The array visualization is rendered using rectangles, with each rectangle containing a number.
The arrows between steps show the merging process.
Code Structure
1. MergeSortVisualization.java
This is the main class that contains the logic for the GUI, sorting algorithm, and visualization.

Attributes:

originalArray: Stores the original input array.
array: A copy of the array used for sorting.
steps: A list of steps that describe the progress of the algorithm.
stepColors: A list of colors corresponding to each step.
stepArrays: A list of arrays that show the state of the array at each step.
stepIndex: Keeps track of the current step in the animation.
Methods:

sort(): Starts the sorting process and records the steps.
divide(): Recursively divides the array.
merge(): Recursively merges divided arrays.
performMerge(): Merges two sub-arrays and records the step.
paintComponent(): Responsible for drawing the array and visualizing the steps.
drawArray(): Draws the array at a given step.
drawArrow(): Draws arrows to indicate the direction of dividing or merging.
animate(): Animates the visualization step by step with a delay.
2. Main Method
The main() method handles user input for the array, initializes the JFrame, and starts the sorting and animation process.
How Merge Sort Works
Merge Sort is a divide-and-conquer algorithm. It works as follows:

Divide: The array is repeatedly divided into two halves until each sub-array contains a single element.
Merge: The sub-arrays are merged back together in sorted order.
Repeat: This process continues recursively until the entire array is sorted.
In the visualization:

The dividing process is shown in blue.
The merging process is shown in green.
The final sorted array is displayed in red.
Visual Elements
Rectangles: Each integer in the array is represented as a rectangle with the integer value displayed inside.
Arrows: Arrows are drawn between the rectangles to show how elements are merged.
Customization
The size and appearance of the visualization can be customized by modifying the drawArray() and drawArrow() methods.
You can change the delay between animation steps by modifying the timer interval in the animate() method.
Potential Improvements
Allow the user to pause and resume the animation.

![WhatsApp Image 2025-02-23 at 12 43 58_50fb0baf](https://github.com/user-attachments/assets/99ae21f5-a17d-4f0c-9008-260f4ef5cb82)

![WhatsApp Image 2025-02-23 at 12 43 58_fb22c179](https://github.com/user-attachments/assets/807b617f-1b45-49f1-baac-c0531961288b)




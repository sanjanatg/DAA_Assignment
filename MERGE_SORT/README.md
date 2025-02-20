Here’s a *README* file that explains the *Merge Sort Visualization with Linked List* program in Java:

---

# Merge Sort Visualization Using Linked List in Java

## Overview

This project demonstrates *Merge Sort* algorithm using *Linked List* as the underlying data structure. The program allows the user to input a series of integers, which are visualized as bars on the screen. The bars' heights correspond to the integers, and the merge sort algorithm is performed and visualized step by step. The program also allows the user to start the sorting operation by clicking a button and watch the entire sorting process in real-time.

### Features
- *Merge Sort Algorithm*: Demonstrates the divide-and-conquer approach using the merge sort algorithm.
- *Linked List Implementation*: Uses a linked list to hold the data, showcasing how merge sort works on linked lists.
- *Graphical Visualization*: The algorithm's execution is visualized using bars representing the data values.
- *Step-by-Step Visualization*: The merge sort algorithm is executed with a step-by-step update on the UI.

## Requirements

- *Java* 8 or higher.
- *IDE* such as IntelliJ IDEA, Eclipse, or any text editor with a Java compiler.
- *JDK* with Swing support for graphical user interfaces (GUI).

## How to Run

1. *Clone or Download* this repository.
2. *Compile* the MergeSortLinkedListVisualizer.java file using the terminal or an IDE.

   bash
   javac MergeSortLinkedListVisualizer.java
   

3. *Run* the program.

   bash
   java MergeSortLinkedListVisualizer
   

4. *Enter numbers*: The program will prompt you to input a series of numbers separated by commas (e.g., 5, 3, 8, 1, 4).
5. *Click* the "Merge Sort" button to start the sorting process. The sorting will be visually represented by bars that adjust their heights as the merge sort algorithm progresses.

## How It Works

1. *Input*: The program accepts a series of integers as input. The user is asked to enter numbers separated by commas.
2. *Linked List Construction*: The input numbers are stored in a linked list.
3. *Merge Sort*: The algorithm divides the linked list recursively into smaller parts and sorts them. After sorting, it merges the sorted sublists.
4. *Visualization*: As the algorithm runs, each merge and split operation is reflected in the graphical user interface. Bars representing numbers are updated dynamically to show the progress of the sort.

## Code Explanation

- *Node Class*: Defines the structure of the linked list nodes that store data and reference to the next node.
- *MergeSortLinkedListVisualizer Class*:
  - *insert()*: Inserts a new element at the end of the linked list.
  - *paintComponent()*: Renders the linked list as bars on the GUI.
  - *mergeSort()*: Performs the merge sort algorithm recursively.
  - *merge()*: Merges two sorted sublists into a single sorted linked list.
  - *getMiddle()*: Finds the middle of the linked list to divide it into two halves.
  - *repaintWithDelay()*: Updates the visualization with a delay to show sorting steps.

### Example of Input and Output

1. *Input*: 5, 3, 8, 1, 4
2. *Output* (sorted linked list): 1, 3, 4, 5, 8

The linked list will be visualized as bars, and as the merge sort progresses, the bars will rearrange to show the sorting process.

## Key Components
- *Linked List*: The data structure used to hold the values to be sorted.
- *Merge Sort*: The sorting algorithm applied to the linked list.
- *Swing GUI*: Used for the visualization of the sorting process.

## Controls
- *Merge Sort Button*: Starts the merge sort algorithm and triggers the visual updates.
- *Visualization*: The heights of the bars correspond to the values being sorted.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
![image](https://github.com/user-attachments/assets/1a4cf48d-fb62-4459-93f3-6a324d09a6d6)

This *README* gives a brief overview of the project, the steps to run it, the input-output process, and an explanation of the key components. It also includes instructions on setting up and running the project.

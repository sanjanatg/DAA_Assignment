import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class MergeSortVisualization extends JPanel {

    private int[] originalArray; // Store the original array
    private int[] array; // Array for sorting and visualization
    private List<String> steps;
    private List<Color> stepColors;
    private List<int[]> stepArrays;
    private int stepIndex;

    public MergeSortVisualization(int[] array) {
        this.originalArray = array.clone(); // Store the original array
        this.array = array.clone(); // Copy for sorting
        this.steps = new ArrayList<>();
        this.stepColors = new ArrayList<>();
        this.stepArrays = new ArrayList<>();
        this.stepIndex = 0;
        setPreferredSize(new Dimension(1200, 2000)); // Increased height to accommodate more steps
        setBackground(new Color(240, 240, 240));
    }

    public void sort() {
        steps.clear();
        stepColors.clear();
        stepArrays.clear();
        stepIndex = 0;

        // First, perform all dividing steps
        divide(array, 0, array.length - 1);

        // Then, perform all merging steps
        merge(array, 0, array.length - 1);

        // Add the final sorted array
        steps.add("Final Sorted Array: " + Arrays.toString(array));
        stepColors.add(new Color(200, 0, 0)); // Red for final sorted array
        stepArrays.add(array.clone());
    }

    private void divide(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            // Record the dividing step
            steps.add("Dividing: " + Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
            stepColors.add(new Color(0, 100, 255)); // Blue for dividing
            stepArrays.add(array.clone());
            divide(array, left, mid);
            divide(array, mid + 1, right);
        }
    }

    private void merge(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            merge(array, left, mid);
            merge(array, mid + 1, right);
            performMerge(array, left, mid, right);
        }
    }

    private void performMerge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            array[i] = temp[k];
        }

        // Record the merging step
        steps.add("Merged: " + Arrays.toString(Arrays.copyOfRange(array, left, right + 1)));
        stepColors.add(new Color(0, 200, 0)); // Green for merging
        stepArrays.add(array.clone());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 50;
        int y = 50;

        // Set font size and style
        g.setFont(new Font("Arial", Font.BOLD, 16));

        // Draw original array (the input array)
        drawArray(g, "Original Array: " + Arrays.toString(originalArray), originalArray, x, y, Color.BLACK);
        y += 70;

        // Draw steps
        for (int i = 0; i < stepIndex; i++) {
            g.setColor(stepColors.get(i));
            drawArray(g, steps.get(i), stepArrays.get(i), x, y, stepColors.get(i));

            // Draw arrows for dividing and merging
            if (steps.get(i).startsWith("Dividing")) {
                drawArrow(g, x + 200, y - 25, x + 400, y + 25, new Color(0, 100, 255)); // Blue arrow for dividing
            } else if (steps.get(i).startsWith("Merged")) {
                drawArrow(g, x + 400, y - 25, x + 200, y + 25, new Color(0, 200, 0)); // Green arrow for merging
            }

            y += 70;
        }
    }

    private void drawArray(Graphics g, String label, int[] array, int x, int y, Color color) {
        g.setColor(color);
        g.drawString(label, x, y - 10);
        int rectWidth = 40;
        int rectHeight = 30;
        int spacing = 10;

        for (int i = 0; i < array.length; i++) {
            g.drawRect(x + i * (rectWidth + spacing), y, rectWidth, rectHeight);
            g.drawString(Integer.toString(array[i]), x + i * (rectWidth + spacing) + 15, y + 20);
        }
    }

    private void drawArrow(Graphics g, int x1, int y1, int x2, int y2, Color color) {
        g.setColor(color);
        g.drawLine(x1, y1, x2, y2);
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - 10, xn = xm, ym = 5, yn = -5, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void animate() {
        Timer timer = new Timer(1000, e -> {
            if (stepIndex < steps.size()) {
                stepIndex++;
                repaint();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        // Take user input for the array
        String input = JOptionPane.showInputDialog("Enter array elements (comma-separated):");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No input provided. Using default array.");
            input = "38, 27, 43, 3, 9, 82, 10"; // Default array
        }

        // Parse the input into an integer array
        String[] elements = input.split(",");
        int[] array = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            try {
                array[i] = Integer.parseInt(elements[i].trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Using default array.");
                array = new int[]{38, 27, 43, 3, 9, 82, 10}; // Default array
                break;
            }
        }

        JFrame frame = new JFrame("Merge Sort Visualization");
        MergeSortVisualization panel = new MergeSortVisualization(array);

        // Add the panel to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        frame.add(scrollPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.sort();
        panel.animate();
    }
}
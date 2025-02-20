import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergeSortLinkedListVisualizer extends JPanel {
    private Node head;
    private JButton sortButton;
    private boolean sortingStarted = false;
    private int maxHeight = 300; // Maximum height for bars
    
    public MergeSortLinkedListVisualizer(int[] values) {
        int maxValue = Integer.MIN_VALUE;
        for (int value : values) {
            head = insert(head, value);
            maxValue = Math.max(maxValue, value);
        }
        
        final int scaleFactor = maxValue == 0 ? 1 : maxHeight / maxValue;
        
        sortButton = new JButton("Merge Sort");
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!sortingStarted) {
                    sortingStarted = true;
                    new Thread(() -> {
                        head = mergeSort(head);
                        repaint();
                    }).start();
                }
            }
        });
    }
    
    private Node insert(Node head, int data) {
        if (head == null) return new Node(data);
        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = new Node(data);
        return head;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Node temp = head;
        int x = 50;
        int maxValue = Integer.MIN_VALUE;
        Node tempCopy = head;
        while (tempCopy != null) {
            maxValue = Math.max(maxValue, tempCopy.data);
            tempCopy = tempCopy.next;
        }
        int scaleFactor = maxValue == 0 ? 1 : maxHeight / maxValue;
        
        while (temp != null) {
            int barHeight = temp.data * scaleFactor;
            g.fillRect(x, getHeight() - barHeight, 40, barHeight);
            g.drawString(Integer.toString(temp.data), x + 15, getHeight() - barHeight - 5);
            x += 50;
            temp = temp.next;
        }
    }
    
    private Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = getMiddle(head);
        Node nextToMiddle = middle.next;
        middle.next = null;
        
        Node left = mergeSort(head);
        repaintWithDelay();
        Node right = mergeSort(nextToMiddle);
        repaintWithDelay();
        
        return merge(left, right);
    }
    
    private Node getMiddle(Node head) {
        if (head == null) return head;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;
        
        Node result;
        if (left.data <= right.data) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        repaintWithDelay();
        return result;
    }
    
    private void repaintWithDelay() {
        try {
            repaint();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter numbers separated by commas:");
        if (input != null && !input.isEmpty()) {
            String[] inputValues = input.split(",");
            int[] values = new int[inputValues.length];
            for (int i = 0; i < inputValues.length; i++) {
                values[i] = Integer.parseInt(inputValues[i].trim());
            }
            
            JFrame frame = new JFrame("Merge Sort Visualization (Linked List)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            MergeSortLinkedListVisualizer visualizer = new MergeSortLinkedListVisualizer(values);
            frame.setLayout(new BorderLayout());
            frame.add(visualizer, BorderLayout.CENTER);
            frame.add(visualizer.sortButton, BorderLayout.SOUTH);
            frame.setSize(800, 600);
            frame.setVisible(true);
        }
    }
}
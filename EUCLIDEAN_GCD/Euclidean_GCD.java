import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Euclidean_GCD {
    // GUI Components
    private JFrame frame;
    private JPanel inputPanel, outputPanel, controlPanel;
    private JTextField countField;
    private JTextArea liveStepsArea, finalStepsArea;
    private JButton startButton, pauseButton, resumeButton;
    private volatile boolean paused = false; // Flag to control animation pause/resume

    public Euclidean_GCD() {
        // Set up the main frame
        frame = new JFrame("Euclidean Algorithm GCD Animation");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Input panel for number of values
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Enter number of values:"));
        countField = new JTextField();
        inputPanel.add(countField);
        startButton = new JButton("Find GCD");
        inputPanel.add(startButton);
        frame.add(inputPanel, BorderLayout.NORTH);

        // Output panel for displaying steps and final result
        outputPanel = new JPanel(new GridLayout(1, 2));
        
        liveStepsArea = new JTextArea(10, 30);
        liveStepsArea.setEditable(false);
        liveStepsArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        liveStepsArea.setForeground(Color.BLUE);
        outputPanel.add(new JScrollPane(liveStepsArea));
        
        finalStepsArea = new JTextArea(10, 30);
        finalStepsArea.setEditable(false);
        finalStepsArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        finalStepsArea.setForeground(Color.BLACK);
        outputPanel.add(new JScrollPane(finalStepsArea));
        
        frame.add(outputPanel, BorderLayout.CENTER);

        // Control panel for pause and resume buttons
        controlPanel = new JPanel();
        pauseButton = new JButton("Pause");
        resumeButton = new JButton("Resume");
        controlPanel.add(pauseButton);
        controlPanel.add(resumeButton);
        frame.add(controlPanel, BorderLayout.SOUTH);
        
        // Event listener for the Start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int count = Integer.parseInt(countField.getText()); // Read the number of values
                    if (count < 2) {
                        JOptionPane.showMessageDialog(frame, "Enter at least two numbers.");
                        return;
                    }
                    
                    int[] numbers = new int[count];
                    for (int i = 0; i < count; i++) {
                        String input = JOptionPane.showInputDialog("Enter number " + (i + 1) + ":");
                        numbers[i] = Integer.parseInt(input);
                    }
                    
                    // Reset text areas and start animation
                    liveStepsArea.setText("");
                    finalStepsArea.setText("");
                    paused = false;
                    animateGCD(numbers);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
                }
            }
        });

        // Pause button event listener
        pauseButton.addActionListener(e -> paused = true);
        
        // Resume button event listener
        resumeButton.addActionListener(e -> {
            paused = false;
            synchronized (this) {
                notify(); // Resume the animation
            }
        });

        frame.setVisible(true); // Show the GUI
    }

    // Method to animate the Euclidean GCD calculation
    private void animateGCD(int[] numbers) {
        new Thread(() -> {
            int a = numbers[0]; // Start with the first number
            StringBuilder finalSteps = new StringBuilder();
            
            for (int i = 1; i < numbers.length; i++) {
                int b = numbers[i];
                int step = 1;
                
                while (b != 0) {
                    synchronized (this) {
                        while (paused) {
                            try {
                                wait(); // Wait if paused
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    
                    int quotient = a / b;
                    int remainder = a % b;
                    
                    // Generate step information
                    String stepInfo = "Step " + step + "\n" +
                            a + " ÷ " + b + " = " + quotient + " (Quotient), remainder = " + remainder + "\n" +
                            "=> GCD(" + a + ", " + b + ") = GCD(" + b + ", " + remainder + ")\n\n";
                    
                    // Update the live steps area
                    liveStepsArea.setText(stepInfo);
                    finalSteps.append(stepInfo);
                    
                    try {
                        Thread.sleep(1000); // Pause for animation effect
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    a = b;
                    b = remainder;
                    step++;
                }
            }
            
            // Display the final GCD result
            finalSteps.append("GCD is: ").append(a).append("\n");
            finalStepsArea.setText(finalSteps.toString());
            finalStepsArea.setBackground(Color.YELLOW);
        }).start(); // Run animation in a separate thread
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Euclidean_GCD::new); // Start the GUI application
    }
}

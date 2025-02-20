import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Euclidean_GCD{
    private JFrame frame;
    private JPanel inputPanel, outputPanel, controlPanel;
    private JTextField countField;
    private JTextArea liveStepsArea, finalStepsArea;
    private JButton startButton, pauseButton, resumeButton;
    private volatile boolean paused = false;
    
    public Euclidean_GCD() {
        frame = new JFrame("Euclidean Algorithm GCD Animation");
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));

        inputPanel.add(new JLabel("Enter number of values:"));
        countField = new JTextField();
        inputPanel.add(countField);

        startButton = new JButton("Find GCD");
        inputPanel.add(startButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        
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
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int count = Integer.parseInt(countField.getText());
                    if (count < 2) {
                        JOptionPane.showMessageDialog(frame, "Enter at least two numbers.");
                        return;
                    }
                    int[] numbers = new int[count];
                    for (int i = 0; i < count; i++) {
                        String input = JOptionPane.showInputDialog("Enter number " + (i + 1) + ":");
                        numbers[i] = Integer.parseInt(input);
                    }
                    liveStepsArea.setText("");
                    finalStepsArea.setText("");
                    paused = false;
                    animateGCD(numbers);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers.");
                }
            }
        });

        pauseButton.addActionListener(e -> paused = true);
        resumeButton.addActionListener(e -> {
            paused = false;
            synchronized (this) {
                notify();
            }
        });

        frame.setVisible(true);
    }

    private void animateGCD(int[] numbers) {
        new Thread(() -> {
            int a = numbers[0];
            StringBuilder finalSteps = new StringBuilder();
            
            for (int i = 1; i < numbers.length; i++) {
                int b = numbers[i];
                int step = 1;
                
                while (b != 0) {
                    synchronized (this) {
                        while (paused) {
                            try {
                                wait();
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    
                    int quotient = a / b;
                    int remainder = a % b;
                    
                    String stepInfo = "Step " + step + "\n" +
                            a + " ÷ " + b + " = " + quotient + " (Quotient), remainder = " + remainder + "\n" +
                            "=> GCD(" + a + ", " + b + ") = GCD(" + b + ", " + remainder + ")\n\n";
                    
                    liveStepsArea.setText(stepInfo);
                    finalSteps.append(stepInfo);
                    
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    a = b;
                    b = remainder;
                    step++;
                }
            }
            
            finalSteps.append("GCD is: ").append(a).append("\n");
            finalStepsArea.setText(finalSteps.toString());
            finalStepsArea.setBackground(Color.YELLOW);
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Euclidean_GCD::new);
    }
}

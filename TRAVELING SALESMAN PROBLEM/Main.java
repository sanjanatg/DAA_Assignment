import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class City {
    private final int x;
    private final int y;
    private final int id;
    private final Map<City, List<Point>> roadPaths;
    
    public City(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.roadPaths = new HashMap<>();
    }
    
    public void addRoadTo(City other, List<Point> path) {
        roadPaths.put(other, path);
    }
    
    public List<Point> getRoadTo(City other) {
        return roadPaths.getOrDefault(other, generateDefaultPath(other));
    }
    
    private List<Point> generateDefaultPath(City other) {
        List<Point> path = new ArrayList<>();
        path.add(new Point(x, y));
        
        Random rand = new Random();
        int numPoints = rand.nextInt(3) + 2;
        
        for (int i = 0; i < numPoints; i++) {
            int midX = x + (other.x - x) * (i + 1) / (numPoints + 1);
            int midY = y + (other.y - y) * (i + 1) / (numPoints + 1);
            
            midX += rand.nextInt(41) - 20;
            midY += rand.nextInt(41) - 20;
            
            path.add(new Point(midX, midY));
        }
        
        path.add(new Point(other.x, other.y));
        return path;
    }
    
    public double distanceTo(City other) {
        List<Point> path = getRoadTo(other);
        double distance = 0;
        
        for (int i = 0; i < path.size() - 1; i++) {
            Point p1 = path.get(i);
            Point p2 = path.get(i + 1);
            distance += Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
        }
        
        return distance;
    }
    
    public int getX() { return x; }
    public int getY() { return y; }
    public int getId() { return id; }
}

class RoadNetwork {
    private final List<City> cities;
    private final Random rand = new Random();
    
    public RoadNetwork(List<City> cities) {
        this.cities = cities;
        generateRoads();
    }
    
    private void generateRoads() {
        for (City city1 : cities) {
            for (City city2 : cities) {
                if (city1 != city2 && rand.nextDouble() < 0.4) {
                    List<Point> roadPath = generateRoadPath(city1, city2);
                    city1.addRoadTo(city2, roadPath);
                }
            }
        }
    }
    
    private List<Point> generateRoadPath(City from, City to) {
        List<Point> path = new ArrayList<>();
        path.add(new Point(from.getX(), from.getY()));
        
        int numPoints = rand.nextInt(3) + 2;
        for (int i = 0; i < numPoints; i++) {
            int midX = from.getX() + (to.getX() - from.getX()) * (i + 1) / (numPoints + 1);
            int midY = from.getY() + (to.getY() - from.getY()) * (i + 1) / (numPoints + 1);
            
            midX += rand.nextInt(61) - 30;
            midY += rand.nextInt(61) - 30;
            
            path.add(new Point(midX, midY));
        }
        
        path.add(new Point(to.getX(), to.getY()));
        return path;
    }
}

class TSP {
    private final List<City> cities;
    private List<City> currentTour;
    private double currentDistance;
    
    public TSP(List<City> cities) {
        this.cities = new ArrayList<>(cities);
        this.currentTour = new ArrayList<>();
    }
    
    public List<City> nearestNeighbor() {
        if (cities.isEmpty()) return new ArrayList<>();
        
        boolean[] visited = new boolean[cities.size()];
        currentTour = new ArrayList<>(cities.size());
        
        City current = cities.get(0);
        currentTour.add(current);
        visited[0] = true;
        
        while (currentTour.size() < cities.size()) {
            double minDistance = Double.MAX_VALUE;
            int nextIndex = -1;
            
            for (int i = 0; i < cities.size(); i++) {
                if (!visited[i]) {
                    double distance = current.distanceTo(cities.get(i));
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextIndex = i;
                    }
                }
            }
            
            current = cities.get(nextIndex);
            currentTour.add(current);
            visited[nextIndex] = true;
        }
        
        calculateTotalDistance();
        return new ArrayList<>(currentTour);
    }
    
    public List<City> twoOpt() {
        boolean improved = true;
        while (improved) {
            improved = false;
            double bestDistance = calculateTotalDistance();
            
            for (int i = 0; i < currentTour.size() - 1; i++) {
                for (int j = i + 1; j < currentTour.size(); j++) {
                    List<City> newTour = new ArrayList<>(currentTour);
                    reverse(newTour, i, j);
                    
                    double newDistance = calculateTourDistance(newTour);
                    if (newDistance < bestDistance) {
                        currentTour = newTour;
                        bestDistance = newDistance;
                        improved = true;
                    }
                }
            }
        }
        
        currentDistance = calculateTotalDistance();
        return new ArrayList<>(currentTour);
    }
    
    private void reverse(List<City> tour, int i, int j) {
        while (i < j) {
            Collections.swap(tour, i, j);
            i++;
            j--;
        }
    }
    
    public double calculateTotalDistance() {
        currentDistance = calculateTourDistance(currentTour);
        return currentDistance;
    }
    
    private double calculateTourDistance(List<City> tour) {
        double distance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            distance += tour.get(i).distanceTo(tour.get(i + 1));
        }
        if (!tour.isEmpty()) {
            distance += tour.get(tour.size() - 1).distanceTo(tour.get(0));
        }
        return distance;
    }
    
    public double getCurrentDistance() {
        return currentDistance;
    }
}

class TSPVisualizer extends JPanel {
    private final List<City> cities;
    private List<City> tour;
    private int currentPathIndex = -1;
    private static final int CITY_SIZE = 10;
    private static final int PADDING = 50;
    private Timer animationTimer;
    
    public TSPVisualizer(List<City> cities) {
        this.cities = cities;
        this.tour = new ArrayList<>();
        setPreferredSize(new Dimension(800, 600));
    }
    
    public void updateTour(List<City> newTour) {
        this.tour = newTour;
        startAnimation();
    }
    
    private void startAnimation() {
        currentPathIndex = -1;
        if (animationTimer != null) {
            animationTimer.cancel();
        }
        animationTimer = new Timer();
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                currentPathIndex++;
                if (currentPathIndex >= tour.size()) {
                    animationTimer.cancel();
                }
                repaint();
            }
        }, 0, 300);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int maxX = cities.stream().mapToInt(City::getX).max().orElse(100);
        int maxY = cities.stream().mapToInt(City::getY).max().orElse(100);
        
        double scaleX = (getWidth() - 2.0 * PADDING) / maxX;
        double scaleY = (getHeight() - 2.0 * PADDING) / maxY;
        
        // Draw road network
        g2d.setColor(new Color(220, 220, 220));
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        
        for (City city1 : cities) {
            for (City city2 : cities) {
                if (city1 != city2) {
                    List<Point> roadPath = city1.getRoadTo(city2);
                    if (roadPath != null) {
                        drawPath(g2d, roadPath, scaleX, scaleY, false);
                    }
                }
            }
        }
        
        // Draw animated tour
        if (!tour.isEmpty() && currentPathIndex >= 0) {
            g2d.setColor(Color.BLUE);
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            
            for (int i = 0; i < Math.min(currentPathIndex + 1, tour.size()); i++) {
                City current = tour.get(i);
                City next = tour.get((i + 1) % tour.size());
                List<Point> roadPath = current.getRoadTo(next);
                if (i == currentPathIndex) {
                    g2d.setColor(Color.RED);
                }
                drawPath(g2d, roadPath, scaleX, scaleY, true);
                g2d.setColor(Color.BLUE);
            }
        }
        
        // Draw cities
        for (City city : cities) {
            int x = PADDING + (int)(city.getX() * scaleX) - CITY_SIZE/2;
            int y = PADDING + (int)(city.getY() * scaleY) - CITY_SIZE/2;
            
            if (city == cities.get(0)) {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x-2, y-2, CITY_SIZE+4, CITY_SIZE+4);
            } else {
                g2d.setColor(Color.RED);
                g2d.fillOval(x, y, CITY_SIZE, CITY_SIZE);
            }
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(city.getId()), x, y);
        }
    }
    
    private void drawPath(Graphics2D g2d, List<Point> path, double scaleX, double scaleY, boolean isTourPath) {
        if (path == null || path.size() < 2) return;
        
        int[] xPoints = new int[path.size()];
        int[] yPoints = new int[path.size()];
        
        for (int i = 0; i < path.size(); i++) {
            Point p = path.get(i);
            xPoints[i] = PADDING + (int)(p.x * scaleX);
            yPoints[i] = PADDING + (int)(p.y * scaleY);
        }
        
        for (int i = 0; i < path.size() - 1; i++) {
            g2d.drawLine(xPoints[i], yPoints[i], xPoints[i + 1], yPoints[i + 1]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            cities.add(new City(i, rand.nextInt(700), rand.nextInt(500)));
        }
        
        new RoadNetwork(cities);
        
        TSP tsp = new TSP(cities);
        TSPVisualizer visualizer = new TSPVisualizer(cities);
        
        JFrame frame = new JFrame("TSP Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel controlPanel = new JPanel();
        JButton nnButton = new JButton("Nearest Neighbor");
        JButton twoOptButton = new JButton("2-opt Improvement");
        JLabel distanceLabel = new JLabel("Distance: ");
        
        controlPanel.add(nnButton);
        controlPanel.add(twoOptButton);
        controlPanel.add(distanceLabel);
        
        nnButton.addActionListener(e -> {
            nnButton.setEnabled(false);
            twoOptButton.setEnabled(false);
            List<City> nnTour = tsp.nearestNeighbor();
            visualizer.updateTour(nnTour);
            distanceLabel.setText(String.format("Distance: %.2f", tsp.getCurrentDistance()));
            Timer enableTimer = new Timer();
            enableTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nnButton.setEnabled(true);
                    twoOptButton.setEnabled(true);
                }
            }, 6000);
        });
        
        twoOptButton.addActionListener(e -> {
            nnButton.setEnabled(false);
            twoOptButton.setEnabled(false);
            List<City> improvedTour = tsp.twoOpt();
            visualizer.updateTour(improvedTour);
            distanceLabel.setText(String.format("Distance: %.2f", tsp.getCurrentDistance()));
            Timer enableTimer = new Timer();
            enableTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    nnButton.setEnabled(true);
                    twoOptButton.setEnabled(true);
                }
            }, 6000);
        });
        
        frame.setLayout(new BorderLayout());
        frame.add(visualizer, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
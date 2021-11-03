package gui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class GraphFrame extends JFrame{	
	public GraphFrame() {
		initComponents();
	}
	
	public void initComponents() {
		List<Double> scores = new ArrayList<>();
		Random random = new Random();
		int maxDataPoints = 20;
		int maxScore = 10;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.add((double) random.nextDouble() * maxScore);
		}
		GraphPanel mainPanel = new GraphPanel(scores);
		mainPanel.setPreferredSize(new Dimension(800, 600));
		this.setTitle("Graph");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().add(mainPanel);
		this.pack();
		this.setVisible(true);
	}
}

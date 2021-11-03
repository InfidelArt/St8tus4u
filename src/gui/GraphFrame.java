package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import controller.ApplicationController;

public class GraphFrame extends JFrame{	
	Color speedColor = new Color(36, 252, 3, 255);
	Color distanceColor = new Color(252, 140, 3, 255);
	Color heartRateColor = new Color(252, 3, 3, 255);
	Color cadenceColor = new Color(3, 152, 252, 255);
	private String[][] currentActivity;
	ApplicationController controller;
	public GraphFrame(ApplicationController controller) {
		this.controller = controller;
		initComponents();
	}
	
	public void initComponents() {
		List<Double> scores = new ArrayList<>();
		currentActivity = controller.getActivityData();
		List<Double> scoresSpeed = new ArrayList<>();
		List<Double> scoresDistance = new ArrayList<>();
		List<Double> scoresHeartRate = new ArrayList<>();
		List<Double> scoresCadence = new ArrayList<>();
		Random random = new Random();
		this.setLayout(new GridLayout(2,2));
		int maxDataPoints = 20;
		int maxScore = 10;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.add((double) random.nextDouble() * maxScore);
		}
		GraphPanel panelSpeed = new GraphPanel(scores, speedColor, "Speed");
		GraphPanel panelDistance = new GraphPanel(scores, distanceColor, "Distance");
		GraphPanel panelHeartRate = new GraphPanel(scores, heartRateColor, "HeartRate");
		GraphPanel panelCadence = new GraphPanel(scores, cadenceColor, "Cadence");
		this.setTitle("Graph");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.add(panelSpeed);
		this.add(panelDistance);
		this.add(panelHeartRate);
		this.add(panelCadence);
		this.getContentPane();
		this.pack();
		this.setSize(800, 600);
		this.setVisible(true);
	}
}

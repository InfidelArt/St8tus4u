package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ApplicationController;

public class GraphFrame extends JFrame {
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
		currentActivity = controller.getActivityData();
		List<Double> scoresSpeed = new ArrayList<>();
		List<Double> scoresDistance = new ArrayList<>();
		List<Double> scoresHeartRate = new ArrayList<>();
		List<Double> scoresCadence = new ArrayList<>();
		List<Double> scoresSeconds = new ArrayList<>();
		for (int i = 0; i < currentActivity.length; i++) {
			scoresSeconds.add(Double.parseDouble(currentActivity[i][2]));
			scoresSpeed.add(Double.parseDouble(currentActivity[i][8]));
			scoresDistance.add(Double.parseDouble(currentActivity[i][6]));
			scoresHeartRate.add(Double.parseDouble(currentActivity[i][7]));
			scoresCadence.add(Double.parseDouble(currentActivity[i][9]));
		}
		Random random = new Random();
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		this.add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 2));
		this.add(centerPanel, BorderLayout.CENTER);
		GraphPanel panelSpeed = new GraphPanel(scoresSpeed, scoresSeconds, speedColor, "Speed");
		GraphPanel panelDistance = new GraphPanel(scoresDistance, scoresSeconds, distanceColor, "Distance");
		GraphPanel panelHeartRate = new GraphPanel(scoresHeartRate, scoresSeconds, heartRateColor, "HeartRate");
		GraphPanel panelCadence = new GraphPanel(scoresCadence, scoresSeconds, cadenceColor, "Cadence");
		this.setTitle("Graph");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		centerPanel.add(panelSpeed);
		centerPanel.add(panelDistance);
		centerPanel.add(panelHeartRate);
		centerPanel.add(panelCadence);
		this.getContentPane();
		this.pack();
		this.setSize(800, 600);
		this.setVisible(true);
	}
}

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ApplicationController;
import date.InvalidDateException;
import time.InvalidTimeException;

public class GraphFrame extends JFrame {
	Color speedColor = new Color(36, 252, 3, 255);
	Color distanceColor = new Color(252, 140, 3, 255);
	Color heartRateColor = new Color(252, 3, 3, 255);
	Color cadenceColor = new Color(3, 152, 252, 255);
	private String[][] currentActivity;
	ApplicationController controller;
	double seconds;
	public GraphFrame(ApplicationController controller) throws IOException, InvalidTimeException, InvalidDateException{
		this.controller = controller;
		initComponents();
	}

	public void initComponents() throws IOException, InvalidTimeException, InvalidDateException {
		List<Double> scoresSpeed = new ArrayList<>();
		List<Double> scoresDistance = new ArrayList<>();
		List<Double> scoresHeartRate = new ArrayList<>();
		List<Double> scoresCadence = new ArrayList<>();
		for (int i = 0; i < controller.getActivityData().length; i++) {
			scoresSpeed.add(Double.parseDouble(controller.getActivityData()[i][6]));
			scoresDistance.add(Double.parseDouble(controller.getActivityData()[i][4]));
			scoresHeartRate.add(Double.parseDouble(controller.getActivityData()[i][5]));
			scoresCadence.add(Double.parseDouble(controller.getActivityData()[i][7]));
		}
		this.setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		this.add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2, 2));
		this.add(centerPanel, BorderLayout.CENTER);
		GraphPanel panelSpeed = new GraphPanel(scoresSpeed, speedColor, "Speed");
		GraphPanel panelDistance = new GraphPanel(scoresDistance, distanceColor, "Distance");
		GraphPanel panelHeartRate = new GraphPanel(scoresHeartRate, heartRateColor, "HeartRate");
		GraphPanel panelCadence = new GraphPanel(scoresCadence, cadenceColor, "Cadence");
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

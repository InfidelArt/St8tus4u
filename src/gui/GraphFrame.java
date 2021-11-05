package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ApplicationController;
import date.InvalidDateException;
import db.DataRetrievalException;
import time.InvalidTimeException;

public class GraphFrame extends JFrame {
	private Color speedColor = new Color(50, 168, 82);
	private Color distanceColor = new Color(252, 140, 3, 255);
	private Color heartRateColor = new Color(252, 3, 3, 255);
	private Color cadenceColor = new Color(3, 152, 252, 255);
	private String[][] currentActivity;
	private ApplicationController controller;
	private int activityId;
	
	public GraphFrame(ApplicationController controller, int activityId) throws IOException, InvalidTimeException, InvalidDateException{
		this.activityId = activityId;
		this.controller = controller;
		initComponents();
	}

	public void initComponents() throws IOException, InvalidTimeException, InvalidDateException {
		try {
			currentActivity = controller.getActivityData(activityId);
		} catch (IOException | DataRetrievalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Double> scoresSpeed = new ArrayList<>();
		List<Double> scoresDistance = new ArrayList<>();
		List<Double> scoresHeartRate = new ArrayList<>();
		List<Double> scoresCadence = new ArrayList<>();
		for (int i = 0; i < currentActivity.length; i++) {
			scoresSpeed.add(Double.parseDouble(currentActivity[i][7]));
			scoresDistance.add(Double.parseDouble(currentActivity[i][5]));
			scoresHeartRate.add(Double.parseDouble(currentActivity[i][6]));
			scoresCadence.add(Double.parseDouble(currentActivity[i][8]));
			}
		this.setLayout(new GridLayout(2, 2));
		GraphPanel panelSpeed = new GraphPanel(scoresSpeed, speedColor, "Speed");
		GraphPanel panelDistance = new GraphPanel(scoresDistance, distanceColor, "Distance");
		GraphPanel panelHeartRate = new GraphPanel(scoresHeartRate, heartRateColor, "HeartRate");
		GraphPanel panelCadence = new GraphPanel(scoresCadence, cadenceColor, "Cadence");
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

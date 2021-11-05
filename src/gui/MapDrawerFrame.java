package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import controller.ApplicationController;
import db.DataRetrievalException;

public class MapDrawerFrame extends JFrame {
	private ApplicationController controller;
	private int activityId;
	private String[][] currentActivity;
	private String activityName;
	private Color primaryColor;
	private Color secondaryColor;
	public MapDrawerFrame(ApplicationController controller, int activityId, String activityName) {
		this.activityId = activityId;
		this.controller = controller;
		this.activityName = activityName;
		this.primaryColor = StyleComponents.primaryColor;
		this.secondaryColor = StyleComponents.secondaryColor;
		initComponents();
	}

	private void initComponents() {
		try {
			currentActivity = controller.getActivityData(activityId);
		} catch (IOException | DataRetrievalException e) {
			e.printStackTrace();
		}
		List<Double> longitudeList = new ArrayList<>();
		List<Double> latitudeList = new ArrayList<>();
		for (int i = 0; i < currentActivity.length; i++) {
			longitudeList.add(Double.parseDouble(currentActivity[i][2]));
			latitudeList.add(Double.parseDouble(currentActivity[i][3]));
		}
		this.setLayout(new GridLayout(1, 1));
		MapDrawerPanel mapPanel = new MapDrawerPanel(longitudeList, latitudeList, activityName, primaryColor, secondaryColor);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.add(mapPanel);
		this.setTitle("MapDrawer");
		this.getContentPane();
		this.pack();
		this.setSize(800, 600);
		this.setVisible(true);
	}
}

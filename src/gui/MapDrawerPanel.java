package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import date.InvalidDateException;
import time.InvalidTimeException;

public class MapDrawerPanel extends JPanel {
	String title;
	private int offset = 30;;
	List<Double> longitude;
	List<Double> latitude;
	Color primaryColor;
	Color secondaryColor;
	private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
	private JLabel lblTitle;

	MapDrawerPanel(List<Double> longitude, List<Double> latitude, String title, Color primaryColor,
			Color secondaryColor) {
		this.title = title;
		this.longitude = longitude;
		this.latitude = latitude;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		for(int i = 0; i<this.longitude.size(); i++) {
			System.out.print(this.longitude.get(i));
		}
		lblTitle = new JLabel(title);
		this.add(lblTitle);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		double xScale = ((double) getWidth() - 2*offset) / (getMaxLongitude() - getMinLongitude());
		double yScale = ((double) getHeight() - 2*offset) / (getMaxLatitude() - getMinLatitude());

		List<Point> graphPoints = new ArrayList<>();
		for (int i = 0; i < latitude.size(); i++) {
			int x1 = (int) ((getMaxLongitude() - longitude.get(i)) * xScale+offset);
			int y1 = (int) ((getMaxLatitude() - latitude.get(i)) * yScale+offset);
			graphPoints.add(new Point(x1, y1));
		}

		// draw background
		graphics.setColor(secondaryColor);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		// Lines are drawn here
		graphics.setStroke(GRAPH_STROKE);
		for (int i = 0; i < graphPoints.size() - 1; i++) {
			graphics.setColor(primaryColor);
			int x1 = graphPoints.get(i).x;
			int y1 = graphPoints.get(i).y;
			int x2 = graphPoints.get(i + 1).x;
			int y2 = graphPoints.get(i + 1).y;
			graphics.drawLine(x1, y1, x2, y2);
		}
	}

	private double getMinLatitude() {
		double minScore = Double.MAX_VALUE;
		for (Double score : latitude) {
			minScore = Math.min(minScore, score);
		}
		return minScore;
	}

	private double getMaxLatitude() {
		double maxScore = Double.MIN_VALUE;
		for (Double score : latitude) {
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}

	private double getMinLongitude() {
		double minScore = Double.MAX_VALUE;
		for (Double score : longitude) {
			minScore = Math.min(minScore, score);
		}
		return minScore;
	}

	private double getMaxLongitude() {
		double maxScore = Double.MIN_VALUE;
		for (Double score : longitude) {
			maxScore = Math.max(maxScore, score);
		}
		return maxScore;
	}
}

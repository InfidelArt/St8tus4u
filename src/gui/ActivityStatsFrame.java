package gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ApplicationController;
import db.DataRetrievalException;

public class ActivityStatsFrame extends JFrame{
	JTextArea txtActivityStats; 
	private String[][] currentActivity;
	
	public ActivityStatsFrame(ApplicationController controller, int activityId, String activityName) throws IOException, DataRetrievalException {
		this.setLayout(new GridLayout(1,1));
		txtActivityStats = new JTextArea("");
		StyleComponents.styleDefaultTextArea(txtActivityStats);
		txtActivityStats.setEditable(false);
		currentActivity = controller.getActivityData(activityId);
		List<Double> scoresSpeed = new ArrayList<>();
		List<Double> scoresHeartRate = new ArrayList<>();
		List<Double> scoresCadence = new ArrayList<>();
		for (int i = 0; i < currentActivity.length; i++) {
			scoresSpeed.add(Double.parseDouble(currentActivity[i][7]));
			scoresHeartRate.add(Double.parseDouble(currentActivity[i][6]));
			scoresCadence.add(Double.parseDouble(currentActivity[i][8]));
		}
		Double maxSpeed = getMaxScore(scoresSpeed);
		Double minSpeed = getMinScore(scoresSpeed);
		Double averageSpeed = getAverageScore(scoresSpeed);
		Double maxHeartRate = getMaxScore(scoresHeartRate);
		Double minHeartRate = getMinScore(scoresHeartRate);
		Double averageHeartRate = getAverageScore(scoresHeartRate);
		Double maxCadence = getMaxScore(scoresCadence);
		Double minCadence = getMinScore(scoresCadence);
		Double averageCadence = getAverageScore(scoresCadence);
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("Activity Name: " + activityName);
		sb.append("\nTotal entries: " + currentActivity.length);
		sb.append("\nTotal Distance: " + currentActivity[currentActivity.length-1][5]+"m");
		sb.append("\nStart Time: " + currentActivity[0][0]);
		sb.append("\nEnd Time: " + currentActivity[currentActivity.length-1][0]);
		sb.append("\nTotal Duration: " + currentActivity[currentActivity.length-1][1]+"s");
		sb.append("\nTop Speed: " + maxSpeed + "m/s");
		sb.append("\nMin Speed: " + minSpeed + "m/s");
		sb.append("\nAverage Speed: " + averageSpeed + "m/s");
		sb.append("\nTop Heart Rate: " + maxHeartRate + "bpm");
		sb.append("\nMin Heart Rate: " + minHeartRate + "bpm");
		sb.append("\nAverage Heart Rate: " + averageHeartRate + "bpm");
		sb.append("\nTop Cadence: " + maxCadence + "spm");
		sb.append("\nMin Cadence: " + minCadence + "spm");
		sb.append("\nAverage Cadence: " + averageCadence + "spm");
		txtActivityStats.setText(sb.toString());
		this.add(txtActivityStats);
		pack();
		this.setSize(this.getWidth()+30, this.getHeight()+30);
		this.setResizable(false);
		this.setTitle("Statistical Info");
		this.setVisible(true);
	}
	private double getMinScore(List<Double> scores) {
		double minScore = Double.MAX_VALUE;
		for (Double score : scores) {
			minScore = Math.min(minScore, score);
		}
		minScore = Math.floor(minScore * 100) / 100;
		return minScore;
	}

	private double getMaxScore(List<Double> scores) {
		double maxScore = Double.MIN_VALUE;
		for (Double score : scores) {
			maxScore = Math.max(maxScore, score);
		}
		maxScore = Math.floor(maxScore * 100) / 100;
		return maxScore;
	}
	
	private double getAverageScore(List<Double> scores) {
		double average = 0;
		for (int i = 0; i < scores.size(); i++) {
			average += scores.get(i);
		}
		average /= scores.size();
		average = Math.floor(average * 100) / 100;
		return average;
	}
}

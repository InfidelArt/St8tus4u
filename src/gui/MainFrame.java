package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import controller.ApplicationController;
import date.InvalidDateException;
import db.DataEntryException;
import db.DataRetrievalException;
import time.InvalidTimeException;

public class MainFrame extends JFrame {

	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnGraph;
	private JButton btnSelect;
	private JButton btnUserSettings;
	private JButton btnImport;
	private JComboBox<String> cbxActivities;
	private JLabel lblUsername;
	private JLabel lblDataOne;
	private JLabel lblDataTwo;
	private JTextField txtCurrentActivity;
	private JPanel bottomPanel;
	private JPanel mainPanel;
	private JScrollPane scrollPane;
	private JTable activityTable;
	private ApplicationController controller;
	private String[][] currentActivityData;
	private String[][] currentUserActivites;
	private String activityName;
	private String[] cbxActivityList;
	private JLabel lblUserData;
	private String userDataUsername;
	private String userDataOne;
	private String userDataTwo;
	private ArrayList<String> activites;
	private String activityId;
	public MainFrame(ApplicationController controller) throws IOException, InvalidTimeException, InvalidDateException {
		this.controller = controller;
		initComponents();
	}

	private void initComponents() throws IOException, InvalidTimeException, InvalidDateException {
		lblUserData = new JLabel();
		StyleComponents.styleDefaultLabel(lblUserData);
		userDataUsername = controller.getUserData()[0];
		userDataOne = "Weight: " + controller.getUserData()[2] + "kg Length: " + controller.getUserData()[3]
				+ "cm Max Heartrate: " + controller.getUserData()[5];
		userDataTwo = "Age: " + controller.getUserData()[4] + " Gender: " + controller.getUserData()[6];
		bottomPanel = new JPanel();
		lblUsername = new JLabel(userDataUsername);
		lblDataOne = new JLabel(userDataOne);
		lblDataTwo = new JLabel(userDataTwo);
		cbxActivities = new JComboBox<>();
		txtCurrentActivity = new DefaultTextBox("");
		txtCurrentActivity.addMouseListener(new AutoEraseListener(activityName, txtCurrentActivity));
		btnRemove = new MainFrameButton("Remove");
		btnRemove.addActionListener(e -> removeActivity());
		btnEdit = new MainFrameButton("Edit");
		btnEdit.addActionListener(e -> editActivity());
		btnEdit.setToolTipText("Saves the changes you've made in the activity tables");
		btnGraph = new MainFrameButton("Show Graph");
		btnGraph.addActionListener(e -> {
			try {
				try {
					selectActivity();
				} catch (DataRetrievalException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				showGraph();
			} catch (IOException | InvalidTimeException | InvalidDateException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnSelect = new MainFrameButton("Select");
		btnSelect.addActionListener(e -> {
			try {
				selectActivity();
			} catch (IOException | DataRetrievalException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnUserSettings = new MainFrameButton("User Settings");
		btnUserSettings.addActionListener(e -> openUserSettings());
		btnImport = new MainFrameButton("Import Activity");
		btnImport.setToolTipText("Never include character '.' in file name");
		btnImport.addActionListener(e -> {
			try {
				importActivity();
			} catch (IOException | InvalidTimeException | InvalidDateException | DataEntryException e1) {
			}
		});
		mainPanel = new JPanel();
		scrollPane = new JScrollPane();
		activityTable = new JTable();
		/*
		 * userActivites = controller.getUserActivities(); for(int i = 0; i <
		 * userActivites.length; i++) {
		 * 
		 * }
		 */
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		StyleComponents.styleDefaultJComboBox(cbxActivities);
		StyleComponents.styleJPanel(bottomPanel);
		StyleComponents.styleTitleLabel(lblUsername);
		StyleComponents.styleMainFrameLabel(lblDataOne);
		StyleComponents.styleMainFrameLabel(lblDataTwo);
		updateActivityList();
		StyleComponents.styleJPanel(mainPanel);
		StyleComponents.styleBorderPanel(mainPanel);
		activityTable.setModel(new DefaultTableModel(
				currentActivityData, new String[] { "Time", "Seconds", "Longitude", "Latitude", "Altitude", "Distance",
						"Heart rate", "Speed", "Cadence" }));
		scrollPane.setViewportView(activityTable); // Adds scroll option to the table

		GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
		bottomPanel.setLayout(bottomPanelLayout);
		bottomPanelLayout.setHorizontalGroup(
				bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollPane));
		bottomPanelLayout.setVerticalGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE));

		GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addGap(36, 36, 36).addGroup(mainPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(txtCurrentActivity)
						.addGroup(mainPanelLayout.createSequentialGroup()
								.addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(mainPanelLayout.createSequentialGroup()
												.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING,
												mainPanelLayout.createSequentialGroup()
														.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDataOne, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblDataTwo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(30, 30, 30))
				.addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		mainPanelLayout.setVerticalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addContainerGap().addComponent(lblUsername)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblDataOne)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(lblDataTwo)
						.addGap(19, 19, 19)
						.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(mainPanelLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18)
						.addGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(txtCurrentActivity)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(bottomPanel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mainPanel,
				GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mainPanel,
				GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE));
		pack();
	}

	private void importActivity() throws IOException, InvalidTimeException, InvalidDateException, DataEntryException {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("C:/"));
		int response = fileChooser.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			String activityName = file.getName().substring(0, file.getName().indexOf("."));
			System.out.println(file.getAbsolutePath() + "\n " + activityName);
			controller.addNewActivity(activityName, file.getAbsolutePath());
			updateActivityList();
		}
	}

	private void openUserSettings() {
		controller.openUserSettings();
	}

	private void selectActivity() throws IOException, DataRetrievalException {
		String s = cbxActivities.getItemAt(cbxActivities.getSelectedIndex());
		// it is \\. instead of . so java splits on a literal dot instead of any character
		activityId = s.split("\\.")[0];
		activityName =  s.split("\\.")[1];
		txtCurrentActivity.setText("Current Activity: " + activityName);
		txtCurrentActivity.addMouseListener(new AutoEraseListener(txtCurrentActivity.getText(), txtCurrentActivity));
		try {
			currentActivityData = controller.getActivityData(Integer.parseInt(activityId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activityTable.setModel(new DefaultTableModel(
				currentActivityData, new String[] { "Time", "Seconds", "Longitude", "Latitude", "Altitude", "Distance",
						"Heart rate", "Speed", "Cadence" }));
	}

	private void showGraph() throws IOException, InvalidTimeException, InvalidDateException {
		controller.showGraph(Integer.parseInt(activityId));
	}

	private void editActivity() {
		String s = cbxActivities.getItemAt(cbxActivities.getSelectedIndex());
		activityId = s.split("\\.")[0];
		try {
			controller.changeActivityName(activityId, txtCurrentActivity.getText());
		} catch (DataEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtCurrentActivity.setText("Current Activity: ");
		txtCurrentActivity.addMouseListener(new AutoEraseListener(txtCurrentActivity.getText(), txtCurrentActivity));
		updateActivityList();
	}

	private void removeActivity() { // Now it takes in activity name, to change in the future
		try {
			controller.removeActivity(activityId);
		} catch (DataEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateActivityList();
	}
	private void updateActivityList() {
		try {
			currentUserActivites = controller.getUserActivities();
		} catch (DataRetrievalException e1) {
			e1.printStackTrace();
		}
		
		activites = new ArrayList<String>();
		for (int i = 0; i < currentUserActivites.length; i++) {
			activites.add(currentUserActivites[i][0].toString() + "." + currentUserActivites[i][1].toString());
		}
		cbxActivityList = activites.toArray(new String[0]);
		cbxActivities.setModel(new DefaultComboBoxModel<>(cbxActivityList));
	}
	public void update() {
		userDataUsername = controller.getUserData()[0];
		userDataOne = "Weight: " + controller.getUserData()[2] + "kg Length: " + controller.getUserData()[3]
				+ "cm Max Heartrate: " + controller.getUserData()[5];
		userDataTwo = "Age: " + controller.getUserData()[4] + " Gender: " + controller.getUserData()[6];
		lblUsername.setText(userDataUsername);
		lblDataOne.setText(userDataOne);
		lblDataTwo.setText(userDataTwo);
	}
}

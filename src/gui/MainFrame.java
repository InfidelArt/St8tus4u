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
	private JButton btnDrawMap;
	private JButton btnShowActivityStats;
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
		btnDrawMap = new MainFrameButton("Draw Map");
		btnDrawMap.addActionListener(e -> {openMapFrame();});
		btnShowActivityStats = new MainFrameButton("Show statistics");
		btnShowActivityStats.addActionListener(e -> { openActivityStats();});
		mainPanel = new JPanel();
		scrollPane = new JScrollPane();
		activityTable = new JTable();
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
		activityTable.setModel(new DefaultTableModel(currentActivityData, new String[] { "Time", "Seconds", "Longitude",
				"Latitude", "Altitude", "Distance", "Heart rate", "Speed", "Cadence" }));
		scrollPane.setViewportView(activityTable); // Adds scroll option to the table

		javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane)
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCurrentActivity)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(cbxActivities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(btnGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(btnUserSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnShowActivityStats, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDrawMap, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDataOne, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDataTwo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDataTwo)
                .addGap(25, 25, 25)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxActivities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDrawMap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUserSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnShowActivityStats, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtCurrentActivity)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
        );

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
		// it is \\. instead of . so java splits on a literal dot instead of any
		// character
		activityId = s.split("\\.")[0];
		activityName = s.split("\\.")[1];
		txtCurrentActivity.setText("Current Activity: " + activityName);
		txtCurrentActivity.addMouseListener(new AutoEraseListener(txtCurrentActivity.getText(), txtCurrentActivity));
		try {
			currentActivityData = controller.getActivityData(Integer.parseInt(activityId));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		activityTable.setModel(new DefaultTableModel(currentActivityData, new String[] { "Time", "Seconds", "Longitude",
				"Latitude", "Altitude", "Distance", "Heart rate", "Speed", "Cadence" }));
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
	
	public void openActivityStats() {
		try {
			controller.showStatisticalData(Integer.parseInt(activityId), activityName);
		} catch (NumberFormatException | IOException | InvalidTimeException | InvalidDateException
				| DataRetrievalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openMapFrame() {
		try {
			controller.showMap(Integer.parseInt(activityId), activityName);
		} catch (NumberFormatException | IOException | InvalidTimeException | InvalidDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

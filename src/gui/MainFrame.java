package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dialog;
import java.io.File;
import java.io.IOException;

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
import time.InvalidTimeException;

public class MainFrame extends JFrame {

	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnGraph;
	private JButton btnSelect;
	private JButton btnUserSettings;
	private JButton btnImport;
	private JComboBox<String> cbxActivities;
	private JLabel lblTitle;
	private JTextField txtCurrentActivity;
	private JPanel upperPanel;
	private JPanel borderPanel;
	private JScrollPane scrollPane;
	private JTable activityTable;
	private ApplicationController controller;
	private String[][] userActivites;
	private String[][] currentActivityData;
	private String activityName;
	private String[] cbxActivityList;
	private JPanel topPanel;
	private JLabel lblUserData;
	private String userData;

	public MainFrame(ApplicationController controller) throws IOException, InvalidTimeException, InvalidDateException {
		this.controller = controller;
		initComponents();
	}

	private void initComponents() throws IOException, InvalidTimeException, InvalidDateException {
		topPanel = new JPanel();
		lblUserData = new JLabel();
		StyleComponents.styleDefaultLabel(lblUserData);
		userData = "Username" + "name" + "Weight" + "Length" + "age" + "Max Heartrate" + "Gender";
		// userData = controller.getUserData()[0] +" "+controller.getUserData()[1] +"
		// "+controller.getUserData()[2] +" "+controller.getUserData()[3] +"
		// "+controller.getUserData()[4] +" "+controller.getUserData()[5] +"
		// "+controller.getUserData()[6];
		upperPanel = new JPanel();
		lblTitle = new JLabel(userData);
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
				showGraph();
			} catch (IOException | InvalidTimeException | InvalidDateException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		});
		btnSelect = new MainFrameButton("Select");
		btnSelect.addActionListener(e -> selectActivity());
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
		borderPanel = new JPanel();
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
		StyleComponents.styleJPanel(upperPanel);
		StyleComponents.styleDefaultLabel(lblTitle);
		StyleComponents.styleDefaultJComboBox(cbxActivities);
		cbxActivities.setModel(new DefaultComboBoxModel<>(new String[] { "Example Acitivity 1", "Example Acitivity 2",
				"Example Acitivity 3", "Example Acitivity 4" })); // Will be filled with activities from activity list
		StyleComponents.styleJPanel(borderPanel);
		StyleComponents.styleBorderPanel(borderPanel);
		currentActivityData = controller.getActivityData();
		activityTable.setModel(new DefaultTableModel( // A lot of nulls to check if the scroll option works, will delete
														// later,
				// Otherwise may be useful when presenting to explain what happens when the
				// amount of data exceeds space in the panel
				currentActivityData, new String[] { "Time", "Seconds", "Longitude", "Latitude", "Altitude", "Distance",
						"Heart rate", "Speed", "Cadence" }));
		scrollPane.setViewportView(activityTable); // Adds scroll option to the table
		GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
		borderPanel.setLayout(borderPanelLayout);
		borderPanelLayout.setHorizontalGroup(
				borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollPane));
		borderPanelLayout.setVerticalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE));
		GroupLayout upperPanelLayout = new GroupLayout(upperPanel);
		upperPanel.setLayout(upperPanelLayout);
		upperPanelLayout.setHorizontalGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(upperPanelLayout.createSequentialGroup().addGap(36, 36, 36)
						.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtCurrentActivity)
								.addGroup(upperPanelLayout.createSequentialGroup()
										.addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(upperPanelLayout.createSequentialGroup()
														.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(GroupLayout.Alignment.TRAILING, upperPanelLayout
														.createSequentialGroup()
														.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 100,
																GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE))))
						.addGap(30, 30, 30))
				.addComponent(borderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		upperPanelLayout.setVerticalGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(upperPanelLayout.createSequentialGroup().addGap(39, 39, 39).addComponent(lblTitle)
						.addGap(34, 34, 34)
						.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18, 18, 18)
						.addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(txtCurrentActivity)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(borderPanel,
								GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(upperPanel,
				GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(upperPanel,
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
		}
	}

	private void openUserSettings() {
		controller.openUserSettings();
	}

	private void selectActivity() {
		activityName = cbxActivities.getItemAt(cbxActivities.getSelectedIndex());
		txtCurrentActivity.setText("Current Activity: " + activityName);
		txtCurrentActivity.addMouseListener(new AutoEraseListener(txtCurrentActivity.getText(), txtCurrentActivity));
		controller.setCurrentActivity(activityName);

	}

	private void showGraph() throws IOException, InvalidTimeException, InvalidDateException {
		controller.showGraph();
	}

	private void editActivity() {
		controller.changeActivityName(activityName, txtCurrentActivity.getText());

	}

	private void removeActivity() { // Now it takes in activity name, to change in the future
		controller.removeActivity(cbxActivities.getItemAt(cbxActivities.getSelectedIndex()));
	}
}

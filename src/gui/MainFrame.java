package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

import controller.ApplicationController;

public class MainFrame extends JFrame {

	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnGraph;
	private JButton btnSelect;
	private JButton btnUserSettings;
	private JButton btnImport;
	private JComboBox<String> cbxActivities;
	private JLabel lblTitle;
	private JLabel lblCurrentActivity;
	private JPanel upperPanel;
	private JPanel borderPanel;
	private JScrollPane scrollPane;
	private JTable activityTable;
	private ApplicationController controller;
	public MainFrame(ApplicationController controller) {
		this.controller = controller;
		initComponents();
	}

	private void initComponents() {

		upperPanel = new JPanel();
		lblTitle = new JLabel("ST8TUS4U");
		cbxActivities = new JComboBox<>();
		lblCurrentActivity = new JLabel();
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(e -> removeActivity());
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(e -> editActivity());
		btnEdit.setToolTipText("Saves the changes you've made in the activity tables");
		btnGraph = new JButton("Show Graph");
		btnGraph.addActionListener(e -> showGraph());
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(e -> selectActivity());
		btnUserSettings = new JButton("User Settings");
		btnUserSettings.addActionListener(e -> openUserSettings());
		btnImport = new JButton("Import Activity");
		borderPanel = new JPanel();
		scrollPane = new JScrollPane();
		activityTable = new JTable();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		lblCurrentActivity.setText("Current Activity: Example Activity"); // Will update itself every time activity is
																			// chosen.
		StyleComponents.styleJPanel(upperPanel);
		StyleComponents.styleTitleLabel(lblTitle);

		cbxActivities.setModel(new DefaultComboBoxModel<>(new String[] { "Example Acitivity 1", "Example Acitivity 2",
				"Example Acitivity 3", "Example Acitivity 4" })); // Will be filled with activities from activity list
																	// later on
		StyleComponents.styleDefaultLabel(lblCurrentActivity);
		StyleComponents.styleMainFrameButton(btnRemove);
		StyleComponents.styleMainFrameButton(btnEdit);
		StyleComponents.styleMainFrameButton(btnGraph);
		StyleComponents.styleMainFrameButton(btnSelect);
		StyleComponents.styleMainFrameButton(btnUserSettings);
		StyleComponents.styleMainFrameButton(btnImport);
		StyleComponents.styleJPanel(borderPanel);
		StyleComponents.styleBorderPanel(borderPanel);
		activityTable.setModel(new DefaultTableModel( // A lot of nulls to check if the scroll option works, will delete
														// later,
				// Otherwise may be useful when presenting to explain what happens when the
				// amount of data exceeds space in the panel
				new Object[][] { { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null },
						{ null }, { null }, { null }, { null }, { null }, { null }, { null }, { null }, { null } },
				new String[] { "Time", "Seconds", "Longitude", "Latitude", "Altitude", "Distance", "Heart rate",
						"Speed", "Cadence" }));
		scrollPane.setViewportView(activityTable); // Adds scroll option to the table

		GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
		borderPanel.setLayout(borderPanelLayout);
		borderPanelLayout.setHorizontalGroup(
				borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(scrollPane));
		borderPanelLayout.setVerticalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE));

		GroupLayout upperPanelLayout = new GroupLayout(upperPanel);
		upperPanel.setLayout(upperPanelLayout);
		upperPanelLayout.setHorizontalGroup(
				upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(upperPanelLayout.createSequentialGroup()
	                .addGap(36, 36, 36)
	                .addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addComponent(lblCurrentActivity)
	                    .addGroup(upperPanelLayout.createSequentialGroup()
	                        .addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                        .addGap(18, 18, 18)
	                        .addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addGroup(upperPanelLayout.createSequentialGroup()
	                                .addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
	                            .addGroup(GroupLayout.Alignment.TRAILING, upperPanelLayout.createSequentialGroup()
	                                .addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                                .addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
	                        .addGroup(upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                            .addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
	                            .addComponent(btnImport, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))))
	                .addGap(30, 30, 30))
	            .addComponent(borderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
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
						.addGap(18, 18, 18).addComponent(lblCurrentActivity)
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

	private void openUserSettings() {
		controller.openUserSettings();
	}

	private void selectActivity() {
		// TODO Auto-generated method stub
	}

	private void showGraph() {
		// TODO Auto-generated method stub
	}

	private void editActivity() {
		// TODO Auto-generated method stub
	}

	private void removeActivity() {
		// TODO Auto-generated method stub
	}
}

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

public class MainFrame extends JFrame {

	private JButton btnRemove;
	private JButton btnEdit;
	private JButton btnGraph;
	private JButton btnSelect;
	private JButton btnUserSettings;
	private JComboBox<String> cbxActivities;
	private JLabel lblTitle;
	private JLabel lblCurrentActivity;
	private JPanel upperPanel;
	private JPanel borderPanel;
	private JScrollPane ScrollPanel;
	private JTable jTable1;

	public MainFrame() {
		initComponents();
	}

	private void initComponents() {

		upperPanel = new JPanel();
		lblTitle = new JLabel("ST8TUS4U");
		cbxActivities = new JComboBox<>();
		lblCurrentActivity = new JLabel();
		btnRemove = new JButton("Remove");
		btnEdit = new JButton("Edit");
		btnEdit.setToolTipText("Saves the changes you've made in the activity tables");
		btnGraph = new JButton("Show Graph");
		btnSelect = new JButton("Select");
		btnUserSettings = new JButton("User Settings");
		borderPanel = new JPanel();
		ScrollPanel = new JScrollPane();
		jTable1 = new JTable();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		lblCurrentActivity.setText("Current Activity: Example Activity"); // Will update itself every time activity is chosen.
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
		StyleComponents.styleJPanel(borderPanel);
		StyleComponents.styleBorderPanel(borderPanel);
		jTable1.setBackground(new java.awt.Color(246, 249, 239));
		jTable1.setModel(new DefaultTableModel( // A lot of nulls to check if the scroll option works, will delete later, 
				// Otherwise may be useful when presenting to explain what happens when the amount of data exceeds space in the panel 
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
		ScrollPanel.setViewportView(jTable1);

		GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
		borderPanel.setLayout(borderPanelLayout);
		borderPanelLayout.setHorizontalGroup(
				borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(ScrollPanel));
		borderPanelLayout.setVerticalGroup(borderPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(ScrollPanel, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE));

		GroupLayout upperPanelLayout = new GroupLayout(upperPanel);
		upperPanel.setLayout(upperPanelLayout);
		upperPanelLayout.setHorizontalGroup(
				upperPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(upperPanelLayout
						.createSequentialGroup().addGap(36, 36, 36).addGroup(upperPanelLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(
										lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(upperPanelLayout.createSequentialGroup().addGroup(upperPanelLayout
										.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblCurrentActivity)
										.addGroup(upperPanelLayout.createSequentialGroup()
												.addComponent(cbxActivities, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addGroup(upperPanelLayout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(upperPanelLayout.createSequentialGroup()
																.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		btnEdit, GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(GroupLayout.Alignment.TRAILING, upperPanelLayout
																.createSequentialGroup()
																.addComponent(btnSelect, GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 100,
																		GroupLayout.PREFERRED_SIZE)))
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(btnUserSettings, GroupLayout.PREFERRED_SIZE, 100,
														GroupLayout.PREFERRED_SIZE)))
										.addGap(0, 0, Short.MAX_VALUE)))
						.addGap(36, 36, 36))
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
								.addComponent(btnGraph, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18).addComponent(lblCurrentActivity)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(borderPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(upperPanel,
				GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(upperPanel,
				GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE));

		pack();
	}
}

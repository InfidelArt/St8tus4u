package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import controller.ApplicationController;
import db.DataEntryException;

public class UserSettingsFrame extends JFrame {
	private ApplicationController controller;
	private JButton btnSaveChanges;
	private JComboBox<String> cbxGender;
	private JPanel containerPanel;
	private JLabel lblBottom;
	private JLabel lblGender;
	private JLabel lblTitle;
	private JPanel mainPanel;
	private JTextField txtAge;
	private JTextField txtLength;
	private JTextField txtUsername;
	private JTextField txtWeight;
	private final String TXT_USERNAME_STANDARD_TEXT = "Username";
	private final String TXT_WEIGHT_STANDARD_TEXT = "Weight";
	private final String TXT_LENGTH_STANDARD_TEXT = "Length";
	private final String TXT_AGE_STANDARD_TEXT = "Age";
	private UserSettingsFrame frame;

	public UserSettingsFrame(ApplicationController controller) {
		this.controller = controller;
		this.frame = this;
		initComponents();
	}

	private void initComponents() {
		containerPanel = new JPanel();
		mainPanel = new JPanel();
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.addActionListener(e -> {
			try {
				saveChanges();
			} catch (DataEntryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		lblTitle = new JLabel();
		txtUsername = new JTextField(TXT_USERNAME_STANDARD_TEXT);
		txtWeight = new JTextField(TXT_WEIGHT_STANDARD_TEXT);
		txtLength = new JTextField(TXT_LENGTH_STANDARD_TEXT);
		lblGender = new JLabel("Gender");
		txtAge = new JTextField(TXT_AGE_STANDARD_TEXT);
		cbxGender = new JComboBox<>();
		lblBottom = new JLabel();
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		StyleComponents.styleJPanel(mainPanel);
		StyleComponents.styleDefaultButton(btnSaveChanges);
		StyleComponents.styleTitleLabel(lblTitle);
		StyleComponents.styleDefaultLabel(lblGender);
		StyleComponents.styleDefaultTextBox(txtWeight);
		StyleComponents.styleDefaultTextBox(txtUsername);
		StyleComponents.styleDefaultTextBox(txtLength);
		StyleComponents.styleDefaultTextBox(txtAge);
		txtUsername.addMouseListener(new AutoEraseListener(TXT_USERNAME_STANDARD_TEXT, txtUsername));
		txtWeight.addMouseListener(new AutoEraseListener(TXT_WEIGHT_STANDARD_TEXT, txtWeight));
		txtLength.addMouseListener(new AutoEraseListener(TXT_LENGTH_STANDARD_TEXT, txtLength));
		txtAge.addMouseListener(new AutoEraseListener(TXT_AGE_STANDARD_TEXT, txtAge));
		StyleComponents.styleBottomLabel(lblBottom);

		cbxGender
				.setModel(new DefaultComboBoxModel<>(new String[] { "Select Your Gender", "Male", "Female", "Other" }));

		GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
		mainPanel.setLayout(mainPanelLayout);
		mainPanelLayout.setHorizontalGroup(mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(mainPanelLayout.createSequentialGroup().addGap(36, 36, 36).addGroup(mainPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(mainPanelLayout.createSequentialGroup().addComponent(lblGender).addGap(18, 18, 18)
								.addComponent(cbxGender, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426,
								Short.MAX_VALUE)
						.addComponent(txtUsername, GroupLayout.Alignment.TRAILING)
						.addComponent(btnSaveChanges, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(txtWeight).addComponent(txtLength)
						.addComponent(txtAge, GroupLayout.Alignment.TRAILING)).addGap(36, 36, 36))
				.addComponent(lblBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		mainPanelLayout
				.setVerticalGroup(
						mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(
										mainPanelLayout.createSequentialGroup().addGap(39, 39, 39)
												.addComponent(lblTitle)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(txtWeight, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(txtLength, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(txtAge, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addGroup(mainPanelLayout
														.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblGender)
														.addComponent(cbxGender, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addComponent(btnSaveChanges, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18).addComponent(lblBottom, GroupLayout.DEFAULT_SIZE,
														66, Short.MAX_VALUE)));

		GroupLayout containerPanelLayout = new GroupLayout(containerPanel);
		containerPanel.setLayout(containerPanelLayout);
		containerPanelLayout.setHorizontalGroup(
				containerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mainPanel,
						GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE));
		containerPanelLayout.setVerticalGroup(containerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(containerPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(containerPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void saveChanges() throws DataEntryException {
		if(!txtAge.getText().equals("Age")) {
		controller.setAge(Integer.parseInt(txtAge.getText()));
		}
		if(!txtWeight.getText().equals("Weight")) {
		controller.setWeight(Double.parseDouble(txtWeight.getText()));
		}
		if(!txtLength.getText().equals("Length")) {
			controller.setLength(Double.parseDouble(txtLength.getText()));
		}
		if(!txtUsername.getText().equals("Username")) {
		controller.setUsername(txtUsername.getText());
		} if(cbxGender.getSelectedIndex() != 0) {
		controller.setGender(cbxGender.getItemAt(cbxGender.getSelectedIndex()));
		}
		dispose();
	}
}

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


public class SignUpDialogueBox extends JFrame {
	private JButton btnCreateAcc;
	private JComboBox<String> cboxGenderPicker;
	private JLabel lblTitle;
	private JLabel lblBottom;
	private JLabel lblGender;
	private JPanel bottomPanel; 
	private JPanel signUpPanel; 
	private JTextField txtNickname;
	private JTextField txtWeight;
	private JTextField txtLength;
	private JTextField txtAge;

	public SignUpDialogueBox() {
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Sign Up Window");
		bottomPanel = new JPanel();
		signUpPanel = new JPanel();
		btnCreateAcc = new JButton("Create Account");
		lblTitle = new JLabel("Fill User Data");
		lblBottom = new JLabel();
		txtNickname = new JTextField("Nickname");
		txtWeight = new JTextField("Weight");
		txtLength = new JTextField("Length");
		lblGender = new JLabel();
		txtAge = new JTextField("Age");
		cboxGenderPicker = new JComboBox<>();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		StyleComponents.styleJPanel(signUpPanel);
		StyleComponents.styleBottomLabel(lblBottom);
		StyleComponents.styleDefaultButton(btnCreateAcc);
		StyleComponents.styleDefaultLabel(lblTitle);
		StyleComponents.styleDefaultLabel(lblGender);
		StyleComponents.styleDefaultTextBox(txtNickname);
		StyleComponents.styleDefaultTextBox(txtAge);
		StyleComponents.styleDefaultTextBox(txtLength);
		StyleComponents.styleDefaultTextBox(txtWeight);
		StyleComponents.styleTitleLabel(lblTitle);
		
		cboxGenderPicker
				.setModel(new DefaultComboBoxModel<>(new String[] { "Select Your Gender", "Male", "Female", "Other" }));

		
		GroupLayout mainLayout = new GroupLayout(signUpPanel);
		signUpPanel.setLayout(mainLayout);
		mainLayout.setHorizontalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(mainLayout.createSequentialGroup().addGap(36, 36, 36).addGroup(mainLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(mainLayout.createSequentialGroup().addComponent(lblGender).addGap(18, 18, 18)
								.addComponent(cboxGenderPicker, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426,
								Short.MAX_VALUE)
						.addComponent(txtNickname, GroupLayout.Alignment.TRAILING)
						.addComponent(btnCreateAcc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtWeight).addComponent(txtLength)
						.addComponent(txtAge, GroupLayout.Alignment.TRAILING)).addGap(36, 36, 36)));
		mainLayout
				.setVerticalGroup(
						mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(
										mainLayout.createSequentialGroup().addGap(39, 39, 39).addComponent(lblTitle)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE,
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
												.addGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblGender)
														.addComponent(cboxGenderPicker, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addComponent(btnCreateAcc, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41,
														Short.MAX_VALUE)
												.addComponent(lblBottom, GroupLayout.PREFERRED_SIZE, 43,
														GroupLayout.PREFERRED_SIZE)));

		GroupLayout bottomLabelLayout = new GroupLayout(bottomPanel);
		bottomPanel.setLayout(bottomLabelLayout);
		bottomLabelLayout.setHorizontalGroup(
				bottomLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(signUpPanel,
						GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE));
		bottomLabelLayout.setVerticalGroup(bottomLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(signUpPanel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane()); // Creates actual components in the panel
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bottomPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(bottomPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}
}

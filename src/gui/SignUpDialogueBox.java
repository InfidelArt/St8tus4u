package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class SignUpDialogueBox extends JFrame {
	private Color primaryColor = new Color(50, 66, 161);
	private Color secondaryColor = new Color(246, 249, 239);
	private Font textFont = new Font("Verdana", 1, 14);
	private Font titleFont = new Font("Verdana", 1, 24);
	private MatteBorder txtFieldBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, secondaryColor);
	private Border buttonBorder = BorderFactory.createLineBorder(secondaryColor, 2);

	private JButton btnCreateAcc;
	private JComboBox<String> cboxGenderPicker;
	private JLabel lblTitle;
	private JLabel lblColorEmpty;
	private JLabel lblGender;
	private JPanel SignUpPanel;
	private JPanel bottomLabel;
	private JTextField txtNickname;
	private JTextField txtWeight;
	private JTextField txtLength;
	private JTextField txtAge;

	public SignUpDialogueBox() {
		initComponents();
	}

	private void initComponents() {

		SignUpPanel = new JPanel();
		bottomLabel = new JPanel();
		btnCreateAcc = new JButton();
		lblTitle = new JLabel();
		lblColorEmpty = new JLabel();
		txtNickname = new JTextField();
		txtWeight = new JTextField();
		txtLength = new JTextField();
		lblGender = new JLabel();
		txtAge = new JTextField();
		cboxGenderPicker = new JComboBox<>();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bottomLabel.setBackground(primaryColor);

		btnCreateAcc.setBackground(primaryColor);
		btnCreateAcc.setFont(titleFont);
		btnCreateAcc.setForeground(secondaryColor);
		btnCreateAcc.setText("Create Account");
		btnCreateAcc.setBorder(buttonBorder);

		lblTitle.setBackground(primaryColor);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(secondaryColor);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText("Fill User Data");

		lblColorEmpty.setBackground(secondaryColor);
		lblColorEmpty.setOpaque(true);

		txtNickname.setBackground(primaryColor);
		txtNickname.setFont(textFont);
		txtNickname.setForeground(secondaryColor);
		txtNickname.setHorizontalAlignment(JTextField.LEFT);
		txtNickname.setText("Nickname");
		txtNickname.setToolTipText("");
		txtNickname.setBorder(txtFieldBorder);

		txtWeight.setBackground(primaryColor);
		txtWeight.setFont(textFont);
		txtWeight.setForeground(secondaryColor);
		txtWeight.setHorizontalAlignment(JTextField.LEFT);
		txtWeight.setText("Weight");
		txtWeight.setToolTipText("");
		txtWeight.setBorder(txtFieldBorder);

		txtLength.setBackground(primaryColor);
		txtLength.setFont(textFont);
		txtLength.setForeground(secondaryColor);
		txtLength.setHorizontalAlignment(JTextField.LEFT);
		txtLength.setText("Length");
		txtLength.setToolTipText("");
		txtLength.setBorder(txtFieldBorder);

		lblGender.setFont(textFont);
		lblGender.setForeground(secondaryColor);
		lblGender.setText("Gender");

		txtAge.setBackground(primaryColor);
		txtAge.setFont(textFont);
		txtAge.setForeground(secondaryColor);
		txtAge.setHorizontalAlignment(JTextField.LEFT);
		txtAge.setText("Age");
		txtAge.setToolTipText("");
		txtAge.setBorder(txtFieldBorder);

		cboxGenderPicker				.setModel(new DefaultComboBoxModel<>(new String[] 
				{ "Select Your Gender", "Male", "Female", "Other" }));

		GroupLayout mainLayout = new GroupLayout(bottomLabel);
		bottomLabel.setLayout(mainLayout);
		mainLayout.setHorizontalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblColorEmpty, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
												.addGroup(mainLayout
														.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(lblGender)
														.addComponent(cboxGenderPicker, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addComponent(btnCreateAcc, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41,
														Short.MAX_VALUE)
												.addComponent(lblColorEmpty, GroupLayout.PREFERRED_SIZE, 43,
														GroupLayout.PREFERRED_SIZE)));

		GroupLayout bottomLabelLayout = new GroupLayout(SignUpPanel);
		SignUpPanel.setLayout(bottomLabelLayout);
		bottomLabelLayout.setHorizontalGroup(bottomLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(bottomLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE));
		bottomLabelLayout.setVerticalGroup(bottomLabelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(bottomLabel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE));

		GroupLayout layout = new GroupLayout(getContentPane()); //Creates actual components in the panel
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(SignUpPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(SignUpPanel,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}
}

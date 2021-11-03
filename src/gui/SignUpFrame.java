package gui;

import java.util.InputMismatchException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import controller.ApplicationController;
import db.DataEntryException;


public class SignUpFrame extends JFrame {
	private JButton btnCreateAcc;
	private JComboBox<String> cbxGenderPicker;
	private JLabel lblTitle;
	private JLabel lblBottom;
	private JLabel lblGender;
	private JPanel bottomPanel; 
	private JPanel signUpPanel; 
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private final String TXT_USERNAME_STANDARD_TEXT = "Username";
	private final String TXT_PASSWORD_STANDARD_TEXT = "Password";
	public ApplicationController controller;
	private char[] password;
	public SignUpFrame(ApplicationController controller) {
		this.controller = controller;
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Sign Up Window");
		bottomPanel = new JPanel();
		signUpPanel = new JPanel();
		btnCreateAcc = new JButton("Create Account");
		btnCreateAcc.addActionListener(e -> createAccount());
		lblTitle = new JLabel("Fill User Data");
		lblBottom = new JLabel();
		txtUsername = new JTextField("Username");
		txtPassword = new JPasswordField("Password");
		txtUsername.addMouseListener(new AutoEraseListener(TXT_USERNAME_STANDARD_TEXT, txtUsername));
		txtPassword.addMouseListener(new AutoEraseListener(TXT_PASSWORD_STANDARD_TEXT, txtPassword));
		lblGender = new JLabel("Gender");	
		cbxGenderPicker = new JComboBox<>();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		StyleComponents.styleDefaultLabel(lblGender);
		StyleComponents.styleJPanel(signUpPanel);
		StyleComponents.styleBottomLabel(lblBottom);
		StyleComponents.styleDefaultButton(btnCreateAcc);
		StyleComponents.styleDefaultLabel(lblTitle);
		StyleComponents.styleDefaultLabel(lblGender);
		StyleComponents.styleDefaultTextBox(txtUsername);
		StyleComponents.styleDefaultTextBox(txtPassword);
		StyleComponents.styleTitleLabel(lblTitle);
		
		cbxGenderPicker
				.setModel(new DefaultComboBoxModel<>(new String[] { "Select Your Gender", "Male", "Female", "Other" }));

		
		GroupLayout signUpPanelLayout = new GroupLayout(signUpPanel);
		signUpPanel.setLayout(signUpPanelLayout);
		signUpPanelLayout.setHorizontalGroup(
				signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(signUpPanelLayout.createSequentialGroup()
                        .addComponent(lblGender)
                        .addGap(18, 18, 18)
                        .addComponent(cbxGenderPicker, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTitle, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                    .addComponent(txtUsername, GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCreateAcc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword))
                .addGap(36, 36, 36))
            .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblBottom, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );
		signUpPanelLayout.setVerticalGroup(
				signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblTitle)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(cbxGenderPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCreateAcc, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                    .addGap(0, 250, Short.MAX_VALUE)
                    .addComponent(lblBottom, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)))
        );

        GroupLayout jPanel1Layout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(signUpPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(signUpPanel, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(bottomPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
	}

	private void createAccount() {
		try {
			controller.registerNewAccount(txtUsername.getText(), txtPassword.getPassword(), cbxGenderPicker.getItemAt(cbxGenderPicker.getSelectedIndex()));
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataEntryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
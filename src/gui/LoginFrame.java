package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

public class LoginFrame extends JFrame {
	private Color primaryColor = new Color(50, 66, 161);
	private Color secondaryColor = new Color(246, 249, 239);
	private Font textFont = new Font("Verdana", 1, 14);
	private Font titleFont = new Font("Verdana", 1, 32);
	private MatteBorder txtFieldBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, secondaryColor);
	private Border buttonBorder = BorderFactory.createLineBorder(secondaryColor, 2);

	private JButton btnSignUp;
	private JButton btnSignIn;
	private JLabel lblTitle;
	private JLabel lblBottom;
	private JPanel loginPanel;
	private JTextField txtPassword;
	private JTextField txtUsername;

	public LoginFrame() {
		initComponents();
	}

	private void initComponents() {
		this.setTitle("St8tus4U");
		loginPanel = new JPanel();
		txtUsername = new JTextField();
		txtPassword = new JTextField();
		btnSignUp = new JButton();
		btnSignIn = new JButton();
		lblTitle = new JLabel();
		lblBottom = new JLabel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(primaryColor);

		loginPanel.setBackground(primaryColor);

		txtUsername.setBackground(primaryColor);
		txtUsername.setFont(textFont);
		txtUsername.setForeground(secondaryColor);
		txtUsername.setHorizontalAlignment(JTextField.LEFT);
		txtUsername.setText("Username");
		txtUsername.setBorder(txtFieldBorder);

		txtPassword.setBackground(primaryColor);
		txtPassword.setFont(textFont);
		txtPassword.setForeground(secondaryColor);
		txtPassword.setHorizontalAlignment(JTextField.LEFT);
		txtPassword.setText("Password");
		txtPassword.setBorder(txtFieldBorder);

		btnSignUp.setBackground(primaryColor);
		btnSignUp.setFont(textFont);
		btnSignUp.setForeground(secondaryColor);
		btnSignUp.setText("Sign Up");
		btnSignUp.setBorder(buttonBorder);

		btnSignIn.setBackground(primaryColor);
		btnSignIn.setFont(textFont);
		btnSignIn.setForeground(secondaryColor);
		btnSignIn.setText("Sign in");
		btnSignIn.setBorder(buttonBorder);

		lblTitle.setBackground(primaryColor);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(secondaryColor);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText("ST8TUS4U");

		lblBottom.setBackground(secondaryColor);
		lblBottom.setOpaque(true); // Sets opacity to 100%, do not change or delete. Since label is empty, it won't
									// be visible otherwise.
		// For GroupLayout: Related = small gap, Unrelated = medium gap, indent = big fucking gap.
		GroupLayout loginPanelLayout = new GroupLayout(loginPanel); // GroupLayout has to be declared here, otherwise it
																	// doesn't show up???? O___O
		loginPanel.setLayout(loginPanelLayout);
		loginPanelLayout.setHorizontalGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(lblBottom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup().addGap(36, 36, 36)
						.addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(GroupLayout.Alignment.LEADING,
										loginPanelLayout.createSequentialGroup()
												.addComponent(btnSignIn, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(btnSignUp, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblTitle, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
								.addComponent(txtUsername).addComponent(txtPassword))
						.addGap(36, 36, 36)));
		loginPanelLayout.setVerticalGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(loginPanelLayout.createSequentialGroup().addGap(39, 39, 39).addComponent(lblTitle)
						.addGap(40, 40, 40)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26)
						.addGroup(loginPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnSignIn, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addComponent(lblBottom, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)));

		// Creates an empty label with white background at the bottom and stretches it
		// over so it covers everything horizontally speaking
		GroupLayout bottomLayout = new GroupLayout(getContentPane());
		getContentPane().setLayout(bottomLayout);
		bottomLayout.setHorizontalGroup(bottomLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				loginPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE));
		bottomLayout.setVerticalGroup(bottomLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE));

		pack(); // I love this method.
	}
}

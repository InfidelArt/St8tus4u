package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StyleComponents {
	private static Color primaryColor = new Color(50, 66, 161);
	private static Color secondaryColor = new Color(246, 249, 239);
	private static Font textFont = new Font("Verdana", 1, 14);
	private static Font titleFont = new Font("Verdana", 1, 32);
	private static MatteBorder txtFieldBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, secondaryColor);
	private static Border buttonBorder = BorderFactory.createLineBorder(secondaryColor, 2);
	
	static JButton styleDefaultButton(JButton button) {
		button.setBackground(primaryColor);
		button.setFont(textFont);
		button.setForeground(secondaryColor);
		button.setBorder(buttonBorder);
		return button;
	}
	static JTextField styleDefaultTextBox(JTextField txtbox) {
		txtbox.setBackground(primaryColor);
		txtbox.setFont(textFont);
		txtbox.setForeground(secondaryColor);
		txtbox.setHorizontalAlignment(JTextField.LEFT);
		txtbox.setBorder(txtFieldBorder);
		return txtbox;
	}
	
	static JLabel styleDefaultLabel(JLabel lbl) {
		
		return lbl;
	}
	
	static JLabel styleTitleLabel(JLabel lbl) {
		lbl.setBackground(primaryColor);
		lbl.setFont(titleFont);
		lbl.setForeground(secondaryColor);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		return lbl;
	}
	
	static JLabel styleBottomLabel(JLabel lbl) {
		lbl.setBackground(secondaryColor);
		lbl.setOpaque(true); //Sets visibility to 100%, otherwise label without text is invisible
		return lbl;
	}
	
	static JPanel styleJPanel(JPanel panel) {
		panel.setBackground(primaryColor);
		return panel;
	}
}

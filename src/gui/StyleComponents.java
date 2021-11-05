package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StyleComponents{
	public static Color primaryColor = new Color(240, 120, 0);
	public static Color hoverColor = new Color(255, 166, 77);
	public static Color secondaryColor = new Color(246, 249, 239);
	private static Color tertiaryColor = new Color(50, 66, 161);
	private static Font textFont = new Font("Verdana", 1, 14);
	private static Font smallerTextFont = new Font("Verdana", 1, 10);
	private static Font titleFont = new Font("Verdana", 1, 32);
	private static MatteBorder txtFieldBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, secondaryColor);
	private static Border buttonBorder = BorderFactory.createLineBorder(secondaryColor, 2);
	private static Border panelBorder = BorderFactory.createMatteBorder(5, 0, 0, 0, tertiaryColor);
	
	static JButton styleDefaultButton(JButton button) {
		button.setBackground(primaryColor);
		button.setFont(textFont);
		button.setForeground(secondaryColor);
		button.setBorder(buttonBorder);
		return button;
	}
	
	static JButton styleDefaultButtonHover(JButton button) {
		button.setBackground(hoverColor);
		return button;
	}
	
	static JButton styleMainFrameButton(JButton button) {
		button.setBackground(primaryColor);
		button.setFont(smallerTextFont);
		button.setForeground(secondaryColor);
		button.setBorder(buttonBorder);
		return button;
	}
	
	static JButton styleMainFrameButtonHover(JButton button) {
		button.setBackground(hoverColor);
		return button;
	}
	
	static JTextField styleDefaultTextBox(JTextField txtBox) {
		txtBox.setBackground(primaryColor);
		txtBox.setFont(textFont);
		txtBox.setForeground(secondaryColor);
		txtBox.setHorizontalAlignment(JTextField.LEFT);
		txtBox.setBorder(txtFieldBorder);
		return txtBox;
	}
	
	static JTextArea styleDefaultTextArea(JTextArea txtArea) {
		txtArea.setBackground(primaryColor);
		txtArea.setFont(textFont);
		txtArea.setForeground(secondaryColor);
		txtArea.setBorder(txtFieldBorder);
		return txtArea;
	}
	static JTextField styleDefaultTextBoxHover(JTextField txtBox) {
		txtBox.setBackground(hoverColor);
		return txtBox;
	}
	
	static JLabel styleDefaultLabel(JLabel lbl) {
		lbl.setFont(textFont);
		lbl.setBackground(primaryColor);
		lbl.setHorizontalAlignment(JTextField.LEFT);
		lbl.setForeground(secondaryColor);
		return lbl;
	}
	
	static JLabel styleMainFrameLabel(JLabel lbl) {
		lbl.setFont(textFont);
		lbl.setBackground(primaryColor);
		lbl.setHorizontalAlignment(JTextField.CENTER);
		lbl.setForeground(secondaryColor);
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
	
	static JPanel styleBorderPanel(JPanel panel) {
		panel.setBorder(panelBorder);
		return panel;
	}
	
	static JTable styleActivityTable(JTable table) {
		table.setBackground(secondaryColor);
		return table;
	}
	
	static JComboBox styleDefaultJComboBox(JComboBox cbx) {
		cbx.setBackground(primaryColor);
		cbx.setFont(smallerTextFont);
		cbx.setForeground(secondaryColor);
		cbx.setBorder(buttonBorder);
		cbx.setRenderer(new DefaultComboBoxRenderer());
		return cbx;
	}
	
	static JLabel styleDefaultLabelHover(JLabel lbl) {
		lbl.setBackground(hoverColor);
		return lbl;
	}
}

package gui;

import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class AutoEraseListener extends MouseInputAdapter {
	private String standardMessage;
	private JTextField sourceText;
	private JPasswordField passwordText;

	public AutoEraseListener(String standardMessage, JPasswordField source) {
		super();
		this.standardMessage = standardMessage;
		this.passwordText = source;
	}

	public AutoEraseListener(String standardMessage, JTextField source) {
		super();
		this.standardMessage = standardMessage;
		this.sourceText = source;
	}

	@SuppressWarnings("deprecation")
	public void mouseClicked(MouseEvent e) {
		if (sourceText != null) {
			if (sourceText.getText().equals(standardMessage)) {
				sourceText.setText("");
			}
		} else {
		if (passwordText.getText().equals(standardMessage)) {
			passwordText.setText("");
		} }
	}
}

package gui;

import java.awt.event.MouseEvent;

import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class AutoEraseListener extends MouseInputAdapter {
	private String standardMessage;
	private JTextField sourceText;

	public AutoEraseListener(String standardMessage, JTextField source) {
		this.standardMessage = standardMessage;
		this.sourceText = source;
	}

	public void mouseClicked(MouseEvent e) {
		if (sourceText.getText().equals(standardMessage)) {
			sourceText.setText("");
		}
	}
}

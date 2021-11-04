package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTextField;

public class DefaultTextBox extends JTextField implements MouseListener {
	DefaultTextBox(String title) {
		this.setText(title);
		addMouseListener(this);
		StyleComponents.styleDefaultTextBox(this);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		StyleComponents.styleDefaultTextBoxHover(this);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		StyleComponents.styleDefaultTextBox(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) { 
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

}

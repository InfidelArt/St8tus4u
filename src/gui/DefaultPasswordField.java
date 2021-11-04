package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPasswordField;

public class DefaultPasswordField extends JPasswordField implements MouseListener {
	DefaultPasswordField(String title) {
		this.setText(title);
		addMouseListener(this);
		StyleComponents.styleDefaultTextBox(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		StyleComponents.styleDefaultTextBoxHover(this);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		StyleComponents.styleDefaultTextBox(this);
	}
}

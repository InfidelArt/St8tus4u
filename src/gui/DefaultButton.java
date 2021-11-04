package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class DefaultButton extends JButton implements MouseListener {
	DefaultButton(String title) {
		this.setText(title);
		addMouseListener(this);
		StyleComponents.styleDefaultButton(this);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		StyleComponents.styleDefaultButtonHover(this);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		StyleComponents.styleDefaultButton(this);
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

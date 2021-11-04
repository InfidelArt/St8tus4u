package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MainFrameButton extends JButton implements MouseListener {
	MainFrameButton(String title) {
		this.setText(title);
		addMouseListener(this);
		StyleComponents.styleMainFrameButton(this);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		StyleComponents.styleMainFrameButtonHover(this);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		StyleComponents.styleMainFrameButton(this);
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

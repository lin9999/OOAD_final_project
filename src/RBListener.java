import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public abstract class RBListener extends MouseAdapter {
	public void mouseEntered(MouseEvent e) {
		JLabel l = (JLabel) e.getSource();
		l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		l.setForeground(Color.red);
	}
	public void mouseExited(MouseEvent e) {
		JLabel l = (JLabel) e.getSource();
		l.setForeground(Color.black);
	}
	
	public abstract void mouseClicked(MouseEvent e);
}

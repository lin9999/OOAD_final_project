import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;

public class HotelfunctionUI extends JPanel{
	final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
	final private Dimension hotelfunctionCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel hotelfunction = new JPanel();
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(Menu.frameWidth, Menu.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(Menu.frameWidth, Menu.frameHeight));
		Menu.background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
		Menu.background.setBounds(0, 0, Menu.frameWidth, Menu.frameHeight);
		layeredPane.add(Menu.background, new Integer(0));
		layeredPane.add(hotelfunction, new Integer(1));
		add(layeredPane);
	}
	
	private void initHotelfunction() {
		hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
				hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);
		
		// go to SearchUI
		JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
		searchText.setFont(new Font("Dialog", Font.BOLD, 36));
		searchText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelfunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelfunctionUI.this);
					root.setContentPane(new SearchUI());
					searchText.setForeground(Color.BLACK);
				}
			}
		);
		
		// go to ReserveUI
		JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
		reserveText.setFont(new Font("Dialog", Font.BOLD, 36));
		reserveText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelfunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelfunctionUI.this);
					root.setContentPane(new ReserveUI());
					reserveText.setForeground(Color.BLACK);
				}
			}
		);
		
		// go to InquiryUI
		JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
		inquiryText.setFont(new Font("Dialog", Font.BOLD, 36));
		inquiryText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelfunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelfunctionUI.this);
					root.setContentPane(new InquiryUI());
					inquiryText.setForeground(Color.BLACK);
				}
			}
		);
		
		// go to RegistrationUI
		JLabel logoutText = new JLabel("LOGOUT", JLabel.CENTER);
		logoutText.setFont(new Font("Dialog", Font.BOLD, 36));
		logoutText.addMouseListener( new RBListener() {
			public void mouseClicked(MouseEvent e) {
				layeredPane.remove(hotelfunction);
				HotelfunctionUI.this.setVisible(false);
				JFrame root = (JFrame) SwingUtilities.getRoot(HotelfunctionUI.this);
				root.setContentPane(new RegistrationUI());
				validate();
				repaint();
				logoutText.setForeground(Color.BLACK);
				}
			}
		);
		
		hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
		hotelfunction.setOpaque(false);
		hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		hotelfunction.add(searchText);
		hotelfunction.add(reserveText);
		hotelfunction.add(inquiryText);
		hotelfunction.add(logoutText);
	}
	
	public HotelfunctionUI() {
		initHotelfunction();
		initLayerPane();
		initPanel();
	}
}

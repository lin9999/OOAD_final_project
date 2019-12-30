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

public class HotelFunctionUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final private int HotelFunctionWidth = 500, HotelFunctionHeight = 200;
	final private Dimension HotelFunctionCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel HotelFunction = new JPanel();
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
		HotelPreference.background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
		layeredPane.add(HotelPreference.background, new Integer(0));
		layeredPane.add(HotelFunction, new Integer(1));
		add(layeredPane);
	}
	
	private void initHotelFunction() {
		HotelFunction.setBounds(HotelFunctionCenter.width - (HotelFunctionWidth / 2),
				HotelFunctionCenter.height - (HotelFunctionHeight / 2), HotelFunctionWidth, HotelFunctionHeight);
		
		// go to SearchUI
		JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
		searchText.setFont(new Font("Dialog", Font.BOLD, 36));
		searchText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelFunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelFunctionUI.this);
					root.setContentPane(new SearchUI());
					searchText.setForeground(Color.BLACK);
				}
			});
		
		// go to ReserveUI
		JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
		reserveText.setFont(new Font("Dialog", Font.BOLD, 36));
		reserveText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelFunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelFunctionUI.this);
					root.setContentPane(new ReserveUI());
					reserveText.setForeground(Color.BLACK);
				}
			});
		
		// go to InquiryUI
		JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
		inquiryText.setFont(new Font("Dialog", Font.BOLD, 36));
		inquiryText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelFunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelFunctionUI.this);
					root.setContentPane(new InquiryUI());
					inquiryText.setForeground(Color.BLACK);
				}
			});

		
		// go to RegistrationUI
		JLabel logoutText = new JLabel("LOGOUT", JLabel.CENTER);
		logoutText.setFont(new Font("Dialog", Font.BOLD, 36));
		logoutText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotelFunctionUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotelFunctionUI.this);
					root.setContentPane(new RegistrationUI());
					logoutText.setForeground(Color.BLACK);
				}
			});
		
		HotelFunction.setLayout(new GridLayout(2, 2, 0, 0));
		HotelFunction.setOpaque(false);
		HotelFunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		HotelFunction.add(searchText);
		HotelFunction.add(reserveText);
		HotelFunction.add(inquiryText);
		HotelFunction.add(logoutText);
	}
	
	public HotelFunctionUI() {
		initHotelFunction();
		initLayerPane();
		initPanel();
	}
}

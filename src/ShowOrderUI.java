import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class ShowOrderUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	final private int reserveorderWidth = 650, reserveorderHeight = 360;
	final private Dimension reserveorderCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel Reserveorder = new JPanel();
	private Order order;
	
	private void initReserveorder(int oid, int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate,
			int night, int p) {
		JLabel cancelText = new JLabel("CANCEL ORDER", JLabel.CENTER);
		cancelText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					// TODO  cancel the order
					// show the message YES or NO!
					JFrame root = (JFrame) SwingUtilities.getRoot(ShowOrderUI.this);
					int result = JOptionPane.showConfirmDialog(root, "CANCEL THE ORDER?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						ShowOrderUI.this.setVisible(false);
						Inquiry.CancelOrder(oid);
						root.setContentPane(new HotelFunctionUI());
					}
					cancelText.setForeground(Color.BLACK);
				}
			});
		JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
		modifyText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					ShowOrderUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(ShowOrderUI.this);
					root.setContentPane(new ModifyUI(order));
					modifyText.setForeground(Color.BLACK);
				}
			});
		JLabel confirmText = new JLabel("CONFIRM", JLabel.CENTER);
		confirmText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					ShowOrderUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(ShowOrderUI.this);
					root.setContentPane(new HotelFunctionUI());
					confirmText.setForeground(Color.BLACK);
				}
			});
		
		
		
		JTextField reserveorderhotelIDField = new JTextField(15);
		JTextField reserveordersingleroomField = new JTextField(2);
		JTextField reserveorderdoubleroomField = new JTextField(2);
		JTextField reserveorderquadroomField = new JTextField(2);
		JTextField reserveordercheckindateField = new JTextField(10);
		JTextField reserveordercheckoutdateField = new JTextField(10);
		JTextField reserveorderstaynightField = new JTextField(2);
		JTextField reserveorderpriceField = new JTextField(5);
		JPanel reserveorderbuttons = new JPanel();
		JTextField successreservenumberField = new JTextField(10);
		// show the order detail
		successreservenumberField.setText(Integer.toString(oid));
		reserveorderhotelIDField.setText(Integer.toString(hid));
		reserveordersingleroomField.setText(Integer.toString(sroom));
		reserveorderdoubleroomField.setText(Integer.toString(droom));
		reserveorderquadroomField.setText(Integer.toString(qroom));
		reserveordercheckindateField.setText(chkindate);
		reserveordercheckoutdateField.setText(chkoutdate);
		reserveorderstaynightField.setText(Integer.toString(night));
		reserveorderpriceField.setText(Integer.toString(p));
		
		
		//-------FOR---GUI---SETTING----------------------------------
		Reserveorder.setBounds(reserveorderCenter.width - (reserveorderWidth / 2),
				reserveorderCenter.height - (reserveorderHeight / 2), reserveorderWidth, reserveorderHeight);
		Reserveorder.setLayout(new GridLayout(6, 1, 0, 0));
		Reserveorder.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserveorder.setOpaque(false);
		// success reserve hotel ID
		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel reservenumber = new JLabel("RESERVATION NUMBER: ");
		reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
		successreservenumberField.setHorizontalAlignment(SwingConstants.CENTER);
		successreservenumberField.setFont(new Font("Serif", Font.BOLD, 23));
		successreservenumberField.setEditable(false);
		successreservenumberField.setOpaque(false);
		reservenumberPanel.add(reservenumber);
		reservenumberPanel.add(successreservenumberField);
		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setOpaque(false);
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderhotelIDField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveorderhotelIDField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderhotelIDField.setEditable(false);
		// hotel ID Panel adding
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(reserveorderhotelIDField);
		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// single room
		JLabel singleroom = new JLabel("Single: ");
		singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveordersingleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveordersingleroomField.setEditable(false);
		reserveordersingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordersingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// double room
		JLabel doubleroom = new JLabel("Double: ");
		doubleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderdoubleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveorderdoubleroomField.setEditable(false);
		reserveorderdoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderdoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// quad room
		JLabel quadroom = new JLabel("Quad: ");
		quadroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderquadroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveorderquadroomField.setEditable(false);
		reserveorderquadroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderquadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(singleroom);
		roomPanel.add(reserveordersingleroomField);
		roomPanel.add(doubleroom);
		roomPanel.add(reserveorderdoubleroomField);
		roomPanel.add(quadroom);
		roomPanel.add(reserveorderquadroomField);
		// lodging date panel
		JPanel lodgingPanel = new JPanel();
		lodgingPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		lodgingPanel.setOpaque(false);
		lodgingPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		reserveordercheckindateField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveordercheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordercheckindateField.setEditable(false);
		JLabel mark = new JLabel("~");
		mark.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveordercheckoutdateField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveordercheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveordercheckoutdateField.setEditable(false);
		// lodgingPanel adding
		lodgingPanel.add(reserveordercheckindateField);
		lodgingPanel.add(mark);
		lodgingPanel.add(reserveordercheckoutdateField);
		// 'total length of stay' and 'total price'
		JPanel staypricePanel = new JPanel();
		staypricePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		staypricePanel.setOpaque(false);
		staypricePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel stay = new JLabel("Total Nights of Stay:");
		stay.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderstaynightField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveorderstaynightField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderstaynightField.setEditable(false);
		JLabel price = new JLabel("Total Price:");
		price.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderpriceField.setHorizontalAlignment(SwingConstants.CENTER);
		reserveorderpriceField.setFont(new Font("Serif", Font.BOLD, 23));
		reserveorderpriceField.setEditable(false);
		// stay price Panel adding
		staypricePanel.add(stay);
		staypricePanel.add(reserveorderstaynightField);
		staypricePanel.add(price);
		staypricePanel.add(reserveorderpriceField);
		// set 'back' and 'next' button
		reserveorderbuttons.setLayout(new GridLayout(1, 3));
		reserveorderbuttons.setOpaque(false);
		reserveorderbuttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		modifyText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		cancelText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		confirmText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reserveorderbuttons.add(cancelText);
		reserveorderbuttons.add(modifyText);
		reserveorderbuttons.add(confirmText);
		// Reserve order adding
		Reserveorder.add(reservenumberPanel);
		Reserveorder.add(hotelIDPanel);
		Reserveorder.add(lodgingPanel);
		Reserveorder.add(roomPanel);
		Reserveorder.add(staypricePanel);
		Reserveorder.add(reserveorderbuttons);
	}

	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
		HotelPreference.background.setBounds(0, 0, HotelPreference.frameWidth, HotelPreference.frameHeight);
		layeredPane.add(HotelPreference.background, new Integer(0));
		layeredPane.add(Reserveorder, new Integer(1));
		add(layeredPane);
	}
	
	public ShowOrderUI(Order _order) {
		order = _order;
		initReserveorder(order.getID(), order.getHotelID(), order.getSnum().size(), order.getDnum().size(),
				order.getQnum().size(), order.getCheckInDate(), order.getCheckOutDate(),
				(int) RoomChecker.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
				order.getSumPrice());
		initLayerPane();
		initPanel();
	}
	
}

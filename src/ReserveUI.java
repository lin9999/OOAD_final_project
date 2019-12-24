import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class ReserveUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private boolean fromSearch = false;
	private String checkInDate, checkOutDate;
	private int people, rooms, hotelID, sRoom, dRoom, qRoom;
	
	final private int reserveWidth = 620, reserveHeight = 300;
	final private Dimension reserveCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();

	private JPanel reserve = new JPanel();
	
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
		layeredPane.add(reserve, new Integer(1));
		add(layeredPane);
	}
	
	private void initReserve() {	
		
		// check in date panel
		JPanel checkInPanel = new JPanel();
		
		checkInPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkInPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkInPanel.setOpaque(false);
		
		// enter check in date
		JLabel checkin = new JLabel("    CHECK IN DATE : ");
		JTextField inputCheckInDate = new JTextField(10);
		
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputCheckInDate.setHorizontalAlignment(SwingConstants.CENTER);
		inputCheckInDate.setEditable(false);
		inputCheckInDate.setFont(new Font("Serif", Font.BOLD, 23));
		inputCheckInDate.setBackground(new Color(255, 255, 255));
		inputCheckInDate.setOpaque(true);
		inputCheckInDate.setBounds(267, 15, 105, 40);
		inputCheckInDate.setColumns(10);
		inputCheckInDate.setText((checkInDate == "") ? "SELECT DATE" : checkInDate);
		inputCheckInDate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup dp = new DatePopup(inputCheckInDate);
				dp.showDialog();
			}
		});
		checkInPanel.add(checkin);
		checkInPanel.add(inputCheckInDate);

		// check out date panel
		JPanel checkOutPanel = new JPanel();
		
		checkOutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkOutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkOutPanel.setOpaque(false);
		
		// enter check out date
		JLabel checkOut = new JLabel(" CHECK OUT DATE : ");
		JTextField inputCheckOutDate = new JTextField(10);
		
		checkOut.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputCheckOutDate.setHorizontalAlignment(SwingConstants.CENTER);
		inputCheckOutDate.setEditable(false);
		inputCheckOutDate.setFont(new Font("Serif", Font.BOLD, 23));
		inputCheckOutDate.setBackground(new Color(255, 255, 255));
		inputCheckOutDate.setOpaque(true);
		inputCheckOutDate.setBounds(267, 15, 105, 40);
		inputCheckOutDate.setColumns(10);
		inputCheckOutDate.setText((checkOutDate == "") ? "SELECT DATE" : checkOutDate);
		inputCheckOutDate.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					DatePopup DP = new DatePopup(inputCheckOutDate);
					DP.showDialog();
				}
			});
		checkOutPanel.add(checkOut);
		checkOutPanel.add(inputCheckOutDate);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		hotelIDPanel.setOpaque(false);
		
		// select hotel ID
		JLabel hotelIDLabel = new JLabel(" HotelID : ");
		JComboBox<Object> hotelIDList = new JComboBox<Object>();
		
		hotelIDLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++)
			option[i] = i.toString();
		hotelIDList = new JComboBox<Object>(option);
		hotelIDList.setSelectedIndex(hotelID);
		hotelIDPanel.add(hotelIDLabel);
		hotelIDPanel.add(hotelIDList);

		// number of room panel
		JPanel roomPanel = new JPanel();	

		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		roomPanel.setOpaque(false);
		
		// single room
		JLabel singleRoom = new JLabel(" Single: ");
		JTextField inputSingleRoom = new JTextField(2);
		
		singleRoom.setFont(new Font("Arial Black", Font.PLAIN, 20));		
		inputSingleRoom.setHorizontalAlignment(SwingConstants.CENTER);
		inputSingleRoom.setEditable(!fromSearch);
		inputSingleRoom.setFont(new Font("Serif", Font.BOLD, 23));
		inputSingleRoom.setText(new Integer(sRoom).toString());
		inputSingleRoom.addKeyListener(new KeyAdapter() { // can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
		
		// double room
		JLabel doubleRoom = new JLabel(" Double: ");
		JTextField inputDoubleRoom = new JTextField(2);
		
		doubleRoom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputDoubleRoom.setHorizontalAlignment(SwingConstants.CENTER);
		inputDoubleRoom.setEditable(!fromSearch);
		inputDoubleRoom.setFont(new Font("Serif", Font.BOLD, 23));
		inputDoubleRoom.setText(new Integer(dRoom).toString());
		inputDoubleRoom.addKeyListener(new KeyAdapter() { // can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
		
		// quad room
		JLabel quadRoom = new JLabel(" Quad: ");
		JTextField inputQuadRoom = new JTextField(2);
		
		quadRoom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputQuadRoom.setHorizontalAlignment(SwingConstants.CENTER);
		inputQuadRoom.setEditable(!fromSearch);
		inputQuadRoom.setFont(new Font("Serif", Font.BOLD, 23));
		inputQuadRoom.setText(new Integer(qRoom).toString());
		inputQuadRoom.addKeyListener(new KeyAdapter() { // can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
		
		roomPanel.add(singleRoom);
		roomPanel.add(inputSingleRoom);
		roomPanel.add(doubleRoom);
		roomPanel.add(inputDoubleRoom);
		roomPanel.add(quadRoom);
		roomPanel.add(inputQuadRoom);

		// setting 'back' and 'next' buttons
		JPanel buttons = new JPanel();
		JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		JLabel nextText = new JLabel("NEXT", JLabel.CENTER);
		
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		cancelText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(cancelText);
		buttons.add(backText);
		buttons.add(nextText);
		
		backText.setVisible(fromSearch);
		
		cancelText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					ReserveUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(ReserveUI.this);
					root.setContentPane(new HotelfunctionUI());
					cancelText.setForeground(Color.BLACK);
				}
			});
		
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					String s1 = inputCheckInDate.getText();
					String s2 = inputCheckOutDate.getText();
					if (RoomChecker.CountDaysBetween(s1, s2) > 0) {
						ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(checkInDate, checkOutDate, people, rooms);
						if (AHR.size() > 0) { // if find available hotel
							ReserveUI.this.setVisible(false);
							JFrame root = (JFrame) SwingUtilities.getRoot(ReserveUI.this);
							root.setContentPane(new HotellistUI(checkInDate, checkOutDate, people, rooms, AHR));
						}
						else {  // TODO no matched hotel
							// show error message
						}
					} else  { // TODO Invalid date (date format error)
						// show error message
					}
					backText.setForeground(Color.BLACK);
				}
			});

		// Reserve adding Panel
		reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
				reserveWidth, reserveHeight);
		reserve.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		reserve.setLayout(new GridLayout(5, 1));
		reserve.setOpaque(false);
		reserve.add(checkInPanel);
		reserve.add(checkOutPanel);
		reserve.add(hotelIDPanel);
		reserve.add(roomPanel);
		reserve.add(buttons);
	}
	
	public ReserveUI(String _cid, String _cod, int _people, int _rooms, Object _hid, Object _sroom, Object _droom, Object _qroom) {
		fromSearch = true;
		checkInDate = _cid;
		checkOutDate = _cod;
		people = _people;
		rooms = _rooms;
		hotelID = (int)_hid;
		sRoom = (int)_sroom;
		dRoom = (int)_droom;
		qRoom = (int)_qroom;
		initPanel();
		initLayerPane();
		initReserve();
	}

	public ReserveUI() {
		fromSearch = false;
		checkInDate = "";
		checkOutDate = "";
		people = 0;
		rooms = 0;
		hotelID = 0;
		sRoom = 0;
		dRoom = 0;
		qRoom = 0;		
		initPanel();
		initLayerPane();
		initReserve();
	}

}

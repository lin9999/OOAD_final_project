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

public class SearchUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private String checkInDate, checkOutDate;
	private int people, rooms;
	
	final private int searchWidth = 600, searchHeight = 300;
	final private Dimension searchCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel search = new JPanel();

	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
		layeredPane.add(HotelPreference.background, new Integer(0));
		layeredPane.add(search, new Integer(1));
		add(layeredPane);
	}
	
	private void initSearch() {
		// check in date panel
		JPanel checkInPanel = new JPanel();
		
		checkInPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkInPanel.setOpaque(false);
		checkInPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		// enter check in date
		JLabel checkIn = new JLabel("  CHECK IN DATE: ");
		JTextField inputCheckInDate = new JTextField(10);

		checkIn.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputCheckInDate.setHorizontalAlignment(SwingConstants.CENTER);
		inputCheckInDate.setEditable(false);
		inputCheckInDate.setFont(new Font("Serif", Font.BOLD, 23));
		inputCheckInDate.setBackground(new Color(255, 255, 255));
		inputCheckInDate.setOpaque(true);
		inputCheckInDate.setBounds(267, 15, 105, 40);
		inputCheckInDate.setColumns(10);
		inputCheckInDate.setText((checkInDate == "") ? "SELECT A DATE" : checkInDate);
		inputCheckInDate.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					DatePopup dp = new DatePopup(inputCheckInDate);
					dp.showDialog();
				}
			});
		checkInPanel.add(checkIn);
		checkInPanel.add(inputCheckInDate);
		
		// check out date panel
		JPanel checkoutPanel = new JPanel();
		
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setOpaque(false);
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		JLabel checkOut = new JLabel("  CHECK OUT DATE: ");
		JTextField inputCheckOutDate = new JTextField(10);
		checkOut.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputCheckOutDate.setHorizontalAlignment(SwingConstants.CENTER);
		inputCheckOutDate.setEditable(false);
		inputCheckOutDate.setFont(new Font("Serif", Font.BOLD, 23));
		inputCheckOutDate.setBackground(new Color(255, 255, 255));
		inputCheckOutDate.setOpaque(true);
		inputCheckOutDate.setBounds(267, 15, 105, 40);
		inputCheckOutDate.setColumns(10);
		inputCheckOutDate.setText((checkOutDate == "") ? "SELECT A DATE" : checkOutDate);
		inputCheckOutDate.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					DatePopup DP = new DatePopup(inputCheckOutDate);
					DP.showDialog();
				}
			});
		checkoutPanel.add(checkOut);
		checkoutPanel.add(inputCheckOutDate);

		// people panel
		JPanel peoplePanel = new JPanel();
		
		peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		peoplePanel.setOpaque(false);
		peoplePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		JLabel peopleLabel = new JLabel("NUMBER OF PEOPLE: ");
		JTextField inputPeople = new JTextField(10);
		peopleLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputPeople.setHorizontalAlignment(SwingConstants.CENTER);
		inputPeople.setEditable(true);
		inputPeople.setFont(new Font("Serif", Font.BOLD, 23));
		inputPeople.setText(new Integer(people).toString());
		inputPeople.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
		peoplePanel.add(peopleLabel);
		peoplePanel.add(inputPeople);

		// room panel
		JPanel roomPanel = new JPanel();
		
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		JLabel roomLabel = new JLabel("NUMBER OF ROOMS: ");
		JTextField inputRoom = new JTextField(10);
		
		roomLabel.setFont(new Font("Arial Black", Font.PLAIN, 20));
		inputRoom.setHorizontalAlignment(SwingConstants.CENTER);
		inputRoom.setEditable(true);
		inputRoom.setFont(new Font("Serif", Font.BOLD, 23));
		inputRoom.setText(new Integer(rooms).toString());
		inputRoom.addKeyListener(new KeyAdapter() {// can only enter number!
				public void keyTyped(KeyEvent e) {
					char keyChar = e.getKeyChar();
					if (!(keyChar >= '0' && keyChar <= '9')) {
						e.consume();
					}
				}
			});
		roomPanel.add(roomLabel);
		roomPanel.add(inputRoom);

		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		JLabel nextText = new JLabel("NEXT", JLabel.CENTER);
		
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					SearchUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(SearchUI.this);
					root.setContentPane(new HotelFunctionUI());
					backText.setForeground(Color.BLACK);
				}
			});
		nextText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					String CID = inputCheckInDate.getText();
					String COD = inputCheckOutDate.getText();

					JFrame root = (JFrame) SwingUtilities.getRoot(SearchUI.this);
					if (RoomChecker.CountDaysBetween(CID, COD) > 0) {
						int People = Integer.parseInt(inputPeople.getText());
						int Rooms = Integer.parseInt(inputRoom.getText());
						ArrayList<AvailableHotelRoom> AHR = Search.SearchAvailableHotels(CID, COD, People, Rooms);
						
						if (AHR.size() > 0) { // if find available hotel
							SearchUI.this.setVisible(false);
							root.setContentPane(new HotellistUI(CID, COD, People, Rooms, AHR));
						} else {
							JOptionPane.showMessageDialog(root, "NO MATCHED HOTEL", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(root, "INVALID DATE", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					nextText.setForeground(Color.BLACK);
				}
			}
		);
		buttons.add(backText);
		buttons.add(nextText);

		search.setBounds(searchCenter.width - (searchWidth / 2),
				searchCenter.height - (searchHeight / 2), searchWidth, searchHeight);
		search.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		search.setLayout(new GridLayout(5, 1));
		search.setOpaque(false);
		search.add(checkInPanel);
		search.add(checkoutPanel);
		search.add(peoplePanel);
		search.add(roomPanel);
		search.add(buttons);
	}
	
	public SearchUI() {
		checkInDate = "";
		checkOutDate = "";
		people = 0;
		rooms = 0;
		initPanel();
		initSearch();
		initLayerPane();
	}
	

	public SearchUI(String cid, String cod, int _people, int _rooms) {
		checkInDate = cid;
		checkOutDate = cod;
		people = _people;
		rooms = _rooms;
		initPanel();
		initSearch();
		initLayerPane();
	}
	
}

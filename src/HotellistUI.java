import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HotellistUI extends JPanel{
	String CID, COD;
	int People, Rooms;
	private JLayeredPane layeredPane = new JLayeredPane();
	
	final private int searchWidth = 570, searchHeight = 240;
	final private Dimension searchCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	
	private JPanel search_option = new JPanel();
	JLabel star5 = new JLabel("5-star", JLabel.CENTER);
	JLabel star4 = new JLabel("4-star", JLabel.CENTER);
	JLabel star3 = new JLabel("3-star", JLabel.CENTER);
	JLabel star2 = new JLabel("2-star", JLabel.CENTER);
	JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
	JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
	
	private JPanel Hotellist = new JPanel();
	final private int hotellistWidth = 820, hotellistHeight = 500;
	final private Dimension hotellistCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	JTable HotellistTable = new JTable();
	String[] heading = new String[] { "ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price", "Select" };
	private JLabel showallText = new JLabel("SHOW   ALL", JLabel.CENTER);
	private JLabel backhotellist = new JLabel("BACK", JLabel.CENTER);
	private JLabel reservehotellist = new JLabel("RESERVE", JLabel.CENTER);
	JPanel star = new JPanel();
	
	JLabel backsearch = new JLabel("BACK", JLabel.CENTER);
	
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
		layeredPane.add(search_option, new Integer(1));
		add(layeredPane);
	}
	
	private void initHotellist() {
		Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
				hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);

		Hotellist.setLayout(new BorderLayout());
		Hotellist.setOpaque(false);
		backhotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
		reservehotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
	}

	
	private void initSearch_option() {
		search_option.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);
		
		pricehighText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SortByPrice(AHR, 0);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		pricelowText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SortByPrice(AHR, 1);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		star5.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 5);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		star4.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 4);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		star3.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 3);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		star2.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					initSearch_option();
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 2);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			}
		);
		
		
		
		
		// set font
		star5.setFont(new Font("Dialog", Font.BOLD, 28));
		star4.setFont(new Font("Dialog", Font.BOLD, 28));
		star3.setFont(new Font("Dialog", Font.BOLD, 28));
		star2.setFont(new Font("Dialog", Font.BOLD, 28));
		pricehighText.setFont(new Font("Dialog", Font.BOLD, 28));
		pricelowText.setFont(new Font("Dialog", Font.BOLD, 28));
		backsearch.setFont(new Font("Dialog", Font.BOLD, 28));
		search_option.setLayout(new GridLayout(4, 1, 0, 0));
		search_option.setOpaque(false);
		search_option.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star.add(star5);
		star.add(star4);
		star.add(star3);
		star.add(star2);
		search_option.add(star);
		search_option.add(pricehighText);
		search_option.add(pricelowText);
		search_option.add(backsearch);
	}
	
	public DefaultTableModel makeHotellist(ArrayList<AvailableHotelRoom> _AHR) {
		DefaultTableModel tablemodel = new DefaultTableModel(heading, 0);
		// get data
		for (int i = 0; i < _AHR.size(); i++) {
			int id = _AHR.get(i).getHotelID(); // id
			int star = _AHR.get(i).getHotelStar(); // star
			String locality = _AHR.get(i).getLocality(); // locality
			String address = _AHR.get(i).getAddress(); // address
			int sroom = _AHR.get(i).getSingle(); // single room
			int droom = _AHR.get(i).getDouble(); // double room
			int qroom = _AHR.get(i).getQuad(); // quad room
			int price = main.CountSumPrice(_AHR.get(i)); // price
			String go = "Select"; // select
			Object[] data = { id, star, locality, address, sroom, droom, qroom, price, go };
			tablemodel.addRow(data);
		}
		return tablemodel;
	}
	
	public void showHotellist(DefaultTableModel tablemodel) {
		HotellistTable = new JTable(tablemodel) {
			public boolean isCellEditable(int row, int column) {
				if (column == 8) {
					return true;
				}
				return false;
			}

		};
		HotellistTable.setOpaque(false);
		JTableHeader head = HotellistTable.getTableHeader();
		head.setFont(new Font("Arial", Font.PLAIN, 20));

		// row height
		HotellistTable.setRowHeight(40);
		// column width
		HotellistTable.getColumnModel().getColumn(0).setMaxWidth(60); // id
		HotellistTable.getColumnModel().getColumn(1).setMaxWidth(50); // star
		HotellistTable.getColumnModel().getColumn(2).setMaxWidth(60); // locality
		HotellistTable.getColumnModel().getColumn(3).setMaxWidth(300); // address
		HotellistTable.getColumnModel().getColumn(4).setMaxWidth(70); // single room
		HotellistTable.getColumnModel().getColumn(5).setMaxWidth(70); // double room
		HotellistTable.getColumnModel().getColumn(6).setMaxWidth(70); // quad room
		HotellistTable.getColumnModel().getColumn(7).setMaxWidth(70); // price
		HotellistTable.getColumnModel().getColumn(8).setMaxWidth(70); // select
		// row color
		DefaultTableCellRenderer ter = new DefaultTableCellRenderer() {
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(new Color(248, 248, 255, 100));
				else if (row % 2 == 1)
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
		for (int i = 0; i <= 8; i++) {
			HotellistTable.getColumn(heading[i]).setCellRenderer(ter);
		}

		// build up Table
		JScrollPane HotellistJScrollPane = new JScrollPane(HotellistTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		ButtonColumn buttonsColumn = new ButtonColumn(HotellistTable, 8);

		// set 'back' and 'reserve' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backhotellist.setFont(new Font("Dialog", Font.BOLD, 25));
		reservehotellist.setFont(new Font("Dialog", Font.BOLD, 25));
		buttons.add(backhotellist);
		buttons.add(reservehotellist);

		star5.setFont(new Font("Dialog", Font.BOLD, 23));
		star4.setFont(new Font("Dialog", Font.BOLD, 23));
		star3.setFont(new Font("Dialog", Font.BOLD, 23));
		star2.setFont(new Font("Dialog", Font.BOLD, 23));
		pricehighText.setFont(new Font("Dialog", Font.BOLD, 23));
		pricelowText.setFont(new Font("Dialog", Font.BOLD, 23));
		showallText.setFont(new Font("Dialog", Font.BOLD, 23));

		JPanel choicepanel = new JPanel();
		choicepanel.setLayout(new GridLayout(2, 1));
		choicepanel.setOpaque(false);
		JPanel pricepanel = new JPanel();
		pricepanel.setLayout(new GridLayout(1, 3, 0, 0));
		pricepanel.setOpaque(false);
		pricepanel.add(pricehighText);
		pricepanel.add(pricelowText);
		pricepanel.add(showallText);
		choicepanel.add(star);
		choicepanel.add(pricepanel);

		Hotellist.removeAll();
		Hotellist.add(choicepanel, BorderLayout.NORTH);
		Hotellist.add(HotellistJScrollPane, BorderLayout.CENTER);
		Hotellist.add(buttons, BorderLayout.SOUTH);
	}



	public HotellistUI(String _CID, String _COD, int _People, int _Rooms) {
		CID = _CID;
		COD = _COD;
		People = _People;
		Rooms = _Rooms;
		initHotellist();
		initSearch_option();
		initLayerPane();
		initPanel();
	}
}

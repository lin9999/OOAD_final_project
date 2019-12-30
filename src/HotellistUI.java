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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class HotellistUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private String checkInDate, checkOutDate;
	private static int people, rooms, hotelID, sRoom, dRoom, qRoom;
	private ArrayList<AvailableHotelRoom> AHR;
	
	final private int hotellistWidth = 820, hotellistHeight = 500;
	final private Dimension hotellistCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel Hotellist = new JPanel();
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
		HotelPreference.background.setIcon(new ImageIcon("images/HotelPreference/hotelbackground.jpg"));
		HotelPreference.background.setBounds(0, 0, HotelPreference.frameWidth, HotelPreference.frameHeight);
		layeredPane.add(HotelPreference.background, new Integer(0));
		layeredPane.add(Hotellist, new Integer(1));
		add(layeredPane);
	}
	
	private DefaultTableModel generateHotellist(ArrayList<AvailableHotelRoom> _AHR) {
		String[] heading = new String[] { "ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price", "" };
		DefaultTableModel tablemodel = new DefaultTableModel(heading, 0);
		// get data
		for (int i = 0 ; i < _AHR.size(); i++) {
			int id = _AHR.get(i).getHotelID(); // id
			int star = _AHR.get(i).getHotelStar(); // star
			String locality = _AHR.get(i).getLocality(); // locality
			String address = _AHR.get(i).getAddress(); // address
			int sroom = _AHR.get(i).getSingle(); // single room
			int droom = _AHR.get(i).getDouble(); // double room
			int qroom = _AHR.get(i).getQuad(); // quad room
			int price = Search.CountSumPrice(_AHR.get(i)); // price
			String select = "Select";
			Object[] data = {id, star, locality, address, sroom, droom, qroom, price, select};
			tablemodel.addRow(data);
		}
		return tablemodel;
	}
	
	private JScrollPane generateHotelListScrollPane(DefaultTableModel tableModel) {
		JTable HotelListTable = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return column == 8;
			}
		};
		HotelListTable.setOpaque(false);
		JTableHeader head = HotelListTable.getTableHeader();
		head.setFont(new Font("Arial", Font.PLAIN, 20));
	
		// row height
		HotelListTable.setRowHeight(40);
		
		// column width
		HotelListTable.getColumnModel().getColumn(0).setMaxWidth(60); // id
		HotelListTable.getColumnModel().getColumn(1).setMaxWidth(50); // star
		HotelListTable.getColumnModel().getColumn(2).setMaxWidth(60); // locality
		HotelListTable.getColumnModel().getColumn(3).setMaxWidth(300); // address
		HotelListTable.getColumnModel().getColumn(4).setMaxWidth(70); // single room
		HotelListTable.getColumnModel().getColumn(5).setMaxWidth(70); // double room
		HotelListTable.getColumnModel().getColumn(6).setMaxWidth(70); // quad room
		HotelListTable.getColumnModel().getColumn(7).setMaxWidth(70); // price
		HotelListTable.getColumnModel().getColumn(8).setMaxWidth(70); // select
		
		// row color
		DefaultTableCellRenderer ter = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;
			
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				if (row % 2 == 0)
					setBackground(new Color(248, 248, 255, 100));
				else if (row % 2 == 1)
					setBackground(Color.white);
				return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			}
		};
		String[] heading = new String[] {"ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price", ""};
		for (int i = 0; i < heading.length; i++)
			HotelListTable.getColumn(heading[i]).setCellRenderer(ter);
		new ButtonColumn(HotelListTable, 8);
		
		// build up Table
		return new JScrollPane(HotelListTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	private void initHotellist() {
		Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
				hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);
		
		Hotellist.setLayout(new BorderLayout());
		Hotellist.setOpaque(false);
		
		JPanel choicepanel = new JPanel();
		
		JPanel pricepanel = new JPanel();
		JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
		JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
		
		pricepanel.setLayout(new GridLayout(1, 2, 0, 0));
		pricepanel.setOpaque(false);
		
		pricehighText.setFont(new Font("Dialog", Font.BOLD, 23));
		pricelowText.setFont(new Font("Dialog", Font.BOLD, 23));
		
		pricehighText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(Search.SortByPrice(AHR, 0));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					pricehighText.setForeground(Color.black);
				}
			});
		
		pricelowText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(Search.SortByPrice(AHR, 1));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					pricelowText.setForeground(Color.black);
				}
			}
		);
		
		pricepanel.add(pricehighText);
		pricepanel.add(pricelowText);
		
		JPanel star = new JPanel();
		JLabel star5Text = new JLabel("5-Star", JLabel.CENTER);
		JLabel star4Text = new JLabel("4-Star", JLabel.CENTER);
		JLabel star3Text = new JLabel("3-Star", JLabel.CENTER);
		JLabel star2Text = new JLabel("2-Star", JLabel.CENTER);
		
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star5Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star4Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star3Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star2Text.setFont(new Font("Dialog", Font.BOLD, 23));

		star5Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(SearchByStar(AHR, 5));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					star5Text.setForeground(Color.black);
				}
			}
		);
		
		star4Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(SearchByStar(AHR, 4));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					star4Text.setForeground(Color.black);
				}
			}
		);
		
		star3Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(SearchByStar(AHR, 3));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					star3Text.setForeground(Color.black);
				}
			}
		);
		
		star2Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					DefaultTableModel dtm = generateHotellist(SearchByStar(AHR, 2));
					BorderLayout layout = (BorderLayout)Hotellist.getLayout();
					Hotellist.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					Hotellist.add(generateHotelListScrollPane(dtm), BorderLayout.CENTER);
					validate();
					repaint();
					star2Text.setForeground(Color.black);
				}
			}
		);

		star.add(star5Text);
		star.add(star4Text);
		star.add(star3Text);
		star.add(star2Text);
		
		choicepanel.setLayout(new GridLayout(2, 1));
		choicepanel.setOpaque(false);
		choicepanel.add(star);
		choicepanel.add(pricepanel);

		// set 'back' and 'reserve' button
		JPanel buttons = new JPanel();
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);

		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backText.setFont(new Font("Dialog", Font.BOLD, 25));
		reserveText.setFont(new Font("Dialog", Font.BOLD, 25));
		buttons.add(backText);
		buttons.add(reserveText);
		
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotellistUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotellistUI.this);
					root.setContentPane(new SearchUI(checkInDate, checkOutDate, people, rooms));
					backText.setForeground(Color.BLACK);
				}
			});
		
		reserveText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					JFrame root = (JFrame) SwingUtilities.getRoot(HotellistUI.this);
					if (sRoom + dRoom + qRoom == 0) {
						JOptionPane.showMessageDialog(root, "NO SCHEME WAS SELECTED", "Warning", JOptionPane.ERROR_MESSAGE);
					} else {
						HotellistUI.this.setVisible(false);
						root.setContentPane(new ReserveUI(checkInDate, checkOutDate, people, rooms, hotelID, sRoom ,dRoom, qRoom));
						reserveText.setForeground(Color.BLACK);
					}
				}
			});
		
		Hotellist.removeAll();
		Hotellist.add(choicepanel, BorderLayout.NORTH);
		Hotellist.add(generateHotelListScrollPane(generateHotellist(AHR)), BorderLayout.CENTER);
		Hotellist.add(buttons, BorderLayout.SOUTH);
	}

	private ArrayList<AvailableHotelRoom> SearchByStar(ArrayList<AvailableHotelRoom> AHR, int Star) {
		ArrayList<AvailableHotelRoom> nAHR = new ArrayList<AvailableHotelRoom>();
		for (AvailableHotelRoom ahr : AHR)
			if (ahr.getHotelStar() == Star)
				nAHR.add(ahr);
		return nAHR;
	}
	
	public HotellistUI(String _checkInDate, String _checkOutDate, int _People, int _Rooms, ArrayList<AvailableHotelRoom> _AHR) {
		checkInDate = _checkInDate;
		checkOutDate = _checkOutDate;
		people = _People;
		rooms = _Rooms;
		hotelID = 0;
		sRoom = 0;
		dRoom = 0;
		qRoom = 0;
		AHR = new ArrayList<AvailableHotelRoom>();
		for(AvailableHotelRoom i : _AHR)
		    AHR.add(i);
		initPanel();
		initHotellist();
		initLayerPane();
	}
	
	public static void setHotelID(int _hotelID) { 
		hotelID = _hotelID;
	}
	public static void setSRoom(int _sRoom) { 
		sRoom = _sRoom;
	}
	public static void setDRoom(int _dRoom) { 
		dRoom = _dRoom;
	}
	public static void setQRoom(int _qRoom) { 
		qRoom = _qRoom;
	}
}


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class HotellistUI extends JPanel{
	
	private String cid, cod;
	private int people, rooms;
	private ArrayList<AvailableHotelRoom> AHR;
	static Object hid, sroom, droom, qroom;
	
	final private int hotellistWidth = 820, hotellistHeight = 500;
	final private Dimension hotellistCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel Hotellist = new JPanel();
	
	// attribute for search option (star5/4/3/2, price high/low)
	private JLabel star5Text = new JLabel("5-star", JLabel.CENTER);
	private JLabel star4Text = new JLabel("4-star", JLabel.CENTER);
	private JLabel star3Text = new JLabel("3-star", JLabel.CENTER);
	private JLabel star2Text = new JLabel("2-star", JLabel.CENTER);
	private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
	private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
	private JLabel backText = new JLabel("BACK", JLabel.CENTER);
	private JLabel reservehotellist = new JLabel("RESERVE", JLabel.CENTER);
	
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
//		layeredPane.add(search_option, new Integer(1));
		DefaultTableModel dtm = makeHotellist(AHR);
		showHotellist(dtm);
		layeredPane.add(Hotellist, new Integer(1));
		add(layeredPane);
	}
	
	private void initHotellist() {
		Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
				hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);
		
		Hotellist.setLayout(new BorderLayout());
		Hotellist.setOpaque(false);
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotellistUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotellistUI.this);
					root.setContentPane(new SearchUI(cid, cod, people, rooms));
					backText.setForeground(Color.BLACK);
				}
			}
		);
		reservehotellist.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					HotellistUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(HotellistUI.this);
	//				 TODO 
					root.setContentPane(new ReserveUI(cid, cod, people, rooms, hid, sroom ,droom, qroom));
					reservehotellist.setForeground(Color.BLACK);
				}
			}
		);
	}
	
	private void arrangeHotellist() {
		ArrayList<AvailableHotelRoom> star5AHR = SearchByStar(AHR, 5);
		ArrayList<AvailableHotelRoom> star4AHR = SearchByStar(AHR, 4);
		ArrayList<AvailableHotelRoom> star3AHR = SearchByStar(AHR, 3);
		ArrayList<AvailableHotelRoom> star2AHR = SearchByStar(AHR, 2);

		pricehighText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
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
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					ArrayList<AvailableHotelRoom> nAHR = main.SortByPrice(AHR, 1);
					DefaultTableModel dtm = makeHotellist(nAHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					pricelowText.setForeground(Color.black);
				}
			}
		);
		star5Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					DefaultTableModel dtm = makeHotellist(star5AHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					star5Text.setForeground(Color.black);
				}
			}
		);
		star4Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					DefaultTableModel dtm = makeHotellist(star4AHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					star4Text.setForeground(Color.black);
				}
			}
		);
		star3Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					DefaultTableModel dtm = makeHotellist(star3AHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					star3Text.setForeground(Color.black);
				}
			}
		);
		star2Text.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
//					layeredPane.remove(search_option);
					layeredPane.remove(Hotellist);
					DefaultTableModel dtm = makeHotellist(star2AHR);
					showHotellist(dtm);
					layeredPane.add(Hotellist, new Integer(3));
					validate();
					repaint();
					star2Text.setForeground(Color.black);
				}
			}
		);
	}
	
	private DefaultTableModel makeHotellist(ArrayList<AvailableHotelRoom> _AHR) {
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
			int price = main.CountSumPrice(_AHR.get(i)); // price
			String go = "Select"; // select
			Object[] data = { id, star, locality, address, sroom, droom, qroom, price, go };
			tablemodel.addRow(data);
		}
		return tablemodel;
	}
	
	private void showHotellist(DefaultTableModel tablemodel) {
		JTable HotellistTable = new JTable(tablemodel) {
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
		String[] heading = new String[] { "ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price", "" };
		for (int i = 0; i <= 8; i++) {
			HotellistTable.getColumn(heading[i]).setCellRenderer(ter);
		}

		// build up Table
		JScrollPane HotellistJScrollPane = new JScrollPane(HotellistTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		ButtonColumns buttonsColumn = new ButtonColumns(HotellistTable, 8);

		// set 'back' and 'reserve' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backText.setFont(new Font("Dialog", Font.BOLD, 25));
		reservehotellist.setFont(new Font("Dialog", Font.BOLD, 25));
		buttons.add(backText);
		buttons.add(reservehotellist);

		star5Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star4Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star3Text.setFont(new Font("Dialog", Font.BOLD, 23));
		star2Text.setFont(new Font("Dialog", Font.BOLD, 23));
		pricehighText.setFont(new Font("Dialog", Font.BOLD, 23));
		pricelowText.setFont(new Font("Dialog", Font.BOLD, 23));
//		showallText.setFont(new Font("Dialog", Font.BOLD, 23));

		JPanel choicepanel = new JPanel();
		choicepanel.setLayout(new GridLayout(2, 1));
		choicepanel.setOpaque(false);
		JPanel star = new JPanel();
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star.add(star5Text);
		star.add(star4Text);
		star.add(star3Text);
		star.add(star2Text);
		JPanel pricepanel = new JPanel();
		pricepanel.setLayout(new GridLayout(1, 2, 0, 0));
		pricepanel.setOpaque(false);
		pricepanel.add(pricehighText);
		pricepanel.add(pricelowText);
//		pricepanel.add(showallText);
		choicepanel.add(star);
		choicepanel.add(pricepanel);

		Hotellist.removeAll();
		Hotellist.add(choicepanel, BorderLayout.NORTH);
		Hotellist.add(HotellistJScrollPane, BorderLayout.CENTER);
		Hotellist.add(buttons, BorderLayout.SOUTH);
	}

	private ArrayList<AvailableHotelRoom> SearchByStar(ArrayList<AvailableHotelRoom> AHR, int Star) {
		ArrayList<AvailableHotelRoom> nAHR = new ArrayList<AvailableHotelRoom>();
		for (AvailableHotelRoom ahr : AHR)
			if (ahr.getHotelStar() == Star)
				nAHR.add(ahr);
		return nAHR;
	}
	
	public HotellistUI(String _CID, String _COD, int _People, int _Rooms, ArrayList<AvailableHotelRoom> _AHR) {
//		AHR =_AHR;
		cid = _CID;
		cod = _COD;
		people = _People;
		rooms = _Rooms;
		AHR = new ArrayList<AvailableHotelRoom>();
		for(AvailableHotelRoom i : _AHR) {
		    AHR.add(i);
		}
		initPanel();
		initHotellist();
		initLayerPane();
		arrangeHotellist();
	}
	
}

class ButtonColumns extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;

	public ButtonColumns(JTable table, int column) {
		super();
		this.table = table;
		renderButton = new JButton();
		editButton = new JButton();
		editButton.setFocusPainted(false);
		editButton.addActionListener(this);

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
		columnModel.getColumn(column).setCellEditor(this);
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (hasFocus) {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));

		} else if (isSelected) {
			renderButton.setForeground(table.getSelectionForeground());
			renderButton.setBackground(table.getSelectionBackground());
		} else {
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}
		renderButton.setText((value == null) ? " " : value.toString());
		return renderButton;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		text = (value == null) ? " " : value.toString();
		editButton.setText(text);
		return editButton;
	}

	public Object getCellEditorValue() {
		return text;
	}

	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		HotellistUI.hid = table.getModel().getValueAt(table.getSelectedRow(), 0);
		HotellistUI.sroom = table.getModel().getValueAt(table.getSelectedRow(), 4);
		HotellistUI.droom = table.getModel().getValueAt(table.getSelectedRow(), 5);
		HotellistUI.qroom = table.getModel().getValueAt(table.getSelectedRow(), 6);
//		Menu.reservehotelid.setSelectedIndex((int) hid);
//		Menu.reservesingleroomField.setText(sroom.toString());
//		Menu.reservedoubleroomField.setText(droom.toString());
//		Menu.reservequadroomField.setText(qroom.toString());
	}
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
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

public class ModifyUI extends JPanel{
	private static final long serialVersionUID = 1L;
	
	final private int revisedateWidth = 820, revisedateHeight = 500;
	final private Dimension revisedateCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JPanel modify = new JPanel();
	private JLayeredPane layeredPane = new JLayeredPane();
	Order order;
	
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
		layeredPane.add(modify, new Integer(1));
		add(layeredPane);
	}
	
	private void initModify() {
		// attribute of revise date		
		JTextField origincheckindateField = new JTextField(7); // 原日期
		JTextField origincheckoutdateField = new JTextField(7);
		JTextField newcheckindateField = new JTextField(10); // 新日期
		JTextField newcheckoutdateField = new JTextField(10);
		// attribute of change room
		JTextField originsingleroomField = new JTextField(2); // 原房間數
		JTextField origindoubleroomField = new JTextField(2);
		JTextField originquadroomField = new JTextField(2);
		JTextField newsingleroomField = new JTextField(2); // 新房間數
		JTextField newdoubleroomField = new JTextField(2);
		JTextField newquadroomField = new JTextField(2);
				
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					ModifyUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(ModifyUI.this);
					root.setContentPane(new ShowOrderUI(order));
					backText.setForeground(Color.BLACK);
				}
			}
		);
		JLabel nextText = new JLabel("NEXT", JLabel.CENTER);
		nextText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					String nCID = newcheckindateField.getText();
					String nCOD = newcheckoutdateField.getText();
					int osn = Integer.parseInt(originsingleroomField.getText());
					int odn = Integer.parseInt(origindoubleroomField.getText());
					int oqn = Integer.parseInt(originquadroomField.getText());
					// 取得修改後的房間數
					int nsn = Integer.parseInt(newsingleroomField.getText());
					int ndn = Integer.parseInt(newdoubleroomField.getText());
					int nqn = Integer.parseInt(newquadroomField.getText());
					if (RoomChecker.CountDaysBetween(nCID, nCOD) > 0) {
						int OrderID = order.getID();
						if (Inquiry.CheckDateforReviseDate(OrderID, nCID, nCOD)) { // check the date first
							Order newOrder = Inquiry.ModifyDate(OrderID, nCID, nCOD);
							System.out.println(newOrder.getCheckInDate() + ", " + newOrder.getCheckOutDate());
							if (nsn > osn || ndn > odn || nqn > oqn) {
//								 change room error 修改房間數失敗 不可增加房間
								JFrame root = (JFrame) SwingUtilities.getRoot(ModifyUI.this);
								JOptionPane.showMessageDialog(root, "ADDING ROOMS IS NOT ALLOWED", "Warning", JOptionPane.ERROR_MESSAGE);
							} else {
								newOrder = Inquiry.ChangeRooms(newOrder.getID(), nsn, ndn, nqn);
								System.out.println(newOrder.getDnum().size());
								// if revise success 修改成功
								ModifyUI.this.setVisible(false);
								JFrame root = (JFrame) SwingUtilities.getRoot(ModifyUI.this);
								root.setContentPane(new ShowOrderUI(newOrder));
								nextText.setForeground(Color.black);
							}
						} else {
							// show error message if revise date error (日期只能縮短不能延長)
							JFrame root = (JFrame) SwingUtilities.getRoot(ModifyUI.this);
							JOptionPane.showMessageDialog(root, "EXTENDING DAYS IS NOT ALLOWED", "Warning", JOptionPane.ERROR_MESSAGE);
							nextText.setForeground(Color.black);
						}
					} else { 
						// show error message (日期格式錯誤) (check in 比 check out晚)
						JFrame root = (JFrame) SwingUtilities.getRoot(ModifyUI.this);
						JOptionPane.showMessageDialog(root, "INVALID DATE", "Warning", JOptionPane.ERROR_MESSAGE);
						nextText.setForeground(Color.black);
					}

				}
			}
		);
		
		String ocid = order.getCheckInDate();
		String ocod = order.getCheckOutDate();
		origincheckindateField.setText(ocid);
		origincheckoutdateField.setText(ocod);
		newcheckindateField.setText(ocid);
		newcheckoutdateField.setText(ocod);
		
		Integer x = order.getSnum().size();
		Integer y = order.getDnum().size();
		Integer z = order.getQnum().size();
		originsingleroomField.setText(x.toString());
		origindoubleroomField.setText(y.toString());
		originquadroomField.setText(z.toString());
		newsingleroomField.setText(x.toString());
		newdoubleroomField.setText(y.toString());
		newquadroomField.setText(z.toString());

		//-------FOR---GUI---SETTING----------------------------------
		modify.setBounds(revisedateCenter.width - (revisedateWidth / 2),
				revisedateCenter.height - (revisedateHeight / 2), revisedateWidth, revisedateHeight);
		modify.setLayout(new GridLayout(6, 1, 0, 0));
		modify.setOpaque(false);
		modify.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		// original date
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setOpaque(false);
		panel1.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change1 = new JLabel("CHANGE DATE FROM");
		change1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to = new JLabel("~");
		to.setFont(new Font("Arial Black", Font.PLAIN, 20));
		origincheckindateField.setHorizontalAlignment(SwingConstants.CENTER);
		origincheckindateField.setEditable(false);
		origincheckindateField.setFont(new Font("Serif", Font.PLAIN, 23));
		origincheckoutdateField.setHorizontalAlignment(SwingConstants.CENTER);
		origincheckoutdateField.setEditable(false);
		origincheckoutdateField.setFont(new Font("Serif", Font.PLAIN, 23));
		// panel1 adding
		panel1.add(change1);
		panel1.add(origincheckindateField);
		panel1.add(to);
		panel1.add(origincheckoutdateField);
		// new date
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setOpaque(false);
		panel2.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change2 = new JLabel("TO");
		change2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to2 = new JLabel("~");
		to2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		newcheckindateField.setHorizontalAlignment(SwingConstants.CENTER);
		newcheckindateField.setEditable(true);
		newcheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
//		newcheckindateField.setText("SELECT DATE");
		newcheckindateField.setBounds(267, 15, 105, 40);
		newcheckindateField.setColumns(10);
		newcheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(newcheckindateField);
				DP.showDialog();
			}
		});
		// setting check out yyyy/mm/dd
		newcheckoutdateField.setHorizontalAlignment(SwingConstants.CENTER);
		newcheckoutdateField.setEditable(true);
		newcheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
//		newcheckoutdateField.setText("SELECT DATE");
		newcheckoutdateField.setBounds(267, 15, 105, 40);
		newcheckoutdateField.setColumns(10);
		newcheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(newcheckoutdateField);
				DP.showDialog();
			}
		});
		// panel2 adding
		panel2.add(change2);
		panel2.add(newcheckindateField);
		panel2.add(to2);
		panel2.add(newcheckoutdateField);
		
		// single
		JPanel panel1_ = new JPanel();
		panel1_.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1_.setOpaque(false);
		panel1_.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change1_ = new JLabel("CHANGE SINGLE ROOM FROM");
		change1_.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to_ = new JLabel("to");
		to_.setFont(new Font("Arial Black", Font.PLAIN, 20));
		originsingleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		originsingleroomField.setEditable(false);
		originsingleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newsingleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		newsingleroomField.setEditable(true);
		newsingleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newsingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel1_.add(change1_);
		panel1_.add(originsingleroomField);
		panel1_.add(to_);
		panel1_.add(newsingleroomField);
		// double
		JPanel panel2_ = new JPanel();
		panel2_.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2_.setOpaque(false);
		panel2_.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change2_ = new JLabel("CHANGE DOUBLE ROOM FROM");
		change2_.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to2_ = new JLabel("to");
		to2_.setFont(new Font("Arial Black", Font.PLAIN, 20));
		origindoubleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		origindoubleroomField.setEditable(false);
		origindoubleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newdoubleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		newdoubleroomField.setEditable(true);
		newdoubleroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newdoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel2_.add(change2_);
		panel2_.add(origindoubleroomField);
		panel2_.add(to2_);
		panel2_.add(newdoubleroomField);
		// quad room
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setOpaque(false);
		panel3.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change3 = new JLabel("CHANGE QUAD ROOM FROM");
		change3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to3 = new JLabel("to");
		to3.setFont(new Font("Arial Black", Font.PLAIN, 20));
		originquadroomField.setHorizontalAlignment(SwingConstants.CENTER);
		originquadroomField.setEditable(false);
		originquadroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newquadroomField.setHorizontalAlignment(SwingConstants.CENTER);
		newquadroomField.setEditable(true);
		newquadroomField.setFont(new Font("Arial Black", Font.BOLD, 23));
		newquadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		panel3.add(change3);
		panel3.add(originquadroomField);
		panel3.add(to3);
		panel3.add(newquadroomField);
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backText.setFont(new Font("Arial Black", Font.PLAIN, 20));
//		backchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextText.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backText);
//		buttons.add(backchangeroom);
		buttons.add(nextText);
		// adding panel
		modify.add(panel1);
		modify.add(panel2);
		modify.add(panel1_);
		modify.add(panel2_);
		modify.add(panel3);
		modify.add(buttons);
	}

	public ModifyUI(Order _order) {
		order = _order;
		initModify();
		initLayerPane();
		initPanel();
	}

}

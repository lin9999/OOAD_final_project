import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * @author b06505032, b06505017, b06505054, b06902023
 *
 */
public class Menu extends JPanel {
	private JLayeredPane layeredPane;
	private JLabel background = new JLabel();
	final static int frameWidth = 1152, frameHeight = 720;
		
	// attribute of sign in error - UNKNOWN ID
	private JPanel Signinerror = new JPanel();
	final private int signinerrorWidth = 700, signinerrorHeight = 110;
	final private Dimension signinerrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signinerrorText = new JLabel("UNKNOWN ID.", JLabel.CENTER);
	private JLabel backsigninerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign in error - WRONG PASSWORD
	private JPanel Signinerror1 = new JPanel();
	final private int signinerror1Width = 700, signinerror1Height = 110;
	final private Dimension signinerror1Center = new Dimension(frameWidth / 2, 500);
	private JLabel signinerror1Text = new JLabel("WRONG PASSWORD.", JLabel.CENTER);
	private JLabel backsigninerror1 = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - USERID ALREADY EXISTS.
	private JPanel Signuperror = new JPanel();
	final private int signuperrorWidth = 500, signuperrorHeight = 110;
	final private Dimension signuperrorCenter = new Dimension(frameWidth / 2, 500);
	private JLabel signuperrorText = new JLabel("USER ID ALREADY EXISTS.", JLabel.CENTER);
	private JLabel backsignuperror = new JLabel("BACK", JLabel.CENTER);

	// attribute of sign up error - WRONG VERIFY CODE.
	private JPanel Signuperror1 = new JPanel();
	final private int signuperror1Width = 500, signuperror1Height = 110;
	final private Dimension signuperrorCenter1 = new Dimension(frameWidth / 2, 500);
	private JLabel signuperror1Text = new JLabel("WRONG VERIFY CODE.", JLabel.CENTER);
	private JLabel backsignuperror1 = new JLabel("BACK", JLabel.CENTER);

	protected JTextField signupidField = new JTextField(13);
	protected JPasswordField signuppasswordField = new JPasswordField(13);
	protected JTextField usercodeField = new JTextField(5);
	protected JLabel verifycodeField = new JLabel("");

	// attribute of Hotel function Search/Reserve/Inquiry
	private JPanel Hotelfunction = new JPanel();
	final private int hotelfunctionWidth = 500, hotelfunctionHeight = 200;
	final private Dimension hotelfunctionCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel searchText = new JLabel("SEARCH", JLabel.CENTER);
	private JLabel reserveText = new JLabel("RESERVE", JLabel.CENTER);
	private JLabel inquiryText = new JLabel("INQUIRY", JLabel.CENTER);
	private JLabel logout = new JLabel("LOGOUT", JLabel.CENTER);

	// attribute of entering Search Date, People, Rooms
	private JPanel EnterSearch = new JPanel();
	final private int entersearchWidth = 600, entersearchlistHeight = 300;
	final private Dimension entersearchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backentersearch = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextentersearch = new JLabel("NEXT", JLabel.CENTER);
	protected JTextField entercheckindateField = new JTextField(10);
	protected JTextField entercheckoutdateField = new JTextField(10);
	protected JTextField enterpeopleField = new JTextField(10);
	protected JTextField enterroomField = new JTextField(10);

	// attribute of invalid date error
	private JPanel Invalid_date_error = new JPanel();
	final private int invaiddateerrorWidth = 300, invaliddateerrorHeight = 75;
	final private Dimension invaliddateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	private JLabel invaliddateerrorText = new JLabel("INVALID DATE!", JLabel.CENTER);

	// attribute of no matched hotel error
	private JPanel No_matched_hotel_error = new JPanel();
	final private int nomatchedhotelerrorWidth = 500, nomatchedhotelerrorHeight = 150;
	final private Dimension nomatchedhotelerrorCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel nomatchedhotelerrorText = new JLabel("NO MATCHED HOTEL!", JLabel.CENTER);
	private JLabel backnomatchedhotelerror = new JLabel("BACK", JLabel.CENTER);

	// attribute of Search
	private JPanel Search = new JPanel();
	final private int searchWidth = 570, searchHeight = 240;
	final private Dimension searchCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	JPanel star = new JPanel();
	private JLabel star5 = new JLabel("5-star", JLabel.CENTER);
	private JLabel star4 = new JLabel("4-star", JLabel.CENTER);
	private JLabel star3 = new JLabel("3-star", JLabel.CENTER);
	private JLabel star2 = new JLabel("2-star", JLabel.CENTER);
	private JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
	private JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
	private JLabel backsearch = new JLabel("BACK", JLabel.CENTER);

	// attribute of Reserve
	private JPanel Reserve = new JPanel();
	final private int reserveWidth = 620, reserveHeight = 300;
	final private Dimension reserveCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JPanel reservebuttons = new JPanel();
	private JLabel cancelreserve = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backreserve = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextreserve = new JLabel("NEXT", JLabel.CENTER);
	protected static JComboBox<Object> reservehotelid = new JComboBox<Object>();
	protected JTextField reservecheckindateField = new JTextField(10);
	protected JTextField reservecheckoutdateField = new JTextField(10);
	protected static JTextField reservesingleroomField = new JTextField(2);
	protected static JTextField reservedoubleroomField = new JTextField(2);
	protected static JTextField reservequadroomField = new JTextField(2);

	// attribute of reserve error (sold out)
	private JPanel Soldout = new JPanel();
	final private int soldoutWidth = 700, soldoutHeight = 150;
	final private Dimension soldoutCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel soldoutText = new JLabel("Sorry, NO VACANT SUITES!", JLabel.CENTER);
	private JLabel backsoldout = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve success
	private JPanel Reserve_success = new JPanel();
	final private int reservesuccessWidth = 600, reservesuccessHeight = 75;
	final private Dimension reservesuccessCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	protected JTextField successreservenumberField = new JTextField(10);

	// attribute of inquiry
	private JPanel Inquiry = new JPanel();
	final private int InquiryWidth = 600, InquiryHeight = 150;
	final private Dimension InquiryCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel backinquiry = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextinquiry = new JLabel("NEXT", JLabel.CENTER);
	protected TextField reservenumberField = new TextField(15);

	// attribute of wrong reservation number
	private JPanel Wrong_reservation_number = new JPanel();
	final private int wrongreservationnumberWidth = 700, wrongreservationnumberHeight = 150;
	final private Dimension wrongreservationnumberCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel wrongreservationnumberText = new JLabel("WRONG RESERVATION NUMBER!", JLabel.CENTER);
	private JLabel backwrongreservationnumber = new JLabel("BACK", JLabel.CENTER);

	// attribute of reserve order (reservation record and modify and cancel
	// reservation)
	private JPanel Reserveorder = new JPanel();
	final private int reserveorderWidth = 650, reserveorderHeight = 360;
	final private Dimension reserveorderCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel cancelText = new JLabel("CANCEL ORDER", JLabel.CENTER);
	private JLabel modifyText = new JLabel("MODIFY", JLabel.CENTER);
	private JLabel confirmText = new JLabel("CONFIRM", JLabel.CENTER);
	protected JTextField reserveorderhotelIDField = new JTextField(15);
	protected JTextField reserveordersingleroomField = new JTextField(2);
	protected JTextField reserveorderdoubleroomField = new JTextField(2);
	protected JTextField reserveorderquadroomField = new JTextField(2);
	protected JTextField reserveordercheckindateField = new JTextField(10);
	protected JTextField reserveordercheckoutdateField = new JTextField(10);
	protected JTextField reserveorderstaynightField = new JTextField(2);
	protected JTextField reserveorderpriceField = new JTextField(5);
	JPanel reserveorderbuttons = new JPanel();

	// attribute of hotel list
	private JPanel Hotellist = new JPanel();
	final private int hotellistWidth = 820, hotellistHeight = 500;
	final private Dimension hotellistCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	JTable HotellistTable = new JTable();
	String[] heading = new String[] { "ID", "Star", "City", "Address", "Single", "Double", "Quad", "Price", "Select" };
	private JLabel showallText = new JLabel("SHOW   ALL", JLabel.CENTER);
	private JLabel backhotellist = new JLabel("BACK", JLabel.CENTER);
	private JLabel reservehotellist = new JLabel("RESERVE", JLabel.CENTER);

	// attribute of modify
	private JPanel Modify = new JPanel();
	final private int modifyWidth = 300, modifyHeight = 200;
	final private Dimension modifyCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel changeroomText = new JLabel("CHANGE ROOM", JLabel.CENTER);
	private JLabel revisedateText = new JLabel("REVISE DATE", JLabel.CENTER);

	// attribute of change room
	private JPanel Changeroom = new JPanel();
	final private int changeroomWidth = 600, changeroomHeight = 240;
	final private Dimension changeroomCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	protected JTextField originsingleroomField = new JTextField(2); // 原房間數
	protected JTextField origindoubleroomField = new JTextField(2);
	protected JTextField originquadroomField = new JTextField(2);
	protected JTextField newsingleroomField = new JTextField(2); // 新房間數
	protected JTextField newdoubleroomField = new JTextField(2);
	protected JTextField newquadroomField = new JTextField(2);
	private JLabel cancelchangeroom = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backchangeroom = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextchangeroom = new JLabel("NEXT", JLabel.CENTER);

	// attribute of revise date
	private JPanel Revisedate = new JPanel();
	final private int revisedateWidth = 820, revisedateHeight = 180;
	final private Dimension revisedateCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	protected JTextField origincheckindateField = new JTextField(7); // 原日期
	protected JTextField origincheckoutdateField = new JTextField(7);
	protected JTextField newcheckindateField = new JTextField(10); // 新日期
	protected JTextField newcheckoutdateField = new JTextField(10);
	private JLabel cancelrevisedate = new JLabel("CANCEL", JLabel.CENTER);
	private JLabel backrevisedate = new JLabel("BACK", JLabel.CENTER);
	private JLabel nextrevisedate = new JLabel("NEXT", JLabel.CENTER);

	// attribute of reduce room / revise date success
	private JPanel ChangeRevise_success = new JPanel();
	final private int changerevisesuccessWidth = 800, changerevisesuccessHeight = 75;
	final private Dimension changerevisesuccessCenter = new Dimension(frameWidth / 2, frameHeight / 5);
	private JLabel changerevisesuccessText = new JLabel("THE FOLOWING IS YOUR REVISED BOOKING ORDER.", JLabel.CENTER);

	// attribute of cancel order success
	private JPanel Cancelorder_success = new JPanel();
	final private int cancelordersuccessWidth = 800, cancelordersuccessHeight = 150;
	final private Dimension cancelordersuccessCenter = new Dimension(frameWidth / 2, frameHeight / 2);
	private JLabel cancelordersuccessText = new JLabel("YOUR ORDER HAS BEEN CANCELED!", JLabel.CENTER);
	private JLabel confirmcancelordersuccess = new JLabel("CONFIRM", JLabel.CENTER);

	// change room error
	private JPanel Changeroom_error = new JPanel();
	final private int changeroomerrorWidth = 800, changeroomerrorHeight = 75;
	final private Dimension changeroomerrorCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel changeroomerrorText = new JLabel("SORRY, ADDING ROOMS IS UNAVAILABLE!!", JLabel.CENTER);

	// reduce room error
	private JPanel Revisedate_error = new JPanel();
	final private int revisedateerrorWidth = 800, revisedateerrorHeight = 75;
	final private Dimension revisedateerrorCenter = new Dimension(frameWidth / 2, frameHeight / 4);
	private JLabel revisedateerrorText = new JLabel("SORRY, EXTENDING LODGE DAYS IS UNAVAILABLE!", JLabel.CENTER);

	/**
	 * Initialize Menu(Panel) settings
	 */
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
	}

	/**
	 * Initialize the title Panel
	 */
	private void initTitle() {
		titleText.setFont(new Font("Brush Script MT", Font.BOLD, 96));
		signinText.setFont(new Font("Arial Black", Font.BOLD, 30));
		signupText.setFont(new Font("Arial Black", Font.BOLD, 30));
		title.setLayout(new GridLayout(1, 1, 0, 0));
		title.setOpaque(false);
		titleText.setForeground(new Color(65, 105, 225));
		titleText.setOpaque(false);
		titleText.setBorder(new EmptyBorder(5, 5, 5, 5));
		title.add(titleText);
	}

	/**
	 * initialize Sign in error panel
	 */
	private void initSigninerror() {
		signinerrorText.setFont(new Font("Dialog", Font.BOLD, 28));
		signinerrorText.setForeground(new Color(255, 0, 0));
		backsigninerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror.setOpaque(false);
		Signinerror.add(signinerrorText);
		Signinerror.add(backsigninerror);
	}

	/**
	 * initialize Sign in error1 panel
	 */
	private void initSigninerror1() {
		signinerror1Text.setFont(new Font("Dialog", Font.BOLD, 28));
		signinerror1Text.setForeground(new Color(255, 0, 0));
		backsigninerror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signinerror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signinerror1.setOpaque(false);
		Signinerror1.add(signinerror1Text);
		Signinerror1.add(backsigninerror1);
	}

	/**
	 * Initialize sign up error panel
	 */
	private void initSignuperror() {
		signuperrorText.setFont(new Font("Dialog", Font.BOLD, 28));
		signuperrorText.setForeground(new Color(255, 0, 0));
		backsignuperror.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror.setOpaque(false);
		Signuperror.add(signuperrorText);
		Signuperror.add(backsignuperror);
	}

	private void initSignuperror1() {
		signuperror1Text.setFont(new Font("Dialog", Font.BOLD, 28));
		signuperror1Text.setForeground(new Color(255, 0, 0));
		backsignuperror1.setFont(new Font("Arial Black", Font.BOLD, 28));
		Signuperror1.setLayout(new GridLayout(2, 1, 0, 0));
		Signuperror1.setOpaque(false);
		Signuperror1.add(signuperror1Text);
		Signuperror1.add(backsignuperror1);
	}

	/**
	 * Initialize the Sign in Panel
	 */
	private void initSignIn() {
		Signin.setLayout(new GridLayout(3, 1));
		Signin.setOpaque(false);

		// enter ID Panel setting
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		IDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter ID
		JLabel ID = new JLabel("       ID       : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinidField = new JTextField(10) {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		signinidField.setEditable(true);
		signinidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		signinidField.setBackground(new Color(232, 232, 232, 120));
		// ID Panel adding
		IDPanel.add(ID);
		IDPanel.add(signinidField);

		// enter password Panel setting
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		passwordPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter password
		JLabel password = new JLabel("PASSWORD : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signinpasswordField = new JPasswordField(10) {
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		signinpasswordField.setEditable(true);
		signinpasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		signinpasswordField.setBackground(new Color(232, 232, 232, 120));
		// whether to show the password or not
		signinpasswordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		AbstractDocument doc = (AbstractDocument) signinpasswordField.getDocument();
		doc.setDocumentFilter(new DocumentFilter());
		AbstractButton b = new JToggleButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton c = (AbstractButton) e.getSource();
				Character ec = c.isSelected() ? 0 : (Character) UIManager.get("PasswordField.echoChar");
				signinpasswordField.setEchoChar(ec);
			}
		});
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
		b.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b.setAlignmentY(Component.CENTER_ALIGNMENT);
		b.setIcon(new ColorIcon(Color.GREEN));
		b.setRolloverIcon(new ColorIcon(Color.GRAY));
		b.setSelectedIcon(new ColorIcon(Color.RED));
		b.setRolloverSelectedIcon(new ColorIcon(Color.GRAY));
		JPanel panel = new JPanel() {
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		panel.setLayout(new OverlayLayout(panel));
		panel.setOpaque(false);
		panel.add(b);
		panel.add(signinpasswordField);
		// password Panel adding
		passwordPanel.add(password);
		passwordPanel.add(panel);

		// set 'back' and 'login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		signinlogin.setFont(new Font("Arial Black", Font.PLAIN, 18));
		signinback.setFont(new Font("Arial Black", Font.PLAIN, 18));
		signinlogin.setOpaque(false);
		signinback.setOpaque(false);
		buttons.add(signinback);
		buttons.add(signinlogin);

		// sign in adding
		Signin.add(IDPanel);
		Signin.add(passwordPanel);
		Signin.add(buttons);
	}

	/**
	 * Initialize Sign up Panel
	 */
	private void initSignUp() {
		Signup.setLayout(new GridLayout(4, 1));
		Signup.setOpaque(false);

		// ID Panel
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		IDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel ID = new JLabel("         ID          ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signupidField = new JTextField(10) {
			@Override
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			@Override
			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		signupidField.setEditable(true);
		signupidField.setFont(new Font("Arial Black", Font.BOLD, 23));
		signupidField.setBackground(new Color(232, 232, 232, 120));
		IDPanel.add(ID);
		IDPanel.add(signupidField);

		// password panel
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		passwordPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel password = new JLabel("PASSWORD    ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		signuppasswordField = new JPasswordField(10) {
			protected void paintComponent(Graphics g) {
				if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
					Graphics2D g2 = (Graphics2D) g.create();
					g2.setPaint(getBackground());
					g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(0, 0, getWidth() - 1, getHeight() - 1));
					g2.dispose();
				}
				super.paintComponent(g);
			}

			public void updateUI() {
				super.updateUI();
				setOpaque(false);
				setBorder(new RoundedCornerBorder());
			}
		};
		signuppasswordField.setEditable(true);
		signuppasswordField.setFont(new Font("Arial Black", Font.BOLD, 23));
		signuppasswordField.setBackground(new Color(232, 232, 232, 120));
		// whether to show the password or not
		signuppasswordField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		AbstractDocument doc = (AbstractDocument) signuppasswordField.getDocument();
		doc.setDocumentFilter(new DocumentFilter());
		AbstractButton b = new JToggleButton(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton c = (AbstractButton) e.getSource();
				Character ec = c.isSelected() ? 0 : (Character) UIManager.get("PasswordField.echoChar");
				signuppasswordField.setEchoChar(ec);
			}
		});
		b.setFocusable(false);
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		b.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 4));
		b.setAlignmentX(Component.RIGHT_ALIGNMENT);
		b.setAlignmentY(Component.CENTER_ALIGNMENT);
		b.setIcon(new ColorIcon(Color.GREEN));
		b.setRolloverIcon(new ColorIcon(Color.GRAY));
		b.setSelectedIcon(new ColorIcon(Color.RED));
		b.setRolloverSelectedIcon(new ColorIcon(Color.GRAY));
		JPanel panel = new JPanel() {
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		panel.setLayout(new OverlayLayout(panel));
		panel.setOpaque(false);
		panel.add(b);
		panel.add(signuppasswordField);
		// password panel adding
		passwordPanel.add(password);
		passwordPanel.add(panel);

		// verify code Panel
		JPanel verifycodePanel = new JPanel();
		verifycodePanel.setOpaque(false);
		verifycodePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		verifycodePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter verify code
		JLabel verifycode = new JLabel("VERIFY CODE        ");
		verifycode.setFont(new Font("Arial Black", Font.PLAIN, 20));
		usercodeField.setEditable(true);
		usercodeField.setFont(new Font("Times New Roman", Font.BOLD, 23));
		usercodeField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				String s = usercodeField.getText();
				if (s.length() >= 6)
					e.consume();
			}
		});
		usercodeField.setBackground(new Color(232, 232, 232, 120));
		verifycodeField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		// verify code panel adding
		verifycodePanel.add(verifycode);
		verifycodePanel.add(usercodeField);
		verifycodePanel.add(verifycodeField);

		// set 'cancel' and 'sign up and login' button
		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		signuplogin.setFont(new Font("Arial Black", Font.PLAIN, 18));
		signupcancel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		buttons.add(signupcancel);
		buttons.add(signuplogin);

		// sign up adding
		Signup.add(IDPanel);
		Signup.add(passwordPanel);
		Signup.add(verifycodePanel);
		Signup.add(buttons);

	}

	/**
	 * Initialize hotel function Panel SEARCH/RESERCE/INQUIRY/LOGOUT
	 */
	private void initHotelfunction() {
		searchText.setFont(new Font("Dialog", Font.BOLD, 36));
		reserveText.setFont(new Font("Dialog", Font.BOLD, 36));
		inquiryText.setFont(new Font("Dialog", Font.BOLD, 36));
		logout.setFont(new Font("Dialog", Font.BOLD, 36));
		Hotelfunction.setLayout(new GridLayout(2, 2, 0, 0));
		Hotelfunction.setOpaque(false);
		Hotelfunction.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Hotelfunction.add(searchText);
		Hotelfunction.add(reserveText);
		Hotelfunction.add(inquiryText);
		Hotelfunction.add(logout);
	}

	/**
	 * Initialize enter hotel list date/people/rooms Panel
	 */
	private void initEnterSearch() {
		EnterSearch.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		EnterSearch.setLayout(new GridLayout(5, 1));
		EnterSearch.setOpaque(false);
		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setOpaque(false);
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckindateField.setHorizontalAlignment(SwingConstants.CENTER);
		entercheckindateField.setEditable(false);
		entercheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckindateField.setBackground(new Color(255, 255, 255));
		entercheckindateField.setText("SELECT A DATE");
		entercheckindateField.setOpaque(true);
		entercheckindateField.setBounds(267, 15, 105, 40);
		entercheckindateField.setColumns(10);
		entercheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(entercheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setOpaque(false);
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		entercheckoutdateField.setHorizontalAlignment(SwingConstants.CENTER);
		entercheckoutdateField.setEditable(false);
		entercheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		entercheckoutdateField.setBackground(new Color(255, 255, 255));
		entercheckoutdateField.setText("SELECT A DATE");
		entercheckoutdateField.setOpaque(true);
		entercheckoutdateField.setBounds(267, 15, 105, 40);
		entercheckoutdateField.setColumns(10);
		entercheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(entercheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(entercheckoutdateField);

		// people panel
		JPanel peoplePanel = new JPanel();
		peoplePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		peoplePanel.setOpaque(false);
		peoplePanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel people = new JLabel("NUMBER OF PEOPLE: ");
		people.setFont(new Font("Arial Black", Font.PLAIN, 20));
		enterpeopleField.setHorizontalAlignment(SwingConstants.CENTER);
		enterpeopleField.setEditable(true);
		enterpeopleField.setFont(new Font("Serif", Font.BOLD, 23));
		enterpeopleField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// people panel adding
		peoplePanel.add(people);
		peoplePanel.add(enterpeopleField);

		// room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setOpaque(false);
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel room = new JLabel("NUMBER OF ROOMS: ");
		room.setFont(new Font("Arial Black", Font.PLAIN, 20));
		enterroomField.setHorizontalAlignment(SwingConstants.CENTER);
		enterroomField.setEditable(true);
		enterroomField.setFont(new Font("Serif", Font.BOLD, 23));
		enterroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(room);
		roomPanel.add(enterroomField);
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 2));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextentersearch.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(backentersearch);
		buttons.add(nextentersearch);
		// EnterHotellist adding
		EnterSearch.add(checkinPanel);
		EnterSearch.add(checkoutPanel);
		EnterSearch.add(peoplePanel);
		EnterSearch.add(roomPanel);
		EnterSearch.add(buttons);
	}

	/**
	 * Initialize invalid date Panel
	 */
	private void initEnterinvaliddateerror() {
		Invalid_date_error.setLayout(new GridLayout(1, 1, 0, 0));
		Invalid_date_error.setOpaque(false);
		invaliddateerrorText.setFont(new Font("Dialog", Font.BOLD, 30));
		invaliddateerrorText.setForeground(new Color(255, 0, 0));
		Invalid_date_error.add(invaliddateerrorText);
	}

	/**
	 * Initialize Search Panel
	 */
	private void initSearch() {
		// set font
		star5.setFont(new Font("Dialog", Font.BOLD, 28));
		star4.setFont(new Font("Dialog", Font.BOLD, 28));
		star3.setFont(new Font("Dialog", Font.BOLD, 28));
		star2.setFont(new Font("Dialog", Font.BOLD, 28));
		pricehighText.setFont(new Font("Dialog", Font.BOLD, 28));
		pricelowText.setFont(new Font("Dialog", Font.BOLD, 28));
		backsearch.setFont(new Font("Dialog", Font.BOLD, 28));
		Search.setLayout(new GridLayout(4, 1, 0, 0));
		Search.setOpaque(false);
		Search.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		star.setLayout(new GridLayout(1, 4, 0, 0));
		star.setOpaque(false);
		star.add(star5);
		star.add(star4);
		star.add(star3);
		star.add(star2);
		Search.add(star);
		Search.add(pricehighText);
		Search.add(pricelowText);
		Search.add(backsearch);
	}

	/**
	 * Initialize hotellist Panel
	 */
	private void initHotellist() {
		Hotellist.setLayout(new BorderLayout());
		Hotellist.setOpaque(false);
		backhotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
		reservehotellist.setFont(new Font("Arial Black", Font.BOLD, 30));
	}

	/**
	 * Initialize search hotel error (No matched Hotel) Panel
	 */
	private void initNomatchedhotelerror() {
		nomatchedhotelerrorText.setFont(new Font("Dialog", Font.BOLD, 28));
		nomatchedhotelerrorText.setForeground(new Color(255, 0, 0));
		nomatchedhotelerrorText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backnomatchedhotelerror.setFont(new Font("Arial Black", Font.BOLD, 28));
		backnomatchedhotelerror.setBorder(new EmptyBorder(20, 40, 20, 40));
		No_matched_hotel_error.setLayout(new GridLayout(2, 1, 0, 0));
		No_matched_hotel_error.setOpaque(false);
		No_matched_hotel_error.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		No_matched_hotel_error.add(nomatchedhotelerrorText);
		No_matched_hotel_error.add(backnomatchedhotelerror);
	}

	/**
	 * Initialize Reserve Panel
	 */
	private void initReserve() {
		Reserve.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Reserve.setLayout(new GridLayout(5, 1));
		Reserve.setOpaque(false);

		// check in date panel
		JPanel checkinPanel = new JPanel();
		checkinPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkinPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkinPanel.setOpaque(false);
		// enter check in date
		JLabel checkin = new JLabel("  CHECK IN DATE: ");
		checkin.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckindateField.setHorizontalAlignment(SwingConstants.CENTER);
		reservecheckindateField.setEditable(false);
		reservecheckindateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckindateField.setBackground(new Color(255, 255, 255));
		reservecheckindateField.setText("SELECT DATE");
		reservecheckindateField.setOpaque(true);
		reservecheckindateField.setBounds(267, 15, 105, 40);
		reservecheckindateField.setColumns(10);
		reservecheckindateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckindateField);
				DP.showDialog();
			}
		});
		// check in panel adding
		checkinPanel.add(checkin);
		checkinPanel.add(reservecheckindateField);

		// check out date panel
		JPanel checkoutPanel = new JPanel();
		checkoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		checkoutPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		checkoutPanel.setOpaque(false);
		// enter check out date
		JLabel checkout = new JLabel("  CHECK OUT DATE: ");
		checkout.setFont(new Font("Arial Black", Font.PLAIN, 20));
		// setting check in yyyy/mm/dd
		reservecheckoutdateField.setHorizontalAlignment(SwingConstants.CENTER);
		reservecheckoutdateField.setEditable(false);
		reservecheckoutdateField.setFont(new Font("Serif", Font.BOLD, 23));
		reservecheckoutdateField.setBackground(new Color(255, 255, 255));
		reservecheckoutdateField.setText("SELECT DATE");
		reservecheckoutdateField.setOpaque(true);
		reservecheckoutdateField.setBounds(267, 15, 105, 40);
		reservecheckoutdateField.setColumns(10);
		reservecheckoutdateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DatePopup DP = new DatePopup(reservecheckoutdateField);
				DP.showDialog();
			}
		});
		// check out panel adding
		checkoutPanel.add(checkout);
		checkoutPanel.add(reservecheckoutdateField);

		// hotelID Panel
		JPanel hotelIDPanel = new JPanel();
		hotelIDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		hotelIDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		hotelIDPanel.setOpaque(false);
		// select hotel ID
		JLabel hotelID = new JLabel("    HotelID     : ");
		hotelID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		String[] option = new String[1500];
		for (Integer i = 0; i < 1500; i++) {
			option[i] = i.toString();
		}
		reservehotelid = new JComboBox<Object>(option);
		hotelIDPanel.add(hotelID);
		hotelIDPanel.add(reservehotelid);

		// number of room panel
		JPanel roomPanel = new JPanel();
		roomPanel.setLayout(new GridLayout(1, 6));
		roomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		roomPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		roomPanel.setOpaque(false);
		// single room
		JLabel singleroom = new JLabel("Single: ");
		singleroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservesingleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reservesingleroomField.setEditable(true);
		reservesingleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservesingleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
		reservedoubleroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reservedoubleroomField.setEditable(true);
		reservedoubleroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservedoubleroomField.addKeyListener(new KeyAdapter() {// can only enter number!
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
		reservequadroomField.setHorizontalAlignment(SwingConstants.CENTER);
		reservequadroomField.setEditable(true);
		reservequadroomField.setFont(new Font("Serif", Font.BOLD, 23));
		reservequadroomField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// room panel adding
		roomPanel.add(singleroom);
		roomPanel.add(reservesingleroomField);
		roomPanel.add(doubleroom);
		roomPanel.add(reservedoubleroomField);
		roomPanel.add(quadroom);
		roomPanel.add(reservequadroomField);

		// setting 'back' and 'next' buttons
		reservebuttons.setLayout(new GridLayout(1, 3));
		reservebuttons.setOpaque(false);
		cancelreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextreserve.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservebuttons.add(cancelreserve);
//		reservebuttons.add(backreserve);
		reservebuttons.add(nextreserve);

		// Reserve adding Panel
		Reserve.add(checkinPanel);
		Reserve.add(checkoutPanel);
		Reserve.add(hotelIDPanel);
		Reserve.add(roomPanel);
		Reserve.add(reservebuttons);
	}

	/**
	 * initialize Reserve success Panel
	 */
	private void initReservesuccess() {
		Reserve_success.setLayout(new GridLayout(1, 1, 0, 0));
		Reserve_success.setOpaque(false);
		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel reservesuccessText = new JLabel("SUCCEED! THANKS FOR YOUR BOOKING!");
		reservesuccessText.setFont(new Font("Dialog", Font.BOLD, 25));
		reservenumberPanel.add(reservesuccessText);
		Reserve_success.add(reservenumberPanel);
	}

	/**
	 * Initialize sold out Panel
	 */
	private void initSoldout() {
		soldoutText.setFont(new Font("Dialog", Font.BOLD, 28));
		soldoutText.setForeground(new Color(255, 0, 0));
		soldoutText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backsoldout.setFont(new Font("Arial Black", Font.BOLD, 28));
		backsoldout.setBorder(new EmptyBorder(20, 40, 20, 40));
		Soldout.setLayout(new GridLayout(2, 1, 0, 0));
		Soldout.setOpaque(false);
		Soldout.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Soldout.add(soldoutText);
		Soldout.add(backsoldout);
	}

	/**
	 * Initialize inquiry Panel
	 */
	private void initInquiry() {
		Inquiry.setLayout(new GridLayout(2, 1, 0, 0));
		Inquiry.setOpaque(false);
		Inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

		// enter reserve number
		JPanel reservenumberPanel = new JPanel();
		reservenumberPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		reservenumberPanel.setOpaque(false);
		reservenumberPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		JLabel reservenumber = new JLabel("RESERVATION NUMBER : ");
		reservenumber.setFont(new Font("Arial Black", Font.PLAIN, 20));
		reservenumberField.setFont(new Font("Serif", Font.BOLD, 23));
		reservenumberField.addKeyListener(new KeyAdapter() {// can only enter number!
			public void keyTyped(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (!(keyChar >= '0' && keyChar <= '9')) {
					e.consume();
				}
			}
		});
		// reservenumberPanel adding
		reservenumberPanel.add(reservenumber);
		reservenumberPanel.add(reservenumberField);

		// set 'back' and 'next' buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		backinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		nextinquiry.setFont(new Font("Arial Black", Font.PLAIN, 23));
		buttons.add(backinquiry);
		buttons.add(nextinquiry);

		// inquiry adding
		Inquiry.add(reservenumberPanel);
		Inquiry.add(buttons);
	}

	/**
	 * Initialize wrong reservation number panel
	 */
	private void initWrongreservationnumber() {
		wrongreservationnumberText.setFont(new Font("Dialog", Font.BOLD, 28));
		wrongreservationnumberText.setForeground(new Color(255, 0, 0));
		wrongreservationnumberText.setBorder(new EmptyBorder(20, 40, 20, 40));
		backwrongreservationnumber.setFont(new Font("Arial Black", Font.BOLD, 28));
		backwrongreservationnumber.setBorder(new EmptyBorder(20, 40, 20, 40));
		Wrong_reservation_number.setLayout(new GridLayout(2, 1, 0, 0));
		Wrong_reservation_number.setOpaque(false);
		Wrong_reservation_number.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Wrong_reservation_number.add(wrongreservationnumberText);
		Wrong_reservation_number.add(backwrongreservationnumber);
	}

	/**
	 * Initialize reserve order Panel
	 */
	private void initReserveorder() {
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

		// MCR adding
		Reserveorder.add(reservenumberPanel);
		Reserveorder.add(hotelIDPanel);
		Reserveorder.add(lodgingPanel);
		Reserveorder.add(roomPanel);
		Reserveorder.add(staypricePanel);
		Reserveorder.add(reserveorderbuttons);
	}

	/**
	 * initialize modify (reduce room or revise date) panel
	 */
	private void initModify() {
		changeroomText.setFont(new Font("Arial Black", Font.BOLD, 28));
		revisedateText.setFont(new Font("Arial Black", Font.BOLD, 28));
		Modify.setLayout(new GridLayout(2, 1, 0, 0));
		Modify.setOpaque(false);
		Modify.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		Modify.add(changeroomText);
		Modify.add(revisedateText);
	}

	/**
	 * initialize change room panel
	 */
	private void initChangeroom() {
		Changeroom.setLayout(new GridLayout(4, 1, 0, 0));
		Changeroom.setOpaque(false);
		Changeroom.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		// single
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setOpaque(false);
		panel1.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change1 = new JLabel("CHANGE SINGLE ROOM FROM");
		change1.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to = new JLabel("to");
		to.setFont(new Font("Arial Black", Font.PLAIN, 20));
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
		panel1.add(change1);
		panel1.add(originsingleroomField);
		panel1.add(to);
		panel1.add(newsingleroomField);
		// double
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setOpaque(false);
		panel2.setBorder(new EmptyBorder(10, 30, 10, 30));
		JLabel change2 = new JLabel("CHANGE DOUBLE ROOM FROM");
		change2.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JLabel to2 = new JLabel("to");
		to2.setFont(new Font("Arial Black", Font.PLAIN, 20));
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
		panel2.add(change2);
		panel2.add(origindoubleroomField);
		panel2.add(to2);
		panel2.add(newdoubleroomField);
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
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		cancelchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextchangeroom.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(cancelchangeroom);
		buttons.add(backchangeroom);
		buttons.add(nextchangeroom);
		// adding panel
		Changeroom.add(panel1);
		Changeroom.add(panel2);
		Changeroom.add(panel3);
		Changeroom.add(buttons);
	}

	/**
	 * Initialize revise date Panel
	 */
	private void initRevisedate() {
		Revisedate.setLayout(new GridLayout(3, 1, 0, 0));
		Revisedate.setOpaque(false);
		Revisedate.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
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
		newcheckindateField.setText("SELECT DATE");
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
		newcheckoutdateField.setText("SELECT DATE");
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
		// set 'back' and 'next' button
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1, 3));
		buttons.setOpaque(false);
		buttons.setBorder(new EmptyBorder(20, 40, 20, 40));
		cancelrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		backrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nextrevisedate.setFont(new Font("Arial Black", Font.PLAIN, 20));
		buttons.add(cancelrevisedate);
		buttons.add(backrevisedate);
		buttons.add(nextrevisedate);
		// revise date adding
		Revisedate.add(panel1);
		Revisedate.add(panel2);
		Revisedate.add(buttons);
	}

	/**
	 * initialize change room / revise date success panel
	 */
	private void initChangeRevisesuccess() {
		ChangeRevise_success.setLayout(new GridLayout(1, 1, 0, 0));
		ChangeRevise_success.setOpaque(false);
		changerevisesuccessText.setFont(new Font("Dialog", Font.BOLD, 28));
		changerevisesuccessText.setForeground(new Color(70, 130, 180));
		ChangeRevise_success.add(changerevisesuccessText);
	}

	/**
	 * initialize change room error panel
	 */
	private void initChangeroomerror() {
		Changeroom_error.setLayout(new GridLayout(1, 1, 0, 0));
		Changeroom_error.setOpaque(false);
		changeroomerrorText.setFont(new Font("Dialog", Font.BOLD, 30));
		changeroomerrorText.setForeground(new Color(255, 0, 0));
		Changeroom_error.add(changeroomerrorText);
	}

	/**
	 * Initialize revise date error
	 */
	private void initRevisedateerror() {
		Revisedate_error.setLayout(new GridLayout(1, 1, 0, 0));
		Revisedate_error.setOpaque(false);
		revisedateerrorText.setFont(new Font("Dialog", Font.BOLD, 30));
		revisedateerrorText.setForeground(new Color(255, 0, 0));
		Revisedate_error.add(revisedateerrorText);
	}

	/**
	 * Initialize cancel order success Panel
	 */
	private void initcancelordersuccess() {
		Cancelorder_success.setLayout(new GridLayout(2, 1, 0, 0));
		Cancelorder_success.setOpaque(false);
		Cancelorder_success.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));
		cancelordersuccessText.setFont(new Font("Dialog", Font.BOLD, 30));
		cancelordersuccessText.setForeground(new Color(70, 130, 180));
		confirmcancelordersuccess.setFont(new Font("Arial", Font.BOLD, 30));
		Cancelorder_success.add(cancelordersuccessText);
		Cancelorder_success.add(confirmcancelordersuccess);
	}

	/**
	 * to show the reserve order on the Panel
	 * 
	 * @param oid order id to be shown on the panel
	 * @param hid hotel id to be shown on the panel
	 * @param sroom single room to be shown on the panel
	 * @param droom double room to be shown on the panel
	 * @param qroom quad room to be shown on the panel
	 * @param chkindate check in date to be shown on the panel
	 * @param chkoutdate check out date to be shown on the panel
	 * @param night staying night to be shown on the panel
	 * @param p total price to be shown on the panel
	 */
	public void showReserveorder(int oid, int hid, int sroom, int droom, int qroom, String chkindate, String chkoutdate,
			int night, int p) {
		successreservenumberField.setText(Integer.toString(oid));
		reserveorderhotelIDField.setText(Integer.toString(hid));
		reserveordersingleroomField.setText(Integer.toString(sroom));
		reserveorderdoubleroomField.setText(Integer.toString(droom));
		reserveorderquadroomField.setText(Integer.toString(qroom));
		reserveordercheckindateField.setText(chkindate);
		reserveordercheckoutdateField.setText(chkoutdate);
		reserveorderstaynightField.setText(Integer.toString(night));
		reserveorderpriceField.setText(Integer.toString(p));
	}

	/**
	 * to show the reserve success message and the reserve order 
	 * 
	 * @param OrderID order id to be shown on the panel
	 * @param hid hotel id to be shown on the panel
	 * @param sroom single room to be shown on the panel
	 * @param droom double room to be shown on the panel
	 * @param qroom quad room to be shown on the panel
	 * @param chkindate check in date to be shown on the panel
	 * @param chkoutdate check out date to be shown on the panel
	 * @param night staying night to be shown on the panel
	 * @param p price to be shown on the panel
	 */
	public void showReservesuccess(int OrderID, int hid, int sroom, int droom, int qroom, String chkindate,
			String chkoutdate, int night, int p) {
		showReserveorder(OrderID, hid, sroom, droom, qroom, chkindate, chkoutdate, night, p);
		layeredPane.add(Reserve_success, new Integer(3));
		layeredPane.add(Reserveorder, new Integer(3));
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
	}

	/**
	 * clear all the Text when logout
	 */
	public void clearalltext() {
		signinidField.setText(null);
		signinpasswordField.setText(null);
		signupidField.setText(null);
		signuppasswordField.setText(null);
		usercodeField.setText(null);
		entercheckindateField.setText("SELECT DATE");
		entercheckoutdateField.setText("SELECT DATE");
		enterpeopleField.setText(null);
		enterroomField.setText(null);
		reserveorderhotelIDField.setText(null);
		reserveordersingleroomField.setText(null);
		reserveorderdoubleroomField.setText(null);
		reserveorderquadroomField.setText(null);
		reserveordercheckindateField.setText(null);
		reserveordercheckoutdateField.setText(null);
		reserveorderstaynightField.setText(null);
		reserveorderpriceField.setText(null);
		reservenumberField.setText(null);
		reservecheckindateField.setText("SELECT DATE");
		reservecheckoutdateField.setText("SELECT DATE");
		reservehotelid.setSelectedIndex(0);
		reservesingleroomField.setText(null);
		reservedoubleroomField.setText(null);
		reservequadroomField.setText(null);
		origincheckindateField.setText(null);
		origincheckoutdateField.setText(null);
		newcheckindateField.setText(null);
		newcheckoutdateField.setText(null);
		originsingleroomField.setText(null);
		origindoubleroomField.setText(null);
		originquadroomField.setText(null);
		newsingleroomField.setText(null);
		newdoubleroomField.setText(null);
		newquadroomField.setText(null);
	}
	
	/**
	 * set up a default table model for the hotel list
	 * 
	 * @param _AHR an array list which store all the hotel list
	 * @return returns a default table model 
	 */
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

	/**
	 * Setting the appearance of the default table model
	 * 
	 * @param tablemodel the default table model to be set the appearance
	 */
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

	/**
	 * initialize the sub menu panel
	 */
	private void initSubMenu() {
		subMenu.setLayout(new GridLayout(1, 2, 0, 0));
		subMenu.setOpaque(false);
		subMenu.setBackground(null);
		subMenu.add(signinText);
		subMenu.add(signupText);
	}

	/**
	 * Initialize the LayeredPane, setting the bounds and dimension of all Panel
	 */
	private void initLayerPane() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(frameWidth, frameHeight));

		this.background.setIcon(new ImageIcon("images/Menu/background.png"));
		this.background.setBounds(0, 0, frameWidth, frameHeight);
		layeredPane.add(background, new Integer(0));

		this.title.setBounds(titleCenter.width - (titleWidth / 2), titleCenter.height - (titleHeight / 2), titleWidth,
				titleHeight);
//		layeredPane.add(title, new Integer(1));

		this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
				subMenuWidth, subMenuHeight);
		layeredPane.add(subMenu, new Integer(2));

		this.add(layeredPane);

		this.Signin.setBounds(signinSetCenter.width - (signinSetWidth / 2),
				signinSetCenter.height - (signinSetHeight / 2), signinSetWidth, signinSetHeight);

		this.Signup.setBounds(signupSetCenter.width - (signupSetWidth / 2),
				signupSetCenter.height - (signupSetHeight / 2), signupSetWidth, signupSetHeight);

		this.Signinerror.setBounds(signinerrorCenter.width - (signinerrorWidth / 2),
				signinerrorCenter.height - (signinerrorHeight / 2), signinerrorWidth, signinerrorHeight);

		this.Signinerror1.setBounds(signinerror1Center.width - (signinerror1Width / 2),
				signinerror1Center.height - (signinerror1Height / 2), signinerror1Width, signinerror1Height);

		this.Signuperror.setBounds(signuperrorCenter.width - (signuperrorWidth / 2),
				signuperrorCenter.height - (signuperrorHeight / 2), signuperrorWidth, signuperrorHeight);

		this.Signuperror1.setBounds(signuperrorCenter1.width - (signuperror1Width / 2),
				signuperrorCenter1.height - (signuperror1Height / 2), signuperror1Width, signuperror1Height);

		this.Hotelfunction.setBounds(hotelfunctionCenter.width - (hotelfunctionWidth / 2),
				hotelfunctionCenter.height - (hotelfunctionHeight / 2), hotelfunctionWidth, hotelfunctionHeight);

		this.EnterSearch.setBounds(entersearchCenter.width - (entersearchWidth / 2),
				entersearchCenter.height - (entersearchlistHeight / 2), entersearchWidth, entersearchlistHeight);

		this.Hotellist.setBounds(hotellistCenter.width - (hotellistWidth / 2),
				hotellistCenter.height - (hotellistHeight / 2), hotellistWidth, hotellistHeight);

		this.Invalid_date_error.setBounds(invaliddateerrorCenter.width - (invaiddateerrorWidth / 2),
				invaliddateerrorCenter.height - (invaliddateerrorHeight / 2), invaiddateerrorWidth,
				invaliddateerrorHeight);

		this.No_matched_hotel_error.setBounds(nomatchedhotelerrorCenter.width - (nomatchedhotelerrorWidth / 2),
				nomatchedhotelerrorCenter.height - (nomatchedhotelerrorHeight / 2), nomatchedhotelerrorWidth,
				nomatchedhotelerrorHeight);

		this.Search.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);

		this.Reserve.setBounds(reserveCenter.width - (reserveWidth / 2), reserveCenter.height - (reserveHeight / 2),
				reserveWidth, reserveHeight);

		this.Reserve_success.setBounds(reservesuccessCenter.width - (reservesuccessWidth / 2),
				reservesuccessCenter.height - (reservesuccessHeight / 2), reservesuccessWidth, reservesuccessHeight);

		this.Soldout.setBounds(soldoutCenter.width - (soldoutWidth / 2), soldoutCenter.height - (soldoutHeight / 2),
				soldoutWidth, soldoutHeight);

		this.Inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
				InquiryWidth, InquiryHeight);

		this.Wrong_reservation_number.setBounds(wrongreservationnumberCenter.width - (wrongreservationnumberWidth / 2),
				wrongreservationnumberCenter.height - (wrongreservationnumberHeight / 2), wrongreservationnumberWidth,
				wrongreservationnumberHeight);

		this.Reserveorder.setBounds(reserveorderCenter.width - (reserveorderWidth / 2),
				reserveorderCenter.height - (reserveorderHeight / 2), reserveorderWidth, reserveorderHeight);

		this.Modify.setBounds(modifyCenter.width - (modifyWidth / 2), modifyCenter.height - (modifyHeight / 2),
				modifyWidth, modifyHeight);

		this.Changeroom.setBounds(changeroomCenter.width - (changeroomWidth / 2),
				changeroomCenter.height - (changeroomHeight / 2), changeroomWidth, changeroomHeight);

		this.Changeroom_error.setBounds(changeroomerrorCenter.width - (changeroomerrorWidth / 2),
				changeroomerrorCenter.height - (changeroomerrorHeight / 2), changeroomerrorWidth,
				changeroomerrorHeight);

		this.Revisedate.setBounds(revisedateCenter.width - (revisedateWidth / 2),
				revisedateCenter.height - (revisedateHeight / 2), revisedateWidth, revisedateHeight);

		this.Revisedate_error.setBounds(revisedateerrorCenter.width - (revisedateerrorWidth / 2),
				revisedateerrorCenter.height - (revisedateerrorHeight / 2), revisedateerrorWidth,
				revisedateerrorHeight);

		this.ChangeRevise_success.setBounds(changerevisesuccessCenter.width - (changerevisesuccessWidth / 2),
				changerevisesuccessCenter.height - (changerevisesuccessHeight / 2), changerevisesuccessWidth,
				changerevisesuccessHeight);

		this.Cancelorder_success.setBounds(cancelordersuccessCenter.width - (cancelordersuccessWidth / 2),
				cancelordersuccessCenter.height - (cancelordersuccessHeight / 2), cancelordersuccessWidth,
				cancelordersuccessHeight);

	}

	/**
	 * default constructor of Menu
	 */
	public Menu() {
		initPanel();
		initTitle();
		initSubMenu();
		initSignIn();
		initSignUp();
		initHotelfunction();
		initEnterSearch();
		initSearch();
		initReserve();
		initInquiry();
		initReserveorder();
		initLayerPane();
		initSigninerror();
		initSigninerror1();
		initSignuperror();
		initSignuperror1();
		initEnterinvaliddateerror();
		initNomatchedhotelerror();
		initWrongreservationnumber();
		initSoldout();
		initReservesuccess();
		initHotellist();
		initModify();
		initChangeroom();
		initRevisedate();
		initChangeRevisesuccess();
		initcancelordersuccess();
		initChangeroomerror();
		initRevisedateerror();
		// buttons in sub menu / sign in / sign up
		signinText.addMouseListener(ml);
		signupText.addMouseListener(ml);
		signinback.addMouseListener(ml);
		signinlogin.addMouseListener(ml);
		signupcancel.addMouseListener(ml);
		signuplogin.addMouseListener(ml);
		// buttons of sign in sign up error
		backsigninerror.addMouseListener(ml);
		backsigninerror1.addMouseListener(ml);
		backsignuperror.addMouseListener(ml);
		backsignuperror1.addMouseListener(ml);
		// buttons in hotel function
		searchText.addMouseListener(ml);
		reserveText.addMouseListener(ml);
		inquiryText.addMouseListener(ml);
		logout.addMouseListener(ml);
		// buttons in enter hotel list
		backentersearch.addMouseListener(ml);
		nextentersearch.addMouseListener(ml);
		// buttons in no matched hotel error
		backnomatchedhotelerror.addMouseListener(ml);
		// buttons in search
		star5.addMouseListener(ml);
		star4.addMouseListener(ml);
		star3.addMouseListener(ml);
		star2.addMouseListener(ml);
		pricehighText.addMouseListener(ml);
		pricelowText.addMouseListener(ml);
		backsearch.addMouseListener(ml);
		// buttons in hotel list
		showallText.addMouseListener(ml);
		backhotellist.addMouseListener(ml);
		reservehotellist.addMouseListener(ml);
		// buttons in reserve
		cancelreserve.addMouseListener(ml);
		backreserve.addMouseListener(ml);
		nextreserve.addMouseListener(ml);
		// buttons in sold out
		backsoldout.addMouseListener(ml);
		// buttons in inquiry
		backinquiry.addMouseListener(ml);
		nextinquiry.addMouseListener(ml);
		// buttons in wrong reservation number
		backwrongreservationnumber.addMouseListener(ml);
		// buttons in modify and cancel reservation
		modifyText.addMouseListener(ml);
		cancelText.addMouseListener(ml);
		confirmText.addMouseListener(ml);
		// buttons in modify
		changeroomText.addMouseListener(ml);
		revisedateText.addMouseListener(ml);
		// buttons in reduce room
		cancelchangeroom.addMouseListener(ml);
		backchangeroom.addMouseListener(ml);
		nextchangeroom.addMouseListener(ml);
		// buttons in revise date
		cancelrevisedate.addMouseListener(ml);
		backrevisedate.addMouseListener(ml);
		nextrevisedate.addMouseListener(ml);
		// buttons in cancel order succsee
		confirmcancelordersuccess.addMouseListener(ml);
	};

	/**
	 * control the mouse event
	 */
	MouseListener ml = new MouseAdapter() {
		public void mouseEntered(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			l.setForeground(Color.red);
		}

		public void mouseExited(MouseEvent e) {
			JLabel l = (JLabel) e.getSource();
			l.setForeground(Color.black);
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == signupText) {
				layeredPane.remove(subMenu);
				verifycodeField.setText(main.getRandomString(6));
				layeredPane.add(Signup, new Integer(2));
				validate();
				repaint();
				signupText.setForeground(Color.black);
			} else if (e.getSource() == signupcancel) {
				layeredPane.remove(Signup);
				layeredPane.add(subMenu, new Integer(2));
				signupidField.setText(null);
				signuppasswordField.setText(null);
				usercodeField.setText(null);
				validate();
				repaint();
				signupcancel.setForeground(Color.black);
			} else if (e.getSource() == signuplogin) {
				String UserID = signupidField.getText();
				String Password = new String(signuppasswordField.getPassword());
				String UserCode = usercodeField.getText(); // user enter verify code
				String VerifyCode = verifycodeField.getText(); // random verify code
				if (main.SignUpCheck(UserID)) {

					if (UserCode.equals(VerifyCode)) {
						// Create a new User
						main.user = new User(UserID, Password);
						databaseUtil.insertUser(main.user);

						layeredPane.remove(Signup);
						layeredPane.remove(title);
						background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
						layeredPane.add(Hotelfunction, new Integer(3));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					} else {// Wrong verify code.
						layeredPane.remove(Signup);
						layeredPane.add(Signuperror1, new Integer(3));
						signinidField.setText("");
						signinpasswordField.setText("");
						verifycodeField.setText(main.getRandomString(6));
						validate();
						repaint();
						signuplogin.setForeground(Color.black);
					}
				} else {// UserID already existed.
					layeredPane.remove(Signup);
					layeredPane.add(Signuperror, new Integer(3));
					signupidField.setText("");
					signuppasswordField.setText("");
					verifycodeField.setText(main.getRandomString(6));
					validate();
					repaint();
					signuplogin.setForeground(Color.black);
				}
			} else if (e.getSource() == signinText) {
				layeredPane.remove(subMenu);
				layeredPane.add(Signin, new Integer(2));
				validate();
				repaint();
				signinText.setForeground(Color.black);
			} else if (e.getSource() == signinback) {
				signinidField.setText(null);
				signinpasswordField.setText(null);
				layeredPane.remove(Signin);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				signinback.setForeground(Color.black);
			} else if (e.getSource() == signinlogin) {
				String UserID = signinidField.getText();
				String Password = new String(signinpasswordField.getPassword());
				int re = main.SignInCheck(UserID, Password);
				if (re == 1) {
					layeredPane.remove(Signin);
					layeredPane.remove(title);
					layeredPane.add(Hotelfunction, new Integer(2));
					background.setIcon(new ImageIcon("images/Menu/hotelbackground.jpg"));
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == 0) {
					// UserID doesn't exist.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				} else if (re == -1) {
					// Wrong Password.
					layeredPane.remove(Signin);
					layeredPane.add(Signinerror1, new Integer(2));
					signinidField.setText("");
					signinpasswordField.setText("");
					validate();
					repaint();
					signinlogin.setForeground(Color.black);
				}
			} else if (e.getSource() == backsigninerror || e.getSource() == backsigninerror1) {
				layeredPane.remove(Signinerror);
				layeredPane.remove(Signinerror1);
				layeredPane.add(Signin);
				signinidField.setText(null);
				signinpasswordField.setText(null);
				validate();
				repaint();
				backsigninerror.setForeground(Color.black);
				backsigninerror1.setForeground(Color.black);
			} else if (e.getSource() == backsignuperror || e.getSource() == backsignuperror1) {
				layeredPane.remove(Signuperror);
				layeredPane.remove(Signuperror1);
				layeredPane.add(Signup);
				signupidField.setText(null);
				signuppasswordField.setText(null);
				usercodeField.setText(null);
				validate();
				repaint();
				backsignuperror.setForeground(Color.black);
				backsignuperror1.setForeground(Color.black);
			} else if (e.getSource() == logout) {
				background.setIcon(new ImageIcon("images/Menu/background.png"));
				layeredPane.remove(Hotelfunction);
				layeredPane.remove(Signin);
				layeredPane.remove(Signup);
				layeredPane.add(subMenu, new Integer(2));
				validate();
				repaint();
				logout.setForeground(Color.black);
				// clear all the text field
				clearalltext();
			} else if (e.getSource() == searchText) {
				layeredPane.remove(Hotelfunction);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				searchText.setForeground(Color.black);
			} else if (e.getSource() == nextentersearch) {
				initSearch();
				String s1 = entercheckindateField.getText();
				String s2 = entercheckoutdateField.getText();
				if (main.CountDaysBetween(s1, s2) > 0) {
					String CID = entercheckindateField.getText();
					String COD = entercheckoutdateField.getText();
					int People = Integer.parseInt(enterpeopleField.getText());
					int Rooms = Integer.parseInt(enterroomField.getText());
					ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
					if (AHR.size() > 0) {
						layeredPane.remove(EnterSearch);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(Search, new Integer(3));
						reservebuttons.removeAll();
						reservebuttons.add(cancelreserve);
						reservebuttons.add(backreserve);
						reservebuttons.add(nextreserve);
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					} else {
						// no matched hotel
						layeredPane.remove(EnterSearch);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(No_matched_hotel_error, new Integer(3));
						validate();
						repaint();
						nextentersearch.setForeground(Color.black);
					}
				} else {// Invaid date
					layeredPane.add(Invalid_date_error, new Integer(3));
					entercheckindateField.setText("SELECT DATE");
					entercheckoutdateField.setText("SELECT DATE");
					enterpeopleField.setText(null);
					enterroomField.setText(null);
					validate();
					repaint();
					nextentersearch.setForeground(Color.black);
				}
			} else if (e.getSource() == showallText) {
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				DefaultTableModel dtm = makeHotellist(AHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				showallText.setForeground(Color.black);
			} else if (e.getSource() == pricehighText) { // show price high first
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SortByPrice(AHR, 0);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				pricehighText.setForeground(Color.black);
			} else if (e.getSource() == pricelowText) { // show price low first
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SortByPrice(AHR, 1);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				pricelowText.setForeground(Color.black);
			} else if (e.getSource() == star5) { // show star 5 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 5);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star5.setForeground(Color.black);
			} else if (e.getSource() == star4) { // show star 4 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 4);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star4.setForeground(Color.black);
			} else if (e.getSource() == star3) { // show star 3 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 3);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(3));
				validate();
				repaint();
				star3.setForeground(Color.black);
			} else if (e.getSource() == star2) { // show star 2 hotel
				layeredPane.remove(Search);
				layeredPane.remove(Hotellist);
				initSearch();

				String CID = entercheckindateField.getText();
				String COD = entercheckoutdateField.getText();
				int People = Integer.parseInt(enterpeopleField.getText());
				int Rooms = Integer.parseInt(enterroomField.getText());
				ArrayList<AvailableHotelRoom> AHR = main.SearchAvailableHotels(CID, COD, People, Rooms);
				ArrayList<AvailableHotelRoom> nAHR = main.SearchByStar(AHR, 2);
				DefaultTableModel dtm = makeHotellist(nAHR);
				showHotellist(dtm);

				layeredPane.add(Hotellist, new Integer(2));
				validate();
				repaint();
				star2.setForeground(Color.black);
			} else if (e.getSource() == backhotellist) {
				initSearch();
				layeredPane.remove(Hotellist);
				layeredPane.add(EnterSearch);
				validate();
				repaint();
				backhotellist.setForeground(Color.black);
			} else if (e.getSource() == reservehotellist) {
				reservecheckindateField.setText(entercheckindateField.getText());
				reservecheckoutdateField.setText(entercheckoutdateField.getText());
				layeredPane.remove(Hotellist);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				reservehotellist.setForeground(Color.black);
			} else if (e.getSource() == backsearch) {
				layeredPane.remove(Search);
				layeredPane.add(EnterSearch, new Integer(3));
				validate();
				repaint();
				backsearch.setForeground(Color.black);
			} else if (e.getSource() == backnomatchedhotelerror) {
				layeredPane.remove(No_matched_hotel_error);
				layeredPane.add(EnterSearch, new Integer(3));
				entercheckindateField.setText("SELECT DATE");
				entercheckoutdateField.setText("SELECT DATE");
				enterpeopleField.setText("0");
				enterroomField.setText("0");
				validate();
				repaint();
				backnomatchedhotelerror.setForeground(Color.black);
			} else if (e.getSource() == backentersearch || e.getSource() == backinquiry
					|| e.getSource() == cancelreserve || e.getSource() == cancelchangeroom
					|| e.getSource() == cancelrevisedate) {
				layeredPane.remove(EnterSearch);
				layeredPane.remove(Inquiry);
				layeredPane.remove(Reserve);
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.remove(Invalid_date_error);
				layeredPane.remove(Changeroom);
				layeredPane.remove(Revisedate);
				layeredPane.remove(Changeroom_error);
				layeredPane.remove(Revisedate_error);
				reservebuttons.removeAll();
				reservebuttons.add(cancelreserve);
				reservebuttons.add(nextreserve);
				layeredPane.add(Hotelfunction);
				validate();
				repaint();
				backentersearch.setForeground(Color.black);
				backinquiry.setForeground(Color.black);
				confirmText.setForeground(Color.black);
				cancelreserve.setForeground(Color.black);
				cancelchangeroom.setForeground(Color.black);
				cancelrevisedate.setForeground(Color.black);
			} else if (e.getSource() == confirmText || e.getSource() == confirmcancelordersuccess) {
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.remove(ChangeRevise_success);
				layeredPane.remove(Cancelorder_success);

				entercheckindateField.setText("SELECT DATE");
				entercheckoutdateField.setText("SELECT DATE");
				enterpeopleField.setText("0");
				enterroomField.setText("0");

				reservenumberField.setText(null);
				successreservenumberField.setText(null);

				reservebuttons.removeAll();
				reservebuttons.add(cancelreserve);
				reservebuttons.add(nextreserve);

				reserveorderbuttons.removeAll();
				reserveorderbuttons.add(cancelText);
				reserveorderbuttons.add(modifyText);
				reserveorderbuttons.add(confirmText);

				layeredPane.add(Hotelfunction);
				validate();
				repaint();
				confirmText.setForeground(Color.black);
			} else if (e.getSource() == reserveText) {
				reservecheckindateField.setText("SELECT DATE");
				reservecheckoutdateField.setText("SELECT DATE");
				reservehotelid.setSelectedIndex(0);
				reservesingleroomField.setText("0");
				reservedoubleroomField.setText("0");
				reservequadroomField.setText("0");
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				reserveText.setForeground(Color.black);
			} else if (e.getSource() == backreserve) {
				layeredPane.remove(Reserve);
				layeredPane.add(Hotellist);
				validate();
				repaint();
				backreserve.setForeground(Color.black);
			} else if (e.getSource() == nextreserve) {
				String s1 = reservecheckindateField.getText();
				String s2 = reservecheckoutdateField.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				if (main.CountDaysBetween(s1, s2) > 0 
						&& main.CountDaysBetween(sdf.format(new Date()), s2) < 365) {
					String CID = reservecheckindateField.getText();// yyyy/MM/dd
					String COD = reservecheckoutdateField.getText();
					int HotelID = Integer.parseInt(reservehotelid.getSelectedItem().toString());
					int sn = Integer.parseInt(reservesingleroomField.getText());
					int dn = Integer.parseInt(reservedoubleroomField.getText());
					int qn = Integer.parseInt(reservequadroomField.getText());
					Order order = main.BookHotel(CID, COD, HotelID, sn, dn, qn);
					if (order != null) {
						// 訂房成功
						layeredPane.remove(Reserve);
						layeredPane.remove(Invalid_date_error);
						showReservesuccess(order.getID(), order.getHotelID(), order.getSnum().size(),
								order.getDnum().size(), order.getQnum().size(), order.getCheckInDate(),
								order.getCheckOutDate(),
								(int) main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
								order.getSumPrice());
						reservenumberField.setText(successreservenumberField.getText());
						reservecheckindateField.setText(null);
						reservecheckoutdateField.setText(null);
						reservebuttons.removeAll();
						reservebuttons.add(cancelreserve);
						reservebuttons.add(nextreserve);
						reserveorderbuttons.removeAll();
						reserveorderbuttons.add(modifyText);
						reserveorderbuttons.add(confirmText);
						validate();
						repaint();
						nextreserve.setForeground(Color.black);
					} else {
						// 訂房失敗 房間數量不足/房間已售罄
						layeredPane.remove(Reserve);
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(Soldout, new Integer(3));
						validate();
						repaint();
						nextreserve.setForeground(Color.black);
					}
				} else { // reserve invalid date
					layeredPane.add(Invalid_date_error, new Integer(3));
					reservecheckindateField.setText("SELECT DATE");
					reservecheckoutdateField.setText("SELECT DATE");
					reservehotelid.setSelectedIndex(0);
					reservesingleroomField.setText(null);
					reservedoubleroomField.setText(null);
					reservequadroomField.setText(null);
					validate();
					repaint();
					nextreserve.setForeground(Color.black);
				}

			} else if (e.getSource() == backsoldout) {
				layeredPane.remove(Soldout);
				layeredPane.remove(Invalid_date_error);
				layeredPane.add(Reserve, new Integer(3));
				validate();
				repaint();
				backsoldout.setForeground(Color.black);
			} else if (e.getSource() == inquiryText) {
				reservenumberField.setText(null);
				layeredPane.remove(Hotelfunction);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				inquiryText.setForeground(Color.black);
			} else if (e.getSource() == nextinquiry) {
				layeredPane.remove(Inquiry);
				// get reserve number
				int OrderID = Integer.parseInt(reservenumberField.getText());
				Order order = main.CheckOrder(OrderID);
				if (order != null) {
					layeredPane.remove(Inquiry);
					showReserveorder(order.getID(), order.getHotelID(), order.getSnum().size(), order.getDnum().size(),
							order.getQnum().size(), order.getCheckInDate(), order.getCheckOutDate(),
							(int) main.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate()),
							order.getSumPrice());
					layeredPane.add(Reserveorder, new Integer(3));
					reserveorderbuttons.removeAll();
					reserveorderbuttons.add(cancelText);
					reserveorderbuttons.add(modifyText);
					reserveorderbuttons.add(confirmText);
					validate();
					repaint();
					nextinquiry.setForeground(Color.black);
				} else {// 不存在此訂單代號
					layeredPane.add(Wrong_reservation_number, new Integer(3));
					validate();
					repaint();
					reservenumberField.setText("");
					nextinquiry.setForeground(Color.black);
				}
			} else if (e.getSource() == backwrongreservationnumber) {
				layeredPane.remove(Wrong_reservation_number);
				reservenumberField.setText(null);
				layeredPane.add(Inquiry, new Integer(3));
				validate();
				repaint();
				backwrongreservationnumber.setForeground(Color.black);
			} else if (e.getSource() == modifyText || e.getSource() == backchangeroom
					|| e.getSource() == backrevisedate) {
				layeredPane.remove(Reserve_success);
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Changeroom);
				layeredPane.remove(Revisedate);
				layeredPane.remove(Changeroom_error);
				layeredPane.remove(Revisedate_error);
				layeredPane.remove(Invalid_date_error);
				layeredPane.add(Modify, new Integer(3));
				validate();
				repaint();
				modifyText.setForeground(Color.black);
				backchangeroom.setForeground(Color.black);
				backrevisedate.setForeground(Color.black);
			} else if (e.getSource() == changeroomText) {
				Order originorder = main.CheckOrder(Integer.parseInt(successreservenumberField.getText()));
				Integer x = originorder.getSnum().size();
				Integer y = originorder.getDnum().size();
				Integer z = originorder.getQnum().size();
				originsingleroomField.setText(x.toString());
				origindoubleroomField.setText(y.toString());
				originquadroomField.setText(z.toString());
				newsingleroomField.setText(null);
				newdoubleroomField.setText(null);
				newquadroomField.setText(null);
				layeredPane.remove(Modify);
				layeredPane.add(Changeroom, new Integer(3));
				validate();
				repaint();
				changeroomText.setForeground(Color.black);
			} else if (e.getSource() == nextchangeroom) {
				int OrderID = Integer.parseInt(successreservenumberField.getText());
				int osn = Integer.parseInt(originsingleroomField.getText());
				int odn = Integer.parseInt(origindoubleroomField.getText());
				int oqn = Integer.parseInt(originquadroomField.getText());
				// 取得修改後的房間數
				int nsn = Integer.parseInt(newsingleroomField.getText());
				int ndn = Integer.parseInt(newdoubleroomField.getText());
				int nqn = Integer.parseInt(newquadroomField.getText());
				if (nsn > osn || ndn > odn || nqn > oqn) {
//					 change room error 修改房間數失敗 不可增加房間
					layeredPane.add(Changeroom_error, new Integer(3));
					newsingleroomField.setText(null);
					newdoubleroomField.setText(null);
					newquadroomField.setText(null);
				} else {
					Order newOrder = main.ChangeRooms(OrderID, nsn, ndn, nqn);
					System.out.println(newOrder.getDnum().size());
					showReserveorder(OrderID, newOrder.getHotelID(), newOrder.getSnum().size(),
							newOrder.getDnum().size(), newOrder.getQnum().size(), newOrder.getCheckInDate(),
							newOrder.getCheckOutDate(),
							(int) main.CountDaysBetween(newOrder.getCheckInDate(), newOrder.getCheckOutDate()),
							newOrder.getSumPrice());
					layeredPane.remove(Changeroom);
					layeredPane.remove(Changeroom_error);
					layeredPane.add(ChangeRevise_success, new Integer(3));
					reserveorderbuttons.remove(cancelText);
					reserveorderbuttons.remove(modifyText);
					layeredPane.add(Reserveorder, new Integer(3));
				}
				validate();
				repaint();
				nextchangeroom.setForeground(Color.black);
			} else if (e.getSource() == revisedateText) {
				Order originorder = main.CheckOrder(Integer.parseInt(successreservenumberField.getText()));
				String x = originorder.getCheckInDate();
				String y = originorder.getCheckOutDate();
				origincheckindateField.setText(x);
				origincheckoutdateField.setText(y);
				newcheckindateField.setText(null);
				newcheckoutdateField.setText(null);
				layeredPane.remove(Modify);
				layeredPane.add(Revisedate, new Integer(3));
				validate();
				repaint();
				revisedateText.setForeground(Color.black);
			} else if (e.getSource() == nextrevisedate) {
				String s1 = newcheckindateField.getText();
				String s2 = newcheckoutdateField.getText();
				if (main.CountDaysBetween(s1, s2) > 0) {
					int OrderID = Integer.parseInt(successreservenumberField.getText());
					String nCID = newcheckindateField.getText();
					String nCOD = newcheckoutdateField.getText();
					if (main.CheckDateforReviseDate(OrderID, nCID, nCOD)) {
						Order newOrder = main.ModifyDate(OrderID, nCID, nCOD);
						System.out.println(newOrder.getCheckInDate() + ", " + newOrder.getCheckOutDate());
						// if revise date success 修改日期成功
						showReserveorder(OrderID, newOrder.getHotelID(), newOrder.getSnum().size(),
								newOrder.getDnum().size(), newOrder.getQnum().size(), newOrder.getCheckInDate(),
								newOrder.getCheckOutDate(),
								(int) main.CountDaysBetween(newOrder.getCheckInDate(), newOrder.getCheckOutDate()),
								newOrder.getSumPrice());
						layeredPane.remove(Invalid_date_error);
						layeredPane.remove(Revisedate_error);
						layeredPane.remove(Revisedate);
						layeredPane.add(ChangeRevise_success, new Integer(3));
						reserveorderbuttons.remove(cancelText);
						reserveorderbuttons.remove(modifyText);
						layeredPane.add(Reserveorder, new Integer(3));
						validate();
						repaint();
						nextrevisedate.setForeground(Color.black);
					} else {
						// if revise date error 
						layeredPane.remove(Invalid_date_error);
						layeredPane.add(Revisedate_error, new Integer(3)); 
						newcheckindateField.setText(null);
						newcheckoutdateField.setText(null);
						validate();
						repaint();
						nextrevisedate.setForeground(Color.black);
					}
				} else { // invalid date
					layeredPane.add(Invalid_date_error, new Integer(3));
					newcheckindateField.setText(null);
					newcheckoutdateField.setText(null);
					validate();
					repaint();
					nextrevisedate.setForeground(Color.black);
				}
			} else if (e.getSource() == cancelText) { // cancel the order
				int OrderID = Integer.parseInt(successreservenumberField.getText());
				main.CancelOrder(OrderID);
				layeredPane.remove(Reserveorder);
				layeredPane.remove(Reserve_success);
				layeredPane.add(Cancelorder_success, new Integer(3));
				validate();
				repaint();
				cancelText.setForeground(Color.black);
			}

		}
	};
}

/**
 * 
 *
 */


/**
 * setting up the color icon 
 */
class ColorIcon implements Icon {
	private final Color color;

	protected ColorIcon(Color color) {
		this.color = color;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(color);
		g2.fillRect(1, 1, getIconWidth() - 2, getIconHeight() - 2);
		g2.dispose();
	}

	public int getIconWidth() {
		return 12;
	}

	public int getIconHeight() {
		return 12;
	}

}


class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text;

	public ButtonColumn(JTable table, int column) {
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
		Object hid = table.getModel().getValueAt(table.getSelectedRow(), 0);
		Object sroom = table.getModel().getValueAt(table.getSelectedRow(), 4);
		Object droom = table.getModel().getValueAt(table.getSelectedRow(), 5);
		Object qroom = table.getModel().getValueAt(table.getSelectedRow(), 6);
		Menu.reservehotelid.setSelectedIndex((int) hid);
		Menu.reservesingleroomField.setText(sroom.toString());
		Menu.reservedoubleroomField.setText(droom.toString());
		Menu.reservequadroomField.setText(qroom.toString());
	}
}
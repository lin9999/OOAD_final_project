import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;


public class RegistrationUI extends JPanel {
	final private int subMenuWidth = 600, subMenuHeight = 270;
	final private Dimension subMenuCenter = new Dimension(Menu.frameWidth / 2, 450);
	private JLayeredPane layeredPane = new JLayeredPane();
	
	private JPanel subMenu = new JPanel();
	private JPanel signIn = new JPanel();
	private JPanel signUp = new JPanel();
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(Menu.frameWidth, Menu.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(Menu.frameWidth, Menu.frameHeight));
		Menu.background.setIcon(new ImageIcon("images/Menu/background.png"));
		Menu.background.setBounds(0, 0, Menu.frameWidth, Menu.frameHeight);
		layeredPane.add(Menu.background, new Integer(0));
		
		signIn.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2), 
				subMenuWidth, subMenuHeight);
		layeredPane.add(subMenu, new Integer(2));
		add(layeredPane);
	}
	
	private void initSubMenu() {
		this.subMenu.setBounds(subMenuCenter.width - (subMenuWidth / 2), subMenuCenter.height - (subMenuHeight / 2),
				subMenuWidth, subMenuHeight);
		
		JPanel buttonPanelet = new JPanel();
		JLabel signInText = new JLabel("SIGN IN", JLabel.CENTER);
		JLabel signUpText = new JLabel("SIGN UP", JLabel.CENTER);
		signInText.setFont(new Font("Arial Black", Font.BOLD, 30));
		signUpText.setFont(new Font("Arial Black", Font.BOLD, 30));	
		signInText.addMouseListener( new RBListener() {
					public void mouseClicked(MouseEvent e) {
						layeredPane.remove(subMenu);
						layeredPane.add(signIn, new Integer(2));
						validate();
						repaint();
						signInText.setForeground(Color.BLACK);
					}
				}
			);
		signUpText.addMouseListener( new RBListener() {
					public void mouseClicked(MouseEvent e) {
						layeredPane.remove(subMenu);
						layeredPane.add(signUp, new Integer(2));
						validate();
						repaint();
						signUpText.setForeground(Color.BLACK);
					}
				}
			);
		buttonPanelet.setLayout(new GridLayout(1, 2, 0, 0));
		buttonPanelet.setOpaque(false);
		buttonPanelet.add(signInText);
		buttonPanelet.add(signUpText);
		
		JPanel blank = new JPanel();
		blank.setOpaque(false);
		
		subMenu.setLayout(new GridLayout(4, 1, 0, 0));
		subMenu.setOpaque(false);
		subMenu.setBackground(null);
		subMenu.add(blank);
		subMenu.add(blank);
		subMenu.add(blank);
		subMenu.add(buttonPanelet);
	}
	
	private void initSignIn() { 
		JPanel IDPanel = new JPanel();
		IDPanel.setOpaque(false);
		IDPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		IDPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		
		// enter ID
		JLabel ID = new JLabel("       ID       : ");
		ID.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JTextField inputID = new JTextField(10) {
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
		inputID.setEditable(true);
		inputID.setFont(new Font("Arial Black", Font.BOLD, 23));
		inputID.setBackground(new Color(232, 232, 232, 120));
		
		IDPanel.add(ID);
		IDPanel.add(inputID);
		
		// enter password Panel setting
		JPanel passwordPanel = new JPanel();
		passwordPanel.setOpaque(false);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		passwordPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		// enter password
		JLabel password = new JLabel("PASSWORD : ");
		password.setFont(new Font("Arial Black", Font.PLAIN, 20));
		JPasswordField inputPassword = new JPasswordField(10) {
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
		inputPassword.setEditable(true);
		inputPassword.setFont(new Font("Arial Black", Font.BOLD, 23));
		inputPassword.setBackground(new Color(232, 232, 232, 120));
		// whether to show the password or not
		inputPassword.setAlignmentX(Component.RIGHT_ALIGNMENT);
		AbstractDocument doc = (AbstractDocument) inputPassword.getDocument();
		doc.setDocumentFilter(new DocumentFilter());
		AbstractButton b = new JToggleButton(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				AbstractButton c = (AbstractButton) e.getSource();
				Character ec = c.isSelected() ? 0 : (Character) UIManager.get("PasswordField.echoChar");
				inputPassword.setEchoChar(ec);
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
		panel.add(inputPassword);

		passwordPanel.add(password);
		passwordPanel.add(panel);

		// set 'back' and 'login' button
		JPanel buttonPanel = new JPanel();
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		JLabel loginText = new JLabel("LOGIN", JLabel.CENTER);
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
		loginText.setFont(new Font("Arial Black", Font.PLAIN, 18));
		backText.setFont(new Font("Arial Black", Font.PLAIN, 18));
		loginText.setOpaque(false);
		backText.setOpaque(false);
		
		loginText.addMouseListener( new RBListener() {
					public void mouseClicked(MouseEvent e) {
						// TODO handle login
						layeredPane.remove(subMenu);
						layeredPane.add(signUp);
						validate();
						repaint();
						loginText.setForeground(Color.BLACK);
					}
				}
			);
		backText.addMouseListener( new RBListener() {
					public void mouseClicked(MouseEvent e) {
						layeredPane.remove(signIn);
						layeredPane.add(subMenu);
						validate();
						repaint();
						backText.setForeground(Color.BLACK);
					}
				}
			);
		
		buttonPanel.add(backText);
		buttonPanel.add(loginText);

		// sign in adding
		signIn.setLayout(new GridLayout(3, 1, 0, 0));
		signIn.setOpaque(false);
		signIn.add(IDPanel);
		signIn.add(passwordPanel);
		signIn.add(buttonPanel);
	}

	private void initSignUp() { 
		JLabel loginText = new JLabel("SIGN UP and LOGIN", JLabel.CENTER);
		JLabel cancelText = new JLabel("CANCEL", JLabel.CENTER);
		JTextField inputID = new JTextField(10);
		JPasswordField inputPassword = new JPasswordField(10);
		JTextField inputCode = new JTextField(5);
		JLabel verifyCode = new JLabel("");
	}
	
	public RegistrationUI() {
		initSubMenu();
		initSignIn();
		initSignUp();
		initLayerPane();
		initPanel();
	}
}

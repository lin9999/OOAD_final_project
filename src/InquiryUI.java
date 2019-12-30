import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class InquiryUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
	final private int InquiryWidth = 600, InquiryHeight = 150;
	final private Dimension InquiryCenter = new Dimension(HotelPreference.frameWidth / 2, HotelPreference.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel inquiry = new JPanel();
	protected TextField reservenumberField = new TextField(15);
	
	private void initPanel() {
		setLayout(new GridLayout(1, 1));
		setOpaque(false);
		setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
	}
	
	private void initLayerPane() {
		layeredPane.setPreferredSize(new Dimension(HotelPreference.frameWidth, HotelPreference.frameHeight));
		HotelPreference.background.setBounds(0, 0, HotelPreference.frameWidth, HotelPreference.frameHeight);
		layeredPane.add(HotelPreference.background, new Integer(0));
		layeredPane.add(inquiry, new Integer(1));
		add(layeredPane);
	}
	
	private void initInquiry() {
		inquiry.setBounds(InquiryCenter.width - (InquiryWidth / 2), InquiryCenter.height - (InquiryHeight / 2),
				InquiryWidth, InquiryHeight);
		
		inquiry.setLayout(new GridLayout(2, 1, 0, 0));
		inquiry.setOpaque(false);
		inquiry.setBorder(new MatteBorder(5, 5, 5, 5, Color.white));

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
		JLabel backText = new JLabel("BACK", JLabel.CENTER);
		backText.setFont(new Font("Arial Black", Font.PLAIN, 23));
		backText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					InquiryUI.this.setVisible(false);
					JFrame root = (JFrame) SwingUtilities.getRoot(InquiryUI.this);
					root.setContentPane(new HotelFunctionUI());
					backText.setForeground(Color.BLACK);
				}
			}
		);
		JLabel nextText = new JLabel("NEXT", JLabel.CENTER);
		nextText.addMouseListener( new RBListener() {
				public void mouseClicked(MouseEvent e) {
					int OrderID = Integer.parseInt(reservenumberField.getText());
					Order order = Inquiry.CheckOrder(OrderID);
					JFrame root = (JFrame) SwingUtilities.getRoot(InquiryUI.this);
					if (order != null) {
						InquiryUI.this.setVisible(false);
						root.setContentPane(new ShowOrderUI(order));
					} else {// 不存在此訂單代號
						// show error message
						JOptionPane.showMessageDialog(root, "NO SUCH RESERVATION!", "Warning", JOptionPane.ERROR_MESSAGE);
					}
					nextText.setForeground(Color.BLACK);
				}
			}
		);
		nextText.setFont(new Font("Arial Black", Font.PLAIN, 23));
		buttons.add(backText);
		buttons.add(nextText);
		// inquiry adding
		inquiry.add(reservenumberPanel);
		inquiry.add(buttons);
	}
	
	public InquiryUI() {
		initInquiry();
		initLayerPane();
		initPanel();
	}
	

}

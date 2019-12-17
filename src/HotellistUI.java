import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class HotellistUI extends JPanel{
	final private int searchWidth = 570, searchHeight = 240;
	final private Dimension searchCenter = new Dimension(Menu.frameWidth / 2, Menu.frameHeight / 2);
	private JLayeredPane layeredPane = new JLayeredPane();
	private JPanel search_option = new JPanel();
	
	
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
	
	private void initSearch() {
		search_option.setBounds(searchCenter.width - (searchWidth / 2), searchCenter.height - (searchHeight / 2),
				searchWidth, searchHeight);
		JPanel star = new JPanel();
		JLabel star5 = new JLabel("5-star", JLabel.CENTER);
		JLabel star4 = new JLabel("4-star", JLabel.CENTER);
		JLabel star3 = new JLabel("3-star", JLabel.CENTER);
		JLabel star2 = new JLabel("2-star", JLabel.CENTER);
		JLabel pricehighText = new JLabel("PRICE (HIGHEST FIRST)", JLabel.CENTER);
		JLabel pricelowText = new JLabel("PRICE (LOWEST FIRST)", JLabel.CENTER);
		JLabel backsearch = new JLabel("BACK", JLabel.CENTER);
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


	public HotellistUI() {
		initSearch();
		initLayerPane();
		initPanel();
	}
}

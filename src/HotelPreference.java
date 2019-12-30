import java.awt.*;
import javax.swing.*;

public class HotelPreference extends JFrame {
	private static final long serialVersionUID = 1L;
	
	final public static int frameWidth = 1152, frameHeight = 720;
	public static JLabel background = new JLabel();
	
	// Program constructor
	public HotelPreference() {
		initFrame();
		background.setIcon(new ImageIcon("images/Menu/background.png"));
		background.setBounds(0, 0, frameWidth, frameHeight);
		this.setContentPane(new RegistrationUI());
        this.setVisible(true);
	}

	// Initialize the frame
	private void initFrame() {
		this.setTitle("HOTEL");
		this.setSize(frameWidth, frameHeight);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	}

	public static void main(String[] args) {
		new HotelPreference();
	}
}

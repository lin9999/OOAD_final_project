import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class mailSender {
	public mailSender() {
		
	}
	public static boolean SendEmail (Order _Order) throws AddressException, MessagingException {
		String host = "smtp.gmail.com";
		String room_num = "";
		int port = 587;
		final String username = "OOAD.Hotel@gmail.com";
		final String password = "FinalProjectSampleCode"; //your password

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("OOAD.Hotel@gmail.com"));
		try {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_Order.getUserID()));
		} catch(Exception AddressException) {
			return false;
		}
		
		if (_Order.getSnum() != null && !_Order.getSnum().isEmpty()) {
			for (int i = 0; i < _Order.getSnum().size(); i++) {
				room_num += _Order.getSnum().get(i);
				room_num += (i == _Order.getSnum().size()-1) ? " " : ", ";
			}
			room_num += " for Single, "; 
		}
		if (_Order.getDnum() != null && !_Order.getDnum().isEmpty()) {
			for (int i = 0; i < _Order.getDnum().size(); i++) {
				room_num += _Order.getDnum().get(i);
				room_num += (i == _Order.getDnum().size()-1) ? " " : ", ";
			}
			room_num += " for Double, "; 
		}
		if (_Order.getQnum() != null && !_Order.getQnum().isEmpty()) {
			for (int i = 0; i < _Order.getQnum().size(); i++) {
				room_num += _Order.getQnum().get(i);
				room_num += (i == _Order.getQnum().size()-1) ? " " : ", ";
			}
			room_num += " for Quad, ";
		}
		
		message.setSubject("Hotel booking information");
		message.setText("Dear gust, \n\nYou've already made a reservation from OOAD_Hotal.com!\n\n"
					  + "Your order ID is: " + _Order.getID() + "\n"
					  + "You've reserved room " + room_num + " from " +  _Order.getCheckInDate() + " to " + _Order.getCheckOutDate() + "\n"
					  + "The total price is " + _Order.getSumPrice() + " NTD.\n\n"
					  + "If you want to check or change your order, please go to our website with your order ID.\n\nBest Regards,\nOOAD Hotel");
   
		Transport transport = session.getTransport("smtp");
		transport.connect(host, port, username, password);
		Transport.send(message);

		System.out.println("寄送email結束.");
		return true;
	 }  
}


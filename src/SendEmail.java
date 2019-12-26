
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
	SendEmail(){
		
	}
	SendEmail (Order _Order) throws AddressException, MessagingException {
	  String host = "smtp.gmail.com";
	  String room_num="";
	  int port = 587;
	  final String username = "OOAD.Hotel@gmail.com";
	  final String password = "FinalProjectSampleCode";//your password

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
	   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(_Order.getUserID()));
	   if(_Order.getSnum()!=null) {room_num=room_num+((_Order.getSnum()).toString()+" for Single ");}// TODO
	   if(_Order.getDnum()!=null) {room_num=room_num+(_Order.getDnum().toString()+" for Double ");}// TODO
	   if(_Order.getQnum()!=null) {room_num=room_num+(_Order.getQnum().toString()+" for Quad ");}// TODO
	   message.setSubject("Hotel booking information");
	   message.setText("Dear gust, \n\nYou've already make a reservation of from OOAD_Hotal.com!\n"
	   		+"Your order ID is: "+ _Order.getID()
	   		+"\nYou've reserved room "+room_num+"from "+ _Order.getCheckInDate()+" to "+_Order.getCheckOutDate()
	   		+"\nThe total price is "+_Order.getSumPrice()+
	   		"\nIf you want to check or change your order, please go to our website with your order ID.\nBest Regards,\nOOAD Hotel");
	   Transport transport = session.getTransport("smtp");
	   transport.connect(host, port, username, password);

	   Transport.send(message);

	   System.out.println("寄送email結束.");

	 } 
	 
}


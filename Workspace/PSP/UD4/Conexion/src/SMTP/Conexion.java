package SMTP;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Conexion {

	public static void main(String[] args) {
		while(true){
			try {
		Properties props = new Properties();
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.host","smtp.outlook.com");
		props.put("mail.smtp.port","587");
		
		String usuario ="";
		String pass ="";
		Session session = Session.getInstance(props, 
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(usuario, pass);
				}
			}
		);
		String destinatario="";
		
		
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario));//pasas el correo que va a enviar el mensaje
			message.setSubject("1er aviso");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setText("Buenas caballero, esto no es un correo auto generado, me gustaria saber si le gustaria ir conmigo(Eric Sierra) al baile de promocion UwU >///<");
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
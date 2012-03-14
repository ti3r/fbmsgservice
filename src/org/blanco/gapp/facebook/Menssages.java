package org.blanco.gapp.facebook;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import com.google.code.facebookapi.FacebookException;
import com.google.code.facebookapi.FacebookJsonRestClient;

/**
 * The messages resources that will be used to check
 * messages status
 * 
 * @author Alexandro Blanco <ti3r.bubblenet@gmail.com>
 */
@Path("/messages")
public class Menssages {

	@GET
	@Path("/check")
	public Response scheduleMessagesCheck(){
		sendMail("0");
		return Response.ok("Messages Check Scheduled!!!").build();
	}
	
	private void sendMail(String msgBody){
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

		try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("alexandro.blanco@autozone.com", 
                            		 "Mr. User"));
            msg.setSubject("Facebook Message Checker Server");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(value={MediaType.TEXT_PLAIN})
	public Response getMessagesNumber(){
		String apiKey = "193553140688780";
		String secret = "3daa47b9d336227b036aecd4b84409c8";
		String sessionId = "0";
		FacebookJsonRestClient client = 
				new FacebookJsonRestClient(apiKey,secret,sessionId);
		
		try {
			JSONObject o = client.notifications_get();
			System.out.println(o.toString());
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    if (MessageServiceFaceImp.get() == null)
			return Response.serverError().build();
		else{
			String response = String.valueOf(MessageServiceFaceImp.get().
					getMessagesCount());
		return Response.ok(response).build();
		}
	}
}

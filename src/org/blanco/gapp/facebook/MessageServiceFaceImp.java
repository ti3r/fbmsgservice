package org.blanco.gapp.facebook;

/**
 * The default implementation for the message service
 * that retrieves the messages from facebook for the client
 * 
 * @author Alexandro Blanco <ti3r.bubblenet@gmail.com>
 *
 */
public class MessageServiceFaceImp implements MessageService {

	private static MessageService singleton = null;
	
	public static MessageService get(){
		if (singleton == null)
			singleton = new MessageServiceFaceImp();
		return singleton;
	}
	
	@Override
	public int getMessagesCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}

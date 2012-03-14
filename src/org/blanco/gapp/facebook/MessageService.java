package org.blanco.gapp.facebook;

/**
 * Interface that declares the behavior of the 
 * service in charge of consulting messages.
 * 
 * @author Alexandro Blanco <ti3r.bubblenet@gmail.com>
 *
 */
public interface MessageService {

	/**
	 * Retrieves the number of messages for the
	 * client.
	 * @return The int number of messages waiting
	 */
	public int getMessagesCount();
	
}
